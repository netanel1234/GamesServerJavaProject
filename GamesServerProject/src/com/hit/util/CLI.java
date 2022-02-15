package com.hit.util;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * This class responsible to interacting with the client 
 * in order to run the server and stop it if necessary.
 */
public class CLI implements Runnable {
	
	/**
	 * Representation of all possible commands to the server from the client 
	 */
	enum InputValidation
	{
		CONFIG, DEFAULT, START, SHUTDOWN, INVALID;
	}

	private PropertyChangeSupport propertyChangeHandler;
	private BufferedReader in;
	private BufferedWriter out;

	/**
	 * Constructor 
	 * @param in - the stream from which the CLI commands are received
	 * @param out - the stream that get the CLI responses to the received commands
	 */
	public CLI(InputStream in,OutputStream out) 
	{
		super();
		this.propertyChangeHandler=new PropertyChangeSupport(this);
		this.in=new BufferedReader(new InputStreamReader(in));
		this.out=new BufferedWriter(new OutputStreamWriter(out));
	}
	
	/**
	 * @param pcl - object that wants to be added to the list of CLI commands observers
	 */
	public void addPropertyChangeListener(PropertyChangeListener pcl)
	{
		this.propertyChangeHandler.addPropertyChangeListener(pcl);
	}
	
	/**
	 * @param pcl - objects that wants to be removed from the list of CLI observers
	 */
	public void removePropertyChangeListener(PropertyChangeListener pcl)
	{
		propertyChangeHandler.removePropertyChangeListener(pcl);
	}
	
	/**
	 * @param response - response to a command that was last received
	 */
	public void writeResponse(String response)
	{
		try 
		{
			out.write(response);
			out.flush();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * run in interface java.lang.Runnable
	 */
	@Override
	public void run() 
	{
		InputValidation result=InputValidation.INVALID;
		while(result!=InputValidation.SHUTDOWN)
		{
				writeResponse("Please enter your command\n");
				String input = null;
				try 
				{
					input = (String)in.readLine();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				result=inputValidation(input);
				switch(result) {
				case CONFIG:
					int capacity=Integer.parseInt(input.split(" ")[1]);
					this.propertyChangeHandler.firePropertyChange("GAME_SERVER_CONFIG", 0, capacity);
					writeResponse("The server can run "+capacity+" games concurrently\n");
					break;
				case DEFAULT:
					this.propertyChangeHandler.firePropertyChange("GAME_SERVER_CONFIG",  0, 2);
					writeResponse("The server can run 2 games concurrently by default\n");
					break;
				case START:
					this.propertyChangeHandler.firePropertyChange("START", 0, 1);
					writeResponse("Strarting server.....\n");
					break;
				case INVALID:
					writeResponse("Not a valid command.\n"
							+ "Legal commands: \n"
							+ "'START' - start the server\n"
							+ "'GAME_SERVER_CONFIG {integer}'- config the server in how many games he can support in parallel\n"
							+ "'GAME_SERVER_CONFIG' - default configuration : the server will be able to support 2 games parallel\n"
							+ "'SHUTDWON' - shut down the server");
					break;
				default:
					break;
				}
		}
	}

	/**
	 * validation of the input entered by the client
	 * @param input - The command entered by the client
	 * @return enum InputValidation
	 */
	public InputValidation inputValidation(String input)
	{
		InputValidation result = InputValidation.INVALID;
		String[] requestAsArray=new String[2];
		requestAsArray=input.split(" ");
		if(requestAsArray.length>2)
			result=InputValidation.INVALID;
		if(requestAsArray.length==0)
			result=InputValidation.INVALID;
		if(requestAsArray.length==2)
			if(requestAsArray[0].equals("GAME_SERVER_CONFIG") && isNumeric(requestAsArray[1]))
				result=InputValidation.CONFIG;
			else
				result=InputValidation.INVALID;
		if(requestAsArray.length==1)
		{
			if(requestAsArray[0].equals("START"))
				result=InputValidation.START;
			else if(requestAsArray[0].equals("SHUTDWON"))
				result=InputValidation.SHUTDOWN;
			else if(requestAsArray[0].equals("GAME_SERVER_CONFIG"))
				result=InputValidation.DEFAULT;
		}
		return result;
	}
	
	/**
	 * check if the client entered valid number 
	 * @param strNum - the string we want to check
	 * @return true if it is valid number false otherwise
	 * Taken from GeeksForGeeks
	 */
	public static boolean isNumeric(String strNum) 
	{
	    if (strNum == null) 
	    {
	        return false;
	    }
	    try 
	    {
	        double d = Integer.parseInt(strNum);
	    } 
	    catch (NumberFormatException nfe) 
	    {
	        return false;
	    }
	    return true;
	}
}

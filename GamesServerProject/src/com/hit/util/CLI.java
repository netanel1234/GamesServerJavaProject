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

public class CLI implements Runnable {

	PropertyChangeSupport propertyChangeHandler;
	BufferedReader in;
	BufferedWriter out;

	public CLI(InputStream in,OutputStream out) 
	{
		super();
		System.out.println("constractor of cli()");
		propertyChangeHandler=new PropertyChangeSupport(this);
		this.in=new BufferedReader(new InputStreamReader(in));
		this.out=new BufferedWriter(new OutputStreamWriter(out));
	}
	
	public void addPropertyChangeListener(PropertyChangeListener pcl)
	{
		propertyChangeHandler.addPropertyChangeListener(pcl);
		System.out.println("addPropertyChangeListener() in cli->"+pcl+" is listening to cli");
	}
	
	public void removePropertyChangeListener(PropertyChangeListener pcl)
	{
		propertyChangeHandler.removePropertyChangeListener(pcl);
	}
	
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

	@Override
	public void run() 
	{	
		System.out.println("run() of cli");
		String request=null;
		String[] commandArr=new String[2];
		while(true)
		{
			try 
			{
				out.write("Please enter your command\n");
				out.flush();
				
				request=(String)in.readLine();
				commandArr=request.split(" ");
				
				if(commandArr[0].equals("GAME_SERVER_CONFIG"))
				{
					if(commandArr.length==2)
					{
						try
						{
							int capacity=Integer.parseInt(commandArr[1]);
							propertyChangeHandler.firePropertyChange("GAME_SERVER_CONFIG",0,capacity);
							writeResponse("The server can run "+capacity+" games concurrently\n");
						}
						catch(Exception e)
						{
							writeResponse("Not a valid command (problem with the capacity...)\n");
						}
					}
					else if(commandArr.length==1)
					{
						propertyChangeHandler.firePropertyChange("GAME_SERVER_CONFIG",0,2);
						writeResponse("The server can run 2 games concurrently by default\n");
					}
					else
					{
						writeResponse("Not a valid command\n");
					}
				}
				else if(commandArr[0].equals("START"))
				{
					propertyChangeHandler.firePropertyChange("START",0,1);
					writeResponse("Strarting server.....\n");
				}
				else if(commandArr[0].equals("SHUTDOWN"))
				{
					propertyChangeHandler.firePropertyChange("SHUTDWON",0,1);
					writeResponse("Shutdown server\n");
					break;
				}
				else
				{
					writeResponse("Not a valid command\n");
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}
}

package com.hit.server;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.hit.services.GameServerController;

/**
 * Server has an object of type - ServerSocket , that listens in port received in the constructor
 * and it manages communication with the clients. Each request is handled in a separate thread. 
 * 
 * implements:
 * PropertyChangeListener - to get relevant commands from CLI
 * Runnable - to run in a separate thread
 */
public class Server implements PropertyChangeListener, Runnable {
	
	private int capacity;
	private ServerSocket servreSocket;	
	private Executor executor;
	private boolean flag;
	
	/**
	 * Constructor 
	 * @param port - the port number that the server listens
	 */
	public Server(int port)
	{
		super();
		this.capacity=2;
		this.flag=true;
		try 
		{
			this.servreSocket = new ServerSocket(port);
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
		Socket client=null;
		HandleRequest handleRequest=null;
		executor=Executors.newFixedThreadPool(this.capacity);
		
		while(flag)
		{
			try
			{
				client=servreSocket.accept();
				handleRequest=new HandleRequest(client, new GameServerController(this.capacity));
				executor.execute(handleRequest);
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		try 
		{
			Thread.sleep(5000);
			((ExecutorService)executor).shutdown();
			((ExecutorService)executor).awaitTermination(1,TimeUnit.HOURS);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * propertyChange in interface java.beans.PropertyChangeListener
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event) 
	{		
		if(event.getPropertyName().equals("GAME_SERVER_CONFIG"))
		{
			this.capacity=(int)event.getNewValue();
		}
		else if(event.getPropertyName().equals("START"))
		{
			this.flag=true;
			new Thread(this).start();
		}
		else if(event.getPropertyName().equals("SHUTDWON"))
		{
			this.flag=false;
		}
		
	}
}

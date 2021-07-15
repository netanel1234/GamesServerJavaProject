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

public class Server implements PropertyChangeListener,Runnable{
	
	int capacity;
	ServerSocket servreSocket;	
	Executor executor;
	boolean flag;

	public Server(int port)
	{
		super();
		System.out.println("constractor of server()");
		this.capacity=2;
		try 
		{
			this.servreSocket = new ServerSocket(port);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			System.out.println("problem with the ServerSocket... in the constractor of class Server...");
		}
	}
	
	@Override
	public void run() 
	{
		System.out.println("run() of server");
		Socket client=null;
		HandleRequest handleRequest=null;
		executor=Executors.newFixedThreadPool(this.capacity);
		
		while(flag)
		{
			try
			{
				System.out.println("in method run() of server class the server is waiting for client to connect");
				client=servreSocket.accept();
				System.out.println("client acccepted->"+client);
				handleRequest=new HandleRequest(client,new GameServerController(this.capacity));
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
	
	@Override
	public void propertyChange(PropertyChangeEvent event) 
	{		
		System.out.println("propertyChange() in server");
		if(event.getPropertyName().equals("GAME_SERVER_CONFIG"))
		{
			this.capacity=(int)event.getNewValue();
		}
		else if(event.getPropertyName().equals("START"))
		{
			System.out.println("event START in propertyChange");
			this.flag=true;
			new Thread(this).start();
		}
		else if(event.getPropertyName().equals("SHUTDWON"))
		{
			this.flag=false;
		}
		
	}
}

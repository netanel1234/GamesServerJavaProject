package com.hit.model;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class GamesClient {
	
	int serverPort;
	InetAddress address;
	Socket myServer;
	ObjectOutputStream output;
	ObjectInputStream input;
	JSONObject jsonObject;
	
	public GamesClient(int serverPort)
	{
		System.out.println("constractor of GamesClient");
		this.serverPort=serverPort;
	}
	
	//Send a message to the server. The messages are in JSON format
	//message - the JSON message as a string
	//hasResponse - a flag that indicates whether to wait for a response from the server
	//returns: the server's response to the message
	public String sendMessage(String message,boolean hasResponse)
	{
		System.out.println("sendmessage in GamesClient");
		try
		{
			output.writeObject(message);
			if(hasResponse)
			{
				jsonObject=(JSONObject)input.readObject();
				System.out.println("the object that accepted from the sever->"+jsonObject);
				System.out.println("the object that accepted from the sever as toString()->"+jsonObject.toString());
				String id=(String)jsonObject.get("ID").toString();
				String type=(String)jsonObject.get("type");
				JSONArray board=(JSONArray)jsonObject.get("board");
				String boardToSend="";
				System.out.println("board.size()->"+board.size());
				for(int i=0;i<board.size();i++)
				{
					System.out.println("i->"+i);
					boardToSend+=board.get(i);
					System.out.println("boardToSend->"+boardToSend+" board.get(i)->"+board.get(i));
				}
					
				String strToSendToGamesModel=id+":"+type+":"+boardToSend;
				System.out.println(strToSendToGamesModel);
				return strToSendToGamesModel;
			}
			else
			{
				return null;
			}
		}
		catch(Exception e)
		{
			//TODO:catch enclosing not done!!!!
			e.printStackTrace();
			return null;
		}
	}
	
	//connect to the server
	public void connectToServer() throws Exception
	{
		System.out.println("connectToServer in GamesClient");
		try
		{
			address=InetAddress.getByName("192.168.56.1");
			myServer=new Socket(address,serverPort);
			System.out.println("myServer is->"+myServer);
			output=new ObjectOutputStream(myServer.getOutputStream());
			input=new ObjectInputStream(myServer.getInputStream());
		}
		catch(Exception e)
		{
			throw new Exception("Problem with connection to the games server");
		}
	}

	//close the connection with the server
	public void closeConnection() throws Exception
	{
		try
		{
			output.close();
			input.close();
			myServer.close();
		}
		catch(Exception e)
		{
			throw new Exception("Problem/s in closing connection with erver....");
		}
	}
}

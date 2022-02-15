package com.hit.model;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * In order to communicate with the server 
 * GamesClient responsible for connecting to the server with the port given to it, 
 * sending messages and receiving comments.
 */
public class GamesClient {

	private int serverPort;
	private InetAddress address;
	private Socket myServer;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private JSONObject inputJSON;

	public GamesClient(int serverPort) 
	{
		this.serverPort = serverPort;
	}

	/**
	 * Send a message to the server. The messages are in JSON format
	 * @param message - the JSON message as a string
	 * @param hasResponse - a flag that indicates whether to wait for a response from the server
	 * @return the server's response to the message
	 */
	public String sendMessage(String message, boolean hasResponse) 
	{
		output.writeObject(message);
		if (hasResponse) 
		{
			inputJSON = (JSONObject) input.readObject();
			String id = (String) inputJSON.get("ID").toString();
			String type = (String) inputJSON.get("type");
			JSONArray board = (JSONArray) inputJSON.get("board");
			String boardToSend = "";
			for (int i = 0; i < board.size(); i++)
				boardToSend += board.get(i);
			String strToSendToGamesModel = id + ":" + type + ":" + boardToSend;
			return strToSendToGamesModel;
		} 
		else 
		{
			return null;
		}
	}

	/**
	 * connect to the server
	 */
	public void connectToServer() 
	{
		this.address = InetAddress.getByName("192.168.56.1");
		this.myServer = new Socket(address, serverPort);
		this.output = new ObjectOutputStream(myServer.getOutputStream());
		this.input = new ObjectInputStream(myServer.getInputStream());
	}
	
	/**
	 * close the connection with the server
	 */
	public void closeConnection()   
	{
		this.output.close();
		this.input.close();
		this.myServer.close();
	}
}

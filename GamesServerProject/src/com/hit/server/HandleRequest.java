package com.hit.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.hit.gameAlgo.GameBoard.GameMove;
import com.hit.gameAlgo.IGameAlgo.GameState;
import com.hit.services.GameServerController;

/**
 * The role of HandleRequest is to receive requests from a particular customer, for a game from start to finish.
 * Requests are accepted from Server - from the relevant client socket.
 */
public class HandleRequest implements Runnable{
	
	private Socket client;
	private GameServerController gameServerController;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private JSONObject jsonObject;
	private JSONObject jsonRequst;
	private JSONArray jsonBoard;
	private String messageFromTheClient;
	private String[] messageAsArray;

	public HandleRequest(Socket clientSocket, GameServerController controller) throws IOException
	{
		super();
		this.client=clientSocket;
		this.gameServerController=controller;
		this.input=new ObjectInputStream(client.getInputStream());
		this.output=new ObjectOutputStream(client.getOutputStream());
		messageAsArray=new String[3];
	}

	/**
	 * get the message from the client as JSON file, parse it, 
	 * calls the right method to execute the client's request in GameServerController.
	 */
	@Override
	public void run() 
	{
		String type=null;
		while(!type.equals("Stop-Game"))
		{
			this.jsonRequst=new JSONObject();
			this.jsonBoard=new JSONArray();
			try 
			{
				messageFromTheClient=(String)input.readObject();
				messageAsArray=messageFromTheClient.split(":");
				
				String type=messageAsArray[0];
				
				if(type.equals("New-Game"))
				{
					String game=messageAsArray[1];
					String opponent=messageAsArray[2];
					int id=gameServerController.newGame(game,opponent);
					jsonRequst.put("type",type);
					jsonRequst.put("ID",id);
					for(char[] array:this.gameServerController.getBoardState(id))
						for(char sign:array)
							jsonBoard.add(sign);
					jsonRequst.put("board",jsonBoard);
					output.writeObject(jsonRequst);
					output.flush();
				}
				else if(type.equals("Update-Move"))
				{
					int id=Integer.parseInt(messageAsArray[1]);
					int row=Integer.parseInt(messageAsArray[2]);
					int col=Integer.parseInt(messageAsArray[3]);
					GameState gameState=gameServerController.updateMove(id, new GameMove(row,col));
					jsonRequst.put("type",type);
					jsonRequst.put("ID",id);
					jsonRequst.put("state", gameState.ordinal());
					for(char[] array:this.gameServerController.getBoardState(id))
						for(char sign:array)
							jsonBoard.add(sign);
					jsonRequst.put("board",jsonBoard);
					output.writeObject(jsonRequst);
					output.flush();
				}
				else if(type.equals("Start-Game"))
				{
					Integer id=(Integer)jsonObject.get("ID");
					char[][] board=gameServerController.computerStartGame(id);
					
					jsonRequst.put("type","Start-Game");
					jsonRequst.put("ID",id);
					for(char[] array:board)
						for(char c:array)
							jsonBoard.add(c);
					jsonRequst.put("board",jsonBoard);
					output.writeObject(jsonRequst);
					output.flush();
				}
				else if(type.equals("Stop-Game"))
				{
					Integer id=(Integer)jsonObject.get("ID");
					gameServerController.endGame(id);
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			} 
		}
	}
}

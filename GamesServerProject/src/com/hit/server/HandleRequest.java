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

public class HandleRequest implements Runnable{
	
	Socket client;
	GameServerController gameServerController;
	ObjectInputStream input;
	ObjectOutputStream output;
	JSONObject jsonObject;
	JSONObject jsonRequst;
	JSONArray jsonBoard;
	String messageFromTheClient;
	String[] arr;//Strings array to split and turn it to JSONObject.

	public HandleRequest(Socket s,GameServerController controller) throws IOException
	{
		super();
		System.out.println("the constractor of HandleRequest");
		this.client=s;
		this.gameServerController=controller;
		this.input=new ObjectInputStream(client.getInputStream());
		this.output=new ObjectOutputStream(client.getOutputStream());
		arr=new String[3];
	}

	@Override
	public void run() 
	{
		System.out.println("the method run() of HandleRequest");
		while(true)
		{
			this.jsonRequst=new JSONObject();
			this.jsonBoard=new JSONArray();
			try 
			{
				System.out.println("HandleRequest is waiting for string message to accept from the client side");
				messageFromTheClient=(String)input.readObject();
				arr=messageFromTheClient.split(":");
				
				String type=arr[0];
				String game=arr[1];
				String opponent=arr[2];
				
				if(type.equals("New-Game"))
				{
					System.out.println("in HandleRequest New-Game event");
					int id=gameServerController.newGame(game,opponent);
					System.out.println("in HandleRequest the id that the method run() event newGame accepted is->"+id);
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
					Integer id=(Integer)jsonObject.get("ID");
					GameMove gameMove=new GameMove((Integer)jsonObject.get("row"),(Integer)jsonObject.get("col"));
					GameState gameState=gameServerController.updateMove(id,gameMove);
					
					jsonRequst.put("type","Update-Move");
					jsonRequst.put("ID",id);
					jsonRequst.put("state",gameState.ordinal());
					if(gameState.ordinal()==0)
						jsonRequst.put("board",null);
					else
					{
						for(char[] array:this.gameServerController.getBoardState(id))
							for(char sign:array)
								jsonBoard.add(sign);
						jsonRequst.put("board",jsonBoard);
					}
					
					output.writeObject(jsonRequst);
					output.flush();
					
					if(gameState.ordinal()>=2)
					{
						this.gameServerController.endGame(id);
						break;
					}
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
					break;
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			} 
		}
	}
}

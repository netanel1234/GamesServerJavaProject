package com.hit.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GamesModel implements Model {
	
	PropertyChangeSupport propertyChangeHandler;
	JSONObject message,jsonObject;
	String messageToTheServerAsString;
	GamesClient gamesClient;
	JSONParser jsonParser;
	Integer id;
	
	public GamesModel()
	{
		System.out.println("constractor of GamesModel");
		propertyChangeHandler=new PropertyChangeSupport(this);
		gamesClient=new GamesClient(34567);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener)
	{
		System.out.println("addPropertyChangeListener in GamesModel");
		propertyChangeHandler.addPropertyChangeListener(propertyChangeListener);
	}

	@Override
	public void newGame(String gameType, String opponentType)
	{
		System.out.println("method newGame in GamesModel");
		try
		{
			gamesClient.connectToServer();
			
			System.out.println("in GamesModel after connecting to the server");
			
			messageToTheServerAsString="New-Game"+":"+gameType+":"+opponentType;
			
			String response=gamesClient.sendMessage(messageToTheServerAsString,true);
			
			System.out.println("in GamesModel, the response from the server->"+response);
			
			String[] responseToArrStr=response.split(":");
			
			char[] board=responseToArrStr[2].toCharArray();
			Character[] boardToSend=new Character[board.length];
			for(int i=0;i<responseToArrStr[2].length();i++)
			{
				boardToSend[i]=board[i];
			}
			System.out.println("in GamesModel printting the before send it to GamesController->");
			for(int i=0;i<boardToSend.length;i++)
			{
				System.out.println(boardToSend[i]);
			}
				
			propertyChangeHandler.firePropertyChange("newGameModel",id,boardToSend);
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void updatePlayerMove(int row, int col) 
	{		
		System.out.println("in GamesModel method updatePlayerMove");
		
		//message=new JSONObject();
		//message.put("type","Update-Move");
		//message.put("ID",id);
		//message.put("row",row);
		//message.put("col",col);
		
		try 
		{
			String response=gamesClient.sendMessage(messageToTheServerAsString,true);
			JSONObject jsonResponse=(JSONObject)jsonParser.parse(response);
			
			JSONArray signs=(JSONArray)jsonResponse.get("board");
			Character[] arraySigns=new Character[signs.size()];
			for(int i=0;i<signs.size();i++)
			{
				arraySigns[i]=(Character)signs.get(i);
			}
			if((Integer)jsonResponse.get("state")>=2)
			{
				gamesClient.closeConnection();
			}
			
			propertyChangeHandler.firePropertyChange("updatePlayerMoveModel",(Integer)jsonResponse.get("state"),arraySigns);
			
		}
		catch (ParseException e) 
		{
			e.printStackTrace();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}

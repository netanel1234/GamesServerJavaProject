package com.hit.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * The role GamesModel is to form the interface in front of the server, 
 * to convey the appropriate messages as defined, 
 * to receive the responses, if any, and to update the graphical interface.
 * 
 * implements the interface Model
 */
public class GamesModel implements Model {
	
	private PropertyChangeSupport propertyChangeHandler;
	private JSONObject message,jsonObject;
	private String messageToTheServerAsString;
	private GamesClient gamesClient;
	private JSONParser jsonParser;
	private Integer id;
	
	public GamesModel()
	{
		this.propertyChangeHandler=new PropertyChangeSupport(this);
		this.gamesClient=new GamesClient(34567);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener)
	{
		propertyChangeHandler.addPropertyChangeListener(propertyChangeListener);
	}

	/**
	 * Sends the server a "new-game" request
	 * @param gameType - the chosen game
	 * @param opponentType - computer type (random \ smart)
	 */
	@Override
	public void newGame(String gameType, String opponentType)
	{
		try
		{
			this.gamesClient.connectToServer();
			this.messageToTheServerAsString="New-Game"+":"+gameType+":"+opponentType;
			String[] response=this.gamesClient.sendMessage(this.messageToTheServerAsString, true).split(":");
			this.id=Integer.parseInt(response[0]);
			String type=response[1];
			char[] board=response[2].toCharArray();
			Character[] boardToSend=new Character[board.length];
			for(int i=0;i<response[2].length();i++)
				boardToSend[i]=board[i];
			propertyChangeHandler.firePropertyChange("newGameModel", id, boardToSend);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * Sends the server an "update-move" message
	 * @param row - the row of the move the player chose
	 * @param col - the column of the move the player chose
	 */
	@Override
	public void updatePlayerMove(int row, int col) 
	{		
		if(row==-1)
			row=col;
		w
		try 
		{
			this.messageToTheServerAsString="Update-Move"+":"+id+":"+row+":"+col;
			String[] response=this.gamesClient.sendMessage(this.messageToTheServerAsString,true).split(":");
			int id=Integer.parseInt(response[0]);
			String type=response[1];
			int gameState=Integer.parseInt(response[2]);
			char[] board=response[3].toCharArray();
			Character[] boardToSend=new Character[board.length];
			for(int i=0;i<response[3].length();i++)
				boardToSend[i]=board[i];
			
			propertyChangeHandler.firePropertyChange("updatePlayerMoveModel",gameState,boardToSend);
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}

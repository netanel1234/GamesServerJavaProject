package com.hit.services;

import java.util.concurrent.ConcurrentHashMap;

import com.hit.GameHandler.BoardGameHandler;
import com.hit.exception.UnknownIdException;
import com.hit.gameAlgo.GameBoard.GameMove;
import com.hit.gameAlgo.IGameAlgo;
import com.hit.gameAlgo.IGameAlgo.GameState;
import com.hit.games.CatchTheBunnyRandom;
import com.hit.games.CatchTheBunnySmart;
import com.hit.games.TicTacTowRandom;
import com.hit.games.TicTacTowSmart;

public class GamesService {
	
	int capacity;
	ConcurrentHashMap <Integer,BoardGameHandler> games;
	
	public GamesService(int capacity) 
	{
		super();
		this.capacity = capacity;
		this.games=new ConcurrentHashMap <Integer,BoardGameHandler>();
	}
	
	synchronized public int newGame(String gameType,String opponent)
	{
		System.out.println("newGame in GamesService");
		IGameAlgo game=null;
		int id=findId();
		
		if(id!=-1)
		{
			if(gameType.equals("Tic Tac Tow"))
			{
				if(opponent.equals("Random"))
				{
					game=new TicTacTowRandom(3,3);
					this.games.put(id,new BoardGameHandler(game));
				}
				else
				{
					System.out.println("in GamesService the game that choosen is ttt smart");
					game=new TicTacTowSmart(3,3);
					this.games.put(id,new BoardGameHandler(game));
				}
			}
			else//if Catch The Bunny
			{
				if(opponent.equals("Random"))
				{
					game=new CatchTheBunnyRandom(9,9);
					this.games.put(id,new BoardGameHandler(game));
				}
				else
				{
					game=new CatchTheBunnySmart(9,9);
					this.games.put(id,new BoardGameHandler(game));
				}
			}
		}
		return id;
	}
	
	synchronized public int findId()
	{
		System.out.println("findId() in GamesService");
		for(int i=1;i<this.capacity;i++)
		{
			if(!this.games.containsKey(i))
			{
				System.out.println("in GamesService the id that fount in findId() is->"+i);
				return i;
			}
		}
		return -1;
	}
	
	public GameState updateMove(Integer gameId,GameMove playerMove) throws UnknownIdException
	{
		if(!this.games.containsKey(gameId))
			throw new UnknownIdException("Method updateMove() in GamesService.java get game id that does not exist",new Exception());
		return this.games.get(gameId).playOneRound(playerMove);
	}
	
	public char[][] getBoardState(Integer gameId) throws UnknownIdException
	{
		if(!this.games.containsKey(gameId))
			throw new UnknownIdException("Method getBoardState() in GamesService.java get game id that does not exist",new Exception());
		return this.games.get(gameId).getBoardState();
	}
	
	public char[][] computerStartGame(Integer gameId) throws UnknownIdException
	{
		if(!this.games.containsKey(gameId))
			throw new UnknownIdException("Method computerStartGame() in GamesService.java get game id that does not exist",new Exception());
		return this.games.get(gameId).computerStartGame();
	}
	
	//End a game even if it is not finished
	synchronized public void endGame(Integer gameId) throws UnknownIdException
	{
		if(!this.games.containsKey(gameId))
			throw new UnknownIdException("Method endGame() in GamesService.java get game id that does not exist",new Exception());
		else
			this.games.remove(gameId);
	}
	
	
	
}
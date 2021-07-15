package com.hit.services;

import com.hit.exception.UnknownIdException;
import com.hit.gameAlgo.GameBoard.GameMove;
import com.hit.gameAlgo.IGameAlgo.GameState;

public class GameServerController {
	
	GamesService gamesService;

	public GameServerController(int capacity) 
	{
		super();
		this.gamesService=new GamesService(capacity);
	}
	
	public int newGame(String gameType,String opponent)
	{
		System.out.println("newGame in GameServerController");
		int id=this.gamesService.newGame(gameType,opponent);
		System.out.println("id in GamesServerController that accepted from GamesSerrvice is->"+id);
		return id;
	}
	
	public void endGame(Integer gameId) throws UnknownIdException
	{
		this.gamesService.endGame(gameId);
	}
	
	public GameState updateMove(Integer gameId,GameMove playerMove) throws UnknownIdException
	{
		return this.gamesService.updateMove(gameId,playerMove);
	}
	
	public char[][] computerStartGame(Integer gameId) throws UnknownIdException
	{
		return this.gamesService.computerStartGame(gameId);
	}
	
	public char[][] getBoardState(Integer gameId) throws UnknownIdException 
	{
		return this.gamesService.getBoardState(gameId);
	}
}

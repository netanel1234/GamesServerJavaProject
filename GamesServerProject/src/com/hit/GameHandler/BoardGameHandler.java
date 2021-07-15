package com.hit.GameHandler;

import com.hit.gameAlgo.IGameAlgo;
import com.hit.gameAlgo.GameBoard.GameMove;
import com.hit.gameAlgo.IGameAlgo.GameState;

public class BoardGameHandler {
	
	IGameAlgo game;

	public BoardGameHandler(IGameAlgo game) 
	{
		super();
		System.out.println("constractor of BoardGameHandler");
		this.game = game;
	}
	
	public GameState playOneRound(GameMove playerMove)
	{
		if(game.getGameState(playerMove)==GameState.IN_PROGRESS)
		{
			game.calcComputerMove();
		}
		return game.getMemberGameState();
	}
	
	public char[][] computerStartGame()
	{
		game.calcComputerMove();
		return getBoardState();
	}
	
	public char[][] getBoardState()
	{
		return game.getBoardState();
	}
}

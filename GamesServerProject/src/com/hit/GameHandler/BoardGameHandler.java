package com.hit.GameHandler;

import com.hit.gameAlgo.IGameAlgo;
import com.hit.gameAlgo.GameBoard.GameMove;
import com.hit.gameAlgo.IGameAlgo.GameState;

/**
 * BoardGameHandler contains only one component, 
 * and it addresses it in order to execute one move of a game.
 */
public class BoardGameHandler {
	
	private IGameAlgo game;

	/**
	 * Constructor 
	 * @param game - game - object of the selected game
	 */
	public BoardGameHandler(IGameAlgo game) 
	{
		super();
		this.game = game;
	}
	
	/**
	 * Execute one round of a game steps: 
	 * 1. update the player's move 
	 * 2. if game is not over: calculate and update the computer's move
	 * @param playerMove - the move the player chose to make
	 * @return the game state: PLAYER_WON \ PLAYER_LOST \ TIE \ IN PROGRESS
	 */
	public GameState playOneRound(GameMove playerMove)
	{
		if(this.game.getGameState(playerMove)==GameState.IN_PROGRESS)
		{
			this.game.calcComputerMove();
		}
		return this.game.getMemberGameState();
	}
	
	/**
	 * The computer was chosen to play first, calculate its move and update the game board
	 * @return - a matrix that represents the board state, the content of each cell in the board (player, computer or blank mark)
	 */
	public char[][] computerStartGame()
	{
		game.calcComputerMove();
		return getBoardState();
	}
	
	/**
	 * @return - a matrix that represents the board state, the content of each cell in the board (player, computer or blank mark)
	 */
	public char[][] getBoardState()
	{
		return game.getBoardState();
	}
}

package com.hit.gameAlgo;

import com.hit.gameAlgo.GameBoard.GameMove;

/**
 * This interface contains the methods that will allow you to run a game from start to finish. 
 */
public interface IGameAlgo {
	
	/**
	 * Representation of all possible game states - 
	 * ILLEGAL_PLAYER_MOVE - player made illegal move
	 * IN_PROGRESS - the game is not over yet
	 * PLAYER_LOST - player lost
	 * PLAYER_WON - player won
	 * TIE - The game ended in a draw
	 */
	public static enum GameState
	{
		ILLEGAL_PLAYER_MOVE, IN_PROGRESS, PLAYER_LOST, PLAYER_WON, TIE; 
	}
	
	/**
	 * calcComputerMove Calculates the copmuter's move and updates the game board
	 */
	void calcComputerMove();
	
	/**
	 * updatePlayerMove Updates the player's move on the board 
	 * in case the move is not legal - nothing is done
	 * @param move - the player's move
	 * @return - true if the move is legal and false otherwise
	 */
	boolean updatePlayerMove(GameMove move);
	
	/**
	 * @param move - the last move made
	 * @return the game state: PLAYER_WON \ PLAYER_LOST \ TIE \ IN PROGRESS 
	 */
	GameState getGameState(GameMove move);

	/**
	 * @return the game board state (each cell's content)
	 */
	char[][] getBoardState();

	/**
	 * @return - game state
	 */
	GameState getMemberGameState();

}

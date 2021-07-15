package com.hit.gameAlgo;

public interface IGameAlgo {
	
	/*
	 * A method that decides what the computer's step is.
	 */
	void calcComputerMove();
	
	/*
	 * A method that updates the player's one step in the game, in case it is valid.
	 */
	boolean updatePlayerMove(GameBoard.GameMove move);
	
	/*
	 * With this method, the reader will be able to know the status of the game, 
	 * view it when needed or decide to end the game. 
	 * Ending a game happens in one of the following situations-
	 * player won, player lost, tie.
	 */
	GameState getGameState(GameBoard.GameMove move);

	/*
	 * Returns the game board state (each cell's content).
	 */
	char[][] getBoardState();

	public static enum GameState
	{
		ILLEGAL_PLAYER_MOVE, IN_PROGRESS, PLAYER_LOST, PLAYER_WON, TIE; 
	}
	
	GameState getMemberGameState();

}

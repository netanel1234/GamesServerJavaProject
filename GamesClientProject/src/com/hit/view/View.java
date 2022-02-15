package com.hit.view;

public interface View {
	
	/**
	 * Starts the view
	 */
	void start();
	
	/**
	 * Update the view when a new-game response message arrives
	 * @param board - the initial game board
	 */
	void updateViewNewGame(java.lang.Character[] board);
	
	/**
	 * Updates the game board with the last move
	 * @param gameState - the state of the game after the last round
	 * @param board - the board state after the last round
	 */
	void updateViewGameMove(int gameState,Character[] board);
	
}

package com.hit.model;

public interface Model {
	
	/**
	 * Sends the server a "new-game" request
	 * @param gameType - the chosen game
	 * @param opponentType - computer type (random \ smart)
	 */
	void newGame(String gameType,String opponentType);
	
	/**
	 * Sends the server an "update-move" message
	 * @param row - the row of the move the player chose
	 * @param col - the column of the move the player chose
	 */
	void updatePlayerMove(int row,int col);
}

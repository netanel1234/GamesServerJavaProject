package com.hit.games;

import com.hit.gameAlgo.GameBoard;
import com.hit.gameAlgo.IGameAlgo.GameState;

/**
 * This abstract class implements the game CatchTheBunny
 */
public abstract class CatchTheBunny extends GameBoard {

	private int countPlayerMoves = 0;

	public CatchTheBunny(int rowLength, int colLength) {
		super(rowLength, colLength);
		this.board[0][4] = 'c';
		this.board[8][4] = 'p';
	}

	/**
	 * @param move - the last move made
	 * @return the game state: PLAYER_WON \ PLAYER_LOST \ TIE \ IN PROGRESS
	 */
	public GameState getGameState(GameMove move) {
		this.gameState = GameState.IN_PROGRESS;
		if (updatePlayerMove(move)) {
			this.countPlayerMoves++;
			if (this.countPlayerMoves > 15)
				this.gameState = GameState.PLAYER_LOST;
		} else
			this.gameState = GameState.ILLEGAL_PLAYER_MOVE;

		return this.gameState;
	}

	/**
	 * updatePlayerMove Updates the player's move on the board in case the move is
	 * not legal - nothing is done
	 * @param move - the player's move
	 * @return true if the move is legal and false otherwise
	 */
	public boolean updatePlayerMove(GameMove move) {
		if (board[move.getRow()][move.getCol()] == 'p')
			return false;

		// if the player chooses to move down
		if (board[move.getRow() + 1][move.getCol()] == 'p')
		{
			board[move.getRow() + 1][move.getCol()] = 'b';
			if (board[move.getRow()][move.getCol()] == 'c')
				gameState = GameState.PLAYER_WON;
			board[move.getRow()][move.getCol()] = 'p';
			return true;
		}
		// if the player chooses to move left
		if (board[move.getRow()][move.getCol() + 1] == 'p')
		{
			board[move.getRow()][move.getCol() + 1] = 'b';
			if (board[move.getRow()][move.getCol()] == 'c')
				gameState = GameState.PLAYER_WON;
			board[move.getRow()][move.getCol()] = 'p';
			return true;
		}
		// if the player chooses to move right
		if (board[move.getRow()][move.getCol() - 1] == 'p')
		{
			board[move.getRow()][move.getCol() - 1] = 'b';
			if (board[move.getRow()][move.getCol()] == 'c')
				gameState = GameState.PLAYER_WON;
			board[move.getRow()][move.getCol()] = 'p';
			return true;
		}
		// if the player chooses to move up
		if (board[move.getRow() - 1][move.getCol()] == 'p')
		{
			board[move.getRow() - 1][move.getCol()] = 'b';
			if (board[move.getRow()][move.getCol()] == 'c')
				gameState = GameState.PLAYER_WON;
			board[move.getRow()][move.getCol()] = 'p';
			return true;
		}
		return false;
	}

	public char[][] getBoardState() 
	{
		return board;
	}

	public GameState getMemberGameState() 
	{
		return gameState;
	}

	public int getCountPlayerMoves() 
	{
		return countPlayerMoves;
	}

	/**
	 * Representation of all possible cell states on the board
	 */
	public static enum BoardSigns {
		BLANK('b'), COMPUTER('c'), PLAYER('p');

		private char c;

		private BoardSigns(char x) {
			c = x;
		}

		public char getSign() {
			return c;
		}
	}

}

package com.hit.games;

import com.hit.gameAlgo.GameBoard;

/**
 * This abstract class implements the game
 */
public abstract class TicTacTow extends GameBoard {
	
	/**
	 * Representation of all possible cell states on the board
	 */
	public static enum BoardSigns
	{
		BLANK('b'), COMPUTER('c'), PLAYER('p');
		
		private char c;
		
		private BoardSigns(char x)
		{
			c=x;
		}
		
		public char getSign()
		{
			return c;
		}
	}
	
	public TicTacTow(int rowLength, int colLength) 
	{
		super(rowLength, colLength);
	}
	
	/**
	 * @param move - the last move made
	 * @return the game state: PLAYER_WON \ PLAYER_LOST \ TIE \ IN PROGRESS
	 */
	public GameState getGameState(GameMove move)
	{
		this.gameState=GameState.IN_PROGRESS;
		if(updatePlayerMove(move))
		{
			if(checkPlayerWin())
				this.gameState=GameState.PLAYER_WON;
			else if(checkTie())
				this.gameState=GameState.TIE;
		}
		return this.gameState;
	}
	
	/**
	 * Check if the player won in the last step
	 * @return - true if the player won, false otherwise
	 */
	public boolean checkPlayerWin()
	{
		if(		board[0][0]=='p' && board[0][1]=='p' && board[0][2]=='p' || 
				board[1][0]=='p' && board[1][1]=='p' && board[1][2]=='p' ||
				board[2][0]=='p' && board[2][1]=='p' && board[2][2]=='p' ||
				board[0][0]=='p' && board[1][0]=='p' && board[2][0]=='p' ||
				board[0][1]=='p' && board[1][1]=='p' && board[2][1]=='p' ||
				board[0][2]=='p' && board[1][2]=='p' && board[2][2]=='p' ||
				board[0][0]=='p' && board[1][1]=='p' && board[2][2]=='p' ||
				board[0][2]=='p' && board[1][1]=='p' && board[2][0]=='p'     )
			return true;
		return false;
	}

	/**
	 * Check if the game ends in a draw. 
	 * This happens when all the cells are full and they are not signs as 'b' (BLANK)
	 * @return - true if the the game ends in a draw, false otherwise.
	 */
	public boolean checkTie()
	{
		if(board[0][0]!='b' && 
			board[0][1]!='b' && 
			board[0][2]!='b' && 
			board[1][0]!='b' && 
			board[1][1]!='b' && 
			board[1][2]!='b' && 
			board[2][0]!='b' && 
			board[2][1]!='b' && 
		    board[2][2]!='b')
			return true;
		return false;
	}
	
	/**
	 * updatePlayerMove Updates the player's move on the board 
	 * in case the move is not legal - nothing is done.
	 * @param - move - the player's move
	 * @return true if the move is legal and false otherwise
	 */
	public boolean updatePlayerMove(GameMove move)
	{
		if(this.board[move.getRow()][move.getCol()]!='b')
		{
			this.gameState=GameState.ILLEGAL_PLAYER_MOVE;
			return false;
		}
		else
		{
			this.board[move.getRow()][move.getCol()]='p';
			return true;
		}
	}

	/**
	 * @return the game board state (each cell's content)
	 */
	public char[][] getBoardState()
	{
		return board;
	}
	
	/**
	 * get game state
	 */
	public GameState getMemberGameState() 
	{
		return this.gameState;
	}
	
	/**
	 * Check if the player lost
	 * @return true if the player lost, false otherwise
	 */
	public boolean checkPlayerLost()
	{
		if(		board[0][0]=='c' && board[0][1]=='c' && board[0][2]=='c' || 
				board[1][0]=='c' && board[1][1]=='c' && board[1][2]=='c' ||
				board[2][0]=='c' && board[2][1]=='c' && board[2][2]=='c' ||
				board[0][0]=='c' && board[1][0]=='c' && board[2][0]=='c' ||
				board[0][1]=='c' && board[1][1]=='c' && board[2][1]=='c' ||
				board[0][2]=='c' && board[1][2]=='c' && board[2][2]=='c' ||
				board[0][0]=='c' && board[1][1]=='c' && board[2][2]=='c' ||
				board[0][2]=='c' && board[1][1]=='c' && board[2][0]=='c'     )
			return true;
		return false;
	}
}

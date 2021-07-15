/*
 * NOTE: The method getGameState does not return the state PLAYER_LOST. 
 * Therefore, we may need to check the game state (by gameState) after every
 * operation of the method calcComputerMove.
 */

package com.hit.games;

import com.hit.gameAlgo.GameBoard;
import com.hit.gameAlgo.IGameAlgo;

public abstract class TicTacTow extends GameBoard {
	
	public GameState gameState;

	public TicTacTow(int r, int c) 
	{
		super(r, c);
		for(int i=0;i<r;i++)
		{
			for(int j=0;j<c;j++) 
			{
				board[i][j]='b';
			}
		}
		
		gameState=GameState.IN_PROGRESS;
	}
	
	public static enum BoardSigns
	{
		BLANK('b'),COMPUTER('c'),PLAYER('p');
		
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
	
	public GameState getGameState(GameMove move)
	{
		gameState=GameState.IN_PROGRESS;
		if(updatePlayerMove(move))
		{
			if(checkPlayerWin())
				gameState=GameState.PLAYER_WON;
			else if(checkTie())
				gameState=GameState.TIE;
		}
		return gameState;
	}
	
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
	
	public boolean checkTie()
	{
		if(board[0][0]!='b' && board[0][1]!='b' && board[0][2]!='b' && board[1][0]!='b' && board[1][1]!='b' && board[1][2]!='b' && board[2][0]!='b' && board[2][1]!='b' && board[2][2]!='b')
		{
			return true;
		}
		return false;
	}
	
	public boolean updatePlayerMove(GameMove move)
	{
		if(board[move.getRow()][move.getCol()]!='b')
		{
			gameState=GameState.ILLEGAL_PLAYER_MOVE;
			return false;
		}
		else
		{
			board[move.getRow()][move.getCol()]='p';
			return true;
		}
	}
	
	public char[][] getBoardState()
	{
		return board;
	}
	
	public GameState getMemberGameState() 
	{
		return gameState;
	}
	
	public void printBoard()
	{
		for(int i=0;i<row;i++)
		{
			for(int j=0;j<col;j++)
			{
				System.out.print(board[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
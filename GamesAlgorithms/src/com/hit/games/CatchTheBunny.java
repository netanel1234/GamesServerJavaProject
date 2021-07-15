package com.hit.games;

import com.hit.gameAlgo.GameBoard;
import com.hit.gameAlgo.IGameAlgo.GameState;

public abstract class CatchTheBunny extends GameBoard {
	
	public GameState gameState;
	public int countPlayerMoves=0; 

	public CatchTheBunny(int r, int c) 
	{
		super(r, c);
		
		for(int i=0;i<r;i++)
		{
			for(int j=0;j<c;j++) 
			{
				board[i][j]='b';
			}
		}
		board[0][4]='c';
		board[8][4]='p';
		
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
			countPlayerMoves++;
			if(countPlayerMoves>15)
				gameState=GameState.PLAYER_LOST;
		}
		else
		{
			gameState=GameState.ILLEGAL_PLAYER_MOVE;
		}
		
		return gameState;
	}
	
	//updatePlayerMove Updates the player's move on the board. in case the move is not legal - nothing is done
	//Returns: true if the move is legal and false otherwise
	public boolean updatePlayerMove(GameBoard.GameMove move)
	{
		if(board[move.getRow()][move.getCol()]=='p')
			return false;
		
		try {
			if(board[move.getRow()+1][move.getCol()]=='p')//if the player chooses to move down.
			{
				board[move.getRow()+1][move.getCol()]='b';
				if(board[move.getRow()][move.getCol()]=='c')
					gameState=GameState.PLAYER_WON;
				board[move.getRow()][move.getCol()]='p';
				return true;
			}
		}
		catch(ArrayIndexOutOfBoundsException e) {}
		finally {
			try {
				if(board[move.getRow()][move.getCol()+1]=='p')//if the player chooses to move left.
				{
					board[move.getRow()][move.getCol()+1]='b';
					if(board[move.getRow()][move.getCol()]=='c')
						gameState=GameState.PLAYER_WON;
					board[move.getRow()][move.getCol()]='p';
					return true;
				}
			}
			catch(ArrayIndexOutOfBoundsException ee) {}
			finally {
				try {
					if(board[move.getRow()][move.getCol()-1]=='p')//if the player chooses to move right.
					{
						board[move.getRow()][move.getCol()-1]='b';
						if(board[move.getRow()][move.getCol()]=='c')
							gameState=GameState.PLAYER_WON;
						board[move.getRow()][move.getCol()]='p';
						return true;
					}
				}
				catch(ArrayIndexOutOfBoundsException eee) {}
				finally {
					try {
						if(board[move.getRow()-1][move.getCol()]=='p')//if the player chooses to move up.
						{
							board[move.getRow()-1][move.getCol()]='b';
							if(board[move.getRow()][move.getCol()]=='c')
								gameState=GameState.PLAYER_WON;
							board[move.getRow()][move.getCol()]='p';
							return true;
						}
					}
					catch(ArrayIndexOutOfBoundsException eeee) {}
				}
			}
		}
			
		return false;
	}
	
	public char[][] getBoardState()
	{
		return board;
	}
	
	public void printBoard()
	{
		System.out.println("  0 1 2 3 4 5 6 7 8");
		for(int i=0;i<row;i++)
		{
			System.out.print(i+" ");
			for(int j=0;j<col;j++)
			{
				System.out.print(board[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public GameState getMemberGameState() {
		return gameState;
	}

	public int getCountPlayerMoves() {
		return countPlayerMoves;
	}
	
	
}

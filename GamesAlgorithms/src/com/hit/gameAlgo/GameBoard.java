package com.hit.gameAlgo;

/**
 * This abstract class implements the IGameAlgo interface.
 * Its role is to serve as a common denominator among all existing games in the software.
 */
public abstract class GameBoard implements IGameAlgo {
	
	protected int rowLength;
	protected int colLength;
	protected char[][] board;
	protected GameState gameState;
	
	public GameBoard(int rowLength,int colLength)
	{
		this.rowLength=rowLength;
		this.colLength=colLength;
		this.board=new char[rowLength][colLength];
		for(int i=0;i<rowLength;i++)
			for(int j=0;j<colLength;j++) 
				board[i][j]='b';
		this.gameState=GameState.IN_PROGRESS;
	}
	
	/**
	 * This inner class represents a possible move
	 */
	public static class GameMove
	{
		private int row;
		private int col;
		
		public GameMove(int row,int col)
		{
			this.row=row;
			this.col=col;
		}
		
		public int getRow()
		{
			return row;
		}
		
		public int getCol()
		{
			return col;
		}
	}
}

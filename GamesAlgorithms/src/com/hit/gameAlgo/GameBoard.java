package com.hit.gameAlgo;


/*
 * This is an abstract class that implements the interface IGameAlgo.
 */
public abstract class GameBoard implements IGameAlgo {
	
	public int row;
	public int col;
	public char[][] board;
	
	public GameBoard(int r,int c)
	{
		row=r;
		col=c;
		board=new char[r][c];
	}
	
	public abstract IGameAlgo.GameState getGameState(GameBoard.GameMove move);
	
	public abstract boolean updatePlayerMove(GameBoard.GameMove move);
	
	public abstract void calcComputerMove();
	
	public abstract char[][] getBoardState();
	
	public static class GameMove
	{
		private int row;
		private int col;
		
		public GameMove(int r,int c)
		{
			row=r;
			col=c;
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
	
	public abstract GameState getMemberGameState();
}
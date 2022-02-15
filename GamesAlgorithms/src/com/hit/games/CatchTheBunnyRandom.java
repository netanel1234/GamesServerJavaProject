package com.hit.games;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class represents an CatchTheBunny game where the computer performs a
 * move at random
 */
public class CatchTheBunnyRandom extends CatchTheBunny {

	public CatchTheBunnyRandom(int rowLength, int colLength) {
		super(rowLength, colLength);
	}

	/**
	 * calcComputerMove Calculates the copmuter's move and updates the game board
	 */
	@Override
	public void calcComputerMove() {
		// Finding the location of the computer
		int x = 0, y = 0;
		for (int i = 0; i < this.rowLength; i++)
			for (int j = 0; j < this.colLength; j++)
				if (board[i][j] == 'c') {
					x = i;
					y = j;
					break;
				}
		
		GameMove computerLocation=new GameMove(x,y);
		List<GameMove> possibleCells=new ArrayList<GameMove>();
		
		if(isComputerOnTopLeftCorner(computerLocation))
		{
			possibleCells.add(new GameMove(1,0));
			possibleCells.add(new GameMove(0,1));
		}
		else if(isComputerOnTopRightCorner(computerLocation))
		{
			possibleCells.add(new GameMove(0,7));
			possibleCells.add(new GameMove(1,8));
		}
		else if(isComputerOnBottomLeftCorner(computerLocation))
		{
			possibleCells.add(new GameMove(7,0));
			possibleCells.add(new GameMove(8,1));
		}
		else if(isComputerOnBottomRightCorner(computerLocation))
		{
			possibleCells.add(new GameMove(7,8));
			possibleCells.add(new GameMove(8,7));
		}
		else if(isComputerOnTheEdge(computerLocation))
		{
			if(x==0)
			{
				possibleCells.add(new GameMove(computerLocation.getRow(),computerLocation.getCol()-1));
				possibleCells.add(new GameMove(computerLocation.getRow()+1,computerLocation.getCol()));
				possibleCells.add(new GameMove(computerLocation.getRow(),computerLocation.getCol()+1));
			}
			if(x==8)
			{
				possibleCells.add(new GameMove(computerLocation.getRow(),computerLocation.getCol()-1));
				possibleCells.add(new GameMove(computerLocation.getRow()-1,computerLocation.getCol()));
				possibleCells.add(new GameMove(computerLocation.getRow(),computerLocation.getCol()+1));
			}
			if(y==0)
			{
				possibleCells.add(new GameMove(computerLocation.getRow()+1,computerLocation.getCol()));
				possibleCells.add(new GameMove(computerLocation.getRow(),computerLocation.getCol()+1));
				possibleCells.add(new GameMove(computerLocation.getRow()-1,computerLocation.getCol()));
			}
			if(y==8)
			{
				possibleCells.add(new GameMove(computerLocation.getRow()+1,computerLocation.getCol()));
				possibleCells.add(new GameMove(computerLocation.getRow(),computerLocation.getCol()-1));
				possibleCells.add(new GameMove(computerLocation.getRow()-1,computerLocation.getCol()));
			}
		}
		else
		{
			possibleCells.add(new GameMove(computerLocation.getRow()+1,computerLocation.getCol()));
			possibleCells.add(new GameMove(computerLocation.getRow(),computerLocation.getCol()-1));
			possibleCells.add(new GameMove(computerLocation.getRow()-1,computerLocation.getCol()));
			possibleCells.add(new GameMove(computerLocation.getRow(),computerLocation.getCol()+1));
		}
		
		Random cellGenerator = new Random();
		GameMove computerMove=possibleCells.get(cellGenerator.nextInt(possibleCells.size()));
		if(this.board[computerMove.getRow()][computerMove.getCol()]=='b')
		{
			this.board[computerMove.getRow()][computerMove.getCol()]='c';
			this.board[computerLocation.getRow()][computerLocation.getCol()]='b';
		}
		else if(this.board[computerMove.getRow()][computerMove.getCol()]=='p')
		{
			this.board[computerLocation.getRow()][computerLocation.getCol()]='b';
			this.gameState=GameState.PLAYER_WON;
		}
	}
	
	public boolean isComputerOnTopLeftCorner(GameMove move)
	{
		return (move.getCol()==0 && move.getRow()==0)?true:false;		
	}
	
	public boolean isComputerOnTopRightCorner(GameMove move)
	{
		return (move.getCol()==0 && move.getRow()==8)?true:false;		
	}
	
	public boolean isComputerOnBottomLeftCorner(GameMove move)
	{
		return (move.getCol()==8 && move.getRow()==0)?true:false;		
	}
	
	public boolean isComputerOnBottomRightCorner(GameMove move)
	{
		return (move.getCol()==8 && move.getRow()==8)?true:false;		
	}
	
	public boolean isComputerOnTheEdge(GameMove move)
	{
		return (move.getCol()==8 && move.getRow()==8)?true:false;		
	} 
}

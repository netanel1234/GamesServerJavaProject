package com.hit.games;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class represents a game in such a way 
 * that the computer performs a step in a calculated manner
 */
public class CatchTheBunnySmart extends CatchTheBunny {

	public CatchTheBunnySmart(int rowLength, int colLength) 
	{
		super(rowLength, colLength);
	}

	@Override
	public void calcComputerMove() 
	{
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
			if(this.board[1][0]!='p')
				possibleCells.add(new GameMove(1,0));
			if(this.board[0][1]!='p')
			possibleCells.add(new GameMove(0,1));
		}
		else if(isComputerOnTopRightCorner(computerLocation))
		{
			if(this.board[0][7]!='p')
				possibleCells.add(new GameMove(0,7));
			if(this.board[1][8]!='p')
				possibleCells.add(new GameMove(1,8));
		}
		else if(isComputerOnBottomLeftCorner(computerLocation))
		{
			if(this.board[7][0]!='p')
				possibleCells.add(new GameMove(7,0));
			if(this.board[8][1]!='p')
				possibleCells.add(new GameMove(8,1));
		}
		else if(isComputerOnBottomRightCorner(computerLocation))
		{
			if(this.board[7][8]!='p')
				possibleCells.add(new GameMove(7,8));
			if(this.board[8][7]!='p')
				possibleCells.add(new GameMove(8,7));
		}
		else if(isComputerOnTheEdge(computerLocation))
		{
			if(x==0)
			{
				if(this.board[computerLocation.getRow()][computerLocation.getCol()-1]!='p')
					possibleCells.add(new GameMove(computerLocation.getRow(),computerLocation.getCol()-1));
				if(this.board[computerLocation.getRow()+1][computerLocation.getCol()]!='p')
					possibleCells.add(new GameMove(computerLocation.getRow()+1,computerLocation.getCol()));
				if(this.board[computerLocation.getRow()][computerLocation.getCol()+1]!='p')
					possibleCells.add(new GameMove(computerLocation.getRow(),computerLocation.getCol()+1));
			}
			if(x==8)
			{
				if(this.board[computerLocation.getRow()][computerLocation.getCol()-1]!='p')
					possibleCells.add(new GameMove(computerLocation.getRow(),computerLocation.getCol()-1));
				if(this.board[computerLocation.getRow()-1][computerLocation.getCol()]!='p')
					possibleCells.add(new GameMove(computerLocation.getRow()-1,computerLocation.getCol()));
				if(this.board[computerLocation.getRow()][computerLocation.getCol()+1]!='p')
					possibleCells.add(new GameMove(computerLocation.getRow(),computerLocation.getCol()+1));
			}
			if(y==0)
			{
				if(this.board[computerLocation.getRow()+1][computerLocation.getCol()]!='p')
					possibleCells.add(new GameMove(computerLocation.getRow()+1,computerLocation.getCol()));
				if(this.board[computerLocation.getRow()][computerLocation.getCol()+1]!='p')
					possibleCells.add(new GameMove(computerLocation.getRow(),computerLocation.getCol()+1));
				if(this.board[computerLocation.getRow()-1][computerLocation.getCol()]!='p')
					possibleCells.add(new GameMove(computerLocation.getRow()-1,computerLocation.getCol()));
			}
			if(y==8)
			{
				if(this.board[computerLocation.getRow()+1][computerLocation.getCol()]!='p')
					possibleCells.add(new GameMove(computerLocation.getRow()+1,computerLocation.getCol()));
				if(this.board[computerLocation.getRow()][computerLocation.getCol()-1]!='p')
					possibleCells.add(new GameMove(computerLocation.getRow(),computerLocation.getCol()-1));
				if(this.board[computerLocation.getRow()-1][computerLocation.getCol()]!='p')
					possibleCells.add(new GameMove(computerLocation.getRow()-1,computerLocation.getCol()));
			}
		}
		else
		{
			if(this.board[computerLocation.getRow()+1][computerLocation.getCol()]!='p')
				possibleCells.add(new GameMove(computerLocation.getRow()+1,computerLocation.getCol()));
			if(this.board[computerLocation.getRow()][computerLocation.getCol()-1]!='p')
				possibleCells.add(new GameMove(computerLocation.getRow(),computerLocation.getCol()-1));
			if(this.board[computerLocation.getRow()-1][computerLocation.getCol()]!='p')
				possibleCells.add(new GameMove(computerLocation.getRow()-1,computerLocation.getCol()));
			if(this.board[computerLocation.getRow()][computerLocation.getCol()+1]!='p')
				possibleCells.add(new GameMove(computerLocation.getRow(),computerLocation.getCol()+1));
		}
		
		Random cellGenerator = new Random();
		GameMove computerMove=possibleCells.get(cellGenerator.nextInt(possibleCells.size()));
		this.board[computerMove.getRow()][computerMove.getCol()]='c';
		this.board[computerLocation.getRow()][computerLocation.getCol()]='b';
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

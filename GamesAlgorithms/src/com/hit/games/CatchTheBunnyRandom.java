package com.hit.games;

import java.util.Random;

public class CatchTheBunnyRandom extends CatchTheBunny {

	public CatchTheBunnyRandom(int r, int c) 
	{
		super(r, c);
	}

	//calcComputerMove Calculates the copmuter's move and updates the game board
	@Override
	public void calcComputerMove() 
	{
		//finding the location of the computer.
		int x = 0,y = 0;
		for(int i=0;i<row;i++)
		{
			for(int j=0;j<col;j++)
			{
				if(board[i][j]=='c')
				{
					x=i;
					y=j;
				}
			}
		}
		
		Random rand=new Random();
		int computerMove=rand.nextInt(4);
		
		boolean legalMove=false;
		while(!legalMove)
		{
			legalMove=findMove(computerMove, x, y);
			computerMove=rand.nextInt(4);
		}
	}
	
	private boolean findMove(int randMove, int x, int y)
	{
		if(randMove==0)
		{
			try {
				if(board[x+1][y]=='b')
				{
					board[x][y]='b';
					board[x+1][y]='c';
				}
				else if(board[x+1][y]=='p')
				{
					board[x][y]='b';
					gameState=GameState.PLAYER_WON;
				}
				return true;
			}
			catch(ArrayIndexOutOfBoundsException e) {}
		}
		if(randMove==1)
		{
			try {
				if(board[x][y+1]=='b')
				{
					board[x][y]='b';
					board[x][y+1]='c';
				}
				else if(board[x][y+1]=='p')
				{
					board[x][y]='b';
					gameState=GameState.PLAYER_WON;
				}
				return true;
			}
			catch(ArrayIndexOutOfBoundsException e) {}
		}
		if(randMove==2)
		{
			try {
				if(board[x][y-1]=='b')
				{
					board[x][y]='b';
					board[x][y-1]='c';
				}
				else if(board[x][y-1]=='p')
				{
					board[x][y]='b';
					gameState=GameState.PLAYER_WON;
				}
				return true;
			}
			catch(ArrayIndexOutOfBoundsException e) {}
		}
		if(randMove==3)
		{
			try {
				if(board[x-1][y]=='b')
				{
					board[x][y]='b';
					board[x-1][y]='c';
				}
				else if(board[x-1][y]=='p')
				{
					board[x][y]='b';
					gameState=GameState.PLAYER_WON;
				}
				return true;
			}
			catch(ArrayIndexOutOfBoundsException e) {}
		}
		
		return false;
	}

}

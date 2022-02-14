package com.hit.games;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class represents a game in such a way 
 * that the computer performs a step in a calculated manner
 */
public class TicTacTowSmart extends TicTacTow {

	public TicTacTowSmart(int rowLength, int colLength) 
	{
		super(rowLength, colLength);
	}

	/**
	 * calcComputerMove Calculates the copmuter's move and updates the game board
	 */
	@Override
	public void calcComputerMove() 
	{
		if(board[0][0]=='p'&&board[0][2]=='p')
			board[0][1]='c';
		else if(board[0][0]=='p'&&board[0][1]=='p')
			board[0][2]='c';
		else if(board[0][1]=='p'&&board[0][2]=='p')
			board[0][0]='c';
		
		else if(board[1][0]=='p'&&board[1][1]=='p')
			board[1][2]='c';
		else if(board[1][0]=='p'&&board[1][2]=='p')
			board[1][1]='c';
		else if(board[1][1]=='p'&&board[1][2]=='p')
			board[1][0]='c';
		
		else if(board[2][0]=='p'&&board[2][1]=='p')
			board[2][2]='c';
		else if(board[2][0]=='p'&&board[2][2]=='p')
			board[2][1]='c';
		else if(board[2][1]=='p'&&board[2][2]=='p')
			board[2][0]='c';
		
		else if(board[0][0]=='p'&&board[1][0]=='p')
			board[2][0]='c';
		else if(board[0][0]=='p'&&board[2][0]=='p')
			board[1][0]='c';
		else if(board[2][0]=='p'&&board[1][0]=='p')
			board[0][0]='c';
		
		else if(board[0][1]=='p'&&board[1][1]=='p')
			board[2][1]='c';
		else if(board[0][1]=='p'&&board[2][1]=='p')
			board[1][1]='c';
		else if(board[1][1]=='p'&&board[2][1]=='p')
			board[0][1]='c';
		
		else if(board[0][2]=='p'&&board[1][2]=='p')
			board[2][2]='c';
		else if(board[0][2]=='p'&&board[2][2]=='p')
			board[1][2]='c';
		else if(board[1][2]=='p'&&board[2][2]=='p')
			board[0][2]='c';
		
		else if(board[0][0]=='p'&&board[1][1]=='p')
			board[2][2]='c';
		else if(board[0][0]=='p'&&board[2][2]=='p')
			board[1][1]='c';
		else if(board[1][1]=='p'&&board[2][2]=='p')
			board[0][0]='c';
		
		else if(board[0][2]=='p'&&board[1][1]=='p')
			board[2][0]='c';
		else if(board[0][2]=='p'&&board[2][0]=='p')
			board[1][1]='c';
		else if(board[1][1]=='p'&&board[2][0]=='p')
			board[0][2]='c';
		else 
		{
			List<GameMove> emptyCells=new ArrayList<GameMove>();
			for(int i=0;i<this.rowLength;i++)
				for(int j=0;j<this.colLength;j++)
					if(this.board[i][j]=='b')
						emptyCells.add(new GameMove(i,j));
			Random cellGenerator=new Random();
			GameMove computerMove=emptyCells.get(cellGenerator.nextInt(emptyCells.size()));
			this.board[computerMove.getCol()][computerMove.getRow()]='c';
			if(this.checkPlayerLost())
				gameState=GameState.PLAYER_LOST;
		}
	}
}

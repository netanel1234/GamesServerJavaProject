package com.hit.games;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class represents an TicTacTow game where the computer performs a move at random
 */
public class TicTacTowRandom extends TicTacTow {

	public TicTacTowRandom(int rowLength, int colLength) 
	{
		super(rowLength, colLength);
	}

	/**
	 * calcComputerMove Calculates the copmuter's move and updates the game board
	 */
	@Override
	public void calcComputerMove() 
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
	
	/**
	 * updatePlayerMove Updates the player's move on the board 
	 * in case the move is not legal - nothing is done
	 * @param move - the player's move
	 * @return true if the move is legal and false otherwise
	 */
	public boolean updatePlayerMove(GameMove move)
	{
		return super.updatePlayerMove(move);
	}
	
}

package com.hit.games;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.hit.gameAlgo.IGameAlgo.GameState;

public class TicTacTowRandom extends TicTacTow {

	public TicTacTowRandom(int r, int c) 
	{
		super(r, c);
	}

	@Override
	public void calcComputerMove() 
	{
		List<Integer> list=new ArrayList<>();
		int place=0;
		int newPlace;
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				if(board[i][j]=='b')
					list.add(place);
				place++;
			}
		}
		place=0;
		newPlace=getRandomElement(list);
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				if(place==newPlace)
					board[i][j]='c';
				place++;
			}
		}

		if(checkPlayerLost())
			gameState=GameState.PLAYER_LOST;
	}
	
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
	
	public int getRandomElement(List<Integer> list)
	{
		Random rand=new Random();
		return list.get(rand.nextInt(list.size()));
	}
	
	public boolean updatePlayerMove(GameMove move)
	{
		return super.updatePlayerMove(move);
	}
	
}

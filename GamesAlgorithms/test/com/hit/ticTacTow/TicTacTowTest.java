package com.hit.ticTacTow;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hit.gameAlgo.GameBoard.GameMove;
import com.hit.gameAlgo.IGameAlgo.GameState;
import com.hit.games.TicTacTow;
import com.hit.games.TicTacTowRandom;
import com.hit.games.TicTacTowSmart;

class TicTacTowTest {
	
	TicTacTow random,smart;
	
	@BeforeEach
	void setUp()
	{
		random=new TicTacTowRandom(3,3);
		smart=new TicTacTowSmart(3,3);
	}
	
	@Test
	void setUpsTest()
	{
		System.out.println("setUpsTest");
		System.out.println();
		
		/*---Random---*/
		random.printBoard();
		assertEquals(GameState.IN_PROGRESS,random.gameState);
		
		/*---Smart---*/
		smart.printBoard();
		assertEquals(GameState.IN_PROGRESS,smart.gameState);
	}

	@Test
	void playerWonTest()
	{
		System.out.println("playerWonTest");
		System.out.println();
		
		/*---Random---*/
		assertEquals(GameState.IN_PROGRESS,random.getGameState(new GameMove(0,2)));
		random.printBoard();
		assertEquals(GameState.IN_PROGRESS,random.getGameState(new GameMove(1,2)));
		random.printBoard();
		assertEquals(GameState.PLAYER_WON,random.getGameState(new GameMove(2,2)));
		random.printBoard();
		
		/*---Smart---*/
		assertEquals(GameState.IN_PROGRESS,smart.getGameState(new GameMove(0,2)));
		smart.printBoard();
		assertEquals(GameState.IN_PROGRESS,smart.getGameState(new GameMove(1,2)));
		smart.printBoard();
		assertEquals(GameState.PLAYER_WON,smart.getGameState(new GameMove(2,2)));
		smart.printBoard();
	}
	
	@Test
	void playerLostTest()
	{
		System.out.println("playerLostTest");
		System.out.println();
		
		/*---Random---*/
		random.calcComputerMove();
		random.calcComputerMove();
		random.calcComputerMove();
		random.calcComputerMove();
		random.calcComputerMove();
		assertEquals(GameState.PLAYER_LOST,random.gameState);

		/*---Smart---*/
		smart.calcComputerMove();
		smart.calcComputerMove();
		smart.calcComputerMove();
		smart.calcComputerMove();
		smart.calcComputerMove();
		assertEquals(GameState.PLAYER_LOST,smart.gameState);

	}
	
	@Test
	void tieGameTest()
	{
		System.out.println("tieGameTest");
		System.out.println();
		
		/*---Random---*/
		assertEquals(GameState.IN_PROGRESS,random.getGameState(new GameMove(0,0)));
		assertEquals(GameState.IN_PROGRESS,random.getGameState(new GameMove(1,1)));
		assertEquals(GameState.IN_PROGRESS,random.getGameState(new GameMove(2,0)));
		assertEquals(GameState.IN_PROGRESS,random.getGameState(new GameMove(1,2)));
		random.board[0][1]='c';
		random.board[0][2]='c';
		random.board[1][0]='c';
		random.board[2][2]='c';
		assertEquals(GameState.TIE,random.getGameState(new GameMove(2,1)));
		random.printBoard();
		
		/*---Smart---*/
		assertEquals(GameState.IN_PROGRESS,smart.getGameState(new GameMove(0,0)));
		assertEquals(GameState.IN_PROGRESS,smart.getGameState(new GameMove(1,1)));
		assertEquals(GameState.IN_PROGRESS,smart.getGameState(new GameMove(2,0)));
		assertEquals(GameState.IN_PROGRESS,smart.getGameState(new GameMove(1,2)));
		smart.board[0][1]='c';
		smart.board[0][2]='c';
		smart.board[1][0]='c';
		smart.board[2][2]='c';
		assertEquals(GameState.TIE,smart.getGameState(new GameMove(2,1)));
		smart.printBoard();
	}
	
	@Test
	void illegalPlayerMoveTest()
	{
		System.out.println("illegalPlayerMoveTest");
		System.out.println();
		
		/*---Random---*/
		assertEquals(GameState.IN_PROGRESS,random.getGameState(new GameMove(0,0)));
		random.printBoard();
		assertEquals(GameState.ILLEGAL_PLAYER_MOVE,random.getGameState(new GameMove(0,0)));
		random.board[0][1]='c';
		assertEquals(GameState.ILLEGAL_PLAYER_MOVE,random.getGameState(new GameMove(0,1)));
		
		/*---Smart---*/
		assertEquals(GameState.IN_PROGRESS,smart.getGameState(new GameMove(0,0)));
		smart.printBoard();
		assertEquals(GameState.ILLEGAL_PLAYER_MOVE,smart.getGameState(new GameMove(0,0)));
		smart.board[0][1]='c';
		assertEquals(GameState.ILLEGAL_PLAYER_MOVE,smart.getGameState(new GameMove(0,1)));

	}
	
	@Test
	void calcComputerMoveTest()
	{
		System.out.println("calcComputerMoveTest");
		System.out.println();
		
		/*---Random---*/
		random.calcComputerMove();
		random.printBoard();
		random.calcComputerMove();
		random.printBoard();
		random.calcComputerMove();
		random.printBoard();
		random.calcComputerMove();
		random.printBoard();
		random.calcComputerMove();
		random.printBoard();
		random.calcComputerMove();
		random.printBoard();
		random.calcComputerMove();
		random.printBoard();
		random.calcComputerMove();
		random.printBoard();
		random.calcComputerMove();
		random.printBoard();
		
		/*---Smart---*/
		smart.calcComputerMove();
		smart.printBoard();
		smart.calcComputerMove();
		smart.printBoard();
		smart.calcComputerMove();
		smart.printBoard();
		smart.calcComputerMove();
		smart.printBoard();
		smart.calcComputerMove();
		smart.printBoard();
		smart.calcComputerMove();
		smart.printBoard();
		smart.calcComputerMove();
		smart.printBoard();
		smart.calcComputerMove();
		smart.printBoard();
		smart.calcComputerMove();
		smart.printBoard();
		
	}
	
	

}

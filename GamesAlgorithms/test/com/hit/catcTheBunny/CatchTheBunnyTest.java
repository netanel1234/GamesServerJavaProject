package com.hit.catcTheBunny;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hit.gameAlgo.GameBoard.GameMove;
import com.hit.gameAlgo.IGameAlgo.GameState;
import com.hit.games.CatchTheBunny;
import com.hit.games.CatchTheBunnyRandom;

class CatchTheBunnyTest {
	
	CatchTheBunny random,smart;
	
	@BeforeEach
	void Set_Up()
	{
		random=new CatchTheBunnyRandom(9,9);
		smart=new CatchTheBunnyRandom(9,9);
	}
	
	@Test
	void setUpsTest()
	{
		System.out.println("setUpsTest");
		System.out.println();

		random.printBoard();
		assertEquals(GameState.IN_PROGRESS,random.gameState);
		
		smart.printBoard();
		assertEquals(GameState.IN_PROGRESS,smart.gameState);

	}
	
	@Test
	void playerLostTest()
	{
		/*player lost the game if he failed to catch the bunny after 15 moves.*/
		
		System.out.println("playerLostTest");
		System.out.println();
		
		/*---Random Game---*/
		assertEquals(GameState.IN_PROGRESS,random.getGameState(new GameMove(7,4)));
		random.printBoard();
		assertEquals(GameState.IN_PROGRESS,random.getGameState(new GameMove(7,5)));
		random.printBoard();
		assertEquals(GameState.IN_PROGRESS,random.getGameState(new GameMove(7,4)));
		random.printBoard();
		assertEquals(GameState.IN_PROGRESS,random.getGameState(new GameMove(7,5)));
		random.printBoard();
		assertEquals(GameState.IN_PROGRESS,random.getGameState(new GameMove(7,4)));
		random.printBoard();
		assertEquals(GameState.IN_PROGRESS,random.getGameState(new GameMove(7,5)));
		random.printBoard();
		assertEquals(GameState.IN_PROGRESS,random.getGameState(new GameMove(7,4)));
		random.printBoard();
		assertEquals(GameState.IN_PROGRESS,random.getGameState(new GameMove(7,5)));
		random.printBoard();
		assertEquals(GameState.IN_PROGRESS,random.getGameState(new GameMove(7,4)));
		random.printBoard();
		assertEquals(GameState.IN_PROGRESS,random.getGameState(new GameMove(7,5)));
		random.printBoard();
		assertEquals(GameState.IN_PROGRESS,random.getGameState(new GameMove(7,4)));
		random.printBoard();
		assertEquals(GameState.IN_PROGRESS,random.getGameState(new GameMove(7,5)));
		random.printBoard();
		assertEquals(GameState.IN_PROGRESS,random.getGameState(new GameMove(7,4)));
		random.printBoard();
		assertEquals(GameState.IN_PROGRESS,random.getGameState(new GameMove(7,5)));
		random.printBoard();
		assertEquals(GameState.IN_PROGRESS,random.getGameState(new GameMove(7,4)));
		random.printBoard();
		assertEquals(GameState.PLAYER_LOST,random.getGameState(new GameMove(7,5)));
		random.printBoard();
		
		/*---Smart Game---*/
		assertEquals(GameState.IN_PROGRESS,smart.getGameState(new GameMove(7,4)));
		smart.printBoard();
		assertEquals(GameState.IN_PROGRESS,smart.getGameState(new GameMove(7,5)));
		smart.printBoard();
		assertEquals(GameState.IN_PROGRESS,smart.getGameState(new GameMove(7,4)));
		smart.printBoard();
		assertEquals(GameState.IN_PROGRESS,smart.getGameState(new GameMove(7,5)));
		smart.printBoard();
		assertEquals(GameState.IN_PROGRESS,smart.getGameState(new GameMove(7,4)));
		smart.printBoard();
		assertEquals(GameState.IN_PROGRESS,smart.getGameState(new GameMove(7,5)));
		smart.printBoard();
		assertEquals(GameState.IN_PROGRESS,smart.getGameState(new GameMove(7,4)));
		smart.printBoard();
		assertEquals(GameState.IN_PROGRESS,smart.getGameState(new GameMove(7,5)));
		smart.printBoard();
		assertEquals(GameState.IN_PROGRESS,smart.getGameState(new GameMove(7,4)));
		smart.printBoard();
		assertEquals(GameState.IN_PROGRESS,smart.getGameState(new GameMove(7,5)));
		smart.printBoard();
		assertEquals(GameState.IN_PROGRESS,smart.getGameState(new GameMove(7,4)));
		smart.printBoard();
		assertEquals(GameState.IN_PROGRESS,smart.getGameState(new GameMove(7,5)));
		smart.printBoard();
		assertEquals(GameState.IN_PROGRESS,smart.getGameState(new GameMove(7,4)));
		smart.printBoard();
		assertEquals(GameState.IN_PROGRESS,smart.getGameState(new GameMove(7,5)));
		smart.printBoard();
		assertEquals(GameState.IN_PROGRESS,smart.getGameState(new GameMove(7,4)));
		smart.printBoard();
		assertEquals(GameState.PLAYER_LOST,smart.getGameState(new GameMove(7,5)));
		smart.printBoard();
	}

	@Test
	void playerWonTest()
	{
		System.out.println("playerWonTest");
		System.out.println();
		
		/*---Random Game---*/
		assertEquals(GameState.IN_PROGRESS,random.getGameState(new GameMove(7,4)));
		random.printBoard();
		assertEquals(GameState.IN_PROGRESS,random.getGameState(new GameMove(6,4)));
		random.printBoard();
		assertEquals(GameState.IN_PROGRESS,random.getGameState(new GameMove(5,4)));
		random.printBoard();
		assertEquals(GameState.IN_PROGRESS,random.getGameState(new GameMove(4,4)));
		random.printBoard();
		assertEquals(GameState.IN_PROGRESS,random.getGameState(new GameMove(3,4)));
		random.printBoard();
		assertEquals(GameState.IN_PROGRESS,random.getGameState(new GameMove(2,4)));
		random.printBoard();
		assertEquals(GameState.IN_PROGRESS,random.getGameState(new GameMove(1,4)));
		random.printBoard();
		assertEquals(GameState.PLAYER_WON,random.getGameState(new GameMove(0,4)));
		random.printBoard();
		
		/*---Smart Game---*/
		assertEquals(GameState.IN_PROGRESS,smart.getGameState(new GameMove(7,4)));
		smart.printBoard();
		assertEquals(GameState.IN_PROGRESS,smart.getGameState(new GameMove(6,4)));
		smart.printBoard();
		assertEquals(GameState.IN_PROGRESS,smart.getGameState(new GameMove(5,4)));
		smart.printBoard();
		assertEquals(GameState.IN_PROGRESS,smart.getGameState(new GameMove(4,4)));
		smart.printBoard();
		assertEquals(GameState.IN_PROGRESS,smart.getGameState(new GameMove(3,4)));
		smart.printBoard();
		assertEquals(GameState.IN_PROGRESS,smart.getGameState(new GameMove(2,4)));
		smart.printBoard();
		assertEquals(GameState.IN_PROGRESS,smart.getGameState(new GameMove(1,4)));
		smart.printBoard();
		assertEquals(GameState.PLAYER_WON,smart.getGameState(new GameMove(0,4)));
		smart.printBoard();
	}
	
	@Test
	void illegalMovesTest()
	{
		System.out.println("illegalMovesTest");
		System.out.println();
		
		/*---Random Game---*/
		assertEquals(GameState.IN_PROGRESS,random.getGameState(new GameMove(7,4)));
		random.printBoard();
		assertEquals(GameState.ILLEGAL_PLAYER_MOVE,random.getGameState(new GameMove(6,5)));
		random.printBoard();
		assertEquals(GameState.ILLEGAL_PLAYER_MOVE,random.getGameState(new GameMove(8,5)));
		random.printBoard();
		assertEquals(GameState.ILLEGAL_PLAYER_MOVE,random.getGameState(new GameMove(8,3)));
		random.printBoard();
		assertEquals(GameState.ILLEGAL_PLAYER_MOVE,random.getGameState(new GameMove(6,3)));
		random.printBoard();
		assertEquals(GameState.ILLEGAL_PLAYER_MOVE,random.getGameState(new GameMove(7,4)));
		random.printBoard();
		
		/*---Smart Game---*/
		assertEquals(GameState.IN_PROGRESS,smart.getGameState(new GameMove(7,4)));
		smart.printBoard();
		assertEquals(GameState.ILLEGAL_PLAYER_MOVE,smart.getGameState(new GameMove(6,5)));
		smart.printBoard();
		assertEquals(GameState.ILLEGAL_PLAYER_MOVE,smart.getGameState(new GameMove(8,5)));
		smart.printBoard();
		assertEquals(GameState.ILLEGAL_PLAYER_MOVE,smart.getGameState(new GameMove(8,3)));
		smart.printBoard();
		assertEquals(GameState.ILLEGAL_PLAYER_MOVE,smart.getGameState(new GameMove(6,3)));
		smart.printBoard();
		assertEquals(GameState.ILLEGAL_PLAYER_MOVE,smart.getGameState(new GameMove(7,4)));
		smart.printBoard();
	}
	
	@Test
	void calcComputerMoveTest()
	{
		System.out.println("calcComputerMoveTest");
		System.out.println();
		
		/*---Random Game---*/
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
		random.calcComputerMove();
		random.printBoard();
		
		/*---Smart Game---*/
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
		smart.calcComputerMove();
		smart.printBoard();
		
	}
}

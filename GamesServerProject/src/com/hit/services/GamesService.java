package com.hit.services;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.hit.GameHandler.BoardGameHandler;
import com.hit.exception.UnknownIdException;
import com.hit.gameAlgo.GameBoard.GameMove;
import com.hit.gameAlgo.IGameAlgo;
import com.hit.gameAlgo.IGameAlgo.GameState;
import com.hit.games.CatchTheBunnyRandom;
import com.hit.games.CatchTheBunnySmart;
import com.hit.games.TicTacTowRandom;
import com.hit.games.TicTacTowSmart;

/**
 * This class supports a variable number of games that the server allows to play at the same time
 */
public class GamesService {
	
	private int capacity;
	private Map<Integer, BoardGameHandler> games;
	
	/**
	 * Constructor 
	 * @param capacity - the maximum number of games that could be played simultaneously
	 */
	public GamesService(int capacity) 
	{
		super();
		this.capacity = capacity;
		this.games=new ConcurrentHashMap<Integer, BoardGameHandler>(this.capacity);
	}
	
	/**
	 * creation of new game
	 * @param gameType - game that was chosen (Tic Tac Tow or Catch the Bunny)
	 * @param opponent - the desired opponent type (whether the computer should play smart or random)
	 * @return - the game ID if the game could be opened or -1 if the maximum number of simultaneous games was reached
	 */
	synchronized public int newGame(String gameType, String opponent)
	{
		int id=findId();
		if(id!=-1)
		{
			if(gameType.equals("Tic Tac Tow"))
			{
				if(opponent.equals("Random"))
					this.games.put(id, new BoardGameHandler(new TicTacTowRandom(3,3)));
				else
					this.games.put(id, new BoardGameHandler(new TicTacTowSmart(3,3)));
			}
			else
			{
				if(opponent.equals("Random"))
					this.games.put(id, new BoardGameHandler(new CatchTheBunnyRandom(9,9)));
				else
					this.games.put(id, new BoardGameHandler(new CatchTheBunnySmart(9,9)));
			}
		}
		return id;
	}
	
	/**
	 * find free id number 
	 * @return - the game ID if the game could be opened or -1 if the maximum number of simultaneous games was reached
	 */
	synchronized public int findId()
	{
		for(int i=1;i<this.capacity;i++)
			if(!this.games.containsKey(i))
				return i;
		return -1;
	}
	
	/**
	 * execution of one round in the game
	 * @param gameId - the game id
	 * @param playerMove - the move the player chose to make
	 * @return - the game state: ILLEGAL_PLAYER_MOVE \ PLAYER_WON \ PLAYER_LOST \ TIE \ IN PROGRESS
	 * @throws UnknownIdException - thrown in case the game id does not exist
	 */
	public GameState updateMove(Integer gameId,GameMove playerMove) throws UnknownIdException
	{
		if(!this.games.containsKey(gameId))
			throw new UnknownIdException("Method updateMove() in GamesService.java get game id that does not exist",new Exception());
		return this.games.get(gameId).playOneRound(playerMove);
	}
	
	/**
	 * get board stste
	 * @param gameId - the id of the game that should be contacted
	 * @return - the game state returned by the corresponding game handler
	 * @throws UnknownIdException - thrown in case the game id does not exist
	 */
	public char[][] getBoardState(Integer gameId) throws UnknownIdException
	{
		if(!this.games.containsKey(gameId))
			throw new UnknownIdException("Method getBoardState() in GamesService.java get game id that does not exist",new Exception());
		return this.games.get(gameId).getBoardState();
	}
	
	/**
	 * the computer starts the game
	 * @param gameId - the id of the game that should be contacted
	 * @return - a matrix that represents the game board after the computer's move
	 * @throws UnknownIdException - thrown in case the game id does not exist
	 */
	public char[][] computerStartGame(Integer gameId) throws UnknownIdException
	{
		if(!this.games.containsKey(gameId))
			throw new UnknownIdException("Method computerStartGame() in GamesService.java get game id that does not exist",new Exception());
		return this.games.get(gameId).computerStartGame();
	}
	
	/**
	 * end a game even if it is not finished
	 * @param gameId - the id of the game that should be contacted
	 * @throws UnknownIdException - thrown in case the game id does not exist
	 */
	synchronized public void endGame(Integer gameId) throws UnknownIdException
	{
		if(!this.games.containsKey(gameId))
			throw new UnknownIdException("Method endGame() in GamesService.java get game id that does not exist", new Exception());
		else
			this.games.remove(gameId);
	}
}

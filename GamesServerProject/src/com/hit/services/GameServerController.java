package com.hit.services;

import com.hit.exception.UnknownIdException;
import com.hit.gameAlgo.GameBoard.GameMove;
import com.hit.gameAlgo.IGameAlgo.GameState;

/**
 * GameServerController create a separation layer between the GameService and the networking layer
 */
public class GameServerController {
	
	private GamesService gamesService;

	/**
	 * Constructor 
	 * @param capacity - the maximum number of games that could be played simultaneously
	 */
	public GameServerController(int capacity) 
	{
		super();
		this.gamesService=new GamesService(capacity);
	}
	
	/**
	 * creation of new game
	 * @param gameType - game that was chosen (Tic Tac Tow or Catch the Bunny)
	 * @param opponent - the desired opponent type (whether the computer should play smart or random)
	 * @return - the game ID if the game could be opened or -1 if the maximum number of simultaneous games was reached
	 */
	public int newGame(String gameType,String opponent)
	{
		return this.gamesService.newGame(gameType,opponent);
	}
	
	/**
	 * end a game even if it is not finished
	 * @param gameId - the id of the game that should be contacted
	 * @throws UnknownIdException - thrown in case the game id does not exist
	 */
	public void endGame(Integer gameId) throws UnknownIdException
	{
		this.gamesService.endGame(gameId);
	}
	
	/**
	 * execution of one round in the game
	 * @param gameId - the game id
	 * @param playerMove - the move the player chose to make
	 * @return - the game state: ILLEGAL_PLAYER_MOVE \ PLAYER_WON \ PLAYER_LOST \ TIE \ IN PROGRESS
	 * @throws UnknownIdException - thrown in case the game id does not exist
	 */
	public GameState updateMove(Integer gameId, GameMove playerMove) throws UnknownIdException
	{
		return this.gamesService.updateMove(gameId, playerMove);
	}
	
	/**
	 * the computer starts the game
	 * @param gameId - the id of the game that should be contacted
	 * @return - a matrix that represents the game board after the computer's move
	 * @throws UnknownIdException - thrown in case the game id does not exist
	 */
	public char[][] computerStartGame(Integer gameId) throws UnknownIdException
	{
		return this.gamesService.computerStartGame(gameId);
	}
	
	/**
	 * get board states
	 * @param gameId - the id of the game that should be contacted
	 * @return - the game state returned by the corresponding game handler
	 * @throws UnknownIdException - thrown in case the game id does not exist
	 */
	public char[][] getBoardState(Integer gameId) throws UnknownIdException 
	{
		return this.gamesService.getBoardState(gameId);
	}
}

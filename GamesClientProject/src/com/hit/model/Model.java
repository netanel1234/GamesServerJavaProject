package com.hit.model;

public interface Model {
	
	//Sends the server a "new-game" request
	void newGame(String gameType,String opponentType);
	
	//Sends the server an "update-move" message
	void updatePlayerMove(int row,int col);
	
}

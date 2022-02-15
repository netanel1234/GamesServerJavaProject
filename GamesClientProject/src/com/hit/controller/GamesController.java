package com.hit.controller;

import java.beans.PropertyChangeEvent;

import com.hit.model.Model;
import com.hit.view.View;

/**
 * GamesController gets the Model and the View and connect between them
 */
public class GamesController implements Controller {
	
	private Model model;
	private View view;
	
	
	public GamesController(Model model,View view)
	{
		this.model=model;
		this.view=view;
	}
	
	/**
	 * propertyChange in interface java.beans.PropertyChangeListener
	 */
	@Override
	public void propertyChange(PropertyChangeEvent event) 
	{
		if(event.getPropertyName().equals("newGameModel"))
			this.view.updateViewNewGame((Character[])event.getNewValue());
		else if(event.getPropertyName().equals("updatePlayerMoveModel"))
			this.view.updateViewGameMove((Integer)event.getOldValue(),(Character[])event.getNewValue());
		else if(event.getPropertyName().equals("newGameView"))
		{
			if((int)event.getNewValue()==1)
				this.model.newGame("Tic Tac Tow","Smart");
			else if((int)event.getNewValue()==2)
				this.model.newGame("Tic Tac Tow","Random");
			else if((int)event.getNewValue()==3)
				this.model.newGame("Catch The Bunny","Smart");			
			else if((int)event.getNewValue()==4)
				this.model.newGame("Catch The Bunny","Random");
		}
		else if(event.getPropertyName().equals("updatePlayerMoveView"))
			this.model.updatePlayerMove((int)event.getOldValue(),(int)event.getNewValue());
		else if(event.getPropertyName().equals("updatePlayerMoveModel"))
			this.view.updateViewGameMove((Integer)event.getOldValue(),(Character[])event.getNewValue());
	}

}

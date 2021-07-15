package com.hit.controller;

import java.beans.PropertyChangeEvent;

import com.hit.model.Model;
import com.hit.view.View;

public class GamesController implements Controller {
	
	Model model;
	View view;
	
	public GamesController(Model model,View view)
	{
		System.out.println("constractor of GamesController");
		this.model=model;
		this.view=view;
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent event) 
	{
		System.out.println("propertyChange in GamesController");
		if(event.getPropertyName().equals("newGameModel"))
		{
			System.out.println("int propertyChange event newGameModel");
			this.view.updateViewNewGame((Character[])event.getNewValue());
		}
		else if(event.getPropertyName().equals("updatePlayerMoveModel"))
		{
			this.view.updateViewGameMove((Integer)event.getOldValue(),(Character[])event.getNewValue());
		}
		else if(event.getPropertyName().equals("newGameView"))
		{
			System.out.println("in propertyChange the newGameView selected");
			String gameType=null;
			String opponentType=null;
			if((int)event.getNewValue()==1)
			{
				gameType="Tic Tac Tow";
				opponentType="Smart";
			}
			else if((int)event.getNewValue()==2)
			{
				gameType="Tic Tac Tow";
				opponentType="Random";
			}
			else if((int)event.getNewValue()==3)
			{
				gameType="Catch The Bunny";
				opponentType="Smart";
			}
			else if((int)event.getNewValue()==4)
			{
				gameType="Catch The Bunny";
				opponentType="Random";
			}
			this.model.newGame(gameType,opponentType);
		}
		else if(event.getPropertyName().equals("updatePlayerMoveView"))
		{
			
		}
	}

}

package com.hit.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * GamesView contains the graphic interface of the system and creates it when the system is running 
 */
public class GamesView extends JPanel implements View, ActionListener, ItemListener {
	
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JPanel opponentPanel, typeGamePanel, gameBoardPanel, mainPanel, boardPanel;
	private JButton newGameButton;
	private JButton[] boardButtons;
	private JLabel opponentLabel, gameLabel;
	private JRadioButton smartButton, randomButton, ticTacTowButton, catcTheBunnyButton;
	private boolean smartChoosen, randomChoosen, tttChoosen, ctbChoosen;
	private Icon xIcon, circleIcon, boyIcon, bunnyIcon;
	private PropertyChangeSupport propertyChangeHandler;
	
	public GamesView()
	{		
		xIcon=new ImageIcon("icons\\x.png");
		circleIcon=new ImageIcon("icons\\circle.png");
		boyIcon=new ImageIcon("icons\\boy.png");
		bunnyIcon=new ImageIcon("icons\\bunny.png");
		
		smartChoosen=true;
		randomChoosen=false;
		tttChoosen=true;
		ctbChoosen=false;
		
		propertyChangeHandler=new PropertyChangeSupport(this);
		
		frame=new JFrame("Games Project");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.Y_AXIS));
		
		mainPanel=new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.X_AXIS));
				
		opponentPanel=new JPanel();
		opponentPanel.setLayout(new BoxLayout(opponentPanel, BoxLayout.Y_AXIS));
		
		opponentLabel=new JLabel("Choose type of opponent:");
		opponentPanel.add(opponentLabel);
		
		smartButton=new JRadioButton();
		smartButton.setText("Smart");
		smartButton.setSelected(true);
		smartButton.addItemListener(this);
		opponentPanel.add(smartButton);
		
		randomButton=new JRadioButton();
		randomButton.setText("Random");
		randomButton.addItemListener(this);
		opponentPanel.add(randomButton);
		
		typeGamePanel=new JPanel();
		typeGamePanel.setLayout(new BoxLayout(typeGamePanel, BoxLayout.Y_AXIS));
		
		gameLabel=new JLabel("Choose game:");
		typeGamePanel.add(gameLabel);
		
		ticTacTowButton=new JRadioButton();
		ticTacTowButton.setText("Tic Tac Tow");
		ticTacTowButton.setSelected(true);
		ticTacTowButton.addItemListener(this);
		typeGamePanel.add(ticTacTowButton);
		
		catcTheBunnyButton=new JRadioButton();
		catcTheBunnyButton.setText("catc The Bunny");
		catcTheBunnyButton.addItemListener(this);
		typeGamePanel.add(catcTheBunnyButton);
		
		newGameButton=new JButton("New Game");
		newGameButton.setActionCommand("newGame");
		newGameButton.addActionListener(this);
		
		mainPanel.add(typeGamePanel);
		mainPanel.add(opponentPanel);
		mainPanel.add(newGameButton);
		
		add(mainPanel);
						
		setOpaque(true);
		frame.setContentPane(this);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener)
	{
		propertyChangeHandler.addPropertyChangeListener(propertyChangeListener);
	}
	
	/**
	 * start in interface View
	 */
	@Override
	public void start() 
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				frame.pack();
				frame.setVisible(true);
			}
		});
	}
	
	/**
	 * updateViewNewGame in interface View
	 * @param board - the initial game board
	 */
	@Override
	public void updateViewNewGame(Character[] board) 
	{	
		boardPanel=new JPanel();
		int row=board.length;
		if(row==9)
		{
			boardButtons=new JButton[9];
			row=3;
			boardPanel.setLayout(new GridLayout(row,row));
			row=row*row;
			for(int i=0;i<row;i++)
			{
				JButton button=null;
				if(board[i]=='b')
					button=new JButton("");
		
				button.setPreferredSize(new Dimension(50,50));
				button.setActionCommand("tttupdateGameMoveView "+i);
				button.addActionListener(this);
				boardButtons[i]=button;
				boardPanel.add(button);
			}
		}
		else
		{
			boardButtons=new JButton[81];
			row=9;
			boardPanel.setLayout(new GridLayout(row,row));
			row=row*row;
			for(int i=0;i<row;i++)
			{
				JButton button=null;
				if(board[i]=='b')
					button=new JButton();
				else if(board[i]=='p')
					button=new JButton(boyIcon);
				else if(board[i]=='c')
					button=new JButton(bunnyIcon);
				
				button.setPreferredSize(new Dimension(50,50));
				button.setActionCommand("ctbupdateGameMoveView "+i);
				button.addActionListener(this);
				boardButtons[i]=button;
				boardPanel.add(button);
			}
		}
		add(boardPanel);
	}
	
	/**
	 * updateViewGameMove in interface View
	 * Updates the game board with the last move
	 * @param gameState - the state of the game after the last round
	 * @param board - the board state after the last round
	 */
	@Override
	public void updateViewGameMove(int gameState, Character[] board) 
	{
		for(int i=0;i<9;i++)
		{
			if(board[i]=='p')
				boardButtons[i].setText("X");
			else if(board[i]=='c')
				boardButtons[i].setText("O");
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) 
	{
		if("newGame".equals(event.getActionCommand()))
		{
			if(smartChoosen==true && randomChoosen==false && tttChoosen==true && ctbChoosen==false)
				propertyChangeHandler.firePropertyChange("newGameView",0,1);
			else if(smartChoosen==false && randomChoosen==true && tttChoosen==true && ctbChoosen==false)
				propertyChangeHandler.firePropertyChange("newGameView",0,2);
			else if(smartChoosen==true && randomChoosen==false && tttChoosen==false && ctbChoosen==true)
				propertyChangeHandler.firePropertyChange("newGameView",0,3);
			else if(smartChoosen==false && randomChoosen==true && tttChoosen==false && ctbChoosen==true)
				propertyChangeHandler.firePropertyChange("newGameView",0,4);
		}
		else if(event.getActionCommand().contains("ctbupdateGameMoveView"))
		{			
			String[] arr=event.getActionCommand().split(" ");
			int buttonNum=Integer.parseInt(arr[1]);
			int row=buttonNum/9;
			int col=buttonNum%9;
			propertyChangeHandler.firePropertyChange("updatePlayerMoveView",row,col);
		}
		else if(event.getActionCommand().contains("tttupdateGameMoveView"))
		{			
			String[] arr=event.getActionCommand().split(" ");
			int buttonNum=Integer.parseInt(arr[1]);
			int row=buttonNum/3;
			int col=buttonNum%3;
			if(row==col)
				row=-1;
			propertyChangeHandler.firePropertyChange("updatePlayerMoveView",row,col);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent event) 
	{
		Object source=event.getItemSelectable();
		if(source==smartButton)
		{
			if(smartChoosen==true)
				smartChoosen=false;
			else
				smartChoosen=true;
		}
		else if(source==randomButton)
		{
			if(randomChoosen==true)
				randomChoosen=false;
			else
				randomChoosen=true;
		}
		else if(source==ticTacTowButton)
		{
			if(tttChoosen==true)
				tttChoosen=false;
			else
				tttChoosen=true;
		}
		else if(source==catcTheBunnyButton)
		{
			if(ctbChoosen==true)
				ctbChoosen=false;
			else
				ctbChoosen=true;
		}
	}
	
}

package com.hit.server;

import com.hit.util.CLI;

/**
 * This class is actually the part that connects all the system components to us and also activates them
 */
public class GameServerDriver {
	
	public static void main(String[] args) 
	{
		CLI cli=new CLI(System.in,System.out);
		Server server=new Server(34567);
		cli.addPropertyChangeListener(server);
		new Thread(cli).start();
	}
}

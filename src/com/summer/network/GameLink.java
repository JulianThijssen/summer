package com.summer.network;

import com.summer.GameState;
import com.summer.log.Log;

public class GameLink extends Thread {
	private Server    server = null;
	private GameState state  = null;
	
	public GameLink(Server server, GameState state) {
		this.server = server;
		this.state = state;
	}
	
	public void run() {
		while(state.inGame) {
			Packet playerinfo = state.getPlayerInfo();
			server.sendAll(playerinfo);
			
			try {
				Thread.sleep(30);
			} catch(InterruptedException e) {
				Log.error("Game link got interrupted");
				return;
			}
		}
	}
}

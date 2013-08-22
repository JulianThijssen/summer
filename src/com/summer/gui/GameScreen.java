package com.summer.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.summer.Application;
import com.summer.network.Client;
import com.summer.network.Packet;
import com.summer.network.Server;

public class GameScreen extends Screen {

	public GameScreen(Application app) {
		super(app);
	}
	
	@Override
	public void loadComponents() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void update(GameContainer gc, int delta) {
		app.state.update(gc, delta);
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		app.state.render(gc, g);
	}

	@Override
	public void onEnter() {
		
	}

	@Override
	public void onExit() {
		
	}
	
	@Override
	public void keyPressed(int key, char c) {
		Server server = app.getServer();
		Client client = app.getClient();
		if(server.started) {
			app.getGameState().keyPressed(0, key);
		}
		else if(client.isConnected()) {
			client.send(new Packet("KeyPressed", key));
		}
	}
	
	@Override
	public void keyReleased(int key, char c) {
		Server server = app.getServer();
		Client client = app.getClient();
		if(server.started) {
			app.getGameState().keyReleased(0, key);
		}
		else if(client.isConnected()) {
			client.send(new Packet("KeyReleased", key));
		}
	}
}

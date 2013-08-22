package com.summer;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.summer.entities.Player;
import com.summer.gui.GameScreen;
import com.summer.log.Log;
import com.summer.network.Packet;
import com.summer.util.NameGenerator;

public class GameState {
	private Application application = null;
	
	//TODO Make scalable
	private List<Player> players = new ArrayList<Player>();
	public  boolean  inGame  = false;
	
	public GameState(Application application) {
		this.application = application;
		String name = NameGenerator.getName();
		Log.debug(name);
		NameGenerator.generateID(name);
		addPlayer(0, NameGenerator.getName());
	}
	
	//State
	public void startGame() {
		inGame = true;
		application.setScreen(new GameScreen(application));
	}
	
	public Packet getGameState() {
		Packet state = new Packet("GameState");
		return state;
	}
	
	public Packet getPlayerInfo() {
		Packet playerinfo = new Packet("PlayerInfo", players.size());
		for(Player player: players) {
			playerinfo.add(player.getID(), player.getName(), player.getX(), player.getY());
		}
		return playerinfo;
	}
	
	public void update(GameContainer gc, int delta) {
		for(Player player: players) {
			player.update(gc, delta);
		}
	}
	
	public void render(GameContainer gc, Graphics g) {
		for(Player player: players) {
			player.render(gc, g);
		}
	}
	
	//Players
	public List<Player> getPlayers() {
		return players;
	}
	
	public void addPlayer(int id, String name) {
		Player player = new Player(id, name);
		players.add(player);
	}
	
	public Player getPlayer(int id) {
		for(Player player: players) {
			if(player.getID() == id) {
				return player;
			}
		}
		return null;
	}
	
	public void removePlayer(int id) {
		Player player = getPlayer(id);
		if(player != null) {
			players.remove(player);
		}
	}
	
	public void movePlayer(int id, int x, int y) {
		Player player = getPlayer(id);
		if(player != null) {
			player.setPosition(x, y);
		}
	}
	
	public void keyPressed(int id, int key) {
		Player player = getPlayer(id);
		if(player != null) {
			player.keyPressed(key);
		}
	}
	
	public void keyReleased(int id, int key) {
		Player player = getPlayer(id);
		if(player != null) {
			player.keyReleased(key);
		}
	}
}

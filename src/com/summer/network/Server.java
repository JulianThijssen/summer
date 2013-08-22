package com.summer.network;

import com.summer.Application;
import com.summer.GameState;
import com.summer.entities.Player;
import com.summer.exception.ConnectionException;
import com.summer.util.NameGenerator;

public class Server extends Thread {
	private Application  	   application = null;
	private ConnectionListener cl          = null;
	private GameLink           gl          = null;
	private Connection[]       clients     = new Connection[3];
	public  boolean            started     = false;
	
	public Server(Application application) {
		this.application = application;
	}
	
	public void startServer(int port) throws ConnectionException {
		cl = new ConnectionListener(this);
		try {
			cl.open(port);
			cl.startListening();
		} catch(ConnectionException ce) {
			throw ce;
		}
		started = true;
		start();
	}
	
	public void stopServer() {
		cl.close();
		cl = null;
		started = false;
	}
	
	public void run() {
		while(started) {
			for(int i = 0; i < clients.length; i++) {
				if(clients[i] != null) {
					Packet packet = clients[i].read();
					handlePacket(i, packet);
				}
			}
		}
	}
	
	public void sendAll(Packet packet) {
		for(int i = 0; i < clients.length; i++) {
			if(clients[i] != null) {
				clients[i].send(packet);
			}
		}
	}
	
	public void startGame() {
		cl.stopListening();
		
		GameState state = application.getGameState();
		state.addPlayer(0, NameGenerator.getName());
		for(int id = 1; id <= clients.length; id++) {
			if(clients[id-1] != null) {
				application.state.addPlayer(id, NameGenerator.getName());
			}
		}
		
		Packet gameStarted = new Packet("GameStarted", state.getPlayers().size());
		
		for(Player player: state.getPlayers()) {
			gameStarted.add(player.getID(), player.getName());
		}
		sendAll(gameStarted);
		
		application.state.startGame();
		
		gl = new GameLink(this, application.state);
		gl.start();
	}

	public void addClient(Connection client) {
		for(int i = 0; i < clients.length; i++) {
			if(clients[i] == null) {
				clients[i] = client;
				return;
			}
		}
	}
	
	public boolean isFull() {
		boolean full = true;
		for(int id = 0; id < clients.length; id++) {
			if(clients[id] == null) {
				full = false;
			}
		}
		return full;
	}
	
	public void handlePacket(int id, Packet packet) {
		if(packet == null) {
			return;
		}
		if(packet.type.equals("JoinRequest")) {
			if(!isFull()) {
				clients[id].send(new Packet("JoinRequestAccepted"));
			} else {
				clients[id].send(new Packet("JoinRequestDenied"));
			}
		}
		if(packet.type.equals("KeyPressed")) {
			int key = packet.getInt(0);
			application.state.keyPressed(id + 1, key);
		}
		if(packet.type.equals("KeyReleased")) {
			int key = packet.getInt(0);
			application.state.keyReleased(id + 1, key);
		}
	}
}

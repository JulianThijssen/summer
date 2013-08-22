package com.summer.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.summer.Application;
import com.summer.exception.ConnectionException;
import com.summer.log.Log;

public class Client extends Thread {
	private Application      application = null;
	private Socket           connection  = null;
	private BufferedReader   in          = null;
	private PrintWriter      out         = null;
	private boolean          listening   = false;
	
	public Client(Application application) {
		this.application = application;
	}
	
	public int joinRoom(String host, int port) {
		try {
			connection = new Socket(host, port);
			connection.setTcpNoDelay(true);
			Log.info("Successfully established socket connection with: " + host);
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			Log.info("Successfully opened input stream to: " + host);
			out = new PrintWriter(connection.getOutputStream());
			Log.info("Successfully opened output stream to: " + host);
		} catch(IOException ie) {
			Log.error("Failed to connect to host: " + host + " on port: " + port);
			return 0;
		}
		return 1;
	}
	
	public void startListening() {
		listening = true;
		start();
	}
	
	public void stopListening() {
		listening = false;
	}

	public void send(Packet packet){
		if(isConnected()) {
			out.println(packet.toString());
			out.flush();
			Log.debug("Client sent: " + packet.toString());
		}
	}
	
	public void run() {
		while(listening) {
			try {
				read();
			} catch(ConnectionException ce) {
				//FIXME I don't want to have to catch here
			}
 		}
	}
	
	public Packet read() throws ConnectionException {
		try {
			String data = in.readLine();
			//Log.debug("Client received: " + data);
			Packet packet = new Packet(data);
			handlePacket(packet);
			return packet;
		} catch(IOException e) {
			//TODO message
			purge();
			throw new ConnectionException("Connection to the host was stopped unexpectedly");
		}
	}
	
	public void handlePacket(Packet packet) {
		if(packet.type.equals("UserJoined")) {
			int id = packet.getInt(0);
			String name = packet.getString(1);
			application.state.addPlayer(id, name);
		}
		else if(packet.type.equals("UserLeft")) {
			int id = packet.getInt(0);
			application.state.removePlayer(id);
		}
		else if(packet.type.equals("GameStarted")) {
			int numPlayers = packet.getInt(0);
			for(int i = 0; i < numPlayers; i++) {
				int id = packet.getInt(i * 2 + 1);
				String name = packet.getString(i * 2 + 2);
				application.state.addPlayer(id, name);
			}
			application.state.startGame();
		}
		else if(packet.type.equals("GameState")) {
			int numPlayers = packet.getInt(0);
			for(int i = 0; i < numPlayers; i++) {
				int id = packet.getInt(i * 3 + 1);
				int x = packet.getInt(i * 3 + 2);
				int y = packet.getInt(i * 3 + 3);
				application.state.movePlayer(id, x, y);
			}
		}
	}
	
	public boolean isConnected() {
		if(connection != null) {
			return connection.isConnected();
		}
		return false;
	}
	
	public void disconnect() {
		if(connection != null) {
			purge();
		}
	}
	
	public void purge() {
		try {
			if(out != null) {out.close();}
			if(in != null) {in.close();}
			if(connection != null) {connection.close();}
			out = null;
			in = null;
			connection = null;
		} catch(IOException e) {
			Log.error("Failed to close connection to host");
		}
		Log.info("Successfully closed connection to host");
	}
}

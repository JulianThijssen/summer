package com.summer.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.summer.exception.ConnectionException;
import com.summer.log.Log;

public class ConnectionListener extends Thread {
	private Server       server    = null;
	private ServerSocket socket    = null;
	private boolean      listening = false;
	
	public ConnectionListener(Server server) {
		this.server = server;
	}

	public void open(int port) throws ConnectionException {
		try {
			socket = new ServerSocket(port);
		} catch(IOException e) {
			Log.error("Failed to open server on port: " + port + ". Has the port been forwarded?");
			throw new ConnectionException("Failed to open server on port: " + port + ". Has the port been forwarded?");
		}
		Log.info("Successfully opened server on port: " + port);
	}
	
	public void close() {
		try {
			if(socket != null) {
				socket.close();
			}
		} catch(IOException e) {
			Log.error("Failed to close server");
		}
		socket = null;
		Log.info("Successfully stopped server");
	}
	
	public void startListening() {
		listening = true;
		start();
	}
	
	public void stopListening() {
		listening = false;
	}
	
	public void run() {
		while(listening) {
			listen();
		}
	}
	
	public void listen() {
		try {
			Socket client = socket.accept();
			client.setTcpNoDelay(true);
			Connection connection = new Connection();
			connection.setSocket(client);
			server.addClient(connection);
		} catch(ConnectionException ce) {
			Log.error("Failed to set socket on connection");	
		} catch(IOException ie) {
			Log.error("Client failed to connect to the server");
		}
	}
}

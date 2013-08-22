package com.summer.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.summer.exception.ConnectionException;
import com.summer.log.Log;

public class Connection {
	private Socket socket      = null;
	private BufferedReader in  = null;
	private PrintWriter    out = null;
	
	public void setSocket(Socket socket) throws ConnectionException {
		this.socket = socket;
		
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch(IOException ie) {
			Log.error("Failed to get input stream from connection");
			throw new ConnectionException("Failed to get input stream from socket");
		}
		try {
			out = new PrintWriter(socket.getOutputStream());
		} catch(IOException ie) {
			Log.error("Failed to get output stream from connection");
			throw new ConnectionException("Failed to get output stream from socket");
		}
	}
	
	public Packet read() {
		try {
			if(!in.ready()) {
				return null;
			}
			String data = in.readLine();
			//Log.debug("Server received: " + data);
			return new Packet(data);
		} catch(IOException e) {
			//TODO message
			return null;
		}
	}
	
	public void send(Packet packet) {
		//Log.debug("Server sent: " + packet.toString());
		out.println(packet.toString());
		out.flush();
	}
}

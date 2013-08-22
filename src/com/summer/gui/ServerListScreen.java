package com.summer.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.summer.Application;
import com.summer.event.Event;
import com.summer.event.EventListener;
import com.summer.exception.ConnectionException;
import com.summer.log.Log;
import com.summer.network.Client;
import com.summer.network.Packet;
import com.summer.network.Server;

public class ServerListScreen extends Screen {

	public ServerListScreen(Application app) {
		super(app);
	}

	@Override
	public void loadComponents() {
		try {
			Image joinButtonImage = new Image("res/JoinButton.png");
			Image connectButtonImage = new Image("res/ConnectButton.png");
			Image hostButtonImage = new Image("res/HostButton.png");
			
			//TODO Load all the available servers in here
			//SERVERLIST
			Button joinButton = ComponentFactory.createButton(80, 200, joinButtonImage);
			joinButton.addEventListener(new EventListener() {
				public void onChange(Event event) {
					/*Client client = app.getClient();
					//FIXME server IP from list
					try {
						client.connect("SERVERIP", 4444);
						app.setScreen(new LobbyScreen(app));
					} catch(ConnectionException ce) {
						//TODO Show failed connection message
					}*/
				}
			});
			
			Button connectButton = ComponentFactory.createButton(240, 200, connectButtonImage);
			connectButton.addEventListener(new EventListener() {
				public void onChange(Event event) {
					Client client = app.getClient();
	
					try {
						client.joinRoom("83.117.238.42", 4444);
						//FIXME joinRoom returns a value
						Packet packet = new Packet("JoinRequest");
						client.send(packet);
						//TODO Waiting for response message
						packet = client.read();
						if(packet.type.equals("JoinRequestAccepted")) {
							client.startListening();
							app.setScreen(new LobbyScreen(app));
						} else {
							client.purge();
							//TODO Denied connection message
						}
					} catch(ConnectionException ce) {
						Log.error(ce.getMessage());
						//TODO Show failed connection message
					}
				}
			});
		
			Button hostButton = ComponentFactory.createButton(400, 200, hostButtonImage);
			hostButton.addEventListener(new EventListener() {
				public void onChange(Event event) {
					Server server = app.getServer();
					try {
						server.startServer(4444);
						app.setScreen(new LobbyScreen(app));
					} catch(ConnectionException ce) {
						//TODO show message ce
					}
				}
			});
			
			children.add(connectButton);
			children.add(joinButton);
			children.add(hostButton);
		} catch(SlickException e) {
			Log.debug("Could not find one or more image resources");
		}
	}

	@Override
	public void update(GameContainer gc, int delta) {

	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		for(Component c: children) {
			c.render(gc, g);
		}
	}

	@Override
	public void onEnter() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onExit() {
		// TODO Auto-generated method stub
	}
}

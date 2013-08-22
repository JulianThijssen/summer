package com.summer.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.summer.Application;
import com.summer.event.Event;
import com.summer.event.EventListener;
import com.summer.log.Log;
import com.summer.network.Server;

public class LobbyScreen extends Screen {

	public LobbyScreen(Application app) {
		super(app);
	}

	@Override
	public void loadComponents() {
		try {
			Image startButtonImage = new Image("res/StartButton.png");
			Image slotImage = new Image("res/Slot.png");
			
			Slot playerSlot1 = ComponentFactory.createSlot(50, 50, slotImage);
			playerSlot1.addEventListener(new EventListener() {
				public void onChange(Event event) {
					
				}
			});
			
			Slot playerSlot2 = ComponentFactory.createSlot(200, 50, slotImage);
			playerSlot2.addEventListener(new EventListener() {
				public void onChange(Event event) {
					
				}
			});
			
			Slot playerSlot3 = ComponentFactory.createSlot(350, 50, slotImage);
			playerSlot3.addEventListener(new EventListener() {
				public void onChange(Event event) {
					
				}
			});
			
			Slot playerSlot4 = ComponentFactory.createSlot(500, 50, slotImage);
			playerSlot4.addEventListener(new EventListener() {
				public void onChange(Event event) {
					
				}
			});
			
			Button startButton = ComponentFactory.createButton(240, 380, startButtonImage);
			startButton.addEventListener(new EventListener() {
				public void onChange(Event event) {
					Server server = app.getServer();
					if(server.started) {
						server.startGame();
					}
				}
			});
			
			children.add(playerSlot1);
			children.add(playerSlot2);
			children.add(playerSlot3);
			children.add(playerSlot4);
			children.add(startButton);
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
		
	}

	@Override
	public void onExit() {

	}
}

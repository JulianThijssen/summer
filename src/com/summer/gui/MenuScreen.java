package com.summer.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.summer.Application;
import com.summer.event.Event;
import com.summer.event.EventListener;
import com.summer.log.Log;

public class MenuScreen extends Screen {
	public MenuScreen(Application app) {
		super(app);
	}
	
	@Override
	public void loadComponents() {
		try {
			Image startButtonImage = new Image("res/StartButton.png");
			Image optionsButtonImage = new Image("res/OptionsButton.png");
			Image quitButtonImage = new Image("res/QuitButton.png");
		
			Button startButton = ComponentFactory.createButton(250, 50, startButtonImage);
			startButton.addEventListener(new EventListener() {
				public void onChange(Event event) {
					app.setScreen(new ServerListScreen(app));
				}
			});

			Button optionButton = ComponentFactory.createButton(250, 150, optionsButtonImage);
			optionButton.addEventListener(new EventListener() {
				public void onChange(Event event) {
					app.setScreen(new OptionScreen(app));
				}
			});
			
			Button quitButton = ComponentFactory.createButton(250, 250, quitButtonImage);
			quitButton.addEventListener(new EventListener() {
				public void onChange(Event event) {
					app.requestClose();
				}
			});
			
			children.add(startButton);
			children.add(optionButton);
			children.add(quitButton);
		} catch(SlickException e) {
			Log.debug("Could not find StartButton.png");
		}
	}
	
	@Override
	public void update(GameContainer gc, int delta) {

	}
	
	@Override
	public void render(GameContainer gc, Graphics g) {
		g.setBackground(new Color(22, 22, 22));
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

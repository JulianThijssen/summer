package com.summer;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.summer.gui.MenuScreen;
import com.summer.gui.Screen;
import com.summer.network.Client;
import com.summer.network.Server;

public class Application extends BasicGame {
	private GameContainer gc             = null;
	private Screen        screen         = null;
	public  GameState     state          = new GameState(this);
	private Server        server         = new Server(this);
	private Client        client         = new Client(this);
	private boolean       closeRequested = false;
	
	public Application(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		this.gc = gc;
		gc.setAlwaysRender(true);
		setScreen(new MenuScreen(this));
	}

	public void setScreen(Screen screen) {
		if(this.screen != null) {
			this.screen.onExit();
		}
		this.screen = screen;
		this.screen.loadComponents();
		this.screen.onEnter();
		gc.getInput().removeAllListeners();
		gc.getInput().addKeyListener(this.screen);
		gc.getInput().addMouseListener(this.screen);
	}
	
	public Server getServer() {
		return server;
	}
	
	public Client getClient() {
		return client;
	}
	
	public GameState getGameState() {
		return state;
	}
	
	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		if(closeRequested) {
			gc.exit();
		}
		screen.update(gc, i);
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		screen.render(gc, g);
	}
	
	public void requestClose() {
		closeRequested = true;
	}

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new Application("Disguise"));
			appgc.setDisplayMode(640, 480, false);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}

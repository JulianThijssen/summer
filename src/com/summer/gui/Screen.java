package com.summer.gui;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.MouseListener;

import com.summer.Application;
import com.summer.Drawable;
import com.summer.event.Event;
import com.summer.network.Client;
import com.summer.network.Packet;
import com.summer.network.Server;

public abstract class Screen implements Drawable, KeyListener, MouseListener {
	protected Application app = null;
	protected ArrayList<Component> children = new ArrayList<Component>();
	
	public Screen(Application app) {
		this.app = app;
	}
	
	public abstract void loadComponents();
	
	public abstract void update(GameContainer gc, int delta);
	
	@Override
	public abstract void render(GameContainer gc, Graphics g);
	
	public abstract void onEnter();
	
	public abstract void onExit();
	
	@Override
	public void keyPressed(int key, char c) {
		
	}

	@Override
	public void keyReleased(int key, char c) {

	}

	@Override
	public void inputEnded() {

	}

	@Override
	public void inputStarted() {

	}

	@Override
	public boolean isAcceptingInput() {
		return true;
	}

	@Override
	public void setInput(Input input) {

	}

	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		if(button == Input.MOUSE_LEFT_BUTTON) {
			for(Component c: children) {
				if(x > c.position.x && x < c.position.x + c.size.x &&
				   y > c.position.y && y < c.position.y + c.size.y) {
					c.fireEvent(new Event());
				}
			}
		}
	}

	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
		
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		for(Component c: children) {
			if(newx > c.position.x && newx < c.position.x + c.size.x &&
			   newy > c.position.y && newy < c.position.y + c.size.y) {
				c.setSelected(true);
			} else {
				c.setSelected(false);
			}
		}
	}

	@Override
	public void mousePressed(int button, int x, int y) {

	}

	@Override
	public void mouseReleased(int button, int x, int y) {

	}

	@Override
	public void mouseWheelMoved(int arg0) {

	}
}

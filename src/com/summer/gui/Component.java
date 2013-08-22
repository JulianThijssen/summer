package com.summer.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

import com.summer.Drawable;
import com.summer.event.Event;
import com.summer.event.EventListener;

public abstract class Component implements Drawable {
	protected Vector2f    position;
	protected Vector2f    size;
	protected boolean     selected = false;
	private EventListener eventListener;
	
	public void setPosition(int x, int y) {
		position = new Vector2f(x, y);
	}
	
	public void setSize(int width, int height) {
		size = new Vector2f(width, height);
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public void addEventListener(EventListener eventListener) {
		this.eventListener = eventListener;
	}
	
	public void fireEvent(Event event) {
		eventListener.onChange(event);
	}
	
	@Override
	public abstract void render(GameContainer gc, Graphics g);
}

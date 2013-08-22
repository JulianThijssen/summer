package com.summer.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

public class Player {
	private static final float SPEED = 0.05f;
	private int         id       = -1;
	private String   	name     = "No Name";
	private PlayerState mode     = PlayerState.HUNTER;
	private Vector2f    position = new Vector2f(100, 100);
	private Vector2f    velocity = new Vector2f(0, 0);
	private boolean     up = false, left = false, down = false, right = false;
	
	
	public Player(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public void update(GameContainer gc, int delta) {
		if(up)    {velocity.y = -SPEED;}
		if(left)  {velocity.x = -SPEED;}
		if(down)  {velocity.y = SPEED;}
		if(right) {velocity.x = SPEED;}
		if(!up && !down) {velocity.y = 0;}
		if(!left && !right) {velocity.x = 0;}
		position.x += velocity.x;
		position.y += velocity.y;
	}
	
	public void render(GameContainer gc, Graphics g) {
		g.setColor(Color.orange);
		g.fillRect(position.x, position.y, 32, 32);
	}
	
	public int getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public PlayerState getMode() {
		return mode;
	}
	
	public Vector2f getPosition() {
		return position;
	}
	
	public int getX() {
		return (int) position.x;
	}
	
	public int getY() {
		return (int) position.y;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setMode(PlayerState mode) {
		this.mode = mode;
	}
	
	public void setPosition(int x, int y) {
		position.x = x;
		position.y = y;
	}
	
	public void keyPressed(int key) {
		if(key == Input.KEY_W) {
			up = true;
		}
		if(key == Input.KEY_A) {
			left = true;
		}
		if(key == Input.KEY_S) {
			down = true;
		}
		if(key == Input.KEY_D) {
			right = true;
		}
	}
	
	public void keyReleased(int key) {
		if(key == Input.KEY_W) {
			up = false;
		}
		if(key == Input.KEY_A) {
			left = false;
		}
		if(key == Input.KEY_S) {
			down = false;
		}
		if(key == Input.KEY_D) {
			right = false;
		}
	}
}

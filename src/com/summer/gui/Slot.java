package com.summer.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

import com.summer.entities.Player;

public class Slot extends Component {
	public static final int DEFAULT_WIDTH = 100;
	public static final int DEFAULT_HEIGHT = 300;
	private Player player = null;
	
	private Image image = null;
	
	public Slot(Image image) {
		this.image = image;
		position = new Vector2f(0, 0);
		size = new Vector2f(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) {
		g.setColor(Color.darkGray);
		g.drawImage(image, position.x, position.y);
		g.setColor(Color.white);
		if(player != null) {
			g.drawString(player.getName(), position.x, position.y);
		} else {
			g.drawString("No Player", position.x, position.y);
		}
	}
}

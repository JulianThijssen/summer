package com.summer.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

public class Button extends Component {
	public static final int DEFAULT_WIDTH = 64;
	public static final int DEFAULT_HEIGHT = 32;
	
	private Image image = null;
	
	public Button(Image image) {
		this.image = image;
		position = new Vector2f(0, 0);
		size = new Vector2f(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		g.setColor(Color.orange);
		if(selected) {
			g.setColor(Color.red);
		}
		
		g.drawImage(image, position.x, position.y);
	}
}

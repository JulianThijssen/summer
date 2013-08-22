package com.summer.gui;

import org.newdawn.slick.Image;

public class ComponentFactory {
	public static Slot createSlot(int x, int y, Image image) {
		Slot slot = new Slot(image);
		slot.setPosition(x, y);
		slot.setSize(image.getWidth(), image.getHeight());
		return slot;
	}
	
	public static Button createButton(int x, int y, Image image) {
		Button button = new Button(image);
		button.setPosition(x, y);
		button.setSize(image.getWidth(), image.getHeight());
		return button;
	}
}

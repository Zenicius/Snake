package com.law.snake.entities;

import java.awt.Color;
import java.awt.Graphics;

public class SnakeBody extends Entity{

	public SnakeBody(int x, int y, int scale) {
		super(x, y, scale);
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, scale, scale);
	}
}

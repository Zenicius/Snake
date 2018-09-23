package com.law.snake.entities;

import java.awt.Graphics;

public class Entity {

	protected int x, y;
	protected int scale;

	public Entity(int x, int y, int scale) {
		setPosition(x, y, scale);
	}
	
	public Entity() {
		
	}
	
	public void tick() {
		
	}
	
	public void draw(Graphics g) {
		
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getScale() {
		return scale;
	}
	
	public void setPosition(int x, int y, int scale){
		this.x = x;
		this.y = y;
		this.scale = scale;
	}

		
}

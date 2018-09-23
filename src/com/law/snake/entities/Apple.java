package com.law.snake.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import com.law.snake.Sound;

public class Apple extends Entity {
	
	public Apple() {
		setPosition(getRandom(), getRandom(), 10);
	}
	
	public int getRandom() {
		Random random = new Random();
		int value = random.nextInt((37) + 10) * 10;
		
		return value;
	}

	public void tick(Snake snake) {
		
		if(snake.getX() == this.x && snake.getY() == this.y) {
			Sound.apple.Play();
			snake.levelUp();
			
			boolean safe = false;
			do {
				int x = getRandom(), y = getRandom();
				for(int i = 0; i < snake.getBody().size(); i++) {
					if(snake.getBody().get(i).x != x && snake.getBody().get(i).y != y) {
						setPosition(x, y, 10);
						safe = true;
					}
				}
			}while(!safe);
		}
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, scale, scale);
	}
}

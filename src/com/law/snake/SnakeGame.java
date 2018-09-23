package com.law.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.law.snake.entities.Apple;
import com.law.snake.entities.Snake;
import com.law.snake.menu.GameOverMenu;
import com.law.snake.menu.MainMenu;
import com.law.snake.menu.Menu;

public class SnakeGame {

	public Snake snake;
	public int s_x = 50, s_y = 50, scale = 10;
	public Apple apple;

	private Menu menu;

	public SnakeGame() {
		snake = new Snake(s_x, s_y, scale);
		apple = new Apple();

		setMenu(new MainMenu());
	}

	public void restartGame() {
		snake = new Snake(s_x, s_y, scale);
		apple = new Apple();

		snake.score = 0;
	}

	public void tick(boolean[] keys) {

		// Input
		boolean up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		boolean down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		boolean left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		boolean right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		boolean action = keys[KeyEvent.VK_SPACE];

		// Menu
		if (menu != null) {
			keys[KeyEvent.VK_W] = keys[KeyEvent.VK_UP] = false;
			keys[KeyEvent.VK_S] = keys[KeyEvent.VK_DOWN] = false;
			if (action) {
				keys[KeyEvent.VK_SPACE] = false;
			}
			menu.tick(up, down, action, this);
		} else {
			// Game
	
			// GameOver
			if (snake.getX() > 490 || snake.getX() < 0 || snake.getY() < 0 || snake.getY() > 490) {
				Sound.gameOver.Play();
				setMenu(new GameOverMenu(snake.score));
			}
			for (int i = 0; i < snake.getBody().size(); i++) {
				if (snake.getX() == snake.getBody().get(i).getX() && snake.getY() == snake.getBody().get(i).getY()) {
					if (i != snake.getBody().size() - 1) {
						Sound.gameOver.Play();
						setMenu(new GameOverMenu(snake.score));
					}
				}
			}

			// Tick entities
			apple.tick(snake);
			snake.tick(up, down, left, right, action);
		}

	}

	public void draw(Graphics g, int WIDTH, int HEIGHT) {

		// Menu
		if (menu != null)
			menu.draw(g);
		
		else {
			
			//Black background
			g.clearRect(0, 0, WIDTH, HEIGHT);
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, WIDTH, HEIGHT);

			// Draw Snake Score
			snake.draw(g);
			// Draw Snake Body Parts
			for (int i = 0; i < snake.getBody().size(); i++) {
				snake.getBody().get(i).draw(g);
			}
			// Draw Apple
			apple.draw(g);
		}

	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

}

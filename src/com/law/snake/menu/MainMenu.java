package com.law.snake.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import com.law.snake.SnakeGame;
import com.law.snake.Sound;

public class MainMenu extends Menu {

	private String[] options = { "Play", "Highscores", "Help" };
	private int selected = 0;

	public void tick(boolean up, boolean down, boolean action, SnakeGame game) {

		if (down && selected >= 0 && selected < 2) {
			Sound.menu.Play();
			selected++;

		} else if (up && selected <= 2 && selected > 0) {
			Sound.menu.Play();
			selected--;

		} else if (action) {
			Sound.menuSelect.Play();
			
			if (selected == 0) {
				// Play
				game.setMenu(null);

			}else if(selected == 1) {
				//HighScores
				game.setMenu(new HighscoreMenu());
		
			}
			else if(selected == 2) {
				// Help
				game.setMenu(new HelpMenu());
			}
		}

	}
	public void draw(Graphics g) {

		g.clearRect(0, 0, 500, 500);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 500, 500);

		// Title
		Font font = new Font("Arial", Font.PLAIN, 60);
		g.setFont(font);
		g.setColor(Color.RED);
		g.drawString("Snake", 170, 80);

		// Play
		if (selected == 0)
			g.setColor(Color.RED);
		else
			g.setColor(Color.GRAY);
		font = new Font("Arial", Font.PLAIN, 35);
		g.setFont(font);
		g.drawString(options[0], 225, 190);

		// About
		if (selected == 1)
			g.setColor(Color.RED);
		else
			g.setColor(Color.GRAY);
		font = new Font("Arial", Font.PLAIN, 35);
		g.setFont(font);
		g.drawString(options[1], 175, 250);

		// Help
		if (selected == 2)
			g.setColor(Color.RED);
		else
			g.setColor(Color.GRAY);
		font = new Font("Arial", Font.PLAIN, 35);
		g.setFont(font);
		g.drawString(options[2], 225, 310);

		// Credits
		font = new Font("Arial", Font.PLAIN, 12);
		g.setFont(font);
		g.setColor(Color.GRAY);
		g.drawString("Made with love by: Law", 370, 490);
		g.drawString("Use WASD or Arrows and Space to select", 5, 490);

	}

}

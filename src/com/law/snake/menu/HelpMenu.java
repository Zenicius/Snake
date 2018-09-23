package com.law.snake.menu;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.law.snake.SnakeGame;
import com.law.snake.Sound;

public class HelpMenu extends Menu {
	
	private String[] help = {"Use WASD or arrows to Move,", "Use Space to select or action,",
							 "Game made for education puporses.", "May contain bugs!", "Made by Law."};
	private String[] options = {"Continue", "GitHub"};
	private int selected = 0;
	
	
	public void tick(boolean up, boolean down, boolean action, SnakeGame game) {
		if(down && selected == 0) {
			Sound.menu.Play();
			selected++;
		}
		else if(up && selected == 1) {
			Sound.menu.Play();
			selected--;
		}
		else if(action) {
			Sound.menuSelect.Play();
			
			if(selected == 0) {
				//Continue
				game.setMenu(new MainMenu());
			}
			else if(selected == 1) {
				//Github link
				Desktop desktop = Desktop.getDesktop();
				try {
					desktop.browse(new URI("http://github.com/Zenicius/Snake"));
				} catch (IOException | URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	}

	public void draw(Graphics g) {
		g.clearRect(0, 0, 500, 500);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 500, 500);

		// Title
		Font font = new Font("Arial", Font.PLAIN, 50);
		g.setFont(font);
		g.setColor(Color.RED);
		g.drawString("Help", 200, 80);
		
		//Help
		for(int i = 0; i < help.length; i++) {
			font = new Font("Arial", Font.PLAIN, 20);
			g.setFont(font);
			g.setColor(Color.GRAY);
			g.drawString(help[i], 100, 130 + i * 40);
		}
		
		//Options
		if(selected == 0)
			g.setColor(Color.RED);
		else
			g.setColor(Color.GRAY);
		g.drawString(options[0], 230, 410);
		
		if(selected == 1)
			g.setColor(Color.RED);
		else
			g.setColor(Color.GRAY);
		g.drawString(options[1], 230, 450);
		
	}

}

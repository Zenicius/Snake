package com.law.snake.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.JOptionPane;

import com.law.snake.SnakeGame;
import com.law.snake.Sound;
import com.law.snake.misc.HighscorePlayer;
import com.law.snake.misc.IOSystem;

public class HighscoreMenu extends Menu {

	private String[] options = { "Continue", "Delete" };
	private int selected = 0;

	private HighscorePlayer[] players = new HighscorePlayer[5];
	private int[] order;

	public HighscoreMenu() {
		// Load players
		players = (HighscorePlayer[]) IOSystem.Load();
		sortPlayers();
	}

	public void sortPlayers() {
		for (int i = 0; i < players.length; i++) {
			if (players[i] == null)
				players[i] = new HighscorePlayer(-1, "");
		}

		Arrays.sort(players, new Comparator<HighscorePlayer>() {
			@Override
			public int compare(HighscorePlayer player1, HighscorePlayer player2) {
				return Integer.compare(player1.score, player2.score);
			}
		});
	}

	public void tick(boolean up, boolean down, boolean action, SnakeGame game) {
		if (down && selected == 0) {
			Sound.menu.Play();
			selected++;
		}

		else if (up && selected == 1) {
			Sound.menu.Play();
			selected--;
		}

		else if (action) {
			Sound.menuSelect.Play();
			
			if (selected == 0)
				//Main Menu
				game.setMenu(new MainMenu());

			else if (selected == 1) {
				//Delete
				int position = -1;
				String deleteName = JOptionPane.showInputDialog("Player name to delete: ");
				
				//Canceled or null
				if(deleteName == null || deleteName.equals(""))
					return;
				
				//Search for name
				for(int i = 0; i < players.length; i++) {
					if(deleteName.equals(players[i].name)) {
						position = i;
					}	
				}
				
				if(position != -1) {
					int option = JOptionPane.showConfirmDialog(null, "Are you sure?");
					if(option == 0) {
						//Delete and save
						players[position] = new HighscorePlayer (-1, "");
						IOSystem.Save(players);
						game.setMenu(new HighscoreMenu());
					}
					else
						game.setMenu(new HighscoreMenu());
				
				}else
					JOptionPane.showMessageDialog(null, "Not found!");
				
			}
		}

	}

	public void draw(Graphics g) {
		g.clearRect(0, 0, 500, 500);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 500, 500);

		// Title
		Font font = new Font("Arial", Font.PLAIN, 40);
		g.setFont(font);
		g.setColor(Color.RED);
		g.drawString("Highscores", 150, 80);

		// Player 1
		font = new Font("Arial", Font.PLAIN, 30);
		g.setFont(font);
		g.setColor(Color.GRAY);
		if (players[4].score == -1)
			g.drawString("Empty", 170, 150);
		else
			g.drawString("1. " + players[4].name + " : " + players[4].score, 170, 150);

		// Player 2
		font = new Font("Arial", Font.PLAIN, 25);
		g.setFont(font);
		if (players[3].score == -1)
			g.drawString("Empty", 170, 210);
		else
			g.drawString("2. " + players[3].name + " : " + players[3].score, 170, 210);

		// Player 3
		font = new Font("Arial", Font.PLAIN, 20);
		g.setFont(font);
		if (players[2].score == -1)
			g.drawString("Empty", 170, 260);
		else
			g.drawString("3. " + players[2].name + " : " + players[2].score, 170, 260);

		// Player 4
		if (players[1].score == -1)
			g.drawString("Empty", 170, 310);
		else
			g.drawString("4. " + players[1].name + " : " + players[1].score, 170, 310);

		// Player 5
		if (players[0].score == -1)
			g.drawString("Empty", 170, 360);
		else
		g.drawString("5. " + players[0].name + " : " + players[0].score, 170, 360);

		font = new Font("Arial", Font.PLAIN, 25);
		g.setFont(font);
		
		// Options
		if (selected == 0)
			g.setColor(Color.RED);
		else
			g.setColor(Color.GRAY);
		
		g.drawString(options[0], 200, 440);

		if (selected == 1)
			g.setColor(Color.RED);
		else
			g.setColor(Color.GRAY);
		
		g.drawString(options[1], 200, 480);

	}

}

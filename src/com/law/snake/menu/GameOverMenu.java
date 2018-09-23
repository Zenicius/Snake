package com.law.snake.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.JOptionPane;

import com.law.snake.SnakeGame;
import com.law.snake.Sound;
import com.law.snake.misc.HighscorePlayer;
import com.law.snake.misc.IOSystem;

public class GameOverMenu extends Menu {

	private String[] options = {"Retry", "Save Score", "Quit"};
	private int selected = 0;
	
	private int score;
	private String playerName;
	
	private boolean saveEnabled = true;
	
	private HighscorePlayer[] players;
	
	public GameOverMenu(int score) {
		this.score = score;
		players = (HighscorePlayer[]) IOSystem.Load();
		
		HighscorePlayer[] newArray = new HighscorePlayer[6];
		for (int i = 0; i < 5; i++) {
			newArray[i] = players[i];
		}
		players = new HighscorePlayer[6];
		players = newArray;
		
		for (int i = 0; i < players.length; i++) {
			if (players[i] == null)
				players[i] = new HighscorePlayer(-1, "");
		}
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
	
	public void deleteFromArray(int delete) {
		HighscorePlayer[] newArray = new HighscorePlayer[players.length - 1];
		int c = 0;
		for(int i = 0; i < players.length; i++) {
			if(i != delete)
				newArray[c++] = players[i];
		}
		players = newArray;
		
	}
	
	public void tick(boolean up, boolean down, boolean action, SnakeGame game) {
		if (down && selected >= 0 && selected < 2) {
			Sound.menu.Play();
			if(!saveEnabled)
				selected += 2;
			else
				selected++;

		} else if (up && selected <= 2 && selected > 0) {
			Sound.menu.Play();
			if(!saveEnabled)
				selected -= 2;
			else
				selected--;
		
		} else if(action) {
			Sound.menuSelect.Play();
			
			if(selected == 0) {
				//Retry
				game.restartGame();
				game.setMenu(null);
			
			}else if(selected == 1 && saveEnabled) {
				//Check if have enough score
				sortPlayers();
				if(score < players[1].score ) {
					JOptionPane.showMessageDialog(null, "Not enough score to get TOP 5, mininum: " + players[1].score);
					saveEnabled = false;
					selected = 0;
					return;
				}
				
				//Get Player name
				playerName = JOptionPane.showInputDialog("Player Name: ");
				if(playerName == null || playerName.equals(""))
					return;
				
				//Check if player name already exists
				boolean exists = false;
				int position = 0;
				for(int i = 0; i < players.length; i++) {
					if(playerName.equals(players[i].name)) {
						position = i;
						exists = true;
					}
				}
				
				if(exists) {
					//Save Score
					if(players[position].score > score) {
						JOptionPane.showMessageDialog(null, playerName + " already have a higher score!! (" + players[position].score + ")");
						return;
					}
					
					players[position].score = score;
					sortPlayers();
					deleteFromArray(0);
					IOSystem.Save(players);
					
					saveEnabled = false;
					selected = 0;
				}else{
					//Save Score
					HighscorePlayer player = new HighscorePlayer(score, playerName);
					players[0] = player;
					sortPlayers();
					deleteFromArray(0);
					IOSystem.Save(players);
					
					saveEnabled = false;
					selected = 0;
				}
				
			}else if(selected == 2) {
				//Quit
				game.restartGame();
				game.setMenu(new MainMenu());
			}
		}
	}

	public void draw(Graphics g) {
		g.clearRect(0, 0, 500, 500);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 500, 500);

		// Title
		Font font = new Font("Arial", Font.PLAIN, 45);
		g.setFont(font);
		g.setColor(Color.RED);
		g.drawString("Game Over", 130, 90);

		font = new Font("Arial", Font.PLAIN, 30);
		g.setFont(font);
		g.setColor(Color.RED);
		g.drawString("Score: " + score, 180, 150);
		
		
		font = new Font("Arial", Font.PLAIN, 20);
		//Retry
		if(selected == 0)
			g.setColor(Color.RED);
		else
			g.setColor(Color.GRAY);
		g.drawString(options[0], 220, 270);
		
		//Save Score
		if(selected == 1 && saveEnabled)
			g.setColor(Color.RED);
		else
			g.setColor(Color.GRAY);
		g.drawString(options[1], 180, 320);
		
		//Quit
		if(selected == 2)
			g.setColor(Color.RED);
		else
			g.setColor(Color.GRAY);
		g.drawString(options[2], 230, 370);
		
	}

}

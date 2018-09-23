package com.law.snake.misc;

import java.io.Serializable;

public class HighscorePlayer implements Serializable{
	private static final long serialVersionUID = 7352393339001224166L;
	
	public String name;
	public int score;
	
	public HighscorePlayer(int score, String name) {
		this.score = score;
		this.name = name;
	}
	
	
	
}

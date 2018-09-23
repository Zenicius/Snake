package com.law.snake.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import com.law.snake.misc.Directions;

public class Snake extends Entity {

	private ArrayList<SnakeBody> body;
	private Directions directions;

	private int speed = 10;
	private int size = 4;
	public int score = 0;

	private int ticks = 0;

	public Snake(int x, int y, int scale) {
		super(x, y, scale);
		body = new ArrayList<SnakeBody>();

		directions = Directions.RIGHT;
	}

	public void levelUp() {
		score += 10;
		size += 3;
	}

	public void tick(boolean up, boolean down, boolean left, boolean right, boolean action) {

		ticks++;
		if (ticks > 1) {
			if (body.size() == 0) {
				body.add(new SnakeBody(x, y, scale));
			}

			if (up && directions != Directions.DOWN && !down && !left && !right)
				directions = Directions.UP;

			if (down && directions != Directions.UP && !up && !left && !right)
				directions = Directions.DOWN;

			if (left && directions != Directions.RIGHT && !down && !up && !right)
				directions = Directions.LEFT;

			if (right && directions != Directions.LEFT && !down && !left && !up)
				directions = Directions.RIGHT;
				

			switch (directions) {
			case RIGHT:
				x += speed;
				break;
			case LEFT:
				x -= speed;
				break;
			case DOWN:
				y += speed;
				break;
			case UP:
				y -= speed;
				break;
			}

			body.add(new SnakeBody(x, y, scale));
			if (body.size() > size) {
				body.remove(0);
			}

			ticks = 0;
		}
	}

	public void draw(Graphics g) {
		// Score
		Font font = new Font("Arial", Font.PLAIN, 16);
		g.setFont(font);
		g.setColor(Color.RED);
		g.drawString("Score: " + score, 6, 18);
	}

	public ArrayList<SnakeBody> getBody() {
		return body;
	}

}

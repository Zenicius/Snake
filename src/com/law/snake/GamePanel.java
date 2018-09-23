package com.law.snake;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.law.snake.misc.HighscorePlayer;
import com.law.snake.misc.IOSystem;

public class GamePanel extends JPanel implements Runnable {
	private static final long serialVersionUID = 6317609740860947020L;

	private static final String TITLE = "Snake";
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;

	private boolean running;
	private Thread thread;
	private Input input;

	private SnakeGame game;

	public GamePanel() {
		Dimension windowSize = new Dimension(WIDTH, HEIGHT);
		setPreferredSize(windowSize);

		setFocusable(true);
		input = new Input();
		addKeyListener(input);

		// Snake
		game = new SnakeGame();
	}

	public void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public void stop() {
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void tick() {
		game.tick(input.keys);
	}

	public void paint(Graphics g) {
		game.draw(g, WIDTH, HEIGHT);
	}

	@Override
	public void run() {

		double nsPerFrame = 1000000000.0 / 60.0;
		double unprocessedTime = 0;
		double maxSkipFrames = 10;

		long lastTime = System.nanoTime();
		long lastFrameTime = System.currentTimeMillis();
		int frames = 0;

		while (running) {
			long now = System.nanoTime();
			double passedTime = (now - lastTime) / nsPerFrame;
			lastTime = now;

			if (passedTime < -maxSkipFrames)
				passedTime = -maxSkipFrames;
			if (passedTime > maxSkipFrames)
				passedTime = maxSkipFrames;

			unprocessedTime += passedTime;

			boolean draw = false;
			while (unprocessedTime > 1) {
				unprocessedTime -= 1;
				tick();
				draw = true;
			}
		

			draw = true;
			if (draw) {
				repaint();
				frames++;
			}

			if (System.currentTimeMillis() > lastFrameTime + 1000) {
				System.out.println(frames + " fps");
				lastFrameTime += 1000;
				frames = 0;
			}

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	public static void main(String[] args) {
		JFrame window = new JFrame(TITLE);
		GamePanel game = new GamePanel();

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(game);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
		window.setLocationRelativeTo(null);
		
		//Create highscore file 
		IOSystem.createHSFile();
		
		game.start();
	}
}

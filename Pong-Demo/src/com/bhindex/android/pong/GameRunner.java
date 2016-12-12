package com.bhindex.android.pong;

import android.util.Log;

public class GameRunner extends Thread {

	private Game game;
	private volatile boolean running = true;

	public GameRunner(Game game) {
		this.game = game;
	}

	@Override
	public void run() {
		
		game.init();

		long lastTime = System.currentTimeMillis();

		// Game loop
		while (running) {
			// Draw

			long now = System.currentTimeMillis();
			long elapsed = now - lastTime;

			if (elapsed < 100) {
				game.update(elapsed);
				game.draw();
			}

			lastTime = now;
		}
	}

	public void shutdown() {
		running = false;
	}

}

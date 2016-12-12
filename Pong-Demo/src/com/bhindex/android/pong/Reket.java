package com.bhindex.android.pong;

import java.util.Random;

import android.graphics.Bitmap;

public class Reket extends Sprite {
	
	private Random random = new Random();
	private int dir;
	private float speed = 0.4f;
	
	public enum Position {
		LEFT, RIGHT
	}
	
	private static final int margin = 20;
	private Position position;

	public Reket(int screenWidth, int screenHeight, Position position) {
		super(screenWidth, screenHeight);
		
		this.position = position;
	}

	@Override
	public void init(Bitmap image, Bitmap shadow, int shadowOffsetX,
			int shadowOffsetY) {
		super.init(image, shadow, shadowOffsetX, shadowOffsetY);
		
		initPosition();
		
		dir = random.nextInt(2)*2 - 1;
		
		if(position == Position.LEFT) {
			setX(margin);
		}
		else if(position == Position.RIGHT) {
			setX((getScreenWidth() - margin) - getRect().centerX());
		}
	}
	
	public void initPosition() {
		setY(getScreenHeight()/2 - getRect().centerY());
	}

	public void setPosition(float y) {
		setY(y - getRect().centerY());
	}

	public void update(long elapsed, Ball ball) {
		
		int decision = random.nextInt(20);
		
		if(decision == 0) {
			dir = 0;
		}
		else if(decision == 1) {
			dir = random.nextInt(2)*2 - 1;
		}
		else if(decision < 4) {
			if(ball.getScreenRect().centerY() < getScreenRect().centerY()) {
				dir = -1;
			}
			else {
				dir = 1;
			}
		}
		
		if(getScreenRect().top <= 0) {
			dir = 1;
		}
		else if(getScreenRect().bottom >= getScreenHeight()) {
			dir = -1;
		}
		
		float y = getY();
		
		y += dir * speed * elapsed;
		
		setY(y);
	}

}

package com.bhindex.android.pong;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Callback {

	private Bitmap button;
	
	private GameRunner runner;
	private Game game;

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);

		SurfaceHolder holder = getHolder();

		holder.addCallback(this);

		
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		game.onTouchEvent(event);
		return true;
	}

	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
	}

	public void surfaceCreated(SurfaceHolder holder) {
		game = new Game(getContext(), getWidth(), getHeight(), holder, getResources());
		runner = new GameRunner(game);
		runner.start();
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		if(runner != null) {
			runner.shutdown();
			
			while(runner != null) {
				try {
					runner.join();
					runner = null;
				} catch (InterruptedException e) {
				}
			}
		}
	}
}

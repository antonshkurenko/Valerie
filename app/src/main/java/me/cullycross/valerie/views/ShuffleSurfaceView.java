package me.cullycross.valerie.views;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by: Anton Shkurenko (cullycross)
 * Project: Valerie
 * Date: 11/19/15
 * Code style: SquareAndroid (https://github.com/square/java-code-styles)
 * Follow me: @tonyshkurenko
 */
public class ShuffleSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

  private DrawThread mDrawThread;

  public ShuffleSurfaceView(Context context, AttributeSet attrs) {
    super(context, attrs);
    getHolder().addCallback(this);
    setFocusable(true);

    mDrawThread = new DrawThread();
  }

  @Override public void surfaceCreated(SurfaceHolder holder) {
    mDrawThread.setRunning(true);
    mDrawThread.start();
  }

  @Override public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

  }

  @Override public void surfaceDestroyed(SurfaceHolder holder) {
    boolean retry = true;
    while (retry) {
      try {
        mDrawThread.join();
        retry = false;
      } catch (InterruptedException e) {
        // try again shutting down the thread
      }
    }
  }

  @Override protected void onDraw(Canvas canvas) {

  }

  class DrawThread extends Thread {

    private final String TAG = DrawThread.class.getSimpleName();

    private boolean mRunning;

    public DrawThread() {
      super();
    }

    public void setRunning(boolean running) {
      this.mRunning = running;
    }

    @Override public void run() {
      long tickCount = 0L;
      Log.d(TAG, "Starting draw loop");
      while (mRunning) {
        tickCount++;
        final Canvas canvas = getHolder().lockCanvas();

        // draw here

        getHolder().unlockCanvasAndPost(canvas);
      }
      Log.d(TAG, "Draw loop executed " + tickCount + " times");

    }
  }
}

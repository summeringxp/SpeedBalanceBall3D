package edu.xiapei;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class GameSurfaceView extends GLSurfaceView /*implements SensorEventListener*/{

    public GameSurfaceView(Context context, AttributeSet attrs) {
        super(context,attrs);
    	mRenderer = new GameRenderer();
        setRenderer(mRenderer);
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
        mContext = context;
        mAttrs = attrs;
       
    }
   /* @Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		return ;
	}

	@Override
	public void onSensorChanged(SensorEvent e) {
		// TODO Auto-generated method stub
		return ;
	}*/
    
   

    @Override public boolean onTrackballEvent(MotionEvent e) {
        mRenderer.fTransX = e.getX() * Statics.TRACKBALL_SCALE_FACTOR;
        mRenderer.fTransY = e.getY() * Statics.TRACKBALL_SCALE_FACTOR;
       // requestRender();
        return true;
    }
    

    @Override public boolean onTouchEvent(MotionEvent e) {
        float x = e.getX();
        float y = e.getY();
        switch (e.getAction()) {
        case MotionEvent.ACTION_MOVE:
            float dx = x - mPreviousX;
            float dy = y - mPreviousY;
            mRenderer.mTransX = dx * Statics.TOUCH_SCALE_FACTOR;
            mRenderer.mTransY = dy * Statics.TOUCH_SCALE_FACTOR;
            requestRender();
        }
        mPreviousX = x;
        mPreviousY = y;
        return true;
    }
    

    @Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
		super.onMeasure(widthMeasureSpec,heightMeasureSpec);
    	//setMeasuredDimension(getSuggestedMinimumWidth() , getSuggestedMinimumHeight() );
	}
 
    public GameRenderer getRenderer() {
		return mRenderer;
	}

	private GameRenderer mRenderer;
    private Context mContext;
    private AttributeSet mAttrs;
    private float mPreviousX;
    private float mPreviousY;
    private Handler mHandler;
	
	
    
}



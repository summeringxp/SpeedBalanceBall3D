package edu.xiapei;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class GameSurfaceView extends GLSurfaceView implements SensorEventListener{

    public GameSurfaceView(Context context, AttributeSet attrs) {
        super(context,attrs);
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_FASTEST);
    }
   @Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		return ;
	}

	@Override
	public void onSensorChanged(SensorEvent e) {
		
        
            if (e.sensor.getType()==Sensor.TYPE_ACCELEROMETER) {
            	 float x = e.values[0];
            	 float y = e.values[1];
            	// float z = (float) (e.values[2]/40.0);
            	 if(x>0){
            		 x-=0.5;
            		 x=x>0?x:0;
            	 }else{
            		 x+=0.5;
            		 x=x<-0?x:-0;
            	 }
            	 if(y>0){
            		 y-=0.5;
            		 y=y>0?y:0;
            	 }else{
            		 y+=0.5;
            		 y=y<0?y:0;
            	 }
            	 x /=40.0f;
            	 y /=40.0f;
            	 mRenderer.fTransX = y * Statics.TRACKBALL_SCALE_FACTOR;
                 mRenderer.fTransY = x * Statics.TRACKBALL_SCALE_FACTOR;
            }         
        
		return ;
	}
    
   

   /* @Override public boolean onTrackballEvent(MotionEvent e) {
        mRenderer.fTransX = e.getX() * Statics.TRACKBALL_SCALE_FACTOR;
        mRenderer.fTransY = e.getY() * Statics.TRACKBALL_SCALE_FACTOR;
       // requestRender();
        return true;
    }*/
    

    @Override public boolean onTouchEvent(MotionEvent e) {
        float x = e.getX();
        float y = e.getY();
        switch (e.getAction()) {
        case MotionEvent.ACTION_MOVE:
            float dx = x - mPreviousX;
            float dy = y - mPreviousY;
            mRenderer.mTransX = -dx * Statics.TOUCH_SCALE_FACTOR;
            mRenderer.mTransY = -dy * Statics.TOUCH_SCALE_FACTOR;
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
    public void setGameRenderer(GameRenderer gr){
    	this.mRenderer = gr;
    	setRenderer(mRenderer);
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }
    
	public GameRenderer getMRenderer() {
		return mRenderer;
	}
	 
	private GameRenderer mRenderer;
    private Context mContext;
    private AttributeSet mAttrs;
    private float mPreviousX;
    private float mPreviousY;
    SensorManager mSensorManager;
	
    
}



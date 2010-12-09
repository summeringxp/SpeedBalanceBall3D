package edu.xiapei;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import storageTools.MapDto;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import gameCharactor.GameCharactor;

public class GameRenderer implements GLSurfaceView.Renderer {
	
	 
	    private GameManager gameManager;

   public GameRenderer(MapDto mdto) {
   	
   	gameManager = new GameManager(mdto);
   	
   	gameManager.getGameCharMngr().createChar(new GameCharactor(mdto));
   
   	
   }

   public void onDrawFrame(GL10 gl) {
       gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
       gl.glMatrixMode(GL10.GL_MODELVIEW);
       gl.glLoadIdentity();
       
       gameManager.getMyCamera().externForce(mTransX, -mTransY,0);
       gameManager.getGameCharMngr().externForce(fTransX*0.05f,-fTransY*0.05f,0.0f);
       mTransX=0;
       mTransY=0;
       fTransX=0;
       fTransY=0;

       gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
       gl.glEnableClientState(GL10.GL_COLOR_ARRAY);         
       gameManager.go(gl);
       if(gameManager.getGameState()==1){
			mHandler.sendEmptyMessage((int)gameManager.getPlayTime());
			gameManager.setState(3);
		}
       
   }


	public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height);
        float ratio = (float) width / height;
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glFrustumf(-ratio, ratio, -1, 1, 1, 30);
   }

   public void onSurfaceCreated(GL10 gl, EGLConfig config) {
       gl.glDisable(GL10.GL_DITHER);
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,
                GL10.GL_FASTEST);
        gl.glClearColor(Statics.SKYCOLOR[0],Statics.SKYCOLOR[1],Statics.SKYCOLOR[2],Statics.SKYCOLOR[3]);
        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glShadeModel(GL10.GL_SMOOTH);
        gl.glEnable(GL10.GL_DEPTH_TEST);
   }
   public void setHandler(Handler handler) {
	   mHandler=handler;
		// TODO Auto-generated method stub
		
	}
   
   public GameManager getGameManager() {
	return gameManager;
}

public float mTransX;
   public float mTransY;
   public float fTransX;
   public float fTransY;
   private Handler mHandler;

}


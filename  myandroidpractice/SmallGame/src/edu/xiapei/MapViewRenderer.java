package edu.xiapei;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import storageTools.MapDao;
import storageTools.MapDto;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import edu.xiapei.GameMap.GameMap;
import gameTools.MyCamera;

public class MapViewRenderer implements GLSurfaceView.Renderer {
	
	 
	    private GameMap gameMap;
	   
   public MapViewRenderer(MapDto mdto) {
   	
	   gameMap = GameMap.getGameMap(mdto);
	 
   }
   public void changeMap(MapDto mdto){
	   gameMap = GameMap.getGameMap(mdto);
   }

   public void onDrawFrame(GL10 gl) {
       gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
       gl.glMatrixMode(GL10.GL_MODELVIEW);
       gl.glLoadIdentity();
   
       
       GLU.gluLookAt(gl, 8.0f+8.0f*(float)Math.cos(mRotate/1000.0f), 8.0f+8.0f*(float)Math.sin(mRotate/1000.0f), 6.5f, 8.0f, 8.0f, 0.0f, 0.0f, 0.0f, 1.0f);
       mRotate=(mRotate+12)%360000;
     //  gameMap.drawMapElements(gl);

       gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
       gl.glEnableClientState(GL10.GL_COLOR_ARRAY);         
      
       
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
 
   private int mRotate;

  
   

}


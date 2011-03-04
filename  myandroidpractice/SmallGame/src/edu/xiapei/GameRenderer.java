package edu.xiapei;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import edu.xiapei.GameMap.GameMap;

import storageTools.MapDto;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.WindowManager;
import gameCharactor.GameCharactor;
import gameCharactor.TargetFlag;
import gameTools.GLTextureFactory;
import gameTools.Graphics2D;

public class GameRenderer implements GLSurfaceView.Renderer {
	
	 //private GameMap gameMap;
	 private GameManager gameManager;
	// private GLTextures glt;
	 private Context context;
   public GameRenderer(MapDto mdto, Context context) {
   	this.context = context;
   	gameManager = new GameManager(mdto);
   	gameManager.getGameCharMngr().createChar(new GameCharactor(mdto));
   	gameManager.getGameCharMngr().createChar(new TargetFlag(mdto));
   //	gameMap = GameMap.getGameMap(mdto);
   	
   }
   public void changeMap(MapDto mdto){
	   gameManager.setGameMap(mdto);
	 //  gameMap = GameMap.getGameMap(mdto);
   }

   public void onDrawFrame(GL10 gl) {
       gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
       gl.glMatrixMode(GL10.GL_MODELVIEW);
       gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
       gl.glEnableClientState(GL10.GL_COLOR_ARRAY);   
       gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

       gl.glActiveTexture(GL10.GL_TEXTURE0);
       gl.glLoadIdentity();
       if(gameManager.getGameStep()==1){
	       gameManager.getMyCamera().changeView(mTransX, mTransY);
	       gameManager.setForce(fTransX, fTransY);
	       float f[]= transForce(fTransX,fTransY,gameManager.getMyCamera().getTheta());
	       gameManager.getGameCharMngr().externForce(f[0]*0.05f,-f[1]*0.05f,0.0f);
	       mTransX=0;
	       mTransY=0;
	       fTransX=0;
	       fTransY=0;
	       gameManager.go(gl);
	       if(gameManager.getGameState()==1){
				mHandler.sendEmptyMessage((int)gameManager.getPlayTime());
				gameManager.setState(3);
			}
       }else if(gameManager.getGameStep()==0){
    	   GLU.gluLookAt(gl, 8.0f+8.0f*(float)Math.cos(mRotate/1000.0f), 8.0f+8.0f*(float)Math.sin(mRotate/1000.0f), 6.5f, 8.0f, 8.0f, 0.0f, 0.0f, 0.0f, 1.0f);
           mRotate=(mRotate+12)%360000;
           gameManager.getGameCharMngr().drawCharacters(gl, 0);
       }else if(gameManager.getGameState()==2){
    	 //  gameManager.getMyCamera().externForce(mTransX, -mTransY,0);
    	   gameManager.goEdit(gl);
       }
       gameManager.getGameMap().drawMapElements(gl,gameManager.getGameStep(),gameManager.getPlayTime());
       if(gameManager.getGameStep()==1){
    	   gameManager.drawGameInfo(gl);
       }
       
       
       
   }


	private float[] transForce(float transX, float transY, float theta) {
	// TODO Auto-generated method stub
		double xr = transX*Math.cos(theta)+transY*Math.sin(theta);
		double yr = -transX*Math.sin(theta)+transY*Math.cos(theta);
		return new float[]{(float)xr,(float)yr};
}
	
	public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height);
        
        float ratio = (float) width / height;
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glFrustumf(-ratio, ratio, -1, 1, 1, 30);
   }

   public void onSurfaceCreated(GL10 gl, EGLConfig config) {
	   GLTextureFactory.allocateTextures(gl, context);
	  // glt = new GLTextures(gl, context); 
	  // glt.add(R.drawable.icon1);
	   WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
	   gameManager.setG(new Graphics2D(gl),wm.getDefaultDisplay().getWidth(),wm.getDefaultDisplay().getHeight());
       gl.glDisable(GL10.GL_DITHER);
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,
                GL10.GL_FASTEST);
        gl.glClearColor(Statics.SKYCOLOR[0],Statics.SKYCOLOR[1],Statics.SKYCOLOR[2],Statics.SKYCOLOR[3]);
        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glShadeModel(GL10.GL_SMOOTH);
        gl.glEnable(GL10.GL_DEPTH_TEST);
       // glt.loadTextures(); 
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
   private int mRotate;

}


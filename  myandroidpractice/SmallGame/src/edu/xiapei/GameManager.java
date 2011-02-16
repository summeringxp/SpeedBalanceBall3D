package edu.xiapei;

import javax.microedition.khronos.opengles.GL10;

import storageTools.MapDto;

import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import edu.xiapei.GameMap.*;
import gameAnimations.GameAnimation;
import gameAnimations.OpenningAnima;
import gameCharactor.GameCharactor;
import gameCharactor.GameCharactorManager;
import gameTools.GameTools;
import gameTools.Graphics2D;
import gameTools.MyCamera;

public class GameManager {
	
	private GameMap gameMap;
	private MapElements[] menuEle;
	private GameAnimation gameAnima;
	private GameCharactorManager gameCharMngr;
	private GameTools gameTool;
	private MyCamera myCamera;
	private MyUI mUi;
	private long startTime;
	private long lastTimer;
	private int state;
	private int gameStep=0;
	private Graphics2D g;
	private TextView textView;
	private static int WIN=1;
	private static int LOSE=2;
	private static int OVER=3;
	private int width,height;
	private long recordTime;
	private float fx,fy;
	
	public void setG(Graphics2D g, int width, int height) {
		this.g = g;
		this.width=width;
		this.height=height;
		g.init(width, height);
	}
	public MyCamera getMyCamera() {
		return myCamera;
	}
	public GameManager(MapDto mdto){
		
		gameMap = GameMap.getGameMap(mdto);
		//gameAnima = new GameAnimation();
		gameCharMngr = new GameCharactorManager();
		myCamera = new MyCamera();
		startTime = SystemClock.currentThreadTimeMillis();
		lastTimer = startTime;
		menuEle = new MapElements[13];
		menuEle[0] = new Hole(0);
		menuEle[1] = new Cube(0);
		menuEle[2] = new SlopNorth(0);
		menuEle[3] = new SlopSouth(0);
		menuEle[4] = new SlopWest(0);
		menuEle[5] = new SlopEast(0);
		menuEle[6] = new Accnorth(0);
		menuEle[7] = new Accsouth(0);
		menuEle[8] = new Accwest(0);
		menuEle[9] = new Acceast(0);
		menuEle[10] = new Mud(0);
		menuEle[11] = new Target(0);
		menuEle[12] = new Glass(0);
		mUi = new MyUI();
		}
	public GameMap getGameMap() {
		return gameMap;
	}
	public void setGameMap(MapDto mdto) {
		this.gameMap = GameMap.getGameMap(mdto);
		this.gameCharMngr.getGameChar(0).setMdto(mdto);
		this.gameCharMngr.getGameChar(1).setMdto(mdto);
	}
	public GameAnimation getGameAnima() {
		return gameAnima;
	}

	public GameCharactorManager getGameCharMngr() {
		return gameCharMngr;
	}

	public GameTools getGameTool() {
		return gameTool;
	}
	public void go(GL10 gl) {
		// TODO Auto-generated method stub
		
		long t = SystemClock.currentThreadTimeMillis()-lastTimer;
		t=t<10?t:10;
		
		myCamera.setCamera(gl, state==3?0:t);
		
		//gameAnima.play(gl);
		//gameMap.drawMapElements(gl);
		myCamera.follow(gameCharMngr.getGameChar(0).posMatrix[12]+0.5f,
				gameCharMngr.getGameChar(0).posMatrix[13]+0.5f,
				gameCharMngr.getGameChar(0).posMatrix[14]+0.5f,t);
		
		gameCharMngr.drawCharacters(gl,state==3?0:t);
		if(state!=3){
			state = gameCharMngr.checkState();
		}
		if(gameCharMngr.getGameChar(0).isDied()){
			reset();
		}
        lastTimer = SystemClock.currentThreadTimeMillis();
        drawGameInfo(gl);
	}
	public void goEdit(GL10 gl) {
		gl.glPushMatrix();
		gl.glLoadIdentity();
		for(int i=0;i<6;i++){
			menuEle[i].draw(gl,0);
		}
		for(int i=0;i<6;i++){
			
		}
		gl.glPopMatrix();
		myCamera.setCamera(gl,0);
		
		
	}
	private void drawGameInfo(GL10 gl) {
		
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(gl.GL_PROJECTION);	 // Select Projection
		gl.glPushMatrix();	 // Push The Matrix
		gl.glLoadIdentity();	 // Reset The Matrix
		
		gl.glOrthof(-width/2,width/2,-height/2,height/2,-100,100);    
        gl.glTranslatef(-width/2,height/2,0);
		gl.glMatrixMode(gl.GL_MODELVIEW);	 // Select Modelview Matrix
		gl.glPushMatrix();	 // Push The Matrix
		gl.glLoadIdentity();	 // Reset The Matrix
		
		        /* draw stuff */
		
		        mUi.draw(g,height,width,getPlayTime(),recordTime,fx,fy);
		       		

		gl.glMatrixMode( gl.GL_PROJECTION );	 // Select Projection
		gl.glPopMatrix();	 // Pop The Matrix
		gl.glMatrixMode( gl.GL_MODELVIEW );	 // Select Modelview
		gl.glPopMatrix();	 // Pop The Matrix
		
		//gl.glOrthox(0, 320, 0, 480, 0, 1);
		
		//gl.glPopMatrix();
		//gl.glMatrixMode(gl.GL_MODELVIEW);
	}
	public int getGameState(){
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getGameStep() {
		return gameStep;
	}
	public void setGameStep(int gameStep) {
		this.gameStep = gameStep;
	}
	public long getPlayTime(){
		return gameCharMngr.getGameChar(0).getLife();
	}
	public void reset(){
		lastTimer = SystemClock.currentThreadTimeMillis();
		//gameCharMngr.getGameChar(0).setMdto(this.mdto);
		gameCharMngr.getGameChar(0).reset();
		myCamera.setPos(gameCharMngr.getGameChar(0).getInitPos());
		setState(0);
	}
	public void setRecordTime(long recordTime) {
		this.recordTime = recordTime;
	}
	
	public void setForce(float x,float y){
		this.fx=x;
		this.fy=y;
	}

}

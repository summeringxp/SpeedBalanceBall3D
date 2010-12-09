package edu.xiapei;

import javax.microedition.khronos.opengles.GL10;

import storageTools.MapDto;

import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import edu.xiapei.GameMap.GameMap;
import gameAnimations.GameAnimation;
import gameAnimations.OpenningAnima;
import gameCharactor.GameCharactor;
import gameCharactor.GameCharactorManager;
import gameTools.GameTools;
import gameTools.MyCamera;

public class GameManager {
	
	private GameMap gameMap;
	private GameAnimation gameAnima;
	private GameCharactorManager gameCharMngr;
	private GameTools gameTool;
	private MyCamera myCamera;
	private long startTime;
	private long lastTimer;
	private int state;
	private TextView textView;
	private static int WIN=1;
	private static int LOSE=2;
	private static int OVER=3;

	
	
	public MyCamera getMyCamera() {
		return myCamera;
	}
	public GameManager(MapDto mdto){
		
		gameMap = GameMap.getGameMap(mdto);
		gameAnima = new GameAnimation();
		gameCharMngr = new GameCharactorManager();
		myCamera = new MyCamera();
		startTime = SystemClock.currentThreadTimeMillis();
		lastTimer = SystemClock.currentThreadTimeMillis();
		
        
	}
	public GameMap getGameMap() {
		return gameMap;
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
		myCamera.setCamera(gl, state==3?0:t);
		
		gameAnima.play(gl);
		gameMap.drawMapElements(gl);
		myCamera.follow(gameCharMngr.getGameChar(0).posMatrix[12]+0.5f,
				gameCharMngr.getGameChar(0).posMatrix[13]+0.5f,
				gameCharMngr.getGameChar(0).posMatrix[14]+0.5f);
		
		gameCharMngr.drawCharacters(gl,state==3?0:t);
		if(state!=3){
			state = gameCharMngr.checkState();
		}
        lastTimer = SystemClock.currentThreadTimeMillis();
        drawGameInfo();
	}
	private void drawGameInfo() {
		
	}
	public int getGameState(){
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}

	public long getPlayTime(){
		return gameCharMngr.getGameChar(0).getLife();
	}
	public void reset(){
		gameCharMngr.getGameChar(0).reset();
		setState(0);
	}

}

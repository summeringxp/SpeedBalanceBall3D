package gameCharactor;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import storageTools.MapDto;

import edu.xiapei.Statics;


public class GameCharactorManager  {
	ArrayList<GameCharactor> gameChars;
	public GameCharactorManager(){
		gameChars = new ArrayList<GameCharactor>();
	}
	public void drawCharacters(GL10 gl,long t) {
		// TODO Auto-generated method stub
		for(int i= 0;i<gameChars.size();i++){
			gameChars.get(i).draw(gl,t);
			
		}
		
	}
	public void createChar(GameCharactor gc){
		gameChars.add(gc);
	}
	public GameCharactor getGameChar(int index){
		return gameChars.get(index);
	}
	public void externForce(float x, float y, float z) {
		// TODO Auto-generated method stub
		for(int i= 0;i<gameChars.size();i++){
			gameChars.get(i).addForce(x,y,z);
		}
	}
	public int checkState() {
		// TODO Auto-generated method stub
		for(int i= 0;i<gameChars.size();i++){
			if(gameChars.get(i).currentMapType()==Statics.TARGET){
				return 1;
			}
		}
		return 0;
	}

}

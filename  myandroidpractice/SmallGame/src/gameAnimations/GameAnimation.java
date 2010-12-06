package gameAnimations;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

public class GameAnimation {

	ArrayList<Playable> animationList = new ArrayList<Playable>();
	
	public void play(GL10 gl){
		for(int i = 0; i < animationList.size();i++){
			boolean died = animationList.get(i).playAnima(gl);
			if(died){
				animationList.remove(i);
			}
			
		}
	}
	public void addAnimation(Playable anima){
		animationList.add(anima);
	}
	

}

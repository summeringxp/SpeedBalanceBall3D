package gameAnimations;

import javax.microedition.khronos.opengles.GL10;

import android.os.SystemClock;


public class OpenningAnima implements Playable{

	private long birthTime;
	private long age;
	public OpenningAnima(){
		birthTime = SystemClock.uptimeMillis();;
	}
	@Override
	public boolean playAnima(GL10 gl) {
		// TODO Auto-generated method stub
		age = SystemClock.uptimeMillis()-birthTime;
		draw(gl);
		if(age>10000){
			return true;
		}
		return false;
	}
	private void draw(GL10 gl) {
		// TODO Auto-generated method stub
		//int 
		
	}
	
}

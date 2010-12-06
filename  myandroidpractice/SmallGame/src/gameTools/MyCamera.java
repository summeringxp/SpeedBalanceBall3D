package gameTools;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLU;

public class MyCamera {
	
	
	public MyCamera(){
		eyeX = 8.0f;
		eyeY = 4.0f;
		eyeZ = 6.5f;
		centerX = 8.0f;
		centerY = 8.0f;
		centerZ = -8.0f;
		upX = 0.0f;
		upY = 2.0f;
		upZ = 1.0f;
		speedX = 0.0f;
		speedY = 0.0f;
		speedZ = 0.0f;
		accX = 0.0f;
		accY =0.0f;
		accZ = 0.0f;
	}
	
	
	private float eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ,speedX,speedY,speedZ,accX,accY,accZ;
	
	public void setCamera(GL10 gl,long t){
		 GLU.gluLookAt(gl, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
		 nextPos(t);
		 accX = 0.0f;
			accY =0.0f;
			accZ = 0.0f;
	}

	public void nextPos(long t) {
		// TODO Auto-generated method stub
		float tempt = t/10.0f;
		
		eyeX+=speedX*tempt;
		eyeY+=speedY*tempt;
		eyeZ+=speedZ*tempt;
		//check boundary
		if(eyeX>16.0f)eyeX=16.0f;
		if(eyeX<-8.0f)eyeX=-8.0f;
		if(eyeY>16.0f)eyeY=16.0f;
		if(eyeY<-8.0f)eyeY=-8.0f;
		centerX=eyeX;
		centerY=eyeY+4.0f;
		centerZ=eyeZ;
		
		speedX=Math.abs(speedX)<1?speedX+accX*tempt*30:speedX/2;
		speedY=Math.abs(speedY)<1?speedY+accY*tempt*30:speedY/2;
		speedZ=Math.abs(speedZ)<1?speedZ+accZ*tempt*30:speedZ/2;
		if(accX==0)speedX/=1.2;
		if(accY==0)speedY/=1.2;
		if(accZ==0)speedZ/=1.2;
		
	}
	public void externForce(float x,float y,float z){
		accX+=x;
		accY+=y;
		accZ += z;
		//System.out.println(accX);
	}
	public void follow(float x,float y,float z){
		accX = (x-eyeX)*0.001f;
		accY = (y-4.0f-eyeY)*0.001f;
		accZ = (z+3.5f-eyeZ)*0.001f;
		if(Math.abs(accX)<0.001f)accX=0;
		if(Math.abs(accY)<0.001f)accY=0;
		if(Math.abs(accZ)<0.001f)accZ=0;
		centerX=eyeX;
		centerY=eyeY+4.0f;
		centerZ=eyeZ-8.0f;
	}
}

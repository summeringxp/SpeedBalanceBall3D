package gameTools;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLU;

public class MyCamera {
	
	
	public MyCamera(){
		eyeX = 8.0f;
		eyeY = 1.0f;
		eyeZ = 4.5f;
		centerX = 8.0f;
		centerY = 8.0f;
		centerZ = -8.0f;
		upX = 0.0f;
		upY = 0.0f;
		upZ = 1.0f;
		speedX = 0.0f;
		speedY = 0.0f;
		speedZ = 0.0f;
		theta = 0.0f;
		height = 0.0f;
		rad=3.8f;
	}
	
	private float theta,height,rad;
	private float eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ,speedX,speedY,speedZ;
	
	public float getTheta() {
		return theta;
	}

	public void setCamera(GL10 gl,long t){
		 GLU.gluLookAt(gl, eyeX+rad*(float)Math.sin(theta), eyeY-(float)Math.cos(theta), eyeZ+height, centerX-rad*(float)Math.sin(theta), centerY+rad*(float)Math.cos(theta), centerZ-height, upX, upY, upZ);
		 nextPos(t);
		 //accX = 0.0f;
		//	accY =0.0f;
		//	accZ = 0.0f;
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
		centerY=eyeY;
		centerZ=eyeZ-8.0f;
		
	}

	public void follow(float x,float y,float z,long t){
		speedX = (x-eyeX)*t/100;
		speedY = (y-eyeY)*t/100;
		speedZ = (z+2.65f-eyeZ)*t/100;
		
		centerX=eyeX;
		centerY=eyeY;
		centerZ=eyeZ-8.0f;
	}
	public void setPos(float[] initpos){
		eyeX = initpos[0];
		eyeY = initpos[1];
		eyeZ = initpos[2]+2.65f;
		centerX=eyeX;
		centerY=eyeY;
		centerZ=eyeZ-8.0f;
		speedX = 0.0f;
		speedY = 0.0f;
		speedZ = 0.0f;
//		theta = 0.0f;
//		height = 0.0f;
//		rad=3.8f;
	//	accX=0;
		//accY=0;
		//accZ=0;
	}
	public void changeView(float x, float y){
		theta += x/360.0f;
		height+= y/100.0f;
		height = height<0?0:height;
		height = height>10?10:height;
		
	}

//	public void externForce(float x,float y,float z){
//	accX+=x;
//	accY+=y;
//	accZ += z;
//	if(accX>0.2)accX=0.2f;
//	if(accY>0.2)accY=0.2f;
//	if(accZ>0.2)accZ=0.2f;
//	//System.out.println(accX);
//}
}

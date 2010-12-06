package gameCharactor;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.Matrix;
import android.os.SystemClock;

import edu.xiapei.Statics;
import edu.xiapei.GameMap.CubeDrawer;
import edu.xiapei.GameMap.Drawable;
import edu.xiapei.GameMap.GameMap;
import edu.xiapei.GameMap.MapElements;

public class GameCharactor {
	public float posMatrix[]=new float[]{
		1.0f,0.0f,0.0f,0.0f,
		0.0f,1.0f,0.0f,0.0f,
		0.0f,0.0f,1.0f,0.0f,
		0.0f,0.0f,0.0f,1.0f
	};
	private float force[] = new float[3];
	private float speed[]= new float[3];
	private float acceleration[]= new float[3];
	
	private float size;
	private float mass=0.2f;
	private float resistanceFactor=1.02f;
	private float jumpFactor=0.3f;
	private Drawable drawer;
	public GameCharactor(){
		
		reset();
		drawer = new BallDrawer(size,0);
	}
	public GameCharactor(float mass, float resistanceFactor,float jumpFactor,int size,int x,int y) {
		acceleration = new float[3];
		this.mass = mass;
		this.resistanceFactor = resistanceFactor;
		this.size = size;
		drawer = new BallDrawer(1, 0);
		posMatrix[12] = (float)x;
		posMatrix[13] = (float)y;
		posMatrix[14] = GameMap.getGameMap().getHight(x, y)+1.0f+size;
		//SystemClock.uptimeMillis();
		drawer = new DefaultCharDrawer(size);
	}
	
	public void setDrawer(Drawable d){
		this.drawer = d;
	}

	public void draw(GL10 gl,long t) {
		// TODO Auto-generated method stub
		gl.glPushMatrix();
		gl.glMultMatrixf(this.posMatrix,0);
		Matrix.rotateM(this.posMatrix, 0, 20.0f, 1.0f*(t%13-6), 1.0f*(t%10-5), 1.0f*(t%17-8));
		
		drawer.draw(gl);
		gl.glPopMatrix();
		
		prepareForNextFrame(t);
	}
	private void prepareForNextFrame(long t) {
		// TODO Auto-generated method stub
		
		
		caculateForce();
		
		for(int i = 0;i<3;i++){
			acceleration[i]=force[i]/mass;
			speed[i]+=acceleration[i]*t;
			speed[i]/=resistanceFactor;		
			force[i]=0;
		}
		
		checkBoudary(t);
		
		if(charDie()){
			dealDie();
		}
		
		//checkValid();
	
	}
	private void checkBoudary(long t) {
		// TODO Auto-generated method stub
		int x = (int) posMatrix[12];
		int y = (int) posMatrix[13];
		int z = (int) posMatrix[14];
		float tempPos[]= new float[3];
		for(int i=0;i<3;i++){
			tempPos[i] = posMatrix[i+12]+speed[i]*t;
		}
		
		float tempH;
		if(outsideMap(x,y,z)){
			tempH=-30.0f;
		}else{
			tempH = getHeight(posMatrix[12], posMatrix[13]);
		}

		if(tempPos[2]<tempH+size){
			tempPos[2] = tempH+size;
			speed[2]=-speed[2]*jumpFactor;
		}
		
		if(!outsideMap(tempPos[0]+size/2,tempPos[1],tempPos[2])){
			if((speed[0]>=0&&getHeight(tempPos[0]+size/2,tempPos[1])-getHeight(posMatrix[12]+size/2,posMatrix[13])>0.05f)||
				(speed[0]<=0&&getHeight(tempPos[0]-size/2,tempPos[1])-getHeight(posMatrix[12]-size/2,posMatrix[13])>0.05f)){
				speed[0]=-speed[0]*jumpFactor;
				tempPos[0] = posMatrix[12]+speed[0];
			}
		}
		
		if(!outsideMap(tempPos[0],tempPos[1]+size/2,tempPos[2])){
			if((tempPos[1]>=posMatrix[13]&&getHeight(tempPos[0],tempPos[1]+size/2)-getHeight(posMatrix[12],posMatrix[13]+size/2)>0.05)||
				(tempPos[1]<=posMatrix[13]&&getHeight(tempPos[0],tempPos[1]-size/2)-getHeight(posMatrix[12],posMatrix[13]-size/2)>0.05)){
				speed[1]=-speed[1]*jumpFactor;
				tempPos[1] = posMatrix[13]+speed[1];
			}
		}
		
		
		
		for(int i=0;i<3;i++){
			
			posMatrix[i+12]=Math.abs(tempPos[i]-4.0f)>30.0f?posMatrix[i+12]:tempPos[i];
			
		}
	}
	private float getHeight(float x,float y){
		int x1 = (int) x;
		int y1 = (int) y;
		return GameMap.getGameMap().getElements(x1, y1).getHight(x-x1, y-y1);
	}
	private void dealDie() {
		// TODO Auto-generated method stub
		reset();
	}
	private void reset() {
		// TODO Auto-generated method stub
		size = 0.4f;
		posMatrix[12] = 1.5f;
		posMatrix[13] = 1.5f;
		posMatrix[14] = GameMap.getGameMap().getElements(1, 1).getHight(0.5f, 0.5f)+size;
		force[0]=0.0f;force[1]=0.0f;force[2]=0.0f;
		speed[0]=0.0f;speed[1]=0.0f;speed[2]=0.0f;
		acceleration[0]=0.0f;acceleration[1]=0.0f;acceleration[2]=0.0f;
	}
	private void caculateForce() {
		//          O
		//         OOO
		//          O
		//
		int x = (int) posMatrix[12];
		int y = (int) posMatrix[13];
		int z = (int) posMatrix[14];
		
		if(outsideMap(x,y,z)){
			force[2]-=0.005;
			return;
		}else if(GameMap.getGameMap().getElements(x, y).getHight(posMatrix[12]-x, posMatrix[13]-y)<posMatrix[14]-size){
			force[2]-=0.005;
			
		
			float temp[] = GameMap.getGameMap().getElements(x, y).getForce();
			addForce(temp[0],temp[1],temp[2]);
		}
		
		
		//GameMap gm = GameMap.getGameMap();
		//GameMapElement center = gm.g
		
		
		
	}
	/*public float[] getPos(){
		return new float[]{posMatrix[12],posMatrix[13],posMatrix[14]};
	}*/
	public void addForce(float x, float y, float z) {
		// TODO Auto-generated method stub
		force[0]+=x;
		force[1]+=y;
		force[2]+=z;
	}
	
	public boolean charDie(){
		
		return posMatrix[14]<-20.0f;
	}
	public int currentMapType() {
		// TODO Auto-generated method stub
		int x = (int) posMatrix[12];
		int y = (int) posMatrix[13];
		int z = (int) posMatrix[14];
		if(outsideMap(x,y,z)){
			return 0;
		}else{
			return GameMap.getGameMap().getMapType(x, y);
		}
	}
	private boolean outsideMap(int x,int y,int z) {
		// TODO Auto-generated method stub
		return x<0||x>8||y<0||y>8||z<0;
	}
	private boolean outsideMap(float x,float y,float z) {
		// TODO Auto-generated method stub
		return x<0||x>8||y<0||y>8||z<0;
	}
	
	
	
}

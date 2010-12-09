package gameCharactor;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import storageTools.MapDto;

import android.opengl.Matrix;
import android.os.SystemClock;

import edu.xiapei.Statics;
import edu.xiapei.GameMap.CubeDrawer;
import edu.xiapei.GameMap.Drawable;
import edu.xiapei.GameMap.GameMap;
import edu.xiapei.GameMap.MapElements;
import gameTools.GameTools;

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
	private float mass=1.0f;
	private float resistanceFactor=1.02f;
	private float jumpFactor=0.5f;
	private Drawable drawer;
	private long birthtime;
	private float[] initPos;
	private MapDto mdto;
	public GameCharactor(MapDto mdto){
		this.mdto = mdto;
		initPos = findInitPos();
		reset();
		drawer = new BallDrawer(size,0);
	}
	private float[] findInitPos() {
		int[][] tempType = GameTools.stringToArray(mdto.mapType);
		int[][] tempHeight = GameTools.stringToArray(mdto.mapHeight);
		
		float x=0.0f,y=0.0f,z=0.0f;
		for(int i=0;i<Statics.MAPWIDTH;i++){
			for(int j=0;j<Statics.MAPHIGHT;j++){
				if(tempType[i][j]==Statics.START){
					x = i+0.5f;
					y = j+0.5f;
					z = getHeight(i+0.5f, j+0.5f)+size;
				}
			}
		}
		return new float[]{x,y,z};
	}
	public GameCharactor(float mass, float resistanceFactor,float jumpFactor,int size,int x,int y) {
		birthtime = System.currentTimeMillis();
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
			if((speed[0]>=0&&getHeight(tempPos[0]+size/2,tempPos[1])-getHeight(posMatrix[12]+size/2,posMatrix[13])>0.2f)||
				(speed[0]<=0&&getHeight(tempPos[0]-size/2,tempPos[1])-getHeight(posMatrix[12]-size/2,posMatrix[13])>0.2f)){
				speed[0]=-speed[0]*jumpFactor;
				tempPos[0] = posMatrix[12]+speed[0];
			}
		}
		
		if(!outsideMap(tempPos[0],tempPos[1]+size/2,tempPos[2])){
			if((tempPos[1]>=posMatrix[13]&&getHeight(tempPos[0],tempPos[1]+size/2)-getHeight(posMatrix[12],posMatrix[13]+size/2)>0.2)||
				(tempPos[1]<=posMatrix[13]&&getHeight(tempPos[0],tempPos[1]-size/2)-getHeight(posMatrix[12],posMatrix[13]-size/2)>0.2)){
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
	public void reset() {
		// TODO Auto-generated method stub
		birthtime = System.currentTimeMillis();
		size = 0.4f;
		posMatrix[12] = initPos[0];
		posMatrix[13] = initPos[1];
		posMatrix[14] = initPos[2];
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
	}
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
	private boolean outsideMap(float x,float y,float z) {
		return x<0||x>Statics.MAPWIDTH-1||y<0||y>Statics.MAPHIGHT-1||z<0;
	}
	public long getLife(){
		return System.currentTimeMillis()- birthtime;
	}
	
	
}

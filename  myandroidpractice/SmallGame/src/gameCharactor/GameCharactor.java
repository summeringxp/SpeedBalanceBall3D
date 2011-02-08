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
	protected float force[] = new float[3];
	protected float speed[]= new float[3];
	protected float acceleration[]= new float[3];
	
	protected float size;
	private float mass=4.0f;
	private float resistanceFactor=1f;
	private float jumpFactor=0.5f;
	protected Drawable drawer;
	protected long birthtime;
	protected float[] initPos =new float[3];
	protected boolean isDied =false;
	protected MapDto mdto;
	
	public void setMdto(MapDto mdto) {
		this.mdto = mdto;
		reset();
	}
	public GameCharactor(MapDto mdto){
		this.mdto = mdto;
		
		reset();
		drawer = new BallDrawer(size,0);
	}
	protected void findInitPos(int type) {
		int[][] tempType = GameTools.stringToArray(mdto.mapType);
		//int[][] tempHeight = GameTools.stringToArray(mdto.mapHeight);
		
		
		for(int i=0;i<Statics.MAPWIDTH;i++){
			for(int j=0;j<Statics.MAPHIGHT;j++){
				if(tempType[j][i]==type){
					initPos[0] = i+0.5f;
					initPos[1] = j+0.5f;
					initPos[2] = getHeight(i+0.5f, j+0.5f)+size;
					return;
				}
			}
		}
		
	}
	public GameCharactor(float mass, float resistanceFactor,float jumpFactor,int size,int x,int y) {
		birthtime = System.currentTimeMillis();
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
		//gl.glMultMatrixf(this.posMatrix,0);
		
		gl.glTranslatef(posMatrix[12], posMatrix[13], posMatrix[14]);
		double theta1 = (posMatrix[12]-initPos[0])*720/Math.PI*size;
		double theta2 = (posMatrix[13]-initPos[1])*720/Math.PI*size;
		//Matrix.rotateM(this.posMatrix, 0, theta1, 0.0f, 1.0f, 0.0f);
		//Matrix.rotateM(this.posMatrix, 0, theta2, 1.0f, 0.0f, 0.0f);
		float initMatrix1[]=new float[]{
				1.0f,0.0f,0.0f,0.0f,
				0.0f,1.0f,0.0f,0.0f,
				0.0f,0.0f,1.0f,0.0f,
				0.0f,0.0f,0.0f,1.0f
			};
		float initMatrix2[]=new float[]{
				1.0f,0.0f,0.0f,0.0f,
				0.0f,1.0f,0.0f,0.0f,
				0.0f,0.0f,1.0f,0.0f,
				0.0f,0.0f,0.0f,1.0f
			};
		Matrix.rotateM(initMatrix1, 0, (float) (theta1*2), 0.0f, 1.0f, 0.0f);
		Matrix.rotateM(initMatrix2, 0, (float) (-theta2*2), 1.0f, 0.0f, 0.0f);
		//Matrix.rotateM(initMatrix, 0, (float) (theta2*2), (float)Math.cos(-theta1*Math.PI/180), 0.0f, (float)Math.sin(-theta1*Math.PI/180));
		gl.glMultMatrixf(initMatrix1, 0);
		gl.glMultMatrixf(initMatrix2, 0);
		drawer.draw(gl,t);
		gl.glPopMatrix();
		
		prepareForNextFrame(t);
	}
	private void prepareForNextFrame(long t) {
		// TODO Auto-generated method stub
		
		
		caculateForce();
		
		for(int i = 0;i<3;i++){
			force[i] = force[i]-resistanceFactor*getResis()*speed[i];
			acceleration[i]=force[i]/mass;
			speed[i]+=acceleration[i]*t;
			//speed[i]/=resistanceFactor;		
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
		int x = (int)Math.floor(posMatrix[12]);
		int y = (int)Math.floor(posMatrix[13]);
		int z = (int)Math.floor(posMatrix[14]);
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
			speed[2]=speed[2]>0.05f?speed[2]:0;
			
		}
		
		if(!outsideMap(tempPos[0]+size/2,tempPos[1],tempPos[2])){
			if(getHeight(tempPos[0]+size/2,tempPos[1])-posMatrix[14]>0.1f){
				
				if(speed[0]>0)speed[0]=-speed[0]*jumpFactor;
				tempPos[0] = posMatrix[12]+speed[0];
				tempPos[0] = (float) (tempPos[0]>(Math.ceil(tempPos[0])-size/2)?Math.ceil(tempPos[0])-size/2:tempPos[0]);
			}
			if(getHeight(tempPos[0]-size/2,tempPos[1])-posMatrix[14]>0.1f){
				if(speed[0]<0)speed[0]=-speed[0]*jumpFactor;
				tempPos[0] = posMatrix[12]+speed[0];
				tempPos[0] = (float) (tempPos[0]<(Math.floor(tempPos[0])+size/2)?Math.floor(tempPos[0])+size/2:tempPos[0]);
			}
		}
		
		if(!outsideMap(tempPos[0],tempPos[1]+size/2,tempPos[2])){
			if(getHeight(tempPos[0],tempPos[1]+size/2)-posMatrix[14]>0.1){
				if(speed[1]>0)speed[1]=-speed[1]*jumpFactor;
				tempPos[1] = posMatrix[13]+speed[1];
				tempPos[1] = (float) (tempPos[1]>(Math.ceil(tempPos[1])-size/2)?Math.ceil(tempPos[1])-size/2:tempPos[1]);
			}
			if(getHeight(tempPos[0],tempPos[1]-size/2)-posMatrix[14]>0.1){
				if(speed[1]<0)speed[1]=-speed[1]*jumpFactor;
				tempPos[1] = posMatrix[13]+speed[1];
				tempPos[1] = (float) (tempPos[1]<(Math.floor(tempPos[1])+size/2)?Math.floor(tempPos[1])+size/2:tempPos[1]);
			}
		}
		
		
		
		for(int i=0;i<3;i++){
			
			posMatrix[i+12]=Math.abs(tempPos[i]-4.0f)>30.0f?posMatrix[i+12]:tempPos[i];
			
		}
	}
	protected float getHeight(float x,float y){
		int x1 = (int) x;
		int y1 = (int) y;
		return GameMap.getGameMap().getElements(x1, y1).getHight(x-x1, y-y1);
	}
	private float getResis(){
		int x1 = (int) posMatrix[12];
		int y1 = (int) posMatrix[13];
		if(outsideMap(posMatrix[12],posMatrix[13],posMatrix[14])){
			return 0;
		}else return GameMap.getGameMap().getElements(x1, y1).getResistant();
	}
	private void dealDie() {
		// TODO Auto-generated method stub
		isDied=true;
	}
	public void reset() {
		// TODO Auto-generated method stub
		findInitPos(13);
		birthtime = System.currentTimeMillis();
		size = 0.25f;
		posMatrix[12] = initPos[0];
		posMatrix[13] = initPos[1];
		posMatrix[14] = initPos[2];
		force[0]=0.0f;force[1]=0.0f;force[2]=0.0f;
		speed[0]=0.0f;speed[1]=0.0f;speed[2]=0.0f;
		acceleration[0]=0.0f;acceleration[1]=0.0f;acceleration[2]=0.0f;
		isDied=false;
	}
	
	private void caculateForce() {
		//          O
		//         OOO
		//          O
		//
		int x = (int)Math.floor(posMatrix[12]);
		int y = (int)Math.floor(posMatrix[13]);
		int z = (int)Math.floor(posMatrix[14]);
		
		if(outsideMap(x,y,z)){
			force[2]-=0.005;
			return;
		}else if(getHeight(posMatrix[12], posMatrix[13])<=posMatrix[14]-size/2){
			force[2]-=0.005;
			float temp[] = GameMap.getGameMap().getElements(x, y).getForce();
			addForce(temp[0]/5,temp[1]/5,temp[2]/5);
		}	
	}
	public void addForce(float x, float y, float z) {
		// TODO Auto-generated method stub
		force[0]+=x;
		force[1]+=y;
		force[2]+=z;
	}
	public float[] getInitPos() {
		return initPos;
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
		return x<0||x>=Statics.MAPWIDTH||y<0||y>=Statics.MAPHIGHT||z<0;
	}
	public long getLife(){
		return System.currentTimeMillis()- birthtime;
	}
	public boolean isDied() {
		return isDied;
	}
	public void setDied(boolean isDied) {
		this.isDied = isDied;
	}
	
	
}

package edu.xiapei.GameMap;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.microedition.khronos.opengles.GL10;

import storageTools.MapDao;
import storageTools.MapDto;

import edu.xiapei.R;
import edu.xiapei.Statics;
import gameTools.GameTools;

public class GameMap {
	private int[][] mapType;
    private int[][] hight;
    private MapElements[][] elements;
    private BackGround bg;
    private static GameMap gm;

    private GameMap(MapDto mdto){
    	
    	mapType = GameTools.stringToArray(mdto.mapType);
    	hight= GameTools.stringToArray(mdto.mapHeight);
    	elements = new MapElements[Statics.MAPHIGHT][Statics.MAPWIDTH];
     	bg = new BackGround();
    	for(int i = 0;i<Statics.MAPHIGHT;i++){
    		for(int j = 0;j<Statics.MAPWIDTH;j++){
    			switch(mapType[i][j]){
    			case 0:elements[i][j] = new Hole(hight[i][j]);break;
    			case 1:elements[i][j] = new Cube(hight[i][j]);break;
    			case 2:elements[i][j] = new SlopNorth(hight[i][j]);break;
    			case 3:elements[i][j] = new SlopSouth(hight[i][j]);break;
    			case 4:elements[i][j] = new SlopWest(hight[i][j]);break;
    			case 5:elements[i][j] = new SlopEast(hight[i][j]);break;
    			case 6:elements[i][j] = new Accnorth(hight[i][j]);break;
    			case 7:elements[i][j] = new Accsouth(hight[i][j]);break;
    			case 8:elements[i][j] = new Accwest(hight[i][j]);break;
    			case 9:elements[i][j] = new Acceast(hight[i][j]);break;
    			case 10:elements[i][j] = new Mud(hight[i][j]);break;
    			case 11:elements[i][j] = new Target(hight[i][j]);break;
    			case 12:elements[i][j] = new Glass(hight[i][j]);break;
    			default:elements[i][j] = new Cube(hight[i][j]);
    			}
    		}
    	}
	}
    public void deleteMap(){
    	gm=null;
    }
    public static GameMap getGameMap(){
    	/*if(gm==null){
    		gm = new GameMap();
    	}*/
    	return gm;
    }
    public static GameMap getGameMap(MapDto mdto){
    	//if(gm==null){

				gm = new GameMap(mdto);
			
    	//}
    	return gm;
    }
	
	public void drawMapElements(GL10 gl, int step,long t) {
		// TODO Auto-generated method stub
		
		gl.glPushMatrix();
		for(int i = 0;i<Statics.MAPHIGHT;i++){
			
    		for(int j = 0;j<Statics.MAPWIDTH;j++){
    			
    			elements[i][j].draw(gl,t);
    			gl.glTranslatex(Statics.CELLSIZE, 0,0);
    		}
    		gl.glTranslatex(-Statics.CELLSIZE*Statics.MAPWIDTH,Statics.CELLSIZE, 0);
		}
		/*gl.glTranslatex(-17*Statics.CELLSIZE,-(Statics.MAPHIGHT+17)*Statics.CELLSIZE,-3*Statics.CELLSIZE);
		if(step==1){
		gl.glEnable(GL10.GL_TEXTURE_2D);
		glt.setTexture(R.drawable.icon1);
		bg.draw(gl);
		gl.glDisable(GL10.GL_TEXTURE_2D);
		}*/
		gl.glPopMatrix();
	}
	public int getMapType(int h,int w) {
		return mapType[w][h];
	}
	public MapElements getElements(int i,int j) {
		return elements[j][i];
	}
	public void setMapType(int h,int w,int value) {
		mapType[h][w] = value;
	}
	public int getHight(int h,int w) {
		return hight[w][h];
	}
	public void setHight(int h,int w,int value) {
		hight[w][h] = value;
	}
	
}

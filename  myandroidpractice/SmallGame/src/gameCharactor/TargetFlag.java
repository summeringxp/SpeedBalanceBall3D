package gameCharactor;

import javax.microedition.khronos.opengles.GL10;

import edu.xiapei.Statics;
import gameTools.GameTools;
import storageTools.MapDto;

public class TargetFlag extends GameCharactor{

	public TargetFlag(MapDto mdto) {
		super(mdto);
		//this.mdto = mdto;
		//reset();
		drawer = new FlagDrawer(this.getLife());
		// TODO Auto-generated constructor stub
	}
	
	public void reset() {
		// TODO Auto-generated method stub
		findInitPos(11);
		birthtime = System.currentTimeMillis();
		size = 0.8f;
		posMatrix[12] = initPos[0];
		posMatrix[13] = initPos[1];
		posMatrix[14] = initPos[2];
		force[0]=0.0f;force[1]=0.0f;force[2]=0.0f;
		speed[0]=0.0f;speed[1]=0.0f;speed[2]=0.0f;
		acceleration[0]=0.0f;acceleration[1]=0.0f;acceleration[2]=0.0f;
		isDied=false;
	}
	
	public void draw(GL10 gl,long t) {
		gl.glPushMatrix();
		//gl.glMultMatrixf(this.posMatrix,0);
		
		gl.glTranslatef(posMatrix[12], posMatrix[13], posMatrix[14]);
		gl.glRotatef(30*this.getLife()/1000, 0, 0, 1);
		drawer.draw(gl, System.currentTimeMillis()-birthtime);
		gl.glPopMatrix();
	}
}

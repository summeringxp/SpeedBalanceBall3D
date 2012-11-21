package gameTools;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import edu.xiapei.GameMap.Drawable;

public class MyLine implements Drawable{
	 private float[] color={133,133,133,255};//色
	    private float[] vertexs=new float[12];//頂点    
	    private float[] colors =new float[16];//色    
	    //private byte[] indics =new byte[6];
	    private FloatBuffer vfb;
	    private FloatBuffer cfb;
	    public MyLine(float x0,float y0,float x1,float y1){
	    	  //頂点配列情報
	         vertexs[0]= x0;vertexs[1]=-y0;vertexs[2]=0;
	         vertexs[3]= x1;vertexs[4]=-y1;vertexs[5]=0;     
	         
	         //カラー配列情報
	         for (int i=0;i<2;i++) {
	             colors[i*4  ]=color[0]/256.0f;
	             colors[i*4+1]=color[1]/256.0f;
	             colors[i*4+2]=color[2]/256.0f;
	             colors[i*4+3]=color[3]/256.0f;
	         }

	        //ラインの描画
	      //  gl.glBindTexture(GL10.GL_TEXTURE_2D,0);
	       

	          //ラインの描画
	        //  gl.glBindTexture(GL10.GL_TEXTURE_2D,0);
	           vfb = Graphics2D.makeFloatBuffer(vertexs);
	           cfb=Graphics2D.makeFloatBuffer(colors);
	          
	    }

		@Override
		public void draw(GL10 gl, long t) {
			// TODO Auto-generated method stub
			 gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		        gl.glVertexPointer(3,GL10.GL_FLOAT,0,vfb);
		        gl.glColorPointer(4,GL10.GL_FLOAT,0,cfb);
		        gl.glPushMatrix();
		            gl.glDrawArrays(GL10.GL_LINE_STRIP,0,2);
		        gl.glPopMatrix();
		}
}

package gameTools;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import edu.xiapei.GameMap.Drawable;

public class MyFillRect implements Drawable{
	 private float[] color={0,0,0,255};//色
	    private float[] vertexs=new float[12];//頂点    
	    private float[] colors =new float[16];//色    
	    private byte[] indics =new byte[6];
	    private FloatBuffer vfb;
	    private FloatBuffer cfb;
	    private ByteBuffer ibb;
	    public MyFillRect(float x,float y,float w,float h, int r, int g, int b, int a){
	    	   vertexs[0]= x;  vertexs[1] =-y;  vertexs[2] =0;
	           vertexs[3]= x;  vertexs[4] =-y-h;vertexs[5] =0;  
	           vertexs[6]= x+w;vertexs[7] =-y-h;vertexs[8] =0;
	           vertexs[9]= x+w;vertexs[10]=-y;  vertexs[11]=0;  
	           
	           //カラー配列情報
	           for (int i=0;i<4;i++) {
	               colors[i*4  ]=r/256.0f;
	               colors[i*4+1]=g/256.0f;
	               colors[i*4+2]=b/256.0f;
	               colors[i*4+3]=a/256.0f;
	           }
	           indics[0]=0; indics[1]=2; indics[2]=1; 
	           indics[3]=0;indics[4]=3;indics[5]=2;
	          //ラインの描画
	        //  gl.glBindTexture(GL10.GL_TEXTURE_2D,0);
	           vfb = Graphics2D.makeFloatBuffer(vertexs);
	           cfb=Graphics2D.makeFloatBuffer(colors);
	           ibb = ByteBuffer.allocateDirect(indics.length);
	           ibb.put(indics);
	           ibb.position(0);
	    }

		@Override
		public void draw(GL10 gl, long t) {
			// TODO Auto-generated method stub
			 gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
			 gl.glVertexPointer(3,GL10.GL_FLOAT,0,vfb);
		        gl.glColorPointer(4,GL10.GL_FLOAT,0,cfb);
		        gl.glPushMatrix();
		        	gl.glDrawElements(GL10.GL_TRIANGLES, 6, GL10.GL_UNSIGNED_BYTE, ibb);
		        // gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP,0,4);
		        gl.glPopMatrix();
		}
}

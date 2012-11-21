package gameCharactor;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import javax.microedition.khronos.opengles.GL10;

import edu.xiapei.Statics;
import edu.xiapei.GameMap.Drawable;
import gameTools.GLTextureFactory;

public class FlagDrawer implements Drawable {
	private IntBuffer   mVertexBuffer;
    private IntBuffer   mColorBuffer;
    private ByteBuffer  mIndexBuffer;
    float height=0;
	FlagDrawer(long t){
			
			int one = Statics.CELLSIZE;
	        int vertices[] = {
	                -one/2, 0, 0,
	                0, 0, 0,
	               0,0,one/2,
	               -one/2,0,one/2,
	               
	               0,0,0,
	               one/2,0,0,
	               one/2,0,one/2,
	               0,0,one/2,
	               
	               -one/2, 0, one/2,
	                0, 0, one/2,
	               0,0,one,
	               -one/2,0,one,
	               
	               0,0,one/2,
	               one/2,0,one/2,
	               one/2,0,one,
	               0,0,one
	        };

	        int colors[] = 
	        		{
	        
	        		0x00000,0x00000,0x00000,0x10000,
	        		0x00000,0x00000,0x00000,0x10000,
	        		0x00000,0x00000,0x00000,0x10000,
	        		0x00000,0x00000,0x00000,0x10000,
	        		
	        		0x10000,0x10000,0x10000,0x10000,
	        		0x10000,0x10000,0x10000,0x10000,
	        		0x10000,0x10000,0x10000,0x10000,
	        		0x10000,0x10000,0x10000,0x10000,
	        		
	        		
	        		0x10000,0x10000,0x10000,0x10000,
	        		0x10000,0x10000,0x10000,0x10000,
	        		0x10000,0x10000,0x10000,0x10000,
	        		0x10000,0x10000,0x10000,0x10000,
	        		0x00000,0x00000,0x00000,0x10000,
	        		0x00000,0x00000,0x00000,0x10000,
	        		0x00000,0x00000,0x00000,0x10000,
	        		0x00000,0x00000,0x00000,0x10000,
	        		
	        		
	        		};      	
	        byte indices[] = {
	                0, 1, 2,    0, 2, 3,
	                4, 5, 6,    4, 6, 7,
	                8, 9, 10,    8,10,11,
	                12,13,14,    12, 14, 15,
	        };
	        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length*4);
	        vbb.order(ByteOrder.nativeOrder());
	        mVertexBuffer = vbb.asIntBuffer();
	        mVertexBuffer.put(vertices);
	        mVertexBuffer.position(0);

	        ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length*4);
	        cbb.order(ByteOrder.nativeOrder());
	        mColorBuffer = cbb.asIntBuffer();
	        mColorBuffer.put(colors);
	        mColorBuffer.position(0);

	        mIndexBuffer = ByteBuffer.allocateDirect(indices.length);
	        mIndexBuffer.put(indices);
	        mIndexBuffer.position(0);
	       
	}
	@Override
	public void draw(GL10 gl,long t) {
		//gl.glFrontFace(GL10.GL_CW);
		//updateVertex(t);
        gl.glVertexPointer(3, GL10.GL_FIXED, 0, mVertexBuffer);
        gl.glColorPointer(4, GL10.GL_FIXED, 0, mColorBuffer);
        gl.glDisable(GL10.GL_CULL_FACE);
        gl.glDrawElements(GL10.GL_TRIANGLES, 24, GL10.GL_UNSIGNED_BYTE, mIndexBuffer);
		// TODO Auto-generated method stub
        gl.glEnable(GL10.GL_CULL_FACE);

	}
	/*private void updateVertex(long t) {
		// TODO Auto-generated method stub
		int one = Statics.CELLSIZE;
        int vertices[] = {
                -one/2, 0, (int) (Math.cos(t/500.0f)*one/4),
                0, 0, (int) (Math.cos(t/500.0f+1.57)*one/4),
               0,0,one/2+(int) (Math.cos(t/500.0f+1.57)*one/4),
               -one/2,0,one/2+(int) (Math.cos(t/500.0f)*one/4),
               
               0,0,(int) (Math.cos(t/500.0f+1.57)*one/4),
               one/2,0,(int) (Math.cos(t/500.0f+3.14)*one/4),
               one/2,0,one/2+(int) (Math.cos(t/500.0f+3.14)*one/4),
               0,0,one/2+(int) (Math.cos(t/500.0f+1.57)*one/4),
               
               -one/2, 0, one/2+(int) (Math.cos(t/500.0f)*one/4),
                0, 0, one/2+(int) (Math.cos(t/500.0f+1.57)*one/4),
               0,0,one+(int) (Math.cos(t/500.0f+1.57)*one/4),
               -one/2,0,one+(int) (Math.cos(t/500.0f)*one/4),
               
               0,0,one/2+(int) (Math.cos(t/500.0f+1.57)*one/4),
               one/2,0,one/2+(int) (Math.cos(t/500.0f+3.14)*one/4),
               one/2,0,one+(int) (Math.cos(t/500.0f+3.14)*one/4),
               0,0,one+(int) (Math.cos(t/500.0f+1.57)*one/4)
        };
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length*4);
        vbb.order(ByteOrder.nativeOrder());
        mVertexBuffer = vbb.asIntBuffer();
        mVertexBuffer.put(vertices);
        mVertexBuffer.position(0);
	}*/

}

package edu.xiapei.GameMap;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import javax.microedition.khronos.opengles.GL10;

import edu.xiapei.Statics;
import gameTools.GLTextureFactory;

public class SlopDrawer implements Drawable {
	int type;
	private IntBuffer   mVertexBuffer;
    private IntBuffer   mColorBuffer;
    private ByteBuffer  mIndexBuffer;
    private IntBuffer  mTextureBuffer;
    int height;
	SlopDrawer(int height,int d){
		this.height = height;
			type = d;
			int one = Statics.CELLSIZE;
	        int vertices[] = {
	                0, 0, 0,
	                one, 0, 0,
	                one,  one, 0,
	                0,  one, 0,
	                0, 0,  ((d==0||d==1?2:1)+height)*Statics.CELLSIZE/2,
	                one, 0,  ((d==1||d==2?2:1)+height)*Statics.CELLSIZE/2,
	                one,  one,  ((d==2||d==3?2:1)+height)*Statics.CELLSIZE/2,
	                0,  one,  ((d==0||d==3?2:1)+height)*Statics.CELLSIZE/2,
	        };

	        int colors[] = 
	        		{
	        
	        		0x01000,0x01000,0x01000,0x10000,
        			0x01000,0x01000,0x01000,0x10000,
        			0x01000,0x01000,0x01000,0x10000,
        			0x01000,0x01000,0x01000,0x10000,
        			0x0C000+(height+1)*0x00A00,0x0C000+(height+1)*0x00A00,0x0C000+(height+1)*0x00A00,0x10000,
        			0x0C000+(height+1)*0x00A00,0x0C000+(height+1)*0x00A00,0x0C000+(height+1)*0x00A00,0x10000,
        			0x0C000+height*0x00A00,0x0C000+height*0x00A00,0x0C000+height*0x00A00,0x10000,
        			0x0C000+height*0x00A00,0x0C000+height*0x00A00,0x0C000+height*0x00A00,0x10000		
        		
	        		};      	
	        byte indices[] = {
	                0, 4, 5,    0, 5, 1,
	                1, 5, 6,    1, 6, 2,
	                2, 6, 7,    2, 7, 3,
	                3, 7, 4,    3, 4, 0,
	                4, 7, 6,    4, 6, 5,
	                /*3, 0, 1,    3, 1, 2*/
	        };
 int texCoords[] = {
	                
	        		0,one,one,one,one,0,0,0,
	                0, one, one, one, one, 0, 0, 0};

	        // Buffers to be passed to gl*Pointer() functions
	        // must be direct, i.e., they must be placed on the
	        // native heap where the garbage collector cannot
	        // move them.
	        //
	        // Buffers with multi-byte datatypes (e.g., short, int, float)
	        // must have their byte order set to native order

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
	        
	        ByteBuffer tbb = ByteBuffer.allocateDirect(texCoords.length * 4);
			   tbb.order(ByteOrder.nativeOrder());
			   mTextureBuffer = tbb.asIntBuffer();
			   mTextureBuffer.put(texCoords);
			   mTextureBuffer.position(0);
	}
	@Override
	public void draw(GL10 gl,long t) {
		gl.glFrontFace(GL10.GL_CW);
        gl.glVertexPointer(3, GL10.GL_FIXED, 0, mVertexBuffer);
        gl.glColorPointer(4, GL10.GL_FIXED, 0, mColorBuffer);
        gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glTexCoordPointer(2, GL10.GL_FIXED, 0, mTextureBuffer);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, GLTextureFactory.BLOCK);
        gl.glDrawElements(GL10.GL_TRIANGLES, 30, GL10.GL_UNSIGNED_BYTE, mIndexBuffer);
		// TODO Auto-generated method stub
        gl.glDisable(GL10.GL_TEXTURE_2D);

	}

}

package gameCharactor;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import javax.microedition.khronos.opengles.GL10;

import edu.xiapei.Statics;
import edu.xiapei.GameMap.Drawable;

public class DefaultCharDrawer implements Drawable {
	private IntBuffer   mVertexBuffer;
    private IntBuffer   mColorBuffer;
    private ByteBuffer  mIndexBuffer;
	public DefaultCharDrawer(float size){
		int one = Statics.CELLSIZE;
		int s = (int) (one*size)/2;
        int vertices[] = {
        		-s, -s, -s,
        		s, s, -s,
        		-s, s, s,
        		s, -s, s,
                
                
        };

        int colors[] = 
        		{
        
        			0x00000,0x00000,0x00000,0x10000,
        			0x10000,0x00000,0x00000,0x10000,
        			0x00000,0x00000,0x00000,0x10000,
        			0x10000,0x10000,0x10000,0x10000,
        				
        		};      	
        byte indices[] = {
                0, 1, 2,    3, 2, 1,
                0, 2, 3,    3, 1, 0,
                
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
		gl.glFrontFace(GL10.GL_CW);
        gl.glVertexPointer(3, GL10.GL_FIXED, 0, mVertexBuffer);
        gl.glColorPointer(4, GL10.GL_FIXED, 0, mColorBuffer);
        gl.glDrawElements(GL10.GL_TRIANGLES, 12, GL10.GL_UNSIGNED_BYTE, mIndexBuffer);
		// TODO Auto-generated method stub
		
	}

}

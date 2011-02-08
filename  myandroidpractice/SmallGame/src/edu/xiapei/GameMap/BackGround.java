package edu.xiapei.GameMap;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import javax.microedition.khronos.opengles.GL10;

import edu.xiapei.Statics;

public class BackGround implements Drawable {
	private IntBuffer   mVertexBuffer;
	private IntBuffer 	mTextureBuffer;
	private IntBuffer   mColorBuffer;
    private ByteBuffer  mIndexBuffer;
	public BackGround() {
		 int one = Statics.CELLSIZE;
		   int vertices[] = {
	                0, 0, 0,
	                one*50, 0, 0,
	                one*50,  one*50, 0,
	                0,  one*50, 0,
	        };
		   int colors[] = {
				   one, one, one, one,
				   one, one, one, one,
				   one, one, one, one,
				   one, one, one, one,

				   };
		   int texCoords[] = {
	                // FRONT
	                0, one, one, one, 0, 0, one, 0};
		   byte indices[] = {
	                2, 1, 0,    3, 2, 0,
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

		   ByteBuffer tbb = ByteBuffer.allocateDirect(texCoords.length * 4);
		   tbb.order(ByteOrder.nativeOrder());
		   mTextureBuffer = tbb.asIntBuffer();
		   mTextureBuffer.put(texCoords);
		   mTextureBuffer.position(0);
	}

	@Override
	public void draw(GL10 gl,long t) {
		// TODO Auto-generated method stub
		
		gl.glFrontFace(GL10.GL_CW);
        gl.glVertexPointer(3, GL10.GL_FIXED, 0, mVertexBuffer);
        gl.glColorPointer(4, GL10.GL_FIXED, 0, mColorBuffer);
        gl.glTexCoordPointer(2, GL10.GL_FIXED, 0, mTextureBuffer);
        gl.glDrawElements(GL10.GL_TRIANGLES, 6, GL10.GL_UNSIGNED_BYTE, mIndexBuffer);
       // gl.glDisable(GL10.GL_TEXTURE_2D);
	}

}

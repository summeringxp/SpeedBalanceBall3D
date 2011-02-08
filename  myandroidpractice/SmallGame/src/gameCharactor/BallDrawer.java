package gameCharactor;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

import edu.xiapei.Statics;
import edu.xiapei.GameMap.Drawable;

public class BallDrawer implements Drawable {

	
	private int segW = 8;
	private int segH = 8;
	private int mRadius = Statics.CELLSIZE/4;
	private IntBuffer   mVertexBuffer;
    private IntBuffer   mColorBuffer;
    private ByteBuffer  mIndexBuffer;
	private int gridU = segW;
	private int gridV = segH;
	public BallDrawer(float size, int type) {
		mRadius = (int) (size*Statics.CELLSIZE)/2;
		int gridU1 = gridU + 1;
		int gridV1 = gridV + 1;
		int incU = 360 / gridU;
		int incV = 2 * mRadius / gridV;
		int cnt;

					
		//vertices
		int[] vertices = new int[(2 + (gridV1-2) * gridU) * 3];
		cnt = 0;
		vertices[cnt++] = 0;
		vertices[cnt++] = -mRadius;
		vertices[cnt++] = 0;
		double d = mRadius;
		double y, t, r;
		for( int iv = 1; iv < gridV1 - 1; ++ iv ) {
			y = iv * incV - d;			
			r = Math.sqrt(d * d - y * y);
			for( int iu = 0; iu < gridU; ++ iu ) {
				t = iu * incU * Math.PI / 180; 
				vertices[cnt++] = (int)(r * Math.cos(t));
				vertices[cnt++] = (int)y;
				vertices[cnt++] = (int)(r * Math.sin(t));
			}
		}
		vertices[cnt++] = 0;
		vertices[cnt++] = mRadius;
		vertices[cnt++] = 0;			

		//indices
		byte[] indices = new byte[((gridV-1) * gridU * 2) * 3];
		cnt = 0;
		for( int iu = 0; iu < gridU; ++ iu ) {
			indices[cnt++] = 0;				
			indices[cnt++] = (byte)((iu + 1) % gridU + 1);
			indices[cnt++] = (byte)(iu + 1);
		}
		for( int iv = 1; iv < gridV1 - 2; ++ iv ) {
			for( int iu = 0; iu < gridU; ++ iu ) {
				int m = (iv - 1) * gridU;
				//Triangle A
				indices[cnt++] = (byte)(iu + 1 + m);
				indices[cnt++] = (byte)((iu + 1) % gridU + 1 + m);
				indices[cnt++] = (byte)(iu + 1 + gridU + m);
				
				//Triangle B
				indices[cnt++] = (byte)((iu + 1) % gridU + 1 + gridU + m);
				indices[cnt++] = (byte)(iu + 1 + gridU + m);
				indices[cnt++] = (byte)((iu + 1) % gridU + 1 + m);
			}
		}

		int n = (2 + (gridV1-2) * gridU) - 1;
		for( int iu = n - gridU; iu < n; ++ iu ) {
			indices[cnt++] = (byte)iu;
			indices[cnt++] = (byte)(iu % gridU + n - gridU );				
			indices[cnt++] = (byte)n;
		}

		int[] colors = new int[(2 + (gridV1-2) * gridU) * 4];
		cnt = 0;
		Random rand = new Random();
		for( int i1 = 0; i1 < colors.length; i1 += 4 ) {
			colors[cnt++] = rand.nextInt();
			colors[cnt++] = rand.nextInt();
			colors[cnt++] = rand.nextInt();
			colors[cnt++] = 0x10000;
		}
		
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
		// TODO Auto-generated constructor stub
	}
	public void draw(GL10 gl,long t) {
		gl.glFrontFace(GL10.GL_CW);
        gl.glVertexPointer(3, GL10.GL_FIXED, 0, mVertexBuffer);
        gl.glColorPointer(4, GL10.GL_FIXED, 0, mColorBuffer);
        gl.glDrawElements(GL10.GL_TRIANGLES, ((gridV-1) * gridU * 2) * 3, GL10.GL_UNSIGNED_BYTE, mIndexBuffer);
	}

}

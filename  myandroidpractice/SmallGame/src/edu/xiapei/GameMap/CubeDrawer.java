package edu.xiapei.GameMap;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import javax.microedition.khronos.opengles.GL10;

import edu.xiapei.Statics;
import gameTools.GLTextureFactory;

public class CubeDrawer implements Drawable{
	private IntBuffer   mVertexBuffer;
    private IntBuffer   mColorBuffer;
    private ByteBuffer  mIndexBuffer;
    private IntBuffer  mTextureBuffer;
    int type;
    int height;
    
    
	public CubeDrawer(int height,int t){
		type = t;
		this.height = height;
		 int one = Statics.CELLSIZE;
	        int vertices[] = {
	                0, 0, 0,
	                one, 0, 0,
	                one,  one, 0,
	                0,  one, 0,
	                0, 0,  (one+height*Statics.CELLSIZE)/2,
	                one, 0,  (one+height*Statics.CELLSIZE)/2,
	                one,  one,  (one+height*Statics.CELLSIZE)/2,
	                0,  one,  (one+height*Statics.CELLSIZE)/2,
	        };
	        

	        int colors[][] = {
	        		{
	        
	        			0x01000,0x01000,0x01000,0x10000,
	        			0x01000,0x01000,0x01000,0x10000,
	        			0x01000,0x01000,0x01000,0x10000,
	        			0x01000,0x01000,0x01000,0x10000,
	        			0x0C000+height*0x00A00,0x0C000+height*0x00A00,0x0C000+height*0x00A00,0x10000,
	        			0x0C000+height*0x00A00,0x0C000+height*0x00A00,0x0C000+height*0x00A00,0x10000,
	        			0x0C000+height*0x00A00,0x0C000+height*0x00A00,0x0C000+height*0x00A00,0x10000,
	        			0x0C000+height*0x00A00,0x0C000+height*0x00A00,0x0C000+height*0x00A00,0x10000		
	        		},
	        		{
	        	        
	        			0x04000,0x04000,0x02000,0x10000,
	        			0x04000,0x04000,0x02000,0x10000,
	        			0x04000,0x04000,0x02000,0x10000,
	        			0x04000,0x04000,0x02000,0x10000,
	        			0x08000,0x08000+height*0x00A00,0x04000,0x10000,
	        			0x08000,0x08000+height*0x00A00,0x04000,0x10000,
	        			0x08000,0x08000+height*0x00A00,0x04000,0x10000,
	        			0x08000,0x08000+height*0x00A00,0x04000,0x10000 		
	        		},
	        		{
	        	        
	        			0x10000,0x10000,0x10000,0x10000,
	        			0x10000,0x10000,0x10000,0x10000,
	        			0x10000,0x10000,0x10000,0x10000,
	        			0x10000,0x10000,0x10000,0x10000,
	        			0x10000,0x10000,0x10000,0x10000,
	        			0x10000,0x10000,0x10000,0x10000,
	        			0x10000,0x10000,0x10000,0x10000,
	        			0x10000,0x10000,0x10000,0x10000 	
	        		},
{
	        	        
	        			0x00000,0x00000,0x10000,0x10000,
	        			0x00000,0x00000,0x10000,0x10000,
	        			0x00000,0x00000,0x10000,0x10000,
	        			0x00000,0x00000,0x10000,0x10000,
	        			0x02000,0x30000,0x10000,0x10000,
	        			0x02000,0x30000,0x10000,0x10000,
	        			0x02000,0x30000,0x10000,0x10000,
	        			0x02000,0x30000,0x10000,0x10000 		
	        		},
{
	        	        
	        			0x00000,0x00000,0x10000,0x10000,
	        			0x00000,0x00000,0x10000,0x10000,
	        			0x00000,0x00000,0x10000,0x10000,
	        			0x00000,0x00000,0x10000,0x10000,
	        			0x02000,0x30000,0x10000,0x10000,
	        			0x02000,0x30000,0x10000,0x10000,
	        			0x02000,0x30000,0x10000,0x10000,
	        			0x02000,0x30000,0x10000,0x10000 		
	        		},
{
	        	        
	        			0x00000,0x00000,0x10000,0x10000,
	        			0x00000,0x00000,0x10000,0x10000,
	        			0x00000,0x00000,0x10000,0x10000,
	        			0x00000,0x00000,0x10000,0x10000,
	        			0x02000,0x30000,0x10000,0x10000,
	        			0x02000,0x30000,0x10000,0x10000,
	        			0x02000,0x30000,0x10000,0x10000,
	        			0x02000,0x30000,0x10000,0x10000 		
	        		},
{
	        	        
	        			0x00000,0x00000,0x10000,0x10000,
	        			0x00000,0x00000,0x10000,0x10000,
	        			0x00000,0x00000,0x10000,0x10000,
	        			0x00000,0x00000,0x10000,0x10000,
	        			0x02000,0x30000,0x10000,0x10000,
	        			0x02000,0x30000,0x10000,0x10000,
	        			0x02000,0x30000,0x10000,0x10000,
	        			0x02000,0x30000,0x10000,0x10000 		
	        		},
{
	        	        
	        			0x06000,0x06000,0x03000,0x10000,
	        			0x06000,0x06000,0x03000,0x10000,
	        			0x06000,0x06000,0x03000,0x10000,
	        			0x06000,0x06000,0x03000,0x10000,
	        			0x0C000,0x0C000,0x06000,0x10000,
	        			0x0C000,0x0C000,0x06000,0x10000,
	        			0x0C000,0x0C000,0x06000,0x10000,
	        			0x0C000,0x0C000,0x06000,0x10000 			
	        		}};
	        

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
 int texCoords2[] = {
	                
	        		one,one,one,0,0,0,0,one,
	        		one,one,one,0,0,0,0,one,};
 int texCoords3[] = {
         
 		one,0,0,0,0,one,one,one,
 		one,0,0,0,0,one,one,one,};
 int texCoords4[] = {
         
		 0,0,0,one,one,one,one,0,
		 0,0,0,one,one,one,one,0,};

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

	        ByteBuffer cbb = ByteBuffer.allocateDirect(colors[type].length*4);
	        cbb.order(ByteOrder.nativeOrder());
	        mColorBuffer = cbb.asIntBuffer();
	        mColorBuffer.put(colors[type]);
	        mColorBuffer.position(0);

	        mIndexBuffer = ByteBuffer.allocateDirect(indices.length);
	        mIndexBuffer.put(indices);
	        mIndexBuffer.position(0);
	        
	        
	        ByteBuffer tbb ;
	        tbb=ByteBuffer.allocateDirect(texCoords.length * 4);
	        tbb.order(ByteOrder.nativeOrder());
			mTextureBuffer = tbb.asIntBuffer();
	        if(type==5){
	        	mTextureBuffer.put(texCoords3);
	        }else if(type==4){
	        	mTextureBuffer.put(texCoords2);
	        }else if(type==3){
	        	mTextureBuffer.put(texCoords4);
	        }else{
	        	mTextureBuffer.put(texCoords);
	        }
			 
			   
			   mTextureBuffer.position(0);
	}
	@Override
	public void draw(GL10 gl,long t) {
		// TODO Auto-generated method stub
		gl.glFrontFace(GL10.GL_CW);
        gl.glVertexPointer(3, GL10.GL_FIXED, 0, mVertexBuffer);
        gl.glColorPointer(4, GL10.GL_FIXED, 0, mColorBuffer);
        gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glTexCoordPointer(2, GL10.GL_FIXED, 0, mTextureBuffer);
		if(type==7){
			gl.glBindTexture(GL10.GL_TEXTURE_2D, GLTextureFactory.MUD);
		}else if(type==2){
			gl.glBindTexture(GL10.GL_TEXTURE_2D, GLTextureFactory.ICE);
		}else if(type==3||type==4||type==5||type==6){
			gl.glBindTexture(GL10.GL_TEXTURE_2D, GLTextureFactory.ARROW);
		}else{
		gl.glBindTexture(GL10.GL_TEXTURE_2D, GLTextureFactory.BLOCK);
		}
		gl.glDrawElements(GL10.GL_TRIANGLES, 30, GL10.GL_UNSIGNED_BYTE, mIndexBuffer);
        gl.glDisable(GL10.GL_TEXTURE_2D);
	}

}

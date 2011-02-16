package gameTools;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import javax.microedition.khronos.opengles.GL10;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Paint;

//OpenGL�ɂ��2D�O���t�B�b�N�X�I�u�W�F�N�g
public class Graphics2D {
    //�e�N�X�`�����_���
    private final static float[] panelVertices=new float[]{
         0,  0, //����
         0, -1, //����
         1,  0, //�E��
         1, -1, //�E��
    };

    //�e�N�X�`��UV���
    private final static float[] panelUVs=new float[]{
        0.0f, 0.0f, //����
        0.0f, 1.0f, //����
        1.0f, 0.0f, //�E��
        1.0f, 1.0f, //�E��
    }; 

    //���
    private GL10  gl;               //�O���t�B�b�N�X    
    private float[] color={0,0,0,255};//�F

    //���[�N
    private float[] vertexs=new float[4*3];//���_    
    private float[] colors =new float[4*4];//�F    
    private byte[] indics =new byte[6];

//====================
//������
//====================
    //�R���X�g���N�^
    public Graphics2D(GL10 gl) {
        this.gl=gl;
    }
    
    //������
    public void init(int w,int h) {
        //�r���[�|�[�g�ϊ�
        gl.glViewport(0,0,w,h);
        
        //���e�ϊ�
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrthof(-w/2,w/2,-h/2,h/2,-100,100);    
        gl.glTranslatef(-w/2,h/2,0);
        
        //���f�����O�ϊ�    
        gl.glMatrixMode(GL10.GL_MODELVIEW);

        //�N���A�F�̐ݒ�
        gl.glClearColor(1,1,1,1);
        
        //���_�z��̐ݒ�
        gl.glVertexPointer(2,GL10.GL_FLOAT,0,makeFloatBuffer(panelVertices));
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        
        //UV�̐ݒ�
        gl.glTexCoordPointer(2,GL10.GL_FLOAT,0,makeFloatBuffer(panelUVs));
            
        //�e�N�X�`���̐ݒ�
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glEnable(GL10.GL_TEXTURE_2D);
            
        //�u�����h�̐ݒ�
        gl.glBlendFunc(GL10.GL_SRC_ALPHA,GL10.GL_ONE_MINUS_SRC_ALPHA);
       gl.glEnable(GL10.GL_BLEND);

        //�|�C���g�̐ݒ�
        gl.glEnable(GL10.GL_POINT_SMOOTH);        
    }

//====================
//�A�N�Z�X
//====================
    //�N���A
    public void clear() {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
    }
    
    //���C�����̎w��
    public void setLineWidth(float lineWidth) {
        gl.glLineWidth(lineWidth);
        gl.glPointSize(lineWidth*0.6f);
    }

    //�F�̎w��
    public void setColor(int r,int g,int b,int a) {
        color[0]=r/256.0f;
        color[1]=g/256.0f;
        color[2]=b/256.0f;
        color[3]=a/256.0f;
    }

    //�F�̎w��
    public void setColor(int r,int g,int b) {
        setColor(r,g,b,256);
    }

    //���s�ړ��̎w��@
    public void setTranslate(float x,float y) {
        gl.glTranslatef(x,-y,0);
    }

    //��]�p�x�̎w��
    public void setRotate(float rotate) {
        gl.glRotatef((float)(rotate*(-180)/Math.PI),0,0,1);
    }

    //�X�P�[���̎w��
    public void setScale(float w,float h) {
        gl.glScalef(w,h,1);
    }
    
    //�s��̃v�b�V��
    public void pushMatrix() {
        gl.glPushMatrix();
    }

    //�s��̃|�b�v
    public void popMatrix() {
        gl.glPopMatrix();
    }

//====================
//�`��
//====================
    //���C���̕`��
    public void drawLine(float x0,float y0,float x1,float y1) {
         //���_�z����
         vertexs[0]= x0;vertexs[1]=-y0;vertexs[2]=0;
         vertexs[3]= x1;vertexs[4]=-y1;vertexs[5]=0;     
         
         //�J���[�z����
         for (int i=0;i<2;i++) {
             colors[i*4  ]=color[0];
             colors[i*4+1]=color[1];
             colors[i*4+2]=color[2];
             colors[i*4+3]=color[3];
         }

        //���C���̕`��
      //  gl.glBindTexture(GL10.GL_TEXTURE_2D,0);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        gl.glVertexPointer(3,GL10.GL_FLOAT,0,makeFloatBuffer(vertexs));
        gl.glColorPointer(4,GL10.GL_FLOAT,0,makeFloatBuffer(colors));
        gl.glPushMatrix();
            gl.glDrawArrays(GL10.GL_LINE_STRIP,0,2);
        gl.glPopMatrix();
    }

    //�|�����C���̕`��
    public void drawPolyline(float[] x,float y[],int length) {
        //���_�z����
        for (int i=0;i<length;i++) {
            vertexs[i*3+0]= x[i];
            vertexs[i*3+1]=-y[i];
            vertexs[i*3+2]=0;
        }
         
        //�J���[�z����
        for (int i=0;i<length;i++) {
            colors[i*4  ]=color[0];
            colors[i*4+1]=color[1];
            colors[i*4+2]=color[2];
            colors[i*4+3]=color[3];
        }

        //���C���̕`��
     //   gl.glBindTexture(GL10.GL_TEXTURE_2D,0);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        gl.glVertexPointer(3,GL10.GL_FLOAT,0,makeFloatBuffer(vertexs));
        gl.glColorPointer(4,GL10.GL_FLOAT,0,makeFloatBuffer(colors));
        gl.glPushMatrix();
            gl.glDrawArrays(GL10.GL_LINE_STRIP,0,length);
            gl.glDrawArrays(GL10.GL_POINTS,0,length);
        gl.glPopMatrix();
    }
    
    //��`�̕`��
    public void drawRect(float x,float y,float w,float h) {
         //���_�z����
         vertexs[0]= x;  vertexs[1] =-y;  vertexs[2] =0;
         vertexs[3]= x;  vertexs[4] =-y-h;vertexs[5] =0;  
         vertexs[6]= x+w;vertexs[7] =-y-h;vertexs[8] =0;
         vertexs[9]= x+w;vertexs[10]=-y;  vertexs[11]=0;  
         
         //�J���[�z����
         for (int i=0;i<4;i++) {
             colors[i*4  ]=color[0];
             colors[i*4+1]=color[1];
             colors[i*4+2]=color[2];
             colors[i*4+3]=color[3];
         }

        //���C���̕`��
      //  gl.glBindTexture(GL10.GL_TEXTURE_2D,0);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        gl.glVertexPointer(3,GL10.GL_FLOAT,0,makeFloatBuffer(vertexs));
        gl.glColorPointer(4,GL10.GL_FLOAT,0,makeFloatBuffer(colors));
        gl.glPushMatrix();
            gl.glDrawArrays(GL10.GL_LINE_LOOP,0,4);
            gl.glDrawArrays(GL10.GL_POINTS,0,4);
        gl.glPopMatrix();
    }

    //��`�̓h��ׂ�
    public void fillRect(float x,float y,float w,float h) {
         //���_�z����
         vertexs[0]= x;  vertexs[1] =-y;  vertexs[2] =0;
         vertexs[3]= x;  vertexs[4] =-y-h;vertexs[5] =0;  
         vertexs[6]= x+w;vertexs[7] =-y;  vertexs[8] =0;
         vertexs[9]= x+w;vertexs[10]=-y-h;vertexs[11]=0;  
         
         //�J���[�z����
         for (int i=0;i<4;i++) {
             colors[i*4  ]=color[0];
             colors[i*4+1]=color[1];
             colors[i*4+2]=color[2];
             colors[i*4+3]=color[3];
         }
         indics[0]=0; indics[1]=2; indics[2]=1; 
         indics[3]=1;indics[4]=2;indics[5]=3;
        //�O�p�`�̕`��
       // gl.glBindTexture(GL10.GL_TEXTURE_2D,GLTextureFactory.ICE);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        gl.glVertexPointer(3,GL10.GL_FLOAT,0,makeFloatBuffer(vertexs));
        gl.glColorPointer(4,GL10.GL_FLOAT,0,makeFloatBuffer(colors));
        ByteBuffer mIndexBuffer = ByteBuffer.allocateDirect(indics.length);
        mIndexBuffer.put(indics);
        mIndexBuffer.position(0);
        gl.glPushMatrix();
        	gl.glDrawElements(GL10.GL_TRIANGLES, 6, GL10.GL_UNSIGNED_BYTE, mIndexBuffer);
        // gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP,0,4);
        gl.glPopMatrix();
    }

    //�~�̕`��
    public void drawCircle(float x,float y,float r) {
        int length=100;
        
        //���_�z����
        for (int i=0;i<length;i++) {
            float angle=(float)(2*Math.PI*i/length);
            vertexs[i*3+0]=(float)( x+Math.cos(angle)*r);
            vertexs[i*3+1]=(float)(-y+Math.sin(angle)*r);
            vertexs[i*3+2]=0;
        }
         
        //�J���[�z����
        for (int i=0;i<length;i++) {
            colors[i*4  ]=color[0];
            colors[i*4+1]=color[1];
            colors[i*4+2]=color[2];
            colors[i*4+3]=color[3];
        }

        //���C���̕`��
       // gl.glBindTexture(GL10.GL_TEXTURE_2D,0);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        gl.glVertexPointer(3,GL10.GL_FLOAT,0,makeFloatBuffer(vertexs));
        gl.glColorPointer(4,GL10.GL_FLOAT,0,makeFloatBuffer(colors));
        gl.glPushMatrix();
            gl.glDrawArrays(GL10.GL_LINE_LOOP,0,length);
            gl.glDrawArrays(GL10.GL_POINTS,0,length);
        gl.glPopMatrix();
    }

    //�~�̓h��ׂ�
    public void fillCircle(float x,float y,float r) {
        int length=100+2;
        
        //���_�z����
        vertexs[0]= x;
        vertexs[1]=-y;
        vertexs[2]=0;
        for (int i=1;i<length;i++) {
            float angle=(float)(2*Math.PI*i/(length-2));
            vertexs[i*3+0]=(float)( x+Math.cos(angle)*r);
            vertexs[i*3+1]=(float)(-y+Math.sin(angle)*r);
            vertexs[i*3+2]=0;
        }
         
        //�J���[�z����
        for (int i=0;i<length;i++) {
            colors[i*4  ]=color[0];
            colors[i*4+1]=color[1];
            colors[i*4+2]=color[2];
            colors[i*4+3]=color[3];
        }

        //���C���̕`��
       // gl.glBindTexture(GL10.GL_TEXTURE_2D,0);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        gl.glVertexPointer(3,GL10.GL_FLOAT,0,makeFloatBuffer(vertexs));
        gl.glColorPointer(4,GL10.GL_FLOAT,0,makeFloatBuffer(colors));
        gl.glPushMatrix();
            gl.glDrawArrays(GL10.GL_TRIANGLE_FAN,0,length);
        gl.glPopMatrix();
    }

    //�O�p�`�̕`��
    public void drawTriangle(float[] x,float[] y) {
         //���_�z����
         int length=3;
         for (int i=0;i<length;i++) {
             vertexs[i*3+0]= x[i];
             vertexs[i*3+1]=-y[i];
             vertexs[i*3+2]=0;
         }
         
         //�J���[�z����
         for (int i=0;i<length;i++) {
             colors[i*4  ]=color[0];
             colors[i*4+1]=color[1];
             colors[i*4+2]=color[2];
             colors[i*4+3]=color[3];
         }

        //���C���̕`��
     //   gl.glBindTexture(GL10.GL_TEXTURE_2D,0);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        gl.glVertexPointer(3,GL10.GL_FLOAT,0,makeFloatBuffer(vertexs));
        gl.glColorPointer(4,GL10.GL_FLOAT,0,makeFloatBuffer(colors));
        gl.glPushMatrix();
            gl.glDrawArrays(GL10.GL_LINE_LOOP,0,length);
            gl.glDrawArrays(GL10.GL_POINTS,0,length);
        gl.glPopMatrix();
    }

    //�O�p�`�̓h��ׂ�
    public void fillTriangle(float[] x,float[] y) {
         //���_�z����
         int length=3;
         for (int i=0;i<length;i++) {
             vertexs[i*3+0]= x[i];
             vertexs[i*3+1]=-y[i];
             vertexs[i*3+2]=0;
         }
         
         //�J���[�z����
         for (int i=0;i<length;i++) {
             colors[i*4  ]=color[0];
             colors[i*4+1]=color[1];
             colors[i*4+2]=color[2];
             colors[i*4+3]=color[3];
         }

        //���C���̕`��
    //    gl.glBindTexture(GL10.GL_TEXTURE_2D,0);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        gl.glVertexPointer(3,GL10.GL_FLOAT,0,makeFloatBuffer(vertexs));
        gl.glColorPointer(4,GL10.GL_FLOAT,0,makeFloatBuffer(colors));
        gl.glPushMatrix();
            gl.glDrawArrays(GL10.GL_LINE_LOOP,0,length);
            gl.glDrawArrays(GL10.GL_TRIANGLES,0,length);
            gl.glDrawArrays(GL10.GL_POINTS,0,length);
        gl.glPopMatrix();
    }    

    //�e�N�X�`���̕`��
    public void drawTexture(Texture texture,float x,float y) {
        gl.glBindTexture(GL10.GL_TEXTURE_2D,texture.name);
        gl.glVertexPointer(2,GL10.GL_FLOAT,0,makeFloatBuffer(panelVertices));
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
        gl.glPushMatrix();
            gl.glTranslatef(x,-y,0);
            gl.glScalef(texture.size,texture.size,1);
            gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP,0,4);
        gl.glPopMatrix();
    }

    //�e�N�X�`���̕`��
    public void drawTexture(Texture texture,float x,float y,float w,float h) {
        gl.glBindTexture(GL10.GL_TEXTURE_2D,texture.name);
        gl.glVertexPointer(2,GL10.GL_FLOAT,0,makeFloatBuffer(panelVertices));
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
        gl.glPushMatrix();
            gl.glTranslatef(x,-y,0);
            gl.glScalef(w*texture.size/texture.width,h*texture.size/texture.height,1);
            gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP,0,4);
        gl.glPopMatrix();
    }    
    
//====================
//�e�N�X�`���̐���
//===================
    //�e�N�X�`���̓ǂݍ���
    public Texture makeTexture(Bitmap bitmap) {
        //���T�C�Y
        int w=(bitmap.getWidth()>bitmap.getHeight())?bitmap.getWidth():bitmap.getHeight();
        int size=32;
        for (;size<1024;size*=2) {
            if (w<=size) break;
        }
        Bitmap result=resizeBitmap(bitmap,size,size);
        
        //�摜�f�[�^�̐���
        ByteBuffer bb=ByteBuffer.allocateDirect(size*size*4);
        bb.order(ByteOrder.BIG_ENDIAN);
        IntBuffer ib=bb.asIntBuffer();
        for (int y=result.getHeight()-1;y>-1;y--) {
            for (int x=0;x<result.getWidth();x++) {
                int pix=result.getPixel(x,result.getHeight()-y-1);
                int alpha=((pix>>24)&0xFF);
                int red  =((pix>>16)&0xFF);
                int green=((pix>>8)&0xFF);
                int blue =((pix)&0xFF);
                ib.put((red<<24)+(green<<16)+(blue<<8)+alpha);            
            }
        }
        ib.position(0);
        bb.position(0);
        int[] textureName=new int[1];
        
        //�e�N�X�`���̐ݒ�
        gl.glGenTextures(1,textureName,0);
        gl.glBindTexture(GL10.GL_TEXTURE_2D,textureName[0]);
        gl.glTexImage2D(GL10.GL_TEXTURE_2D,0,GL10.GL_RGBA,                
            result.getWidth(),result.getHeight(),
            0,GL10.GL_RGBA,GL10.GL_UNSIGNED_BYTE,bb);        
        gl.glTexParameterf(GL10.GL_TEXTURE_2D,GL10.GL_TEXTURE_MAG_FILTER,GL10.GL_LINEAR); 
        gl.glTexParameterf(GL10.GL_TEXTURE_2D,GL10.GL_TEXTURE_MIN_FILTER,GL10.GL_LINEAR);        
        
        //�e�N�X�`���I�u�W�F�N�g�̐���
        Texture texture=new Texture();
        texture.name=textureName[0];
        texture.width=result.getWidth();
        texture.height=result.getHeight();
        texture.size=size;
        return texture;
    }

    //������e�N�X�`���̐���
    public Texture makeTexture(String text,Paint paint) {
        int w=(int)paint.measureText(text);
        int h=(int)(paint.descent()-paint.ascent());
        Bitmap result=Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(result);
        canvas.drawText(text,0,(int)-paint.ascent(),paint);
        return makeTexture(result);   
    }
    
    //�r�b�g�}�b�v�̃��T�C�Y
    private Bitmap resizeBitmap(Bitmap bmp,int w,int h) {
        Bitmap result=Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(result);
        BitmapDrawable drawable=new BitmapDrawable(bmp);
        drawable.setBounds(0,0,bmp.getWidth(),bmp.getHeight());
        drawable.draw(canvas);
        return result;
    }    
    
//====================
//�o�b�t�@�̐���
//====================
    //Float�o�b�t�@�̐���
    public static FloatBuffer makeFloatBuffer(float[] arr) {
        ByteBuffer bb=ByteBuffer.allocateDirect(arr.length*4);
        bb.order(ByteOrder.nativeOrder());
        FloatBuffer fb=bb.asFloatBuffer();
        fb.put(arr);
        fb.position(0);
        return fb;
    }

    //Float�o�b�t�@�̐���
    public static IntBuffer makeFloatBuffer(int[] arr) {
        ByteBuffer bb=ByteBuffer.allocateDirect(arr.length*4);
        bb.order(ByteOrder.nativeOrder());
        IntBuffer ib=bb.asIntBuffer();
        ib.put(arr);
        ib.position(0);
        return ib;
    }
}
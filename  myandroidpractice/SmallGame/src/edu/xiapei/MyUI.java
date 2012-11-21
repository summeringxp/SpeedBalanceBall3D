package edu.xiapei;

import gameTools.Graphics2D;
import gameTools.MyFillRect;
import gameTools.MyLine;
import gameTools.MyRect;

import javax.microedition.khronos.opengles.GL10;

public class MyUI {
	private MyRect rect1,rect2;
	private MyFillRect frect1,frect2,frect3,frect4;
	private MyLine line1,line2;
	float ONE = 0;
	public MyUI(Graphics2D g, int h, int w){
		ONE = h/320.0f;
		    rect1 = new MyRect(ONE*10,ONE*53,ONE*200,ONE*26);
		    frect1= new MyFillRect(ONE*10,ONE*53,ONE*200,ONE*26,0,128,0,200);
		    frect2= new MyFillRect(ONE*210,ONE*53,ONE*200,ONE*26,204,255,255,0);
	      
		    frect3= new MyFillRect(ONE*46,ONE*121,ONE*8,ONE*8,0,100,100,255);
	        
	       
		    line1= new MyLine(ONE*46,ONE*125,ONE*54,ONE*125);
		    line2= new MyLine(ONE*50,ONE*121,ONE*50,ONE*129);

	        rect2 = new MyRect(ONE*10,ONE*85,ONE*80,ONE*80);
	        
	        frect4= new MyFillRect(ONE*10,ONE*85,ONE*80,ONE*80,188,188,188,128);
	}

	public void draw(GL10 g, int h, int w, long playTime, long recordTime, float fx, float fy) {
		// TODO Auto-generated method stub
		long leftTime=recordTime-playTime;
		float l = leftTime*1.0f/recordTime;
	    float ONE = h/320.0f;
	    l = l>0?l:0;
	    
	    int derX=(int) (2600*fx/1.0f);
	    int derY=(int) (2600*fy/1.0f);
	    derX=derX>36?36:derX;
	    derX=derX<-36?-36:derX;
	    derY=derY>36?36:derY;
	    derY=derY<-36?-36:derY;
        //•`‰æ
	    
	    
	    setLineWidth(g, 3);
	    rect1.draw(g, 0);
	    setLineWidth(g, 1);
	    g.glPushMatrix();
	    g.glTranslatef(-ONE*200*(1-l), 0, 0);
	    frect2.draw(g, 0);
	    g.glPopMatrix();
	    
	    setLineWidth(g, 2);
	    frect1.draw(g, 0);
	   
	    
	    g.glPushMatrix();
	    g.glTranslatef(ONE*derX, -ONE*derY, 0);
	    frect3.draw(g, 0);
	    g.glPopMatrix();
	    
	    line1.draw(g, 0);
	    line2.draw(g, 0);
	    setLineWidth(g, 3);
	    rect2.draw(g, 0);
	    setLineWidth(g, 2);
	    frect4.draw(g, 0);
        /*g.setColor(133,133,133);
        g.setLineWidth(3);
        g.drawRect(ONE*10,ONE*53,ONE*200,ONE*26);
        
        //‹éŒ`‚Ì“h‚è’×‚µ
        g.setColor((int) (128-128*l),(int) (128*l),0,200);
        g.setLineWidth(1);
        g.fillRect(ONE*10,ONE*53,ONE*200*l,ONE*26);
//        
        g.setColor(0,100,100);
        g.setLineWidth(2);
        g.fillRect(ONE*46+derX,ONE*121+derY,ONE*8,ONE*8);
        
        g.setColor(133,133,133);
        g.drawLine(ONE*46,ONE*125,ONE*54,ONE*125);
        g.drawLine(ONE*50,ONE*121,ONE*50,ONE*129);

        g.setLineWidth(3);
        g.drawRect(ONE*10,ONE*85,ONE*80,ONE*80);
        
        g.setColor(188,188,188,128);
        g.setLineWidth(2);
        g.fillRect(ONE*10,ONE*85,ONE*80,ONE*80);
        
        */
        
	}
	 public void setLineWidth(GL10 g,float lineWidth) {
	        g.glLineWidth(lineWidth);
	        g.glPointSize(lineWidth*0.6f);
	    }

}

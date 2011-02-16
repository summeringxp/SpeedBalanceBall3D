package edu.xiapei;

import gameTools.Graphics2D;

import javax.microedition.khronos.opengles.GL10;

public class MyUI {

	public void draw(Graphics2D g, int h, int w, long playTime, long recordTime, float fx, float fy) {
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
        //‹éŒ`‚Ì•`‰æ
        g.setColor(133,133,133);
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
        
        
        
	}

}

/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.xiapei;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import storageTools.MapDao;
import storageTools.MapDto;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;

public class SmallGame extends Activity {
	private GameSurfaceView mGLSurfaceView;
	
	private MapDao md;
	private int mapindex=-1;
	private int time;
	public View hidenview;
    public View getHidenview() {
		return hidenview;
	}

	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	 super.onCreate(savedInstanceState);
    	 this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    	 Bundle extras = getIntent().getExtras(); 
    	 if(extras !=null)
    	 {
    		 mapindex= extras.getInt("mapindex");
    	 }

         setContentView(R.layout.main);
                
         mGLSurfaceView = (GameSurfaceView) findViewById(R.id.glsurfaceview);
         md = new MapDao(this);
         
         GameRenderer mRenderer = new GameRenderer(md.find(mapindex),null);
         mGLSurfaceView.setGameRenderer(mRenderer);
         
         final Handler mHandler = new Handler(){
        	 public void handleMessage(Message msg) {
        		 
        		 mGLSurfaceView.setVisibility(View.INVISIBLE);
        		 time = msg.what;
        		 showDialog();
        		 }

         };
         
         mGLSurfaceView = (GameSurfaceView) findViewById(R.id.glsurfaceview);
         
         mGLSurfaceView.requestFocus();
         mGLSurfaceView.setFocusableInTouchMode(true);  
         mRenderer.setHandler(mHandler);
                 
    }
	
	

    @Override
    protected void onResume() {
        // Ideally a game should implement onResume() and onPause()
        // to take appropriate action when the activity looses focus
        super.onResume();
        mGLSurfaceView.onResume();
    }

    @Override
    protected void onPause() {
        // Ideally a game should implement onResume() and onPause()
        // to take appropriate action when the activity looses focus
        super.onPause();
        mGLSurfaceView.onPause();
    }
    @Override
    protected void onStop() {
    	mGLSurfaceView.mSensorManager.unregisterListener(mGLSurfaceView);
        super.onStop();
    }

	
   
    private void showDialog() {
    	AlertDialog.Builder alert = new AlertDialog.Builder(this);  
    	MapDto mdto= md.find(mapindex);
    	if(mdto.hightScore>time){
	    	alert.setTitle("New Record!!! "+time/1000.0+"s");  
	    	alert.setMessage("Your name:");  
	    	  
	    	// Set an EditText view to get user input   
	    	final EditText input = new EditText(this);  
	    	alert.setView(input);  
	    	alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {  
	    	public void onClick(DialogInterface dialog, int whichButton) {  
	    	  String value = input.getText().toString();  
	    	  MapDto mdto= md.find(mapindex);
	    	  mdto.hightScore = time;
	    	  mdto.hsPlayer = value;
	    	  md.update(mdto);
	    	  
	    	  mGLSurfaceView.getMRenderer().getGameManager().reset();
	    	  mGLSurfaceView.setVisibility(View.VISIBLE);
	    	  }  
	    	});  
	    	  
	    	alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {  
	    	  public void onClick(DialogInterface dialog, int whichButton) {  
	    	    // Canceled.  
	    		  mGLSurfaceView.getMRenderer().getGameManager().reset();
	        
	        	  mGLSurfaceView.setVisibility(View.VISIBLE);
	    	  }  
	    	});  
    	}else{
    		alert.setTitle("You Win! "+time/1000.0+"s");  
	    	alert.setMessage("Try again to break "+mdto.hsPlayer+"'s record! ("+mdto.hightScore/1000.0+"s)");  
	    		    	  
	    	alert.setNegativeButton("Retry", new DialogInterface.OnClickListener() {  
	    	  public void onClick(DialogInterface dialog, int whichButton) {  
	    	    // Canceled.  
	    		  mGLSurfaceView.getMRenderer().getGameManager().reset();
	        	  mGLSurfaceView.setVisibility(View.VISIBLE);
	    	  }  
	    	}); 
    	}
    	alert.show();  
    }

}


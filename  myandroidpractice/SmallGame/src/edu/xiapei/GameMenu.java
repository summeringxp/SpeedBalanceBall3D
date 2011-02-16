package edu.xiapei;



import gameTools.GameTools;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import storageTools.MapDao;
import storageTools.MapDto;
import storageTools.StorageTools;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import gameTools.GLTextureFactory;
public class GameMenu extends Activity{
	private MapDao md;
	
	private Button start,/*edit,*/exit,pre,next,reset,back,save;
	private GameSurfaceView menuView;
	private GameRenderer gameRenderer;
	private TextView mapinfo,hsinfo,timeinfo;
	private int mapindex = 1;
	private int mapCount = 1;
	private long tempRec ;
	//private int gameStep = 0;
	private int time=0;
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	    this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	    md = new MapDao(this);
		checkDatabase();
		MapDto mdto = md.find(mapindex);
		tempRec=mdto.hightScore;
	    setContentView(R.layout.menu);
	    menuView = (GameSurfaceView) findViewById(R.id.menuview);
	    gameRenderer = new GameRenderer(mdto,this);
	    gameRenderer.getGameManager().setRecordTime(tempRec);
	    menuView.setGameRenderer(gameRenderer);
	    
		mapinfo = (TextView)findViewById(R.id.mapinfo);
	    hsinfo = (TextView)findViewById(R.id.hsinfo);
	    timeinfo = (TextView)findViewById(R.id.timeinfo);
	    start = (Button) findViewById(R.id.start);
	    //edit = (Button) findViewById(R.id.edit);
	    exit = (Button) findViewById(R.id.exit);
	    pre = (Button) findViewById(R.id.pre);
	    next = (Button) findViewById(R.id.next);
	    reset = (Button) findViewById(R.id.reset);
	    back = (Button) findViewById(R.id.back);
	    save = (Button) findViewById(R.id.save);
	    updateButtonState(0);
	    start.setOnClickListener(mStartListener);
	    exit.setOnClickListener(mExitListener);
	    pre.setOnClickListener(mPreListener);
	    next.setOnClickListener(mNextListener);
	    reset.setOnClickListener(mResetListener);
	    back.setOnClickListener(mBackListener);
	   // edit.setOnClickListener(mEditListener);
	    
	    hsinfo.setText("BestRecord: "+mdto.hightScore/1000.0+"s. by "+mdto.hsPlayer);
	    mapinfo.setText(mdto.name +" "+mdto.author);
	    final Handler mHandler = new Handler(){
       	 public void handleMessage(Message msg) {
       		 
       		menuView.setVisibility(View.INVISIBLE);
       		 time = msg.what;
       		 showDialog();
       		 }

        };
        gameRenderer.setHandler(mHandler);
	}
	 private void updateButtonState(int gameStep) {
		// TODO Auto-generated method stub
		   pre.setClickable(mapindex>1);
		   next.setClickable(mapindex<mapCount);
		   if(gameStep == 0){
			   mapinfo.setVisibility(View.VISIBLE);
			    hsinfo.setVisibility(View.VISIBLE);
			    start.setVisibility(View.VISIBLE);
			    //edit.setVisibility(View.VISIBLE);
			    timeinfo.setVisibility(View.INVISIBLE);
			    exit.setVisibility(View.VISIBLE);
			    pre.setVisibility(View.VISIBLE);
			    next.setVisibility(View.VISIBLE);
			    reset.setVisibility(View.INVISIBLE);
			    back.setVisibility(View.INVISIBLE);
			    save.setVisibility(View.INVISIBLE);
		   }else if(gameStep == 1){
			  
			    start.setVisibility(View.INVISIBLE);
			    //edit.setVisibility(View.INVISIBLE);
			    exit.setVisibility(View.INVISIBLE);
			    timeinfo.setVisibility(View.VISIBLE);
			    pre.setVisibility(View.INVISIBLE);
			    next.setVisibility(View.INVISIBLE);
			    reset.setVisibility(View.VISIBLE);
			    back.setVisibility(View.VISIBLE);
			    save.setVisibility(View.INVISIBLE);
		   }/*else if(gameStep == 2){
			   start.setVisibility(View.INVISIBLE);
			   // edit.setVisibility(View.INVISIBLE);
			    exit.setVisibility(View.INVISIBLE);
			    timeinfo.setVisibility(View.INVISIBLE);
			    pre.setVisibility(View.INVISIBLE);
			    next.setVisibility(View.INVISIBLE);
			    reset.setVisibility(View.INVISIBLE);
			    back.setVisibility(View.VISIBLE);
			    save.setVisibility(View.VISIBLE);
		   }*/
	}
	private void checkDatabase() {
		mapCount = md.getMapNumber();
		if(mapCount<8){
			for(int i= 0;i<8;i++){
			MapDto mdto = new MapDto();
			mdto.mapType = GameTools.arrayToString(Statics.testMap[i]);
			mdto.mapHeight = GameTools.arrayToString(Statics.testHightMap[i]);
			mdto.author = "Anonymous";
			mdto.hightScore=999999;
			mdto.hsPlayer="None";
			mdto.name="defaltmap"+(i+1);
			md.insert(mdto);
			}
			mapCount = 8;
			
		}
		
		
	}
	protected void onResume() {
	        // Ideally a game should implement onResume() and onPause()
	        // to take appropriate action when the activity looses focus
	        super.onResume();
	 }

	    @Override
	 protected void onPause() {
	        // Ideally a game should implement onResume() and onPause()
	        // to take appropriate action when the activity looses focus
	        super.onPause();
	 }
	    
	 OnClickListener mStartListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			gameRenderer.getGameManager().reset();
			gameRenderer.getGameManager().setRecordTime(tempRec);
			gameRenderer.getGameManager().setGameStep(1);
			//gameRenderer.changeMap(md.find(mapindex));
			
			updateButtonState(1);
		}
		 
	 };
	 OnClickListener mResetListener = new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				gameRenderer.getGameManager().reset();
				
			}
			 
		 };
		 OnClickListener mBackListener = new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					gameRenderer.getGameManager().reset();
					gameRenderer.getGameManager().setGameStep(0);
					//gameRenderer.changeMap(md.find(mapindex));
					
					updateButtonState(0);
					
				}
				 
			 };
	 
	 OnClickListener mExitListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			finish();
		}
		 
	 };
	 OnClickListener mEditListener = new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				gameRenderer.getGameManager().reset();
				gameRenderer.getGameManager().setGameStep(2);
				
				updateButtonState(2);
			}
			 
		 };
	 OnClickListener mPreListener = new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(mapindex>1){
					mapindex--;
				
				MapDto mdto = md.find(mapindex);
				 gameRenderer.changeMap(mdto);
				 hsinfo.setText("BestRecord: "+mdto.hightScore/1000.0+"s. by "+mdto.hsPlayer);
				    mapinfo.setText(mdto.name +" "+mdto.author);
				    tempRec = mdto.hightScore;
				}
				
				 updateButtonState(gameRenderer.getGameManager().getGameStep());
			}
			 
		 };
		 OnClickListener mNextListener = new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(mapindex<mapCount){
					mapindex++;
					MapDto mdto = md.find(mapindex);
					 gameRenderer.changeMap(mdto);
					 hsinfo.setText("BestRecord: "+mdto.hightScore/1000.0+"s. by "+mdto.hsPlayer);
					    mapinfo.setText(mdto.name +" "+mdto.author);
					    tempRec = mdto.hightScore;
					}
					
					updateButtonState(gameRenderer.getGameManager().getGameStep());
				}
				 
			 };
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
				    	  tempRec=time;
				    	  gameRenderer.getGameManager().setRecordTime(tempRec);
				    	  gameRenderer.getGameManager().reset();
				    	  hsinfo.setText("BestRecord: "+mdto.hightScore/1000.0+"s. by "+mdto.hsPlayer);
						    mapinfo.setText(mdto.name +" "+mdto.author);
				    	  menuView.setVisibility(View.VISIBLE);
				    	  }  
				    	});  
				    	  
				    	alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {  
				    	  public void onClick(DialogInterface dialog, int whichButton) {  
				    	    // Canceled.  
				    		  gameRenderer.getGameManager().reset();
				        
				    		  menuView.setVisibility(View.VISIBLE);
				    	  }  
				    	});  
			    	}else{
			    		alert.setTitle("You Win! "+time/1000.0+"s");  
				    	alert.setMessage("Try again to break "+mdto.hsPlayer+"'s record! ("+mdto.hightScore/1000.0+"s)");  
				    		    	  
				    	alert.setNegativeButton("Retry", new DialogInterface.OnClickListener() {  
				    	  public void onClick(DialogInterface dialog, int whichButton) {  
				    	    // Canceled.  
				    		  gameRenderer.getGameManager().reset();
				        	  menuView.setVisibility(View.VISIBLE);
				    	  }  
				    	}); 
			    	}
			    	 hsinfo.setText("BestRecord: "+mdto.hightScore/1000.0+"s. by "+mdto.hsPlayer);
					    mapinfo.setText(mdto.name +" "+mdto.author);
			    	alert.show();  
			    }

}

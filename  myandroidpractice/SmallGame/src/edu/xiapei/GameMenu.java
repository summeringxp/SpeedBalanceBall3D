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
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class GameMenu extends Activity{
	private MapDao md;
	
	private Button start,edit,exit,pre,next;
	private GLSurfaceView menuView;
	private MapViewRenderer mvr;
	private TextView mapinfo,hsinfo;
	//private String[] filenames={"defaltmap"};
	private int mapindex = 1;
	private int mapCount = 1;
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	    md = new MapDao(this);
	    
		checkDatabase();
		MapDto mdto = md.find(mapindex);
	    setContentView(R.layout.menu);
	    menuView =
            (GLSurfaceView) findViewById(R.id.menuview);
	    mvr = new MapViewRenderer(mdto);
	    menuView.setRenderer(mvr);
	    mapinfo = (TextView)findViewById(R.id.mapinfo);
	    hsinfo = (TextView)findViewById(R.id.hsinfo);
	    start = (Button) findViewById(R.id.start);
	    edit = (Button) findViewById(R.id.edit);
	    exit = (Button) findViewById(R.id.exit);
	    pre = (Button) findViewById(R.id.pre);
	    next = (Button) findViewById(R.id.next);
	    updateButtonState();
	    start.setOnClickListener(mStartListener);
	    exit.setOnClickListener(mExitListener);
	    pre.setOnClickListener(mPreListener);
	    next.setOnClickListener(mNextListener);
	    
	    hsinfo.setText("BestRecord: "+mdto.hightScore/1000.0+"s. by "+mdto.hsPlayer);
	    mapinfo.setText(mdto.name +" "+mdto.author);
	    
	}
	 private void updateButtonState() {
		// TODO Auto-generated method stub
		   pre.setClickable(mapindex>1);
		   next.setClickable(mapindex<mapCount-1);
	}
	private void checkDatabase() {
		mapCount = md.getMapNumber();
		if(mapCount<2){
			MapDto mdto = new MapDto();
			mdto.mapType = GameTools.arrayToString(Statics.testMap);
			mdto.mapHeight = GameTools.arrayToString(Statics.testHightMap);
			mdto.author = "XiaPei";
			mdto.hightScore=99000;
			mdto.hsPlayer="XiaPei";
			mdto.name="defaltmap";
			md.insert(mdto);
			mdto.mapType = GameTools.arrayToString(Statics.testMap2);
			mdto.mapHeight = GameTools.arrayToString(Statics.testHightMap2);
			mdto.author = "XiaPei";
			mdto.hightScore=99000;
			mdto.hsPlayer="XiaPei";
			mdto.name="defaltmap2";
			md.insert(mdto);
			mapCount = 2;
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
			
			Intent intent = new Intent(GameMenu.this, SmallGame.class);
			intent.putExtra("mapindex",mapindex );
			startActivity(intent);
			finish();
		}
		 
	 };
	 
	 OnClickListener mExitListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			finish();
		}
		 
	 };
	 OnClickListener mPreListener = new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(mapindex>1){
					mapindex--;
				
				MapDto mdto = md.find(mapindex);
				 mvr.changeMap(mdto);
				}
				 updateButtonState();
			}
			 
		 };
		 OnClickListener mNextListener = new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(mapindex<mapCount){
					mapindex++;
					MapDto mdto = md.find(mapindex);
					 mvr.changeMap(mdto);
					}
					 updateButtonState();
				}
				 
			 };
	 
}

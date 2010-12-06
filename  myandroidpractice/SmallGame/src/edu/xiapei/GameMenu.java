package edu.xiapei;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GameMenu extends Activity{
	
	private Button start,edit,exit,pre,next;
	private GLSurfaceView menuView;
	private String[] filenames={"defaltmap"};
	private int mapnumber = 0;
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	  //  try {
		getMapNames();
		//} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
	//		e.printStackTrace();
		//} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
	    setContentView(R.layout.menu);
	    menuView =
            (GLSurfaceView) findViewById(R.id.menuview);
	    menuView.setRenderer(new MapViewRenderer("defaltmap"));
	    start = (Button) findViewById(R.id.start);
	    edit = (Button) findViewById(R.id.edit);
	    exit = (Button) findViewById(R.id.exit);
	    pre = (Button) findViewById(R.id.pre);
	    next = (Button) findViewById(R.id.next);
	    updateButtonState();
	    start.setOnClickListener(mStartListener);
	    exit.setOnClickListener(mExitListener);
	    
	    
	}
	 private void updateButtonState() {
		// TODO Auto-generated method stub
		   pre.setClickable(mapnumber>0);
		   next.setClickable(mapnumber<filenames.length-1);
	}
	private void getMapNames() {
		// TODO Auto-generated method stub
		 //File dir = getDir("maps",Context.MODE_WORLD_READABLE|Context.MODE_WORLD_WRITEABLE);
		 //filenames = dir.list();
		 //if(filenames.length==0){
			 
			/* FileOutputStream fos = openFileOutput(dir.getName()+"/defaultmap", 0);
			 
			 for(int i = 0;i<Statics.MAPHIGHT;i++){
				 for(int j=0;j<Statics.MAPWIDTH;i++){
					 fos.write(Statics.testMap[i][j]);
					 fos.write(Statics.testHightMap[i][j]);
				 }
			 }
			 
			 fos.write(s.getBytes());
			 fos.close();*/
			 String s = "Xia Pei$Xia Pei&99";
			 filenames = new String[1];
			 filenames[0] = "defaultmap";
		 //}

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
			intent.putExtra("filename", "default");
			startActivity(intent);
			
			//onStop();
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
	 
}

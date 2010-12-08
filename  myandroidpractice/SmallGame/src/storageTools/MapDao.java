package storageTools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MapDao {
	
	public static StorageTools dbcon;
	SQLiteDatabase db;
	public MapDao(Context ctx) {
		if (dbcon == null) dbcon = StorageTools.getInstance(ctx);
	}
	public void insert(MapDto dto){
		ContentValues values = new ContentValues();
		//values.put(MapSchema.ID, dto.id);
		values.put(MapSchema.MAPTYPE, dto.mapType);
		values.put(MapSchema.MAPHEIGHT, dto.mapHeight);
		values.put(MapSchema.AUTHOR, dto.author);
		values.put(MapSchema.HIGHSCORE, dto.hightScore);
		values.put(MapSchema.HSPLAYER, dto.hsPlayer);
		values.put(MapSchema.NAME, dto.name);
		
		db = dbcon.getWritableDatabase();
		db.insertOrThrow(MapSchema.TABLE_NAME, null, values);
		db.close();
	}
	public void update(MapDto dto) {
		ContentValues values = new ContentValues();
		//values.put(MapSchema.ID, dto.id);
		values.put(MapSchema.MAPTYPE, dto.mapType);
		values.put(MapSchema.MAPHEIGHT, dto.mapHeight);
		values.put(MapSchema.AUTHOR, dto.author);
		values.put(MapSchema.HIGHSCORE, dto.hightScore);
		values.put(MapSchema.HSPLAYER, dto.hsPlayer);
		values.put(MapSchema.NAME, dto.name);
		String where = MapSchema.ID + " = " + dto.id;
		db = dbcon.getWritableDatabase();
		db.update(MapSchema.TABLE_NAME, values, where ,null);
		db.close();
	}
	public void delete(MapDto dto) {
		String where = MapSchema.ID + " = " + dto.id;
		db = dbcon.getWritableDatabase();
		db.delete(MapSchema.TABLE_NAME, where ,null);
		db.close();
	}
	public MapDto find(int id){
		//String where = MapSchema.ID + " = " + id;
		db = dbcon.getReadableDatabase();
		//String[] column = new String[]{MapSchema.ID,MapSchema.MAPTYPE,
		//		MapSchema.MAPHEIGHT,MapSchema.AUTHOR,MapSchema.HIGHSCORE,MapSchema.HSPLAYER,MapSchema.NAME}; 
		//Cursor cursor = db.query(MapSchema.TABLE_NAME,column, MapSchema.ID+"=?", new String[]{String.valueOf(id)}, null, null, null);
		Cursor cursor = db.rawQuery("SELECT * from "+MapSchema.TABLE_NAME+" WHERE "+MapSchema.ID+" = "+String.valueOf(id), null);
		MapDto md = new MapDto();
		md.id = -1;
		while(cursor.moveToNext()){       
			md.id = id;
			md.mapType = cursor.getString(cursor.getColumnIndex(MapSchema.MAPTYPE)); 
			md.mapHeight = cursor.getString(cursor.getColumnIndex(MapSchema.MAPHEIGHT)); 
			md.author = cursor.getString(cursor.getColumnIndex(MapSchema.AUTHOR)); 
			md.hightScore = cursor.getInt(cursor.getColumnIndex(MapSchema.HIGHSCORE)); 
			md.hsPlayer = cursor.getString(cursor.getColumnIndex(MapSchema.HSPLAYER));
			md.name = cursor.getString(cursor.getColumnIndex(MapSchema.NAME));
         }  
		db.close();
		return md;
	}
	public int getMapNumber(){
		int count=0;
		db = dbcon.getReadableDatabase();
		Cursor cursor = db.query(MapSchema.TABLE_NAME, new String[]{MapSchema.ID}, null, null, null, null, null);
		count+=cursor.getCount();
		db.close();
		return count;
	}
	
	
}

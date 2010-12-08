package storageTools;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;	
public class StorageTools extends SQLiteOpenHelper{
	
	
		private static final String DATABASE_NAME = "BalanceBall.db";
		private static final int DATABASE_VERSION = 1;
		private static StorageTools dbConnection;
		public static StorageTools getInstance(Context ctx){
			if(dbConnection == null) dbConnection = new StorageTools(ctx);
			return dbConnection ;
		}
		private StorageTools(Context ctx) {
			super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
		}
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE maps (id INTEGER PRIMARY KEY AUTOINCREMENT, mapType TEXT, mapHeight TEXT, "
					+"author TEXT, highScore INTEGER, hsplayer TEXT, name TEXT)");
		}
		@Override
		public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}
	
}

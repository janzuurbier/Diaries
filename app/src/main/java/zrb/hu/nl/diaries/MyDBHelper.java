package zrb.hu.nl.diaries;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Date;

public class MyDBHelper extends SQLiteOpenHelper {
	private  static MyDBHelper theInstance = null;

	private MyDBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}
	
	public static MyDBHelper getInstance(Context ctx){
		if (theInstance == null)
			theInstance = new MyDBHelper(ctx, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
		return theInstance;
	}
	
	

	private static final String CREATE_TABLE = "create table " + 
	Constants.TABLE_NAME + " (" + 
	Constants.KEY_ID + " integer primary key autoincrement, " +
	Constants.TITLE_NAME +  " text not null, " +
	Constants.CONTENT_NAME + " text not null, " +
	Constants.DATE_NAME + " long);";

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.v("MyDBHelper oncreate", "creating all the tables");
		try{
			db.execSQL(CREATE_TABLE);
		}
		catch(SQLiteException ex){
			Log.v("Create exception", ex.getMessage());
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w("TaskDBAdapter", "Upgrading from version " + oldVersion + 
				" to " + newVersion +
				", wich wil destroy all old data");
		db.execSQL("drop table if exists " + Constants.TABLE_NAME);
		onCreate(db);
	}
	
	public long insertDiary(String title, String content) {
		try{
			SQLiteDatabase db = getWritableDatabase();
			ContentValues newTaskValue = new ContentValues();
			newTaskValue.put(Constants.TITLE_NAME, title);
			newTaskValue.put(Constants.CONTENT_NAME, content);
			newTaskValue.put(Constants.DATE_NAME, new Date().getTime());
			return db.insert(Constants.TABLE_NAME, null, newTaskValue);
		}
		catch(SQLiteException ex){
			Log.e("Insert exception", ex.getMessage());
			return -1;			
		}
	}

	public Cursor getDiaries() {
		SQLiteDatabase db = getReadableDatabase();
		Cursor c = db.query(Constants.TABLE_NAME, null, null, null, null, null, Constants.DATE_NAME);
		return c;
	}

	public void verwijderDiary(long id) {
		SQLiteDatabase db = getWritableDatabase();
		db.delete(Constants.TABLE_NAME, Constants.KEY_ID+"="+id, null);		
	}		
}

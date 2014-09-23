package com.kohlerbear.whowascnscalc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/** Helper to the database, manages versions and creation */
public class SQLHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "Lifts.db";
	private static final int DATABASE_VERSION = 8;

	// Table name
	public static final String TABLE01 = "Lifts";

	// Columns
	public static final String LIFTDATE = "liftDate"; //PREVIOUSLY TIME
	public static final String CYCLE = "Cycle"; //PREVIOUSLY TITLE 
	public static final String LIFT = "Lift";
	public static final String FREQUENCY = "Frequency";
	public static final String FIRST = "First_Lift";
	public static final String SECOND = "Second_Lift";
	public static final String THIRD = "Third_Lift";
	public static final String TRAINING_MAX = "Training_Max";
	public static final String LBFLAG = "column_lbFlag";
	
	String sql01 = "create table " + TABLE01 + "(liftDate text not null, Cycle integer, Lift text not null, Frequency text not null, First_Lift real, Second_Lift real, Third_Lift real, Training_Max integer, column_lbFlag integer);";

	public SQLHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override  		
	public void onCreate(SQLiteDatabase db) {
		Log.d("EventsData", "onCreate: SQL01 " + sql01);
		db.execSQL(sql01);		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (oldVersion >= newVersion)
			return;

		String sql = null;
		if (oldVersion == 1) 
			sql = "alter table " + TABLE01 + " add note text;";
		if (oldVersion == 2)
			sql = "";

		Log.d("EventsData", "onUpgrade	: " + sql);
		if (sql != null)
			db.execSQL(sql);
	}

}
package com.example.phonecapture;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelper extends SQLiteOpenHelper{
    public static final String TB_NAME="imgDatabase";
    public static final String ID="_id";
    public static final String LOCATION_X="locX";
    public static final String LOCATION_Y="locY";
    public static final String DEVICE="devive";
    public static final String TIME="time";
	public MyHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE IF NOT EXISTS "+TB_NAME+" ("
		             +ID+" INTEGER PRIMARY KEY,"+DEVICE+" VARCHAR,"
		             +TIME+" VARCHAR,"+LOCATION_X+" VARCHAR,"+LOCATION_Y+" VARCHAR)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS "+TB_NAME);
		onCreate(db);//重新建立一张数据表
	}

}

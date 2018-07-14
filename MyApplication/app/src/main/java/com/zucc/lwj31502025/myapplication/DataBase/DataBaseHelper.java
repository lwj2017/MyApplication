package com.zucc.lwj31502025.myapplication.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "myapplication.db";
    private static final int VERSION = 1;
    //建表语句
//    public static final String CREATE_USER = "create table cost(_id integer primary key autoincrement," +
//            "username char,password char); ";
    public static final String CREATE_IC = "create table ic(_id integer primary key autoincrement," +
            "money real,source varchar(32),incomeCostType integer,incomeCostDate varchar(16)); ";
    public static final String CREATE_PLAN = "create table plan1(_id integer primary key autoincrement," +
            "date varchar(10),title varchar(10),hour varchar(10),minutes varchar(10),postscript varchar(10),address varchar(25)) ";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
    public static DataBaseHelper helper;

    public static DataBaseHelper getInstance(Context context) {
        if (helper == null) {
            helper = new DataBaseHelper(context);
        }
        return helper;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //sqLiteDatabase.execSQL(CREATE_USER);
        sqLiteDatabase.execSQL(CREATE_IC);
        sqLiteDatabase.execSQL(CREATE_PLAN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

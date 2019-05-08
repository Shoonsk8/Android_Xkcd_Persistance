package com.shoon.android_xkcd_persistance;


import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class XkcdDbHelper extends SQLiteOpenHelper
{

    private static final int    DATABASE_VERSION = 2;
    private static final String DATABASE_NAME    = "Comics.db";
    Context context;

    public  XkcdDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public XkcdDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, Context context1) {
        super( context, name, factory, version );
        this.context = context1;
    }

    public XkcdDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler, Context context1) {
        super( context, name, factory, version, errorHandler );
        this.context = context1;
    }

    public XkcdDbHelper(Context context, String name, int version, SQLiteDatabase.OpenParams openParams, Context context1) {
        super( context, name, version, openParams );
        this.context = context1;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( XkcdDbContract.ComicEntry.SQL_CREATE_TABLE );


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( XkcdDbContract.ComicEntry.SQL_DELETE_TABLE );
        onCreate(  db);
    }
}

package edu.uga.cs.statecapitalsquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;

/*
 * This is the class directly responsible for creating the states database.
 */
public class readcsvDBHelper extends SQLiteOpenHelper {

    private static final String DEBUG_TAG = "readcsvDBHelper";
    private static final String DB_NAME = "readcsv.db";
    private static final int DB_VERSION = 1;
    public static final String TABLE_STATES = "states";
    public static final String STATES_COLUMN_ID = "_id";
    public static final String STATES_COLUMN_STATES = "state";
    public static final String STATES_COLUMN_CAPITALCITY = "capitalCity";
    public static final String STATES_COLUMN_ADDITIONALCITY1 = "additionalCity1";
    public static final String STATES_COLUMN_ADDITIONALCITY2 = "additionalCity2";
    private static readcsvDBHelper helperInstance;

    private static final String CREATE_STATES =
            "create table if not exists " + TABLE_STATES + " ("
            + STATES_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + STATES_COLUMN_STATES + " TEXT, "
            + STATES_COLUMN_CAPITALCITY + " TEXT, "
            + STATES_COLUMN_ADDITIONALCITY1 + " TEXT, "
            + STATES_COLUMN_ADDITIONALCITY2 + " TEXT"
            + ")";

    private readcsvDBHelper (Context context ) {super (context, DB_NAME, null, DB_VERSION);}

    public static synchronized  readcsvDBHelper getInstance(Context context) {
        if ( helperInstance == null) {
            helperInstance = new readcsvDBHelper( context.getApplicationContext());
        }
        return helperInstance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL (CREATE_STATES);
        Log.d(DEBUG_TAG, "Table " + TABLE_STATES + " created");
    }

    @Override
    public void onUpgrade ( SQLiteDatabase db, int oldVersion, int newVersion ) {
        db.execSQL( "drop table if exists " + TABLE_STATES);
        onCreate(db);
        Log.d( DEBUG_TAG, "Table " + TABLE_STATES + " upgraded" );
    }
}
package edu.uga.cs.statecapitalsquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class readcsvData {

    public static final String DEBUG_TAG = "readcsvData";
    private SQLiteDatabase db;
    private SQLiteOpenHelper readcsvDbHelper;
    private static final String[] allColumns = {
            readcsvDBHelper.STATES_COLUMN_ID,
            readcsvDBHelper.STATES_COLUMN_STATES,
            readcsvDBHelper.STATES_COLUMN_CAPITALCITY,
            readcsvDBHelper.STATES_COLUMN_ADDITIONALCITY1,
            readcsvDBHelper.STATES_COLUMN_ADDITIONALCITY2
    };

    public readcsvData(Context context) {
        this.readcsvDbHelper = readcsvDBHelper.getInstance(context);
    }

    public void open() {
        db = readcsvDbHelper.getWritableDatabase();
        Log.d(DEBUG_TAG, "readcsvData: db open");
    }

    public void close() {
        if ( readcsvDbHelper != null) {
            readcsvDbHelper.close();
            Log.d(DEBUG_TAG, "readcsvData: db closed");
        }
    }

    public boolean isDBOpen() {return db.isOpen();}
    public List<readcsv> retrieveAllreadcsvLeads() {
        ArrayList<readcsv> readcsvs = new ArrayList<>();
        Cursor cursor = null;
        int columnIndex;
        try {
            cursor = db.query(readcsvDBHelper.TABLE_STATES, allColumns, null, null, null, null, null);
            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    if (cursor.getColumnCount() >= 5) {
                        columnIndex = cursor.getColumnIndex(readcsvDBHelper.STATES_COLUMN_ID);
                        long id = cursor.getLong(columnIndex);
                        columnIndex = cursor.getColumnIndex(readcsvDBHelper.STATES_COLUMN_STATES);
                        String state = cursor.getString(columnIndex);
                        columnIndex = cursor.getColumnIndex(readcsvDBHelper.STATES_COLUMN_CAPITALCITY);
                        String capitalCity = cursor.getString(columnIndex);
                        columnIndex = cursor.getColumnIndex(readcsvDBHelper.STATES_COLUMN_ADDITIONALCITY1);
                        String additionalCity1 = cursor.getString(columnIndex);
                        columnIndex = cursor.getColumnIndex(readcsvDBHelper.STATES_COLUMN_ADDITIONALCITY2);
                        String additionalCity2 = cursor.getString(columnIndex);
                        readcsv readcsv = new readcsv(state, capitalCity, additionalCity1, additionalCity2);
                        readcsv.setId(id);
                        readcsvs.add(readcsv);
                        Log.d(DEBUG_TAG, "Retrieved readcsvList: " + readcsv);
                    }
                }
            }
            if (cursor != null) {
                Log.d(DEBUG_TAG, "Number of records from DB: " + cursor.getCount());
            } else {
                Log.d(DEBUG_TAG, "Number of records from DB: 0");
            }
        } catch (Exception e) {
            Log.d(DEBUG_TAG, "Exception caught: " + e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return readcsvs;
    }

    public readcsv storereadcsv (readcsv readcsvToken) {
        ContentValues values = new ContentValues();
        values.put( readcsvDBHelper.STATES_COLUMN_STATES, readcsvToken.getState());
        values.put( readcsvDBHelper.STATES_COLUMN_CAPITALCITY, readcsvToken.getCapitalCity());
        values.put( readcsvDBHelper.STATES_COLUMN_ADDITIONALCITY1, readcsvToken.getAdditionalCity1());
        values.put( readcsvDBHelper.STATES_COLUMN_ADDITIONALCITY2, readcsvToken.getAdditionalCity2());
        long id = db.insert( readcsvDBHelper.TABLE_STATES, null, values);
        readcsvToken.setId(id);
        Log.d(DEBUG_TAG, "Stored new job lead with id: " + String.valueOf(readcsvToken.getId()));
        return readcsvToken;
    }

}

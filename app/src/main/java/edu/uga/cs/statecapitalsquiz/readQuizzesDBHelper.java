package edu.uga.cs.statecapitalsquiz;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.util.Log;

public class readQuizzesDBHelper extends SQLiteOpenHelper {
    private static final String DEBUG_TAG = "readQuizzesDBHelper";
    private static final String DB_Name = "readQuizzes.db";
    private static final int DB_VERSION = 1;
    public static final String TABLE_QUIZZES = "quizzes";
    public static final String QUIZZES_COLUMN_ID = "_id";
    public static final String QUIZZES_COLUMN_QUIZDATE = "quizDate";
    public static final String QUIZZES_COLUMN_QUESTION1 = "question1";
    public static final String QUIZZES_COLUMN_QUESTION2 = "question2";
    public static final String QUIZZES_COLUMN_QUESTION3 = "question3";
    public static final String QUIZZES_COLUMN_QUESTION4 = "question4";
    public static final String QUIZZES_COLUMN_QUESTION5 = "question5";
    public static final String QUIZZES_COLUMN_QUESTION6 = "question6";
    public static final String QUIZZES_COLUMN_NUMBERCORRECTANSWERS = "number_correct_answers";
    public static final String QUIZZES_COLUMN_NUMBERCOMPLETEDANSWERS = "number_completed_answers";
    private static readQuizzesDBHelper helperInstance;
    private static final String CREATE_QUIZZES =
            "create table if not exists " + TABLE_QUIZZES + " ("
            + QUIZZES_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + QUIZZES_COLUMN_QUIZDATE + " TEXT, "
            + QUIZZES_COLUMN_QUESTION1 + " TEXT, "
            + QUIZZES_COLUMN_QUESTION2 + " TEXT, "
            + QUIZZES_COLUMN_QUESTION3 + " TEXT, "
            + QUIZZES_COLUMN_QUESTION4 + " TEXT, "
            + QUIZZES_COLUMN_QUESTION5 + " TEXT, "
            + QUIZZES_COLUMN_QUESTION6 + " TEXT, "
            + QUIZZES_COLUMN_NUMBERCORRECTANSWERS + " INTEGER, "
            + QUIZZES_COLUMN_NUMBERCOMPLETEDANSWERS + " INTEGER"
            + ")";

    private readQuizzesDBHelper (Context context) {super (context, DB_Name, null, DB_VERSION);}
    public static synchronized  readQuizzesDBHelper getInstance(Context context) {
        if (helperInstance == null) {
            helperInstance = new readQuizzesDBHelper(context.getApplicationContext());
        }
        return helperInstance;
    }
    @Override
    public void onCreate(SQLiteDatabase db2) {
        db2.execSQL(CREATE_QUIZZES);
        Log.d(DEBUG_TAG, "Table " + TABLE_QUIZZES + " created");
    }
    @Override
    public void onUpgrade( SQLiteDatabase db2, int oldVersion, int newVersion) {
        db2.execSQL("drop table if exists " + TABLE_QUIZZES);
        onCreate(db2);
        Log.d(DEBUG_TAG, "Table " + TABLE_QUIZZES + " upgraded");
    }




}

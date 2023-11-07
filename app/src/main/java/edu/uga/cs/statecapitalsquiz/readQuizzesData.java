package edu.uga.cs.statecapitalsquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class readQuizzesData {
    public static final String DEBUG_TAG = "readQuizzesData";
    private SQLiteDatabase db2;
    private SQLiteOpenHelper readQuizzesDbHelper;
    private static final String[] allColumns = {
            readQuizzesDBHelper.QUIZZES_COLUMN_ID,
            readQuizzesDBHelper.QUIZZES_COLUMN_QUIZDATE,
            readQuizzesDBHelper.QUIZZES_COLUMN_QUESTION1,
            readQuizzesDBHelper.QUIZZES_COLUMN_QUESTION2,
            readQuizzesDBHelper.QUIZZES_COLUMN_QUESTION3,
            readQuizzesDBHelper.QUIZZES_COLUMN_QUESTION4,
            readQuizzesDBHelper.QUIZZES_COLUMN_QUESTION5,
            readQuizzesDBHelper.QUIZZES_COLUMN_QUESTION6,
            readQuizzesDBHelper.QUIZZES_COLUMN_NUMBERCORRECTANSWERS,
            readQuizzesDBHelper.QUIZZES_COLUMN_NUMBERCOMPLETEDANSWERS };
    public readQuizzesData(Context context) {
        this.readQuizzesDbHelper = readQuizzesDBHelper.getInstance(context);
    }

    public void open() {
        db2 = readQuizzesDbHelper.getWritableDatabase();
        Log.d(DEBUG_TAG, "readQuizzesData: db open");
    }

    public void close() {
        if (readQuizzesDbHelper != null) {
            readQuizzesDbHelper.close();
            Log.d(DEBUG_TAG, "readQuizzesData: db closed");
        }
    }

    public List<readQuizzes> retrieveAllreadQuizzesLeads() {
        ArrayList<readQuizzes> readQuizzes = new ArrayList<>();
        Cursor cursor = null;
        int columnIndex;
        try {
            cursor = db2.query(readQuizzesDBHelper.TABLE_QUIZZES, allColumns, null, null, null, null, null);
            if (cursor != null && cursor.getCount() > 0) {
                while(cursor.moveToNext()) {
                    if (cursor.getColumnCount() >= 10) {
                        columnIndex = cursor.getColumnIndex(readQuizzesDBHelper.QUIZZES_COLUMN_ID);
                        long id = cursor.getLong(columnIndex);
                        columnIndex = cursor.getColumnIndex((readQuizzesDBHelper.QUIZZES_COLUMN_QUIZDATE));
                        String quizDate = cursor.getString(columnIndex);
                        columnIndex = cursor.getColumnIndex(readQuizzesDBHelper.QUIZZES_COLUMN_QUESTION1);
                        String question1 = cursor.getString(columnIndex);
                        columnIndex = cursor.getColumnIndex(readQuizzesDBHelper.QUIZZES_COLUMN_QUESTION2);
                        String question2 = cursor.getString(columnIndex);
                        columnIndex = cursor.getColumnIndex(readQuizzesDBHelper.QUIZZES_COLUMN_QUESTION3);
                        String question3 = cursor.getString(columnIndex);
                        columnIndex = cursor.getColumnIndex(readQuizzesDBHelper.QUIZZES_COLUMN_QUESTION4);
                        String question4 = cursor.getString((columnIndex));
                        columnIndex = cursor.getColumnIndex(readQuizzesDBHelper.QUIZZES_COLUMN_QUESTION5);
                        String question5 = cursor.getString(columnIndex);
                        columnIndex = cursor.getColumnIndex(readQuizzesDBHelper.QUIZZES_COLUMN_QUESTION6);
                        String question6 = cursor.getString(columnIndex);
                        columnIndex = cursor.getColumnIndex(readQuizzesDBHelper.QUIZZES_COLUMN_NUMBERCORRECTANSWERS);
                        int number_correct_answers = cursor.getInt(columnIndex);
                        columnIndex = cursor.getColumnIndex(readQuizzesDBHelper.QUIZZES_COLUMN_NUMBERCOMPLETEDANSWERS);
                        int number_completed_answers = cursor.getInt(columnIndex);

                        readQuizzes readQuiz = new readQuizzes(quizDate, question1, question2, question3, question4, question5, question6, number_correct_answers, number_completed_answers);
                        readQuiz.setIdSecondDB(id);
                        readQuizzes.add(readQuiz);
                        Log.d(DEBUG_TAG, "Retrieved readQuizzesList: " + readQuiz);

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
        return readQuizzes;
    }

    public readQuizzes storeReadQuizzes (readQuizzes readQuizzesToken) {
        ContentValues values = new ContentValues();
        values.put(readQuizzesDBHelper.QUIZZES_COLUMN_QUIZDATE, readQuizzesToken.getQuizDate());
        values.put(readQuizzesDBHelper.QUIZZES_COLUMN_QUESTION1, readQuizzesToken.getQuestion1());
        values.put(readQuizzesDBHelper.QUIZZES_COLUMN_QUESTION2, readQuizzesToken.getQuestion2());
        values.put(readQuizzesDBHelper.QUIZZES_COLUMN_QUESTION3, readQuizzesToken.getQuestion3());
        values.put(readQuizzesDBHelper.QUIZZES_COLUMN_QUESTION4, readQuizzesToken.getQuestion4());
        values.put(readQuizzesDBHelper.QUIZZES_COLUMN_QUESTION5, readQuizzesToken.getQuestion5());
        values.put(readQuizzesDBHelper.QUIZZES_COLUMN_QUESTION6, readQuizzesToken.getQuestion6());
        values.put(readQuizzesDBHelper.QUIZZES_COLUMN_NUMBERCORRECTANSWERS, readQuizzesToken.getNumber_correct_answers());
        values.put(readQuizzesDBHelper.QUIZZES_COLUMN_NUMBERCOMPLETEDANSWERS, readQuizzesToken.getNumber_completed_answers());
        long id = db2.insert(readQuizzesDBHelper.TABLE_QUIZZES, null, values);
        readQuizzesToken.setIdSecondDB(id);
        Log.d(DEBUG_TAG, "Stored new quiz lead with id: " + String.valueOf(readQuizzesToken.getIdSecondDB()));
        return readQuizzesToken;
    }

    public boolean updateReadQuizzes(long id, readQuizzes updateReadQuizzes) {
        ContentValues values = new ContentValues();
        values.put(readQuizzesDBHelper.QUIZZES_COLUMN_QUIZDATE, updateReadQuizzes.getQuizDate());
        values.put(readQuizzesDBHelper.QUIZZES_COLUMN_QUESTION1, updateReadQuizzes.getQuestion1());
        values.put(readQuizzesDBHelper.QUIZZES_COLUMN_QUESTION2, updateReadQuizzes.getQuestion2());
        values.put(readQuizzesDBHelper.QUIZZES_COLUMN_QUESTION3, updateReadQuizzes.getQuestion3());
        values.put(readQuizzesDBHelper.QUIZZES_COLUMN_QUESTION4, updateReadQuizzes.getQuestion4());
        values.put(readQuizzesDBHelper.QUIZZES_COLUMN_QUESTION5, updateReadQuizzes.getQuestion5());
        values.put(readQuizzesDBHelper.QUIZZES_COLUMN_QUESTION6, updateReadQuizzes.getQuestion6());
        values.put(readQuizzesDBHelper.QUIZZES_COLUMN_NUMBERCORRECTANSWERS, updateReadQuizzes.getNumber_correct_answers());
        values.put(readQuizzesDBHelper.QUIZZES_COLUMN_NUMBERCOMPLETEDANSWERS, updateReadQuizzes.getNumber_completed_answers());
        String whereClause = readQuizzesDBHelper.QUIZZES_COLUMN_ID + " = ?";
        String[] whereArgs = {String.valueOf(id)};
        int rowsUpdated = db2.update(readQuizzesDBHelper.TABLE_QUIZZES, values, whereClause, whereArgs);
        return rowsUpdated > 0;
    }


    public boolean isDB2Open() {return db2.isOpen();}

}

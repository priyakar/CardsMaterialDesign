package com.example.priya.cardsmaterialdesign;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Piyu004 on 8/18/2015.
 */

public class RemindersDatabaseHelper extends SQLiteOpenHelper {
    private static RemindersDatabaseHelper databaseHelperInstance;

    public static final String FILE_NAME = "today.db";
    private Context myContext;
    private static String DB_PATH;

    private static String DB_NAME = "todayDB";

    private SQLiteDatabase myDataBase;


    public static RemindersDatabaseHelper getInstance(Context context) {
        if (databaseHelperInstance == null) {
            databaseHelperInstance = new RemindersDatabaseHelper(context);
        }
        return databaseHelperInstance;
    }

    public RemindersDatabaseHelper(Context context) {

        super(context, DB_NAME, null, 1);
        this.myContext = context;
        String path = context.getFilesDir().getPath();
        DB_PATH = path.substring(0, path.lastIndexOf("/")) + "/databases";
    }

    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if (dbExist) {
            //do nothing - database already exist
        } else {
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }

    }

    private boolean checkDataBase() {

        SQLiteDatabase checkDB = null;

        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        } catch (SQLiteException e) {
            e.printStackTrace();
        }

        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null;
    }

    private void copyDataBase() throws IOException {

        InputStream myInput = myContext.getAssets().open(FILE_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public Cursor getData(String year) {
        String myPath = DB_PATH + DB_NAME;
        String[] selectColumns = {"title", "artist_as_appears_on_the_label"};
        String whereClause = "year_peaked=?";
        String[] whereArguemnts = {year};
        myDataBase = SQLiteDatabase.openDatabase(myPath, null,
                SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = myDataBase.query(DB_NAME, selectColumns, whereClause, whereArguemnts, null, null, null);
        return cursor;
    }

    @Override
    public synchronized void close() {

        if (myDataBase != null)
            myDataBase.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}

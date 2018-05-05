package com.example.oussama.lab7;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by oussama on 06/05/2018.
 */

public class MyDBHandler extends SQLiteOpenHelper {

    Context context;
    // info sur la BD
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "info.db";
    //Tables et leurs champs (colonnes)
    public static final String TABLE_NAME = "tblAMIGO";
    public static final String COLUMN_RECID = "recID";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";

    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createtable = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_RECID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COLUMN_NAME + " TEXT ," +
                COLUMN_PHONE + " INTEGER )";
        db.execSQL(createtable);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        Log.d("DB", "The table has been removed!");
        onCreate(db);

    }

    // Imprimer la BD sous la forme d’une chaîne
    public String databaseToString() {
        String dbData = " ";
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex(COLUMN_NAME)) != null) {
                dbData += c.getString(c.getColumnIndex(COLUMN_RECID));
                dbData += " | " + c.getString(c.getColumnIndex(COLUMN_NAME));
                dbData += " | " + c.getString(c.getColumnIndex(COLUMN_PHONE));
                dbData += "\n";

            }
            c.moveToNext();
        }
        db.close();
        c.close();
        return dbData;
    }
    public void addRecord(String naStr, String phStr){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME,naStr);
        contentValues.put(COLUMN_PHONE,phStr);
        Toast.makeText(context, "Added successfully", Toast.LENGTH_SHORT).show();

        db.insertOrThrow(TABLE_NAME,null,contentValues);
    }
}

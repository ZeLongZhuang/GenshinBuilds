package com.mobdeve.group30.genshinbuilds;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.Date;

public class GenshinDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "GenshinBuilds.db";
    private static final int DATABASE_VERSION = 1;

    private static final String USER_TABLE_NAME = "users";
    private static final String USER_COLUMN_ID = "_id";
    private static final String USER_COLUMN_USERNAME = "username";
    private static final String USER_COLUMN_PASSWORD = "password";
    private static final String USER_COLUMN_NAME = "name";
    private static final String USER_COLUMN_BIRTHDAY = "birthday";
    private static final String USER_COLUMN_UID = "uid";

    GenshinDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + USER_TABLE_NAME +
                " (" + USER_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USER_COLUMN_USERNAME + " TEXT, " +
                USER_COLUMN_PASSWORD + " TEXT, " +
                USER_COLUMN_NAME + " TEXT, " +
                USER_COLUMN_BIRTHDAY + " DATE, " +
                USER_COLUMN_UID + " TEXT);";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        onCreate(db);
    }

    public Boolean insertUser(String username, String password, String name, Date birthday, String uid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USER_COLUMN_USERNAME, username);
        cv.put(USER_COLUMN_PASSWORD, password);
        cv.put(USER_COLUMN_NAME, name);
        cv.put(USER_COLUMN_BIRTHDAY, String.valueOf(birthday));
        cv.put(USER_COLUMN_UID, uid);

        long result = db.insert(USER_TABLE_NAME, null, cv);

        if(result == -1)
            return false;
        else
            return true;
    }

    public Boolean isUniqueUsername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username = ?", new String[]{username});

        if(cursor.getCount() > 0)   // if found out that the username exists, which means USERNAME NOT UNIQUE
            return true;
        else                        // if username not taken
            return false;
    }

    public Boolean verifyUsernamePassword(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username = ? AND password = ?", new String[]{username, password});

        if(cursor.getCount() > 0)   // if found out that the both username and password matches
            return true;
        else                        // if username and password doesn't match
            return false;
    }
}

package com.mobdeve.group30.genshinbuilds;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.sql.Date;

public class GenshinDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "GenshinBuilds.db";
    private static final int DATABASE_VERSION = 2;

    private static final String USER_TABLE_NAME = "users";
    private static final String USER_COLUMN_ID = "_id";
    private static final String USER_COLUMN_USERNAME = "username";
    private static final String USER_COLUMN_PASSWORD = "password";
    private static final String USER_COLUMN_NAME = "name";
    private static final String USER_COLUMN_BIRTHDAY = "birthday";
    private static final String USER_COLUMN_UID = "uid";

    private static final String BUILD_TABLE_NAME = "builds";
    private static final String BUILD_COLUMN_ID = "_id";
    private static final String BUILD_COLUMN_USERNAME = "username";
    private static final String BUILD_COLUMN_LEVEL = "level";
    private static final String BUILD_COLUMN_CHARACTER = "character";
    private static final String BUILD_COLUMN_WEAPON = "weapon";
    private static final String BUILD_COLUMN_ARTIFACTSET = "artifactset";
    private static final String BUILD_COLUMN_HP = "hp";
    private static final String BUILD_COLUMN_ATK = "atk";
    private static final String BUILD_COLUMN_DEF = "def";
    private static final String BUILD_COLUMN_ER = "er";
    private static final String BUILD_COLUMN_CRITRATE = "critrate";
    private static final String BUILD_COLUMN_CRITDMG = "critdamage";


    GenshinDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryUser = "CREATE TABLE " + USER_TABLE_NAME +
                " (" + USER_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USER_COLUMN_USERNAME + " TEXT, " +
                USER_COLUMN_PASSWORD + " TEXT, " +
                USER_COLUMN_NAME + " TEXT, " +
                USER_COLUMN_BIRTHDAY + " DATE, " +
                USER_COLUMN_UID + " TEXT);";

        db.execSQL(queryUser);

        String queryBuild = "CREATE TABLE " + BUILD_TABLE_NAME +
                " (" + BUILD_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                BUILD_COLUMN_USERNAME + " TEXT, " +
                BUILD_COLUMN_LEVEL + " INTEGER, " +
                BUILD_COLUMN_CHARACTER + " TEXT, " +
                BUILD_COLUMN_WEAPON + " TEXT, " +
                BUILD_COLUMN_ARTIFACTSET + " TEXT, " +
                BUILD_COLUMN_HP + " INTEGER, " +
                BUILD_COLUMN_ATK + " INTEGER, " +
                BUILD_COLUMN_DEF + " INTEGER, " +
                BUILD_COLUMN_ER + " INTEGER, " +
                BUILD_COLUMN_CRITRATE + " INTEGER, " +
                BUILD_COLUMN_CRITDMG + " INTEGER);";

        db.execSQL(queryBuild);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BUILD_TABLE_NAME);
        onCreate(db);
    }

    public Boolean insertUser(String username, String password, String name, String birthday, String uid) {
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

    public Boolean updateUser(String username, String password, String name, String birthday, String uid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USER_COLUMN_PASSWORD, password);
        cv.put(USER_COLUMN_NAME, name);
        cv.put(USER_COLUMN_BIRTHDAY, birthday);
        cv.put(USER_COLUMN_UID, uid);

        long result = db.update(USER_TABLE_NAME, cv, "username=?", new String[]{username});

        if (result == -1) {
            return false;
        }
        else {
            return true;
        }

    }

    public Boolean isUniqueUsername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username = ?", new String[]{username});

        if(cursor.getCount() > 0)   // if found out that the username exists, which means USERNAME NOT UNIQUE
            return false;
        else                        // if username not taken, which means USERNAME IS UNIQUE
            return true;
    }

    public Boolean verifyUsernamePassword(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username = ? AND password = ?", new String[]{username, password});

        if(cursor.getCount() > 0)   // if found out that the both username and password matches
            return true;
        else                        // if username and password doesn't match
            return false;
    }

    public Boolean insertBuild(String username, String character, int level, String weapon, String artifactSet, int hp, int atk, int def, int er, int critRate, int critDmg) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(BUILD_COLUMN_USERNAME, username);
        cv.put(BUILD_COLUMN_CHARACTER, character);
        cv.put(BUILD_COLUMN_LEVEL, level);
        cv.put(BUILD_COLUMN_WEAPON, weapon);
        cv.put(BUILD_COLUMN_ARTIFACTSET, artifactSet);
        cv.put(BUILD_COLUMN_HP, hp);
        cv.put(BUILD_COLUMN_ATK, atk);
        cv.put(BUILD_COLUMN_DEF, def);
        cv.put(BUILD_COLUMN_ER, er);
        cv.put(BUILD_COLUMN_CRITRATE, critRate);
        cv.put(BUILD_COLUMN_CRITDMG, critDmg);


        long result = db.insert(BUILD_TABLE_NAME, null, cv);

        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor readAllBuilds() {
        String query = "SELECT * FROM " + BUILD_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor readUser(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username = ?", new String[]{username});

        return cursor;
    }
}

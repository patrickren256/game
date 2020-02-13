package com.example.game.Model.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.game.Model.Interface.Observer;

import static android.content.ContentValues.TAG;

public class DatabaseHelper extends SQLiteOpenHelper implements Observer {

    private static final String TABLE_NAME = "players";
    private static final String USER_NAME = "username";
    private static final String CURRENCY = "currency";
    private static final String PLAY_TIME = "playtime";
    private static final String POINTS = "points";
    private static final String WINS = "wins";
    private static final String SKIN = "skin";
    private static final String INVENTORY = "inventory";

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " " +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USER_NAME + " TEXT NOT NULL UNIQUE, " +
                CURRENCY + " INTEGER, " +
                PLAY_TIME + " DOUBLE, " +
                POINTS + " INTEGER, " +
                WINS + " INTEGER, " +
                SKIN + " TEXT, " +
                INVENTORY + " TEXT" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addUser(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME, username);

        Log.d(TAG, "addUser: Adding " + username + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public int updateUserName(String oldUsername, String newUsername) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME, newUsername);
        return db.update(TABLE_NAME, contentValues, "username = ?", new String[]{oldUsername});
    }

    public int update(String username, int currency, double playtime, int points, int wins, String skin, String inventory) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CURRENCY, currency);
        contentValues.put(PLAY_TIME, playtime);
        contentValues.put(POINTS, points);
        contentValues.put(WINS, wins);
        contentValues.put(SKIN, skin);
        contentValues.put(INVENTORY, inventory);
        return db.update(TABLE_NAME, contentValues, "username = ?", new String[]{username});
    }

    public int deleteUser(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "username = ?", new String[]{username});
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public Cursor getUserData(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE username = ?", new String[]{username});
    }

    public void dropTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

package com.example.rentit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "login.db";
    private static final int DB_VERSION = 2; // Increment version to trigger table creation

    private static final String TABLE_USERS = "users";
    private static final String TABLE_ADMIN = "admin_table";

    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_PHONE_NUMBER = "phoneNumber";
    private static final String COLUMN_ADDRESS = "Address";
    private static final String COLUMN_NRC_NUMBER = "NRCNumber";
    private static final String COLUMN_LICENSE_NUMBER = "LicenseNumber";


    private static final String COLUMN_ADMIN_ID = "admin_id";
    private static final String COLUMN_ADMIN_USERNAME = "admin_username";
    private static final String COLUMN_ADMIN_PASSWORD = "admin_password";

    private static final String CREATE_USERS_TABLE =
            "CREATE TABLE " + TABLE_USERS + "(" +
                    COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_USERNAME + " TEXT, " +
                    COLUMN_PASSWORD + " TEXT,"  +
                    COLUMN_PHONE_NUMBER + " TEXT, " +
                    COLUMN_ADDRESS + " TEXT, " +
                    COLUMN_NRC_NUMBER + " TEXT, " +
                    COLUMN_LICENSE_NUMBER + " TEXT" +
                    ")";

    private static final String CREATE_ADMIN_TABLE =
            "CREATE TABLE " + TABLE_ADMIN + "(" +
                    COLUMN_ADMIN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_ADMIN_USERNAME + " TEXT, " +
                    COLUMN_ADMIN_PASSWORD + " TEXT" +
                    ")";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_ADMIN_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMIN);
        onCreate(db);
    }

    public boolean insertDataUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USERNAME, username);
        contentValues.put(COLUMN_PASSWORD, password);
        long result = db.insert(TABLE_USERS, null, contentValues);
        return result != -1;
    }

    public boolean checkUserName(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, null, COLUMN_USERNAME + "=?", new String[]{username}, null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public boolean checkUserNamePassword(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, null, COLUMN_USERNAME + "=? AND " + COLUMN_PASSWORD + "=?", new String[]{username, password}, null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public boolean insertDataAdmin(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ADMIN_USERNAME, username);
        contentValues.put(COLUMN_ADMIN_PASSWORD, password);
        long result = db.insert(TABLE_ADMIN, null, contentValues);
        return result != -1;
    }

    public boolean checkUserNameAdmin(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ADMIN, null, COLUMN_ADMIN_USERNAME + "=?", new String[]{username}, null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public boolean checkUserNamePasswordAdmin(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ADMIN, null, COLUMN_ADMIN_USERNAME + "=? AND " + COLUMN_ADMIN_PASSWORD + "=?", new String[]{username, password}, null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }
    public boolean insertUserProfileData(String Username, String phoneNumber, String address, String NRCNumber, String licenseNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PHONE_NUMBER, phoneNumber);
        contentValues.put(COLUMN_ADDRESS, address);
        contentValues.put(COLUMN_NRC_NUMBER, NRCNumber);
        contentValues.put(COLUMN_LICENSE_NUMBER, licenseNumber);
        long result = db.update(TABLE_USERS, contentValues, COLUMN_USERNAME + "=?", new String[]{Username});
        return result != -1;
    }

    public boolean checkUserExists(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, null, COLUMN_USERNAME + "=?", new String[]{username}, null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }
}

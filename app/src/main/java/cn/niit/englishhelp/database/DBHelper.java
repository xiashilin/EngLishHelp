package cn.niit.englishhelp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_name = "record.db";
    public static final int VERSION = 2;

    public static final String TABLE_RECORD = "recordlist";
    public static final String TABLE_REVIEWLIST = "reviewList";
    public static final String TABLE_USER = "userList";
    public static final String TABLE_STUDY = "studyList";

    public static final String ID = "_id";
    public static final String QUERY = "query";
    public static final String EXPLAIN = "explain";
    public static final String USER = "username";
    public static final String PASSWORD = "password";
    public static final String TITLE = "title";
    public static final String CONTENT = "password";


    public DBHelper(Context context) {
        super(context, DB_name, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + TABLE_RECORD + "(" + ID + " integer primary key autoincrement," + QUERY
                + " text not null," + EXPLAIN + " text not null" + ")";
        String review_sql = "create table " + TABLE_REVIEWLIST + "(" + ID + " integer primary key autoincrement," + QUERY
                + " text not null," + EXPLAIN + " text not null" + ")";
        String user_sql = "create table " + TABLE_USER + "(" + ID + " integer primary key autoincrement," + USER
                + " text not null," + PASSWORD + " text not null" + ")";
        String study_sql = "create table " + TABLE_STUDY + "(" + ID + " integer primary key autoincrement," + TITLE
                + " text not null," + CONTENT + " text not null" + ")";
        db.execSQL(sql);
        db.execSQL(review_sql);
        db.execSQL(user_sql);
        db.execSQL(study_sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}

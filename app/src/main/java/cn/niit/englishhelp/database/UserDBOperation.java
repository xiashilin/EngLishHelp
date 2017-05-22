package cn.niit.englishhelp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * User:xsl
 * Date:2017/5/22
 * Time:18:32
 */

public class UserDBOperation {
    private DBHelper dbHelper;

    public UserDBOperation(Context context) {
        dbHelper = new DBHelper(context);
    }

    public long insetUser(User user) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", user.getUserName());
        cv.put("password", user.getUserPwd());
        long result = 0;
        Cursor mCursor = database.query(DBHelper.TABLE_USER, null, DBHelper.USER
                        + "=" + user.getUserName(), null, null,
                null, null);
        if (mCursor != null) {
            result = mCursor.getCount();
            database.insert(DBHelper.TABLE_USER, null, cv);
            database.close();
            mCursor.close();
        }
        return result;
    }

    public int findUserByNameAndPwd(User user) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        int result = 0;
        Cursor mCursor = database.query(DBHelper.TABLE_USER, null, DBHelper.USER
                        + "=" + user.getUserName() + " and " + DBHelper.PASSWORD + "=" + user.getUserPwd(), null, null,
                null, null);

        if (mCursor != null) {
            result = mCursor.getCount();
            mCursor.close();
        }
        return result;
    }

}

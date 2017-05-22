package cn.niit.englishhelp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.niit.englishhelp.bean.Record;

/**
 * User:xsl
 * Date:2017/5/22
 * Time:1:09
 */

public class ReviewDBOperation {
    private DBHelper dbHelper;

    public ReviewDBOperation(Context context) {
        dbHelper = new DBHelper(context);
    }

    public long insetRecord(Record record) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("query", record.getExplain());
        cv.put("explain", record.getQuery());
        long stat = database.insert(DBHelper.TABLE_REVIEWLIST, null, cv);
        database.close();
        return stat;
    }

    public List<Record> queryRecord() {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        List<Record> list = new ArrayList<Record>();
        Cursor cursor = database.query(DBHelper.TABLE_REVIEWLIST, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Record record = new Record();
            record.setExplain(cursor.getString(cursor.getColumnIndex(DBHelper.EXPLAIN)));
            record.setQuery(cursor.getString(cursor.getColumnIndex(DBHelper.QUERY)));
            list.add(record);
        }
        database.close();
        return list;

    }

    public void deleteRecord(String query) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.delete(DBHelper.TABLE_REVIEWLIST, "query=?", new String[]{query});// 第二个参数是数据库中的_id字段,第三个id是listview中的id
        database.close();
    }


}

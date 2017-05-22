package cn.niit.englishhelp.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import cn.niit.englishhelp.bean.Record;

public class DBoperation {
    private DBHelper dbHelper;

    public DBoperation(Context context) {
        dbHelper = new DBHelper(context);
    }

    public long insetRecord(Record record) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("query", record.getExplain());
        cv.put("explain", record.getQuery());
        long stat = database.insert(DBHelper.TABLE_RECORD, null, cv);
        database.close();
        return stat;

    }

    public List<Record> queryRecord() {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        List<Record> list = new ArrayList<Record>();
        Cursor cursor = database.query(DBHelper.TABLE_RECORD, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Record record = new Record();
            record.setExplain(cursor.getString(cursor.getColumnIndex(DBHelper.EXPLAIN)));
            record.setQuery(cursor.getString(cursor.getColumnIndex(DBHelper.QUERY)));
            list.add(record);
        }
        database.close();
        return list;

    }

    public void deleteRecord() {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.delete(DBHelper.TABLE_RECORD, null, null);
        database.close();
    }
}

package cn.niit.englishhelp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cn.niit.englishhelp.bean.Record;
import cn.niit.englishhelp.bean.StudyBean;

/**
 * User:xsl
 * Date:2017/5/22
 * Time:21:14
 */

public class RecordDBOpration {
    private DBHelper dbHelper;

    public RecordDBOpration(Context context) {
        dbHelper = new DBHelper(context);
    }

    public long insetRecord(StudyBean studyBean) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("title", studyBean.getTitle());
        cv.put("content", studyBean.getContent());
        long stat = database.insert(DBHelper.TABLE_STUDY, null, cv);
        database.close();
        return stat;

    }

    public List<StudyBean> queryRecord() {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        List<StudyBean> list = new ArrayList<StudyBean>();
        Cursor cursor = database.query(DBHelper.TABLE_STUDY, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            StudyBean studyBean = new StudyBean();
            studyBean.setTitle(cursor.getString(cursor.getColumnIndex(DBHelper.TITLE)));
            studyBean.setContent(cursor.getString(cursor.getColumnIndex(DBHelper.CONTENT)));
            list.add(studyBean);
        }
        database.close();
        return list;

    }

    public void deleteRecord() {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.delete(DBHelper.TABLE_STUDY, null, null);
        database.close();
    }
}

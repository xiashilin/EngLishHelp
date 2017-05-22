package cn.niit.englishhelp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import cn.niit.englishhelp.R;
import cn.niit.englishhelp.adapter.StudyAdapter;
import cn.niit.englishhelp.bean.StudyBean;
import cn.niit.englishhelp.database.RecordDBOpration;

/**
 * User:xsl
 * Date:2017/5/22
 * Time:21:12
 */

public class StudyRecordActivity extends AppCompatActivity {
    private ActionBar mToolbar;
    private ListView study_lv;
    private RecordDBOpration recordDBOpration;
    private List<StudyBean> studyBeanList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
        mToolbar = getSupportActionBar();
        mToolbar.setTitle("学习笔记");
        mToolbar.setDisplayHomeAsUpEnabled(true);

        study_lv = (ListView) findViewById(R.id.study_lv);
        recordDBOpration = new RecordDBOpration(this);
        studyBeanList = recordDBOpration.queryRecord();
        if (studyBeanList.size() == 0) {
            LayoutInflater mInflater = LayoutInflater.from(StudyRecordActivity.this);
            View view = mInflater.inflate(R.layout.layout_empty, null);
            study_lv.setEmptyView(view);
        }

        StudyAdapter studyAdapter = new StudyAdapter(this, studyBeanList);
        study_lv.setAdapter(studyAdapter);
        studyAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

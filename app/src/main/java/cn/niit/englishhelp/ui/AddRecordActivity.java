package cn.niit.englishhelp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.niit.englishhelp.R;
import cn.niit.englishhelp.bean.StudyBean;
import cn.niit.englishhelp.database.RecordDBOpration;

/**
 * User:xsl
 * Date:2017/5/22
 * Time:21:11
 */

public class AddRecordActivity extends AppCompatActivity {
    private ActionBar mToolbar;

    private EditText ev_title, ev_content;

    private Button btn_submit;

    private RecordDBOpration recordDBOpration;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        mToolbar = getSupportActionBar();
        mToolbar.setTitle("添加笔记");
        mToolbar.setDisplayHomeAsUpEnabled(true);
        ev_title = (EditText) findViewById(R.id.ev_title);
        ev_content = (EditText) findViewById(R.id.ev_content);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        recordDBOpration = new RecordDBOpration(this);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(ev_title.getText().toString())) {
                    Toast.makeText(AddRecordActivity.this, "请输入标题", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(ev_content.getText().toString())) {
                    Toast.makeText(AddRecordActivity.this, "请输入内容", Toast.LENGTH_LONG).show();
                    return;
                }
                StudyBean studyBean = new StudyBean();
                studyBean.setTitle(ev_title.getText().toString());
                studyBean.setContent(ev_content.getText().toString());
                long flag = recordDBOpration.insetRecord(studyBean);
                if (flag != 0) {
                    Toast.makeText(AddRecordActivity.this, "提交成功", Toast.LENGTH_LONG).show();
                    finish();
                } else Toast.makeText(AddRecordActivity.this, "提交失败", Toast.LENGTH_LONG).show();
            }
        });
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

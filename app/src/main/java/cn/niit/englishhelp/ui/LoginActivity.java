package cn.niit.englishhelp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.niit.englishhelp.R;
import cn.niit.englishhelp.database.User;
import cn.niit.englishhelp.database.UserDBOperation;


/**
 * User:xsl
 * Date:2017/5/21
 * Time:15:53
 */

public class LoginActivity extends AppCompatActivity {

    private EditText input_userName, input_password;
    private Button btn_login;
    private UserDBOperation userDBOperation;
    private TextView fast_registration;
    private ActionBar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mToolbar = getSupportActionBar();
        mToolbar.setTitle("登录");

        input_userName = (EditText) findViewById(R.id.input_userName);
        input_password = (EditText) findViewById(R.id.input_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        fast_registration = (TextView) findViewById(R.id.fast_registration);

        userDBOperation = new UserDBOperation(this);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(input_userName.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "请输入用户名", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(input_password.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_LONG).show();
                    return;
                }
                User user = new User();
                user.setUserName(input_userName.getText().toString());
                user.setUserPwd(input_password.getText().toString());
                long flag = userDBOperation.findUserByNameAndPwd(user);

                if (flag != 0) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                } else Toast.makeText(LoginActivity.this, "该用户不存在", Toast.LENGTH_LONG).show();

            }
        });
        fast_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }
}

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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.niit.englishhelp.R;
import cn.niit.englishhelp.database.User;
import cn.niit.englishhelp.database.UserDBOperation;

/**
 * User:xsl
 * Date:2017/5/22
 * Time:18:20
 */

public class RegisterActivity extends AppCompatActivity {
    private ActionBar mToolbar;
    private Toast mToast;

    private Button btn_register;
    private EditText input_userName, input_password, input_phone, input_rePassword;

    private UserDBOperation userDBOperation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mToolbar = getSupportActionBar();
        mToolbar.setTitle("注册");
        mToolbar.setDisplayHomeAsUpEnabled(true);
        input_userName = (EditText) findViewById(R.id.input_userName);
        input_password = (EditText) findViewById(R.id.input_password);
        input_phone = (EditText) findViewById(R.id.input_phone);
        input_rePassword = (EditText) findViewById(R.id.input_rePassword);
        userDBOperation = new UserDBOperation(this);

        btn_register = (Button) findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(input_userName.getText().toString())) {
                    toast("用户名不能为空");
                    return;
                }
                if (TextUtils.isEmpty(input_phone.getText().toString())) {
                    toast("手机号不能为空");
                    return;
                }
                if (!isMobileNO(input_phone.getText().toString())) {
                    toast("手机号格式不正确");
                    return;
                }
                if (TextUtils.isEmpty(input_password.getText().toString())) {
                    toast("密码不能为空");
                    return;
                }
                if (input_password.getText().toString().length() < 6) {
                    input_password.setError("密码过于简单");
                    return;
                }
                if (TextUtils.isEmpty(input_rePassword.getText().toString())) {
                    toast("请再次输入密码");
                    return;
                }

                if (!input_password.getText().toString().equals(input_rePassword.getText().toString())) {
                    toast("两次密码不一致");
                    return;
                }
                User user = new User();
                user.setUserName(input_userName.getText().toString());
                user.setUserPwd(input_password.getText().toString());
                long flag = userDBOperation.insetUser(user);
                if (flag == 0) {
                    toast("注册成功");
                    finish();
                } else toast("该用户已存在");
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

    public void toast(String str) {
        if (mToast == null) {
            mToast = Toast.makeText(getBaseContext(), str, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(str);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

}

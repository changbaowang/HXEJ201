package cn.hxgroup.www.hhu.ui.mainview;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.tools.SharedPreferencesUtils;
import cn.hxgroup.www.hhu.ui.base.BaseActivity;

public class LoginActivity extends BaseActivity {
    private EditText uesrnameEt;
    private EditText passwordEt;
    private Button loginBtn;
    private String mUsername = "admin";
    private String mPassword = "123456";
    private ImageView showIv;
    private TextView showTv;
    private RelativeLayout showRl;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                showTv.setText(msg.arg1 + "s");
            }
            if (msg.what == 2) {
                check();
            }
        }
    };

    @Override
    protected int getResourceId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        uesrnameEt = (EditText) findViewById(R.id.login_username_et);
        passwordEt = (EditText) findViewById(R.id.login_password_et);
        loginBtn = (Button) findViewById(R.id.login_btn);
        showIv = (ImageView) findViewById(R.id.guide_iv);
        showTv = (TextView) findViewById(R.id.guide_show_tv);
        showRl = (RelativeLayout) findViewById(R.id.login_show_rl);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        showIv();
    }

    @Override
    protected void initListener() {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private long time = 0;

    private void check() {
        time = System.currentTimeMillis();
        String logintime = SharedPreferencesUtils.getStringState(this, "logintime");
        try {
            long oldTime = Long.parseLong(logintime);
            if ("null".equals(logintime)) {
                showIv.setVisibility(View.INVISIBLE);
                showTv.setVisibility(View.INVISIBLE);
                showRl.setVisibility(View.VISIBLE);
                return;
            } else if (time - oldTime < (20*24*3600*1000)) {
                SharedPreferencesUtils.setStringState(this, "logintime", time + "");
                startActivity(new Intent(LoginActivity.this, MainViewActivity.class));
                finish();
            } else {
                showIv.setVisibility(View.INVISIBLE);
                showTv.setVisibility(View.INVISIBLE);
                showRl.setVisibility(View.VISIBLE);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            showIv.setVisibility(View.INVISIBLE);
            showTv.setVisibility(View.INVISIBLE);
            showRl.setVisibility(View.VISIBLE);
            return;
        }

    }

    private void showIv() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 3; i++) {
                        Thread.sleep(1000);
                        Message msg = new Message();
                        msg.arg1 = 2 - i;
                        msg.what = 1;
                        mHandler.sendMessage(msg);
                    }
                    mHandler.sendEmptyMessage(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void login() {
        String username = uesrnameEt.getText().toString();
        String password = passwordEt.getText().toString();
        if ("".equals(username) || "".equals(password)) {
            showToast(getResources().getString(R.string.UsernamePassIsEmpty));
        } else {
            if (mUsername.equals(username) && mPassword.equals(password)) {
                SharedPreferencesUtils.setStringState(this, "logintime", time + "");
                startActivity(new Intent(LoginActivity.this, MainViewActivity.class));
                finish();
            } else {
                showToast(getResources().getString(R.string.UsernamePassIsError));
            }
        }
    }
}

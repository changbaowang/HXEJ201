package cn.hxgroup.www.hhu.ui.feature;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.control.LocalUploadControl;
import cn.hxgroup.www.hhu.ui.base.BaseActivity;

/**
 * Created by HEX170 on 2016/10/24.
 * 对设备进行本地升级
 */
public class LocalUploadActivity extends BaseActivity implements View.OnClickListener {
    private View mBackBtn;
    private LinearLayout mTerminalLl;
    private TextView mVersionTv;
    private LocalUploadControl mControl;

    @Override
    protected int getResourceId() {
        return R.layout.activity_localupdate;
    }

    @Override
    protected void initView() {
        mBackBtn = findViewById(R.id.backBtn);
        mTerminalLl = (LinearLayout) findViewById(R.id.localupdate_terminal_version_ll);
        mVersionTv = (TextView) findViewById(R.id.localupdate_terminal_version_tv);
    }

    @Override
    protected void initData(Bundle saveedInstanceState) {
        mControl = new LocalUploadControl(this);
    }

    @Override
    protected void initListener() {
        mBackBtn.setOnClickListener(this);
        mTerminalLl.setOnClickListener(this);
    }

    public void updateVersion(String version) {
        mVersionTv.setText(version);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backBtn:
                finish();
                break;
            case R.id.localupdate_terminal_version_ll:
//                CommonUtils.goActivityForResult(this, FileSelectActivtiy.class, 111);
                mControl.pullData();
                break;

        }
    }

}

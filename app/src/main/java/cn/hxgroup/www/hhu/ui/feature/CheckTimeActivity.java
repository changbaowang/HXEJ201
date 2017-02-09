package cn.hxgroup.www.hhu.ui.feature;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.control.CheckTimeControl;
import cn.hxgroup.www.hhu.tools.SharedPreferencesUtils;
import cn.hxgroup.www.hhu.ui.base.BaseActivity;

/**
 * Created by CXJ on 2016/5/22.
 * 对时界面
 */
public class CheckTimeActivity extends BaseActivity implements View.OnClickListener {
    private View mBackBtn;
    private EditText mTimeEt;
    private CheckBox mCheckBox;
    private View mCheckTimeBtn;
    private TextView mDeviceTimeTv;
    private CheckTimeControl mControl;

    @Override
    protected int getResourceId() {
        return R.layout.activity_checktime;
    }

    @Override
    protected void initView() {
        mBackBtn = findViewById(R.id.backBtn);
        mCheckTimeBtn = findViewById(R.id.checkTimeBtn);
        mCheckBox = (CheckBox) findViewById(R.id.checktime_item_local_time_cb);
        mTimeEt = (EditText) findViewById(R.id.checktime_item_local_time_et);
        mDeviceTimeTv = (TextView) findViewById(R.id.checktime_item_device_time_tv);
    }

    @Override
    protected void initData(Bundle saveedInstanceState) {
        mControl = new CheckTimeControl(this);
    }

    @Override
    protected void initListener() {
        mBackBtn.setOnClickListener(this);
        mCheckTimeBtn.setOnClickListener(this);
        mCheckBox.setOnCheckedChangeListener(initCheckBoxListener());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backBtn:
                finish();
                break;
            case R.id.checkTimeBtn:
                String s = mTimeEt.getText().toString();
                mControl.checkTime(s);
                break;
        }
    }

    public void updateDeviceTime(String time) {
        mDeviceTimeTv.setText(time);
    }

    public void updateLocalTime(String time) {
        if (mCheckBox.isChecked()) {
            return;
        }
        mTimeEt.setText(time);
    }

    private CompoundButton.OnCheckedChangeListener initCheckBoxListener() {
        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mTimeEt.setEnabled(true);
                } else {
                    mTimeEt.setEnabled(false);
                }
            }
        };
        return listener;
    }

    @Override
    protected void onStop() {
        super.onStop();
        mControl.mLocalTimer.cancel();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mControl.destroy();
    }
}

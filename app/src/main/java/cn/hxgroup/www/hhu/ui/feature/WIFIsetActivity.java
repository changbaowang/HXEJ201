package cn.hxgroup.www.hhu.ui.feature;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.control.WifiSetControl;
import cn.hxgroup.www.hhu.ui.base.BaseActivity;

public class WIFIsetActivity extends BaseActivity implements View.OnClickListener {
    private ImageView mBackiv;
    private CheckBox mCheckBox;
    private EditText mWifinameEt, mOldEt, mNewEt, mNewEt2;
    private WifiSetControl mControl;
    private Button mSetpwsBtn, mSetnameBtn;
    private LinearLayout mPwsLayout;
    private boolean isclick = false;


    @Override
    protected int getResourceId() {
        return R.layout.activity_wifiset;
    }

    @Override
    protected void initView() {
        mBackiv = (ImageView) findViewById(R.id.feature_wifiset_backiv);
        mPwsLayout = (LinearLayout) findViewById(R.id.setwifi_ll2);
        mCheckBox = (CheckBox) findViewById(R.id.wifiset_item_local_wifi_cb);
        mWifinameEt = (EditText) findViewById(R.id.wifiset_item_local_wifi_et);
        mOldEt = (EditText) findViewById(R.id.setwifi_old_pws);
        mNewEt = (EditText) findViewById(R.id.setwifi_new_pws1);
        mNewEt2 = (EditText) findViewById(R.id.setwifi_new_pws2);
        mSetpwsBtn = (Button) findViewById(R.id.setwifi_setpws_btn);
        mSetnameBtn = (Button) findViewById(R.id.setwifi_setname_btn);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mControl = new WifiSetControl(this);
    }

    public void upDate(String str) {
        mWifinameEt.setText(str);
    }

    @Override
    protected void initListener() {
        mCheckBox.setOnCheckedChangeListener(initCheckBoxListener());
        mBackiv.setOnClickListener(this);
        mPwsLayout.setOnClickListener(this);
        mSetpwsBtn.setOnClickListener(this);
        mSetnameBtn.setOnClickListener(this);
    }

    private CompoundButton.OnCheckedChangeListener initCheckBoxListener() {
        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mWifinameEt.setEnabled(true);
                } else {
                    mWifinameEt.setEnabled(false);
                }
            }
        };
        return listener;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.feature_wifiset_backiv:
                finish();
                break;
            case R.id.setwifi_ll2:
                if (isclick) {
                    isclick = false;
                    mOldEt.setVisibility(View.INVISIBLE);
                    mNewEt.setVisibility(View.INVISIBLE);
                    mNewEt2.setVisibility(View.INVISIBLE);
                } else {
                    isclick = true;
                    mOldEt.setVisibility(View.VISIBLE);
                    mNewEt.setVisibility(View.VISIBLE);
                    mNewEt2.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.setwifi_setpws_btn:
                setupPws();
                break;
            case R.id.setwifi_setname_btn:
                setupName();
                break;
        }
    }

    private void setupName() {
        String mWifiname = mWifinameEt.getText().toString();
        if ("".equals(mWifiname)) {
            showToast(R.string.wifiName);
        } else {
            mControl.requestWifiName(mWifiname);
        }
    }

    private void setupPws() {
        String mOldpws = mOldEt.getText().toString();
        String mNewpws = mNewEt.getText().toString();
        String mNewpws2 = mNewEt2.getText().toString();
        mControl.requestWifiPws(mOldpws, mNewpws, mNewpws2);
    }
}


package cn.hxgroup.www.hhu.ui.feature;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.control.SimControl;
import cn.hxgroup.www.hhu.tools.SIMUtils;
import cn.hxgroup.www.hhu.tools.SendMessageUtil;
import cn.hxgroup.www.hhu.tools.SharedPreferencesUtils;
import cn.hxgroup.www.hhu.ui.base.BaseActivity;

/**
 * Created by HEX170 on 2016/8/2.
 * SIM卡验证界面
 */
public class SIMActivity extends BaseActivity implements View.OnClickListener {
    private ImageView mBackIv;
    private TextView mTitleTv;
    private TextView mImeiTv;
    private TextView mCcidTv;
    private TextView mPhoneTv;
    private EditText mPhoneEt;
    private EditText mVerificationEt;
    public Button mPhoneBtn;
    private Button mVerificationBtn;
    private SimControl mControl;
    private View phoneview;
    private View hasVerificationll;


    @Override
    protected int getResourceId() {
        return R.layout.activity_sim;
    }

    @Override
    protected void initView() {
        mBackIv = (ImageView) findViewById(R.id.point_sim_back_iv);
        mTitleTv = (TextView) findViewById(R.id.sim_title_value_tv);
        mImeiTv = (TextView) findViewById(R.id.sim_has_verification_imei_tv);
        mCcidTv = (TextView) findViewById(R.id.sim_has_verification_ccid_tv);
        mPhoneTv = (TextView) findViewById(R.id.sim_has_verification_phone_tv);
        mPhoneEt = (EditText) findViewById(R.id.sim_phone_num_et);
        mPhoneBtn = (Button) findViewById(R.id.sim_phone_num_btn);
        mVerificationBtn = (Button) findViewById(R.id.sim_has_verification_btn);
        mVerificationEt = (EditText) findViewById(R.id.sim_verification_num_et);
        phoneview = findViewById(R.id.sim_phone_ll);
        hasVerificationll = findViewById(R.id.sim_has_verification_ll);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mControl = new SimControl(this);
        chack();
    }

    //检查认证是否通过
    private void chack() {
        String mimei = SharedPreferencesUtils.getStringState(this, "imei");
        String mccid = SharedPreferencesUtils.getStringState(this, "ccid");
        String mphone = SharedPreferencesUtils.getStringState(this, "phone");
        if ("null".equals(mimei)) {
        } else {
            phoneview.setVisibility(View.INVISIBLE);
            mVerificationBtn.setVisibility(View.INVISIBLE);
            hasVerificationll.setVisibility(View.VISIBLE);
            mTitleTv.setText(getResources().getString(R.string.kSIMAuthenticationHas));
            mImeiTv.setText("IMEI :" + mimei);
            mCcidTv.setText("CCID :" + mccid);
            if (mphone.length() > 4) {
                mphone = mphone.substring(0, mphone.length() - 4) + "xxxx";
                mPhoneTv.setText("PHONE :" + mphone);
            }
        }
    }

    @Override
    protected void initListener() {
        mBackIv.setOnClickListener(this);
        mPhoneBtn.setOnClickListener(this);
        mVerificationBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.point_sim_back_iv:
                finish();
                break;
            case R.id.sim_phone_num_btn:
                mControl.dellPhone(mPhoneEt.getText().toString());
                break;
            case R.id.sim_has_verification_btn:
                pullVerification();
                break;
        }
    }

    private void pullVerification() {
        boolean b = mControl.pullVerification(mVerificationEt.getText().toString());
        if (b) {
            phoneview.setVisibility(View.INVISIBLE);
            mVerificationBtn.setVisibility(View.INVISIBLE);
            hasVerificationll.setVisibility(View.VISIBLE);
            List<String> siMinfo = SIMUtils.getSIMinfo(this);
            SharedPreferencesUtils.setStringState(this, "imei", siMinfo.get(0));
            SharedPreferencesUtils.setStringState(this, "ccid", siMinfo.get(1));
            SharedPreferencesUtils.setStringState(this, "phone", mPhoneEt.getText().toString());
            mTitleTv.setText(getResources().getString(R.string.kSIMAuthenticationHas));
            mImeiTv.setText("IMEI :" + siMinfo.get(0));
            mCcidTv.setText("CCID :" + siMinfo.get(1));
            mPhoneTv.setText("PHONE :" + mPhoneEt.getText().toString());
        }
    }

    public void update(int time) {
        if (0 == time) {
            mPhoneBtn.setText(getResources().getString(R.string.kSIMUsernum));
            mPhoneBtn.setClickable(true);
        } else {
            mPhoneBtn.setText(time + "s");
            mPhoneBtn.setClickable(false);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        SendMessageUtil.unregister(this);
    }
}

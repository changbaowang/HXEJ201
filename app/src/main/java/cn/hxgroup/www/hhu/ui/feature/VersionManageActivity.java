package cn.hxgroup.www.hhu.ui.feature;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import java.util.List;
import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.control.VersionManageControl;
import cn.hxgroup.www.hhu.ui.base.BaseActivity;

/**
 * Created by CXJ on 2016/5/22.
 * 版本管理
 */
public class VersionManageActivity extends BaseActivity implements OnClickListener {
    private TextView mManufacturerTv;
    private TextView mDeviceCodeTv;
    private TextView mSoftWareVersionTv;
    private TextView mSoftWareDateTv;
    private TextView mDevcieCapacityTv;
    private TextView mProtocolVersionTv;
    private TextView mHardWareVersionTv;
    private TextView mHardWareDateTv;
    private TextView mCommunicationVersionTv;

    private View mBackBtn;
    private VersionManageControl mControl;

    @Override
    protected int getResourceId() {
        return R.layout.activity_version_manage;
    }

    @Override
    protected void initView() {
        mBackBtn = findViewById(R.id.backBtn);
        mManufacturerTv = (TextView) findViewById(R.id.version_item_manufacturer_tv);
        mDeviceCodeTv = (TextView) findViewById(R.id.version_item_devicecode_tv);
        mSoftWareVersionTv = (TextView) findViewById(R.id.version_item_software_version_tv);
        mSoftWareDateTv = (TextView) findViewById(R.id.version_item_software_date_tv);
        mDevcieCapacityTv = (TextView) findViewById(R.id.version_item_device_capacity_tv);
        mProtocolVersionTv = (TextView) findViewById(R.id.version_item_device_protocol_version_tv);
        mHardWareVersionTv = (TextView) findViewById(R.id.version_item_hardware_version_tv);
        mHardWareDateTv = (TextView) findViewById(R.id.version_item_hardware_date_tv);
        mCommunicationVersionTv = (TextView) findViewById(R.id.version_item_communication_tv);
    }

    @Override
    protected void initData(Bundle saveedInstanceState) {
        mControl = new VersionManageControl(this);
    }

    @Override
    protected void initListener() {
        mBackBtn.setOnClickListener(this);
    }

    public void setData(List<String> list) {
        mManufacturerTv.setText(list.get(0));
        mDeviceCodeTv.setText(list.get(1));
        mSoftWareVersionTv.setText(list.get(2));
        mSoftWareDateTv.setText(list.get(3));
        mDevcieCapacityTv.setText(list.get(4));
        mProtocolVersionTv.setText(list.get(5));
        mHardWareVersionTv.setText(list.get(6));
        mHardWareDateTv.setText(list.get(7));
    }

    public void setData(String list) {
        mCommunicationVersionTv.setText(list);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backBtn:
                mControl.destroy();
                finish();
                break;
        }
    }
}

package cn.hxgroup.www.hhu.ui.feature;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.control.DeviceOperationControl;
import cn.hxgroup.www.hhu.ui.base.BaseActivity;
import cn.hxgroup.www.hhu.ui.component.MessageDialog;

/**
 * Created by CXJ on 2016/5/22.
 * 继电器操作界面
 */
public class DeviceOperationActivity extends BaseActivity implements View.OnClickListener {
    private View mBackBtn;
    private TextView stateTv;
    private View mRemoteSwitchOffLayout;
    private View mAllowSwitchOffLayout;
    private View mRemoteSwitchOnLayout;

    private DeviceOperationControl mControl;

    @Override
    protected int getResourceId() {
        return R.layout.activity_device_operation;
    }

    @Override
    protected void initView() {
        mBackBtn = findViewById(R.id.backBtn);
        stateTv = (TextView) findViewById(R.id.operation_item_state_state_tv);
        mRemoteSwitchOffLayout = findViewById(R.id.operation_item_remote_switch_off_ll);
        mAllowSwitchOffLayout = findViewById(R.id.operation_item_allow_switch_on_ll);
        mRemoteSwitchOnLayout = findViewById(R.id.operation_item_remote_switch_on_ll);
    }

    @Override
    protected void initData(Bundle saveedInstanceState) {
        mControl = new DeviceOperationControl(this);
    }

    @Override
    protected void initListener() {
        mBackBtn.setOnClickListener(this);
        mRemoteSwitchOffLayout.setOnClickListener(this);
        mAllowSwitchOffLayout.setOnClickListener(this);
        mRemoteSwitchOnLayout.setOnClickListener(this);
    }

    public void upData(String data) {
        stateTv.setText(data);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(DeviceOperationActivity.this, DeviceOperation2Activity.class);
        switch (v.getId()) {
            case R.id.backBtn:
                mControl.destroy();
                finish();
                break;
            case R.id.operation_item_remote_switch_off_ll:
                intent.putExtra("id", 1);
                startActivityForResult(intent, 1);
                break;
            case R.id.operation_item_allow_switch_on_ll:
                showTipDialog();
                break;
            case R.id.operation_item_remote_switch_on_ll:
                intent.putExtra("id", 2);
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1 || resultCode == 2) {
            String delayTime = data.getStringExtra("delayTime");
            String restrictEleTime = data.getStringExtra("restrictEleTime");
            switch (resultCode) {
                case 1:
                    mControl.requestData(delayTime, restrictEleTime);
                    break;
                case 2:
                    mControl.requestData(delayTime);
                    break;
            }
        }
    }

    /**
     * 点击按钮后的提示对话框
     */
    private void showTipDialog() {
        MessageDialog dialog = new MessageDialog(this, R.style.CustomDialogStyle);
        dialog.setTitle(R.string.kTip);
        String allow = getResources().getString(R.string.kAllowSwitchOn) + "?";
        dialog.setMessage(allow);
        dialog.setOnLeftBtnClickListener(R.string.kCancel, null);
        dialog.setOnRightBtnClickListener(R.string.kOk, new MessageDialog.Callback() {
            @Override
            public void callback() {
                mControl.requestData1();
            }
        });
        dialog.show();
    }
}

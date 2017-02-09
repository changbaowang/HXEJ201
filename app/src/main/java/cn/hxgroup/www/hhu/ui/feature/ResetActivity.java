package cn.hxgroup.www.hhu.ui.feature;

import android.os.Bundle;
import android.view.View;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.constant.ResetType;
import cn.hxgroup.www.hhu.control.ResetControl;
import cn.hxgroup.www.hhu.ui.base.BaseActivity;
import cn.hxgroup.www.hhu.ui.component.MessageDialog;

/**
 * Created by CXJ on 2016/6/3.
 */
public class ResetActivity extends BaseActivity implements View.OnClickListener {

    private View mResetDeviceLayout;
    private View mResetDataLayout;
    private View mResetAllLayout;
    private View mBackBtn;
    private ResetControl mControl;

    @Override
    protected int getResourceId() {
        return R.layout.activity_reset;
    }

    @Override
    protected void initView() {
        mResetDeviceLayout = findViewById(R.id.reset_device_ll);
        mResetDataLayout = findViewById(R.id.reset_data_ll);
        mResetAllLayout = findViewById(R.id.reset_all_ll);
        mBackBtn = findViewById(R.id.backBtn);
    }

    @Override
    protected void initData(Bundle saveedInstanceState) {
        mControl = new ResetControl(this);
    }

    @Override
    protected void initListener() {
        mResetDeviceLayout.setOnClickListener(this);
        mResetDataLayout.setOnClickListener(this);
        mResetAllLayout.setOnClickListener(this);
        mBackBtn.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mControl.destroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reset_device_ll:
                showTipDialog(ResetType.RESET_TYPE_DEVICE);
                break;
            case R.id.reset_data_ll:
                showTipDialog(ResetType.RESET_TYPE_DATA);
                break;
            case R.id.reset_all_ll:
                showTipDialog(ResetType.RESET_TYPE_ALL);
                break;
            case R.id.backBtn:
                finish();
                break;
        }
    }

    /**
     * 点击按钮后的提示对话框
     */
    private void showTipDialog(final ResetType type) {
        MessageDialog dialog = new MessageDialog(this, R.style.CustomDialogStyle);
        dialog.setTitle(R.string.kTip);
        dialog.setMessage(R.string.Allowreset);
        dialog.setOnLeftBtnClickListener(R.string.kCancel, null);
        dialog.setOnRightBtnClickListener(R.string.kOk, new MessageDialog.Callback() {
            @Override
            public void callback() {
                mControl.reset(type);
            }
        });
        dialog.show();
    }


    /**
     * 收到服务器的确认命令弹出的确认对话框
     */
    public void showResponseConfirmDialog() {

    }
}

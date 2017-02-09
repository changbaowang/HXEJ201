package cn.hxgroup.www.hhu.ui.feature;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.ui.base.BaseActivity;

/**
 * Created by CXJ on 2016/5/22.
 * 表计档案管理界面
 */
public class DeviceDocumentManageActivity extends BaseActivity implements View.OnClickListener {
    private ImageView backIv;

    @Override
    protected int getResourceId() {
        return R.layout.activity_device_document_manage;
    }

    @Override
    protected void initView() {
        backIv = (ImageView) findViewById(R.id.Device_Document_Manage_backiv);
    }

    @Override
    protected void initData(Bundle saveedInstanceState) {

    }

    @Override
    protected void initListener() {
        backIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Device_Document_Manage_backiv:
                finish();
                break;
        }
    }
}

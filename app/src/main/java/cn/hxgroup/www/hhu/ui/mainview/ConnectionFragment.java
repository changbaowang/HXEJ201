package cn.hxgroup.www.hhu.ui.mainview;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.business.AppConfig;
import cn.hxgroup.www.hhu.control.ConnectionControl;
import cn.hxgroup.www.hhu.ui.base.BaseFragment;

/**
 * Created by CXJ on 2016/5/22.
 */
public class ConnectionFragment extends BaseFragment implements View.OnClickListener {

    private Button mWifiBtn;
    private Button mSetBtn;
    private ConnectionControl mControl;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.connection_wifi_btn://wifi连接
                toWIFIActivity();
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mContentView == null) {
            mContentView = inflater.inflate(R.layout.fragment_connection, container, false);
            initView();
            initData();
            initListener();
        }
        return mContentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void initView() {
        mWifiBtn = (Button) findViewById(R.id.connection_wifi_btn);
        mSetBtn = (Button) findViewById(R.id.connection_set_btn);
    }

    private void initData() {
        mControl = new ConnectionControl(this);
    }

    private void initListener() {
        mWifiBtn.setOnClickListener(this);
    }

    private void toWIFIActivity() {
        Intent intent = new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
        startActivity(intent);
    }


    //显示终端ID和连接wifi的名称
    public void upData(String terminalId) {
        if ("".equals(terminalId)) {
            return;
        }
        mSetBtn.setVisibility(View.VISIBLE);
        mSetBtn.setText(getResources().getString(R.string.kConnected) + ": " + terminalId);
    }

    public boolean chack() {
        boolean wifi = AppConfig.getInstance().getWifiState();
        if (!wifi) {
            mSetBtn.setVisibility(View.INVISIBLE);
        }
        CharSequence text = mSetBtn.getText();
        if ("".equals(text)) {
            return false;
        }
        return true;
    }
}

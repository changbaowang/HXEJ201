package cn.hxgroup.www.hhu.ui.mainview;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.business.AppConfig;
import cn.hxgroup.www.hhu.ui.TimeOutSelectActivity;
import cn.hxgroup.www.hhu.ui.base.BaseFragment;
import cn.hxgroup.www.hhu.ui.feature.GpsSetActivity;
import cn.hxgroup.www.hhu.ui.feature.IPsetActivity;
import cn.hxgroup.www.hhu.ui.feature.ManageDataActivity;
import cn.hxgroup.www.hhu.ui.feature.SIMActivity;
import cn.hxgroup.www.hhu.ui.feature.TerminalEleSetActivity;
import cn.hxgroup.www.hhu.ui.feature.WIFIsetActivity;
import cn.hxgroup.www.hhu.ui.settings.AppUpdateActivity;
import cn.hxgroup.www.hhu.utils.CommonUtils;

/**
 * Created by CXJ on 2016/5/22.
 */
public class SettingFragment extends BaseFragment implements View.OnClickListener {
    private View mAppUpdateLayout;
    private View mTimeoutLayout;
    private TextView mTimeOutTv, mWifiTv, mIPtv, mGpsTv, mDataTv,mTerminalEleTv;
    private View mSimll;


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting_checkupdate_ll://APP升级
                CommonUtils.goActivityWithoutParam(getActivity(), AppUpdateActivity.class);
                break;
            case R.id.setting_sim_ll://SIM卡认证
                CommonUtils.goActivityWithoutParam(getActivity(), SIMActivity.class);
                break;
            case R.id.setting_timeout_ll://超时时间设置界面
                CommonUtils.goActivityWithoutParam(getActivity(), TimeOutSelectActivity.class);
                break;
            case R.id.setting_setwifi_tv://wifi设置读取界面
                CommonUtils.goActivityWithoutParam(getActivity(), WIFIsetActivity.class);
                break;
            case R.id.setting_setip_tv://IP设置读取界面
                CommonUtils.goActivityWithoutParam(getActivity(), IPsetActivity.class);
                break;
            case R.id.setting_setgps_tv://gps设置读取界面
                CommonUtils.goActivityWithoutParam(getActivity(), GpsSetActivity.class);
                break;
            case R.id.setting_terminal_ele_tv://终端底度电量读取设置
                CommonUtils.goActivityWithoutParam(getActivity(), TerminalEleSetActivity.class);
                break;
            case R.id.setting_data_tv://数据管理界面
                CommonUtils.goActivityWithoutParam(getActivity(), ManageDataActivity.class);
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mContentView == null) {
            mContentView = inflater.inflate(R.layout.fragment_setting, container, false);
            initView();
            initListener();
        }
        return mContentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        mTimeOutTv.setText("" + AppConfig.getInstance().getTimeOut() + getResources().getString(R.string.kSecond));
    }

    private void initView() {
        mAppUpdateLayout = findViewById(R.id.setting_checkupdate_ll);
        mTimeoutLayout = findViewById(R.id.setting_timeout_ll);
        mSimll = findViewById(R.id.setting_sim_ll);
        mTimeOutTv = (TextView) findViewById(R.id.setting_timeout_tv);
        mWifiTv = (TextView) findViewById(R.id.setting_setwifi_tv);
        mIPtv = (TextView) findViewById(R.id.setting_setip_tv);
        mGpsTv = (TextView) findViewById(R.id.setting_setgps_tv);
        mDataTv = (TextView) findViewById(R.id.setting_data_tv);
        mTerminalEleTv = (TextView) findViewById(R.id.setting_terminal_ele_tv);
    }

    private void initListener() {
        mAppUpdateLayout.setOnClickListener(this);
        mTimeoutLayout.setOnClickListener(this);
        mWifiTv.setOnClickListener(this);
        mIPtv.setOnClickListener(this);
        mGpsTv.setOnClickListener(this);
        mSimll.setOnClickListener(this);
        mDataTv.setOnClickListener(this);
        mTerminalEleTv.setOnClickListener(this);
    }
}

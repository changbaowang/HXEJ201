package cn.hxgroup.www.hhu.ui.feature;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.control.ManageDataControl;
import cn.hxgroup.www.hhu.ui.base.BaseActivity;
import cn.hxgroup.www.hhu.utils.CommonUtils;

public class ManageDataActivity extends BaseActivity implements View.OnClickListener {
    private TextView realdataTv, activeTv, clearTv, terminalTv;
    private ImageView backIv;
    private TextView clearTerTv, clearMeterTv, clearRealTv;
    private ManageDataControl mControl;

    @Override
    protected int getResourceId() {
        return R.layout.activity_manage_data;
    }

    @Override
    protected void initView() {
        realdataTv = (TextView) findViewById(R.id.setting_realdata_tv);
        activeTv = (TextView) findViewById(R.id.setting_active_value_tv);
        clearTv = (TextView) findViewById(R.id.setting_clear_data_tv);
        backIv = (ImageView) findViewById(R.id.setting_data_back_iv);
        terminalTv = (TextView) findViewById(R.id.setting_terminal_value_tv);
//        clearTerTv = (TextView) findViewById(R.id.setting_clear_terminal_acdata_tv);
//        clearMeterTv = (TextView) findViewById(R.id.setting_clear_meter_acdata_tv);
//        clearRealTv = (TextView) findViewById(R.id.setting_clear_rldata_tv);

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mControl = new ManageDataControl(this);
    }

    @Override
    protected void initListener() {
        realdataTv.setOnClickListener(this);
        activeTv.setOnClickListener(this);
        clearTv.setOnClickListener(this);
        backIv.setOnClickListener(this);
        terminalTv.setOnClickListener(this);
//        clearTerTv.setOnClickListener(this);
//        clearMeterTv.setOnClickListener(this);
//        clearRealTv.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting_active_value_tv:  //导出所有日冻结数据
                mControl.getActiveValue();
                break;
            case R.id.setting_realdata_tv:      //导出实时数据
                mControl.getRealData();
                break;
            case R.id.setting_clear_data_tv:   //清除所有数据
                mControl.clearData();
                break;
            case R.id.setting_data_back_iv:
                finish();
                break;
            case R.id.setting_terminal_value_tv:    //读取终端日冻结数据
                CommonUtils.goActivityWithoutParam(ManageDataActivity.this, TermFroDataActivity.class);
                break;
//            case R.id.setting_clear_terminal_acdata_tv:    //清除终端日冻结数据
////                mControl.clearData();
//                break;
//            case R.id.setting_clear_meter_acdata_tv:        //清除表计日冻结数据
////                mControl.clearData();
//                break;
//            case R.id.setting_clear_rldata_tv:              //清除实时数据
////                mControl.clearData();
//                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mControl.realDatabase.close();
        mControl.database.close();
    }
}

package cn.hxgroup.www.hhu.ui.feature;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.adapter.CommonLVadapter;
import cn.hxgroup.www.hhu.control.ActivePowerControl;
import cn.hxgroup.www.hhu.tools.SharedPreferencesUtils;
import cn.hxgroup.www.hhu.ui.base.BaseActivity;

/**
 * Created by HEX170 on 2016/8/2.
 * 当前正向有功电能示值界面
 */


public class ActivePowerActivity extends BaseActivity {
    private ImageView mBackIv;
    private ListView mListView;
    private CommonLVadapter mAdapter;
    private List<String> data = new ArrayList<>();
    private List<String> strData = new ArrayList<>();
    public String pn = "";
    private ActivePowerControl mControl;

    @Override
    protected int getResourceId() {
        return R.layout.activity_active_power;
    }

    @Override
    protected void initView() {
        mBackIv = (ImageView) findViewById(R.id.active_power_back_iv);
        mListView = (ListView) findViewById(R.id.active_power_listview);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        pn = SharedPreferencesUtils.getStringState(this, "pn");
        mControl = new ActivePowerControl(this);
        pullData(1);
        mAdapter = new CommonLVadapter(strData, data, this);
        mListView.setAdapter(mAdapter);
    }

    public void update(List<String> realdata) {
        data.clear();
        data.addAll(realdata);
        pullData(Integer.parseInt(data.get(1)));
        mAdapter.notifyDataSetChanged();
    }

    //解析终端返回数据
    private List<String> pullData(int n) {
        strData.clear();
        strData.add(getResources().getString(R.string.TerminalMeterReadingTime));
        strData.add(getResources().getString(R.string.RateNumber));
        strData.add(getResources().getString(R.string.TotalPowerIndication));
        for (int i = 0; i < n; i++) {
            strData.add(getResources().getString(R.string.Rate) + (i + 1) + getResources().getString(R.string.PowerIndication));
        }
        return strData;
    }

    @Override
    protected void initListener() {
        mBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

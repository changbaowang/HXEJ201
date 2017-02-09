package cn.hxgroup.www.hhu.ui.feature;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.adapter.CommonLVadapter;
import cn.hxgroup.www.hhu.business.AppConfig;
import cn.hxgroup.www.hhu.tools.SharedPreferencesUtils;
import cn.hxgroup.www.hhu.ui.base.BaseActivity;
import hexing.icomm.IGetdataCallback;
import hexing.tools.GetTerminalInfoByParam;

public class PowerActivity extends BaseActivity {
    private ImageView mBackIv;
    private ListView mListView;
    private CommonLVadapter mAdapter;
    private List<String> data = new ArrayList<>();
    private List<String> strData = new ArrayList<>();
    private GetTerminalInfoByParam getTerminalInfoByParam;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (!flag) {
                showToast(R.string.NoData);
            }
        }
    };

    @Override
    protected int getResourceId() {
        return R.layout.activity_power;
    }

    @Override
    protected void initView() {
        mBackIv = (ImageView) findViewById(R.id.active_power_back_iv);
        mListView = (ListView) findViewById(R.id.active_power_listview);
        getTerminalInfoByParam = new GetTerminalInfoByParam(this);
    }

    public String pn = "";

    @Override
    protected void initData(Bundle savedInstanceState) {
        pn = SharedPreferencesUtils.getStringState(this, "pn");
        String tempPn = getIntent().getStringExtra("pn");
//        showToast(tempPn);
        if (tempPn == null) {
        } else {
            pn = tempPn;
        }
        pullData(0);
        mAdapter = new CommonLVadapter(strData, data, this);
        mListView.setAdapter(mAdapter);
        getdata();
    }


    //解析终端返回数据
    private List<String> pullData(int n) {
        strData.clear();
        strData.add(getResources().getString(R.string.TerminalMeterReadingTime));
        strData.add(getResources().getString(R.string.RateNumber));
        strData.add(getResources().getString(R.string.TotalPowerIndication));
        for (int i = 0; i < n; i++) {
            strData.add(getResources().getString(R.string.Rate) + i + getResources().getString(R.string.PowerIndication));
        }
        return strData;
    }

    @Override
    protected void initListener() {
        mBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private boolean flag = false;

    private void getdata() {
        if (pn == null) {
            pn = "0";
        }
        boolean wifi = AppConfig.getInstance().getWifiState();
        if (wifi) {
            handler.sendEmptyMessageDelayed(1, 2000);
            getTerminalInfoByParam.get("0C", "129", pn, "", new IGetdataCallback() {
                @Override
                public void succeed(List<String> res) {
                    flag = true;
                    if (res != null && res.size() > 3) {
                        data.addAll(res);
                        try {
                            int n = Integer.parseInt(data.get(1));
                            pullData(n);
                            mAdapter.notifyDataSetChanged();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        showToast(R.string.NoData);
                    }
                }
            });
        }
    }
}

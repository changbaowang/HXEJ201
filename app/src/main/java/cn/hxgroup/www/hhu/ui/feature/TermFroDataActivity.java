package cn.hxgroup.www.hhu.ui.feature;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.adapter.CommonLVadapter;
import cn.hxgroup.www.hhu.control.TermFroDataControl;
import cn.hxgroup.www.hhu.ui.base.BaseActivity;

public class TermFroDataActivity extends BaseActivity implements View.OnClickListener {
    public EditText strDateTv, endDataTv;
    private ImageView backIv;
    private TermFroDataControl mControl;
    private Button strBtn, endBtn, queryBtn;
    public Button saveBtn;
    public TextView numTv;
    private ListView listView;
    private CommonLVadapter mAdapter;
    private List<String> data = new ArrayList<>();
    private List<String> strData = new ArrayList<>();

    @Override
    protected int getResourceId() {
        return R.layout.activity_term_fro_data;
    }

    @Override
    protected void initView() {
        backIv = (ImageView) findViewById(R.id.setting_termial_data_back_iv);
        strDateTv = (EditText) findViewById(R.id.terfor_data_strDate_et);
        endDataTv = (EditText) findViewById(R.id.terfor_data_endDate_et);
        strBtn = (Button) findViewById(R.id.terfor_data_strDate_btn);
        endBtn = (Button) findViewById(R.id.terfor_data_endDate_btn);
        queryBtn = (Button) findViewById(R.id.terfor_data_query);
        saveBtn = (Button) findViewById(R.id.terfor_data_save);
        numTv = (TextView) findViewById(R.id.terfor_data_num_tv);
        listView = (ListView) findViewById(R.id.terfor_data_listview);
//        String strDate = SharedPreferencesUtils.getStringState(this, "strDate");
//        String endDate = SharedPreferencesUtils.getStringState(this, "endDate");
//        if (strDate != null) {
//            strDateTv.setText(strDate);
//        }
//        if (endDate != null) {
//            endDataTv.setText(endDate);
//        }
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mControl = new TermFroDataControl(this);
        mAdapter = new CommonLVadapter(strData, data, this);
        listView.setAdapter(mAdapter);
    }

    @Override
    protected void initListener() {
        backIv.setOnClickListener(this);
        strBtn.setOnClickListener(this);
        endBtn.setOnClickListener(this);
        queryBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting_termial_data_back_iv:
                finish();
                break;
            case R.id.terfor_data_strDate_btn:
                mControl.getTime(0);
                break;
            case R.id.terfor_data_endDate_btn:
                mControl.getTime(1);
                break;
            case R.id.terfor_data_query:
                mControl.query();
                break;
            case R.id.terfor_data_save:
                mControl.saveData();
                break;
        }
    }

    public void update(List<String> realdata) {
        data.clear();
        data.addAll(realdata);
        if (data.size() > 2) {
            int rate = Integer.parseInt(data.get(2));
            int length = 8 - (4 - rate);
            pullData(rate, realdata.size() / length);
            mAdapter.notifyDataSetChanged();
        } else {
            showToast(getResources().getString(R.string.MeterNotExist));
        }
    }

    //解析终端返回数据
    private List<String> pullData(int n, int k) {
        strData.clear();
        for (int j = 0; j < k; j++) {
            strData.add(getResources().getString(R.string.kDataTimeScale));
            strData.add(getResources().getString(R.string.TerminalMeterReadingTime));
            strData.add(getResources().getString(R.string.RateNumber));
            strData.add(getResources().getString(R.string.TotalPowerIndication));
            for (int i = 0; i < n; i++) {
                strData.add(getResources().getString(R.string.Rate) + i + getResources().getString(R.string.PowerIndication));
            }
        }
        return strData;
    }
}

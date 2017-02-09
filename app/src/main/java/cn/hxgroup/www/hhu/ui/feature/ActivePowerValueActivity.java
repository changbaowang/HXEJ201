package cn.hxgroup.www.hhu.ui.feature;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.adapter.CommonLVadapter;
import cn.hxgroup.www.hhu.control.ActivePowerValueControl;
import cn.hxgroup.www.hhu.tools.DialogUtils;
import cn.hxgroup.www.hhu.tools.SharedPreferencesUtils;
import cn.hxgroup.www.hhu.ui.base.BaseActivity;
import cn.hxgroup.www.hhu.ui.interfaces.IGetDialogDatepicker;
import hexing.tools.DecimalConversion;

/**
 * Created by HEX170 on 2016/8/2.
 * 日冻结正向有功电能示值界面
 */
public class ActivePowerValueActivity extends BaseActivity implements View.OnClickListener {
    private ImageView mBackIv;
    private ListView mListView;
    private CommonLVadapter mAdapter;
    private EditText mTimeEt;
    private Button mQueryBtn;
    private Button mSaveBtn;
    private List<String> data = new ArrayList<>();
    private List<String> strData = new ArrayList<>();
    private ActivePowerValueControl mControl;


    @Override
    protected int getResourceId() {
        return R.layout.activity_active_power_value;
    }

    @Override
    protected void initView() {
        mBackIv = (ImageView) findViewById(R.id.active_power_value_back_iv);
        mListView = (ListView) findViewById(R.id.active_power_value_listview);
        mTimeEt = (EditText) findViewById(R.id.active_power_value_time_et);
        mQueryBtn = (Button) findViewById(R.id.active_power_value_time_btn);
        mSaveBtn = (Button) findViewById(R.id.active_value_savedata_btn);
    }

    public String pn = "";
    public String address = "";

    @Override
    protected void initData(Bundle savedInstanceState) {
        pn = SharedPreferencesUtils.getStringState(this, "pn");
        String tempPn = getIntent().getStringExtra("pn");
        address = getIntent().getStringExtra("address");
        if (tempPn == null) {
        } else {
            pn = tempPn;
        }
        mControl = new ActivePowerValueControl(this);
        pullData(1);
        mAdapter = new CommonLVadapter(strData, data, this);
        mListView.setAdapter(mAdapter);
    }

    public void update(List<String> realdata) {
        data.clear();
        data.addAll(realdata);
        if (data.size() > 2) {
            pullData(Integer.parseInt(data.get(2)));
            mAdapter.notifyDataSetChanged();
        } else {
            showToast(getResources().getString(R.string.MeterNotExist));
        }
    }

    //解析终端返回数据
    private List<String> pullData(int n) {
        strData.clear();
        strData.add(getResources().getString(R.string.kDataTimeScale));
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
        mBackIv.setOnClickListener(this);
        mQueryBtn.setOnClickListener(this);
        mSaveBtn.setOnClickListener(this);
        mTimeEt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.active_power_value_back_iv:
                finish();
                break;
            case R.id.active_power_value_time_btn:
                String s = mTimeEt.getText().toString();
                if ("".equals(s)) {
                    this.showToast(R.string.kTime);
                } else {
                    mControl.requestData(s);
                }
                break;
            case R.id.active_value_savedata_btn:
                mControl.saveData();
                break;
            case R.id.active_power_value_time_et:
//                showAlertDialog();
                choiceDate();
                break;
        }
    }

    private void choiceDate() {
        DialogUtils dialogUtils = new DialogUtils(this);
        dialogUtils.showAlertDialog(new IGetDialogDatepicker() {
            @Override
            public void getDatePicker(DatePicker datePicker) {
                int year = datePicker.getYear();
                int month = datePicker.getMonth() + 1;
                int dayOfMonth = datePicker.getDayOfMonth();
                String temp = year + "-" + DecimalConversion.padLeft1(month + "", 2, "0") + "-" + DecimalConversion.padLeft1(dayOfMonth + "", 2, "0");
                mTimeEt.setText(temp);
//                showToast(temp);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mControl.database.close();
    }
}

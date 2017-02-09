package cn.hxgroup.www.hhu.ui.feature;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.adapter.SpinnerAdapter;
import cn.hxgroup.www.hhu.control.AddMeterControl;
import cn.hxgroup.www.hhu.ui.base.BaseActivity;

/**
 * Created by HEX170 on 2016/8/2.
 * 添加表计界面
 */
public class AddMeterActivity extends BaseActivity implements View.OnClickListener {
    private ImageView backIv;
    private Button addBtn;
    private Spinner portSp;
    private Spinner rateSp;
    private Spinner protocolSp;
    private EditText addressEt;
    private AddMeterControl mControl;
    private SpinnerAdapter portAdapter;
    private SpinnerAdapter rateAdapter;
    private SpinnerAdapter protocolAdapter;
    private List<String> portData = new ArrayList<>();
    private List<String> rateData = new ArrayList<>();
    private List<String> protocolData = new ArrayList<>();
    private List<String> AddMeterData = new ArrayList<>();
    private int id = 0;


    @Override
    protected int getResourceId() {
        return R.layout.activity_add_meter;
    }

    @Override
    protected void initView() {
        backIv = (ImageView) findViewById(R.id.terminal_add_meter_iv);
        addBtn = (Button) findViewById(R.id.add_meter_btn);
        rateSp = (Spinner) findViewById(R.id.add_meter_rate_sp);
        portSp = (Spinner) findViewById(R.id.add_meter_port_sp);
        protocolSp = (Spinner) findViewById(R.id.add_meter_protocol_sp);
        addressEt = (EditText) findViewById(R.id.add_meter_address_tv);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        id = getIntent().getIntExtra("id", 0);
        for (int i = 0; i < 5; i++) {
            AddMeterData.add("0");
        }
        mControl = new AddMeterControl(this);
//        portData.add("RS485");  预留拓展RS482
        portData.add("RF/PLC");
        rateData.add(getResources().getString(R.string.kMeterDefault));
        rateData.add("600");
        rateData.add("1200");
        rateData.add("2400");
        rateData.add("4800");
        rateData.add("7200");
        rateData.add("9600");
        rateData.add("19200");
        protocolData.add(getResources().getString(R.string.kMeterDefault));
        protocolData.add("交采");
        portAdapter = new SpinnerAdapter(portData, this);
        rateAdapter = new SpinnerAdapter(rateData, this);
        protocolAdapter = new SpinnerAdapter(protocolData, this);
        portSp.setAdapter(portAdapter);
        rateSp.setAdapter(rateAdapter);
        protocolSp.setAdapter(protocolAdapter);
    }

    @Override
    protected void initListener() {
        backIv.setOnClickListener(this);
        addBtn.setOnClickListener(this);
        portSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                AddMeterData.set(1, i + 1 + "");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        rateSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                AddMeterData.set(2, i + "");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        protocolSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        AddMeterData.set(4, "32");
                        break;
                    case 1:
                        AddMeterData.set(4, "02");
                        break;
                    default:
                        AddMeterData.set(4, "32");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.terminal_add_meter_iv:
                finish();
                break;
            case R.id.add_meter_btn:
                pulladdMeterData();
                break;
        }
    }

    private void pulladdMeterData() {
        AddMeterData.set(0, id + "");
        String address = addressEt.getText().toString();
        if ("0".equals(address)) {
            showToast(getResources().getString(R.string.MeterAddress));
        } else {
            AddMeterData.set(3, address);
            mControl.addMeter(AddMeterData);
        }
    }
}

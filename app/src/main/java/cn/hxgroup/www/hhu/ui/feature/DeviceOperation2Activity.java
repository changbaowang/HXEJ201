package cn.hxgroup.www.hhu.ui.feature;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.ui.base.BaseActivity;

public class DeviceOperation2Activity extends BaseActivity implements View.OnClickListener {
    private TextView titleTv, nameTv, rlueTv, name1Tv, rlue1Tv;
    private EditText valueEt, value1Et;
    private Button comfirmBtn, cancelBtn;
    private ImageView backIv;
    private int id;

    @Override
    protected int getResourceId() {
        return R.layout.activity_device_operation2;
    }

    @Override
    protected void initView() {
        titleTv = (TextView) findViewById(R.id.device_operation2_title_tv);
        nameTv = (TextView) findViewById(R.id.device_operation2_name_tv);
        valueEt = (EditText) findViewById(R.id.device_operation2_value_et);
        name1Tv = (TextView) findViewById(R.id.device_operation2_name1_tv);
        value1Et = (EditText) findViewById(R.id.device_operation2_value1_et);
        rlueTv = (TextView) findViewById(R.id.device_operation2_rule_tv);
        rlue1Tv = (TextView) findViewById(R.id.device_operation2_rule1_tv);
        comfirmBtn = (Button) findViewById(R.id.device_operation2_comfirm_btn);
        cancelBtn = (Button) findViewById(R.id.device_operation2_cancel_btn);
        backIv = (ImageView) findViewById(R.id.DeviceOperation2_backiv);
    }

    private void setupData2() {
        titleTv.setText(R.string.kRemoteSwitchOn);
        name1Tv.setText(R.string.restrictDelayTime);
        rlueTv.setText(R.string.OperationRlue3);
        valueEt.setVisibility(View.INVISIBLE);
    }

    private void setupData1() {
        titleTv.setText(R.string.kRemoteSwitchOff);
        nameTv.setText(R.string.restrictEleTime);
        name1Tv.setText(R.string.restrictDelayTime);
        rlueTv.setText(R.string.OperationRlue1);
        rlue1Tv.setText(R.string.OperationRlue2);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        switch (id) {
            case 1:
                setupData1();
                break;
            case 2:
                setupData2();
                break;
        }
    }

    @Override
    protected void initListener() {
        comfirmBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
        backIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.device_operation2_comfirm_btn:
                getData();
                break;
            case R.id.device_operation2_cancel_btn:
                finish();
                break;
            case R.id.DeviceOperation2_backiv:
                finish();
                break;
        }
    }

    private void getData() {
        Intent intent = new Intent(DeviceOperation2Activity.this, DeviceOperationActivity.class);
        switch (id) {
            case 1:
                if ("".equals(value1Et.getText().toString()) || "".equals(valueEt.getText().toString())) {
                    Toast.makeText(this, getResources().getString(R.string.PleaseEnterData), Toast.LENGTH_SHORT).show();
                } else {
                    String delayTime = Integer.toHexString(Integer.parseInt(value1Et.getText().toString()));
                    String restrictEleTime = Integer.toHexString(Integer.parseInt(valueEt.getText().toString()));
                    if (Integer.parseInt(value1Et.getText().toString()) < 16 && Integer.parseInt(valueEt.getText().toString()) < 16) {
                        intent.putExtra("delayTime", delayTime);
                        intent.putExtra("restrictEleTime", restrictEleTime);
                        setResult(id, intent);
                        finish();
                    } else {
                        Toast.makeText(this,getResources().getString(R.string.DataShouldBeLessThan16), Toast.LENGTH_SHORT).show();
                    }

                }
                break;
            case 2:
                if ("".equals(value1Et.getText().toString())) {
                    Toast.makeText(this, getResources().getString(R.string.PleaseEnterData), Toast.LENGTH_SHORT).show();
                } else {
                    String delayTime = Integer.toHexString(Integer.parseInt(value1Et.getText().toString()));
                    intent.putExtra("delayTime", delayTime);
                    setResult(id, intent);
                    finish();
                }
                break;
        }
    }
}

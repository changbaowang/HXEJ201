package cn.hxgroup.www.hhu.ui.feature;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.adapter.CommonLVadapter;
import cn.hxgroup.www.hhu.control.MeterInfoControl;
import cn.hxgroup.www.hhu.ui.base.BaseActivity;

/**
 * Created by HEX170 on 2016/8/2.
 * 表计详细信息界面
 */
public class MeterInfoActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv;
    private ImageView backIv;
    private Button realDataBtn, powerBtn;
    private Button delBtn;
    private ArrayList<String> data = new ArrayList<>();
    private ArrayList<String> strdata = new ArrayList<>();
    private CommonLVadapter adapter;
    private ListView mListview;
    private MeterInfoControl mControl;
    private int flag = 0;//是否有删除表操作的标志  1  表示删除,0表示没有删除操作


    @Override
    protected int getResourceId() {
        return R.layout.activity_meter_info;
    }

    @Override
    protected void initView() {
        mListview = (ListView) findViewById(R.id.meter_info_lv);
        backIv = (ImageView) findViewById(R.id.point_meterinfo_back_iv);
        realDataBtn = (Button) findViewById(R.id.meterinfo_realdata_btn);
        powerBtn = (Button) findViewById(R.id.meterinfo_power_btn);
        delBtn = (Button) findViewById(R.id.meterinfo_del_meter_btn);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mControl = new MeterInfoControl(this);
        data = getIntent().getStringArrayListExtra("data");
        String[] stringArray = getResources().getStringArray(R.array.kPointBitmapListview);
        for (int j = 0; j < stringArray.length; j++) {
            strdata.add(stringArray[j]);
        }
        adapter = new CommonLVadapter(strdata, data, this);
        mListview.setAdapter(adapter);
    }

    @Override
    protected void initListener() {
        backIv.setOnClickListener(this);
        realDataBtn.setOnClickListener(this);
        powerBtn.setOnClickListener(this);
        delBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.point_meterinfo_back_iv:
                finish();
                break;
            case R.id.meterinfo_del_meter_btn:
                mControl.dialog(data.get(1), data.get(3));
                break;
            case R.id.meterinfo_realdata_btn:
//                intent.putExtra("pn", data.get(1));
//                intent.putExtra("address", data.get(5));
//                intent.setClass(MeterInfoActivity.this, RealDataActivity.class);
//                startActivity(intent);
                intent.putExtra("pn", data.get(1));
                intent.putExtra("address", data.get(5));
                intent.setClass(MeterInfoActivity.this, PowerActivity.class);
                startActivity(intent);
                break;
            case R.id.meterinfo_power_btn:
                intent.putExtra("pn", data.get(1));
                intent.putExtra("address", data.get(5));
                intent.setClass(MeterInfoActivity.this, ActivePowerValueActivity.class);
                startActivity(intent);
                break;
        }
    }
}

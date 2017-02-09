package cn.hxgroup.www.hhu.ui.feature;

import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.control.GpsSetControl;
import cn.hxgroup.www.hhu.tools.DialogUtils;
import cn.hxgroup.www.hhu.tools.GPSTools;
import cn.hxgroup.www.hhu.ui.base.BaseActivity;

/**
 * Created by HEX170 on 2016/8/2.
 * GPS界面
 */
public class GpsSetActivity extends BaseActivity implements View.OnClickListener {
    private ImageView mBackiv;
    private TextView mLongitudeEt, mLatitudeEt;
    private GpsSetControl mControl;
    private Button mSetBtn;
    private Button mGetBtn;
    private Button mLocationBtn;
    private DialogUtils mDialog;
    //gps请求成功标志
    private boolean flag = false;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (!flag) {
                mDialog.close();
                Toast.makeText(GpsSetActivity.this, getResources().getString(R.string.kLoadFail), Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected int getResourceId() {
        return R.layout.activity_gpsset;
    }

    @Override
    protected void initView() {
        mBackiv = (ImageView) findViewById(R.id.setting_gpsset_backiv);
        mLongitudeEt = (TextView) findViewById(R.id.gps_set_tv1);
        mLatitudeEt = (TextView) findViewById(R.id.gps_set_tv2);
        mSetBtn = (Button) findViewById(R.id.gps_set_setbtn);
        mGetBtn = (Button) findViewById(R.id.gps_get_terminal_gps_btn);
        mLocationBtn = (Button) findViewById(R.id.gps_set_new_gps);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mDialog = new DialogUtils(this);
        mControl = new GpsSetControl(this);
    }

    @Override
    protected void initListener() {
        mBackiv.setOnClickListener(this);
        mSetBtn.setOnClickListener(this);
        mGetBtn.setOnClickListener(this);
        mLocationBtn.setOnClickListener(this);
    }

    //获取终端位置---显示
    public void upDate(List<String> data) {
        mLongitudeEt.setText(data.get(0));
        mLatitudeEt.setText(data.get(1));
    }

    //设置终端位置信息
    private void setGPS() {
        List<String> data = new ArrayList<>();
        String longitude = mLongitudeEt.getText().toString();
        String latitude = mLatitudeEt.getText().toString();
        data.add(longitude);
        data.add(latitude);
        mControl.setData(data);
    }

    //配合GpsTools类实现时时更新位置信息
    public void getData(Location location) {
        mDialog.close();
        flag = true;
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
        mLongitudeEt.setText(longitude + "");
        mLatitudeEt.setText(latitude + "");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setting_gpsset_backiv:
                finish();
                break;
            case R.id.gps_set_setbtn:
                setGPS();
                break;
            case R.id.gps_get_terminal_gps_btn:
                mControl.requestData();
                break;
            case R.id.gps_set_new_gps:
                locationGPS();
                break;
        }
    }

    //获取位置信息
    private void locationGPS() {
        String title = getResources().getString(R.string.Meterhite);
        String content = getResources().getString(R.string.MeterAddData);
        mDialog.show(title, content);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                    mHandler.sendEmptyMessage(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        GPSTools tools = new GPSTools(this);
    }
}


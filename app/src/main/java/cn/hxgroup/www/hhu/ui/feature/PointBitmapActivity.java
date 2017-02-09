package cn.hxgroup.www.hhu.ui.feature;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.adapter.MeterInfoAdapter;
import cn.hxgroup.www.hhu.adapter.SpinnerAdapter;
import cn.hxgroup.www.hhu.constant.DefaultConstant;
import cn.hxgroup.www.hhu.control.PointBitmapControl;
import cn.hxgroup.www.hhu.ui.base.BaseActivity;
import cn.hxgroup.www.hhu.ui.bean.MeterInfo;
import hexing.protocol.GWHelp.Analysis;

/**
 * Created by HEX170 on 2016/8/2.
 * 表计管理界面
 */
public class PointBitmapActivity extends BaseActivity implements View.OnClickListener {
    private ImageView mBackIv;
    private Button mAddBtn;
    private Button mMeterStateBtn;
    private Spinner mSpinner;
    private Button mUpdateBtn;
    public PullToRefreshListView mListView;
    public TextView totalTv;
    private List<MeterInfo> data = new ArrayList<>();
    private List<MeterInfo> tempdata = new ArrayList<>();
    private PointBitmapControl mControl;
    public MeterInfoAdapter adapter;
    public List<MeterInfo> rs485data = new ArrayList<>();
    public List<MeterInfo> plcdata = new ArrayList<>();
    public Map<Integer, List<String>> mapData = new HashMap<>();

    @Override
    protected int getResourceId() {
        return R.layout.activity_point_bitmap;
    }

    @Override
    protected void initView() {
        mBackIv = (ImageView) findViewById(R.id.point_bitmap_back_iv);
        mUpdateBtn = (Button) findViewById(R.id.point_bitmap_updata_btn);
        mAddBtn = (Button) findViewById(R.id.point_bitmap_add_btn);
        mMeterStateBtn = (Button) findViewById(R.id.point_bitmap_meter_state_btn);
        mSpinner = (Spinner) findViewById(R.id.point_bitmap_choice_sp);
        mListView = (PullToRefreshListView) findViewById(R.id.point_bitmap_listview);
        totalTv = (TextView) findViewById(R.id.point_bitmap_total_tv);
    }

    //处理spinner
    private SpinnerAdapter spAdapter;
    private List<String> spData = new ArrayList<>();

    @Override
    protected void initData(Bundle savedInstanceState) {
        mListView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        mControl = new PointBitmapControl(this);
        adapter = new MeterInfoAdapter(this, data);
        spData.add("ALL");
        spData.add("RS485");
        spData.add("RF/PLC");
        spAdapter = new SpinnerAdapter(spData, this);
        mSpinner.setAdapter(spAdapter);
        mListView.setAdapter(adapter);
    }

    private boolean addDataFlag = true;//是否刷新数据标志位

    @Override
    protected void onRestart() {
        super.onRestart();
        if (!addDataFlag) {
            mControl.requestData();
        }
        addDataFlag = true;
    }

    private ArrayList<Integer> numData = new ArrayList<>();

    //得到已用的测量点号
    public void getNumData(List<String> list) {
        String numTemp1 = list.get(0);
        if (4 == (numTemp1.length() % 4)) {
            String numTemp2 = Analysis.resverstr(numTemp1.substring(4, numTemp1.length()));
            for (int i = 0; i < (numTemp2.length() % 4); i++) {
                numData.add(Integer.parseInt(numTemp2.substring(4 * i, 4 * i + 4)));
            }
        }
    }

    //数据改变更新界面
    public void update2(List<MeterInfo> realdata) {
        if (realdata != null) {
            data.clear();
            data.addAll(realdata);
            tempdata.clear();
            tempdata.addAll(realdata);
            mSpinner.setSelection(0);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void initListener() {
        mBackIv.setOnClickListener(this);
        mUpdateBtn.setOnClickListener(this);
        mAddBtn.setOnClickListener(this);
        mMeterStateBtn.setOnClickListener(this);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        data.clear();
                        totalTv.setText("TOTAL:" + mControl.total);
                        data.addAll(tempdata);
                        adapter.notifyDataSetChanged();
                        break;
                    case 1:
                        data.clear();
                        totalTv.setText("TOTAL:" + rs485data.size());
                        data.addAll(rs485data);
                        adapter.notifyDataSetChanged();
                        break;
                    case 2:
                        data.clear();
                        totalTv.setText("TOTAL:" + plcdata.size());
                        data.addAll(plcdata);
                        adapter.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                ArrayList<String> data = new ArrayList<String>();
                data.addAll(mapData.get(i - 1));
                addDataFlag = true;
                intent.putStringArrayListExtra("data", data);
                intent.setClass(PointBitmapActivity.this, MeterInfoActivity.class);
                startActivityForResult(intent, 0);
            }
        });
        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                showToast(mControl.page);
                mControl.handler.sendEmptyMessageDelayed(4, DefaultConstant.DEFAULT_TIMEOUT * 1000);
                if (mControl.page == 0) {
                    mControl.text(1);
                } else {
                    mControl.text(mControl.page);
                }
            }
        });
    }

    //根据返回码确定是否刷新界面
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (1 == resultCode) {
            addDataFlag = false;
        }
    }

    int page = 1;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.point_bitmap_back_iv:
                finish();
                break;
            case R.id.point_bitmap_updata_btn:
                data.clear();
                rs485data.clear();
                plcdata.clear();
                mControl.meterData1.clear();
                mControl.page = 0;
                totalTv.setText("");
                mControl.requestData();
                break;
            case R.id.point_bitmap_add_btn:
                addDataFlag = false;
                Intent intent = new Intent(PointBitmapActivity.this, Meter2Activity.class);
                startActivity(intent);
                break;
            case R.id.point_bitmap_meter_state_btn:
                startActivity(new Intent(PointBitmapActivity.this, TerminalStateInfoActivity.class));
                break;
        }
    }
}

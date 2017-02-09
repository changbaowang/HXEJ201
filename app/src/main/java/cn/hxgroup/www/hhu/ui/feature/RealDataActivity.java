package cn.hxgroup.www.hhu.ui.feature;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.adapter.RealDataLVadapter;
import cn.hxgroup.www.hhu.control.RealDataControl;
import cn.hxgroup.www.hhu.control.model.UIElectricInfo;
import cn.hxgroup.www.hhu.tools.SharedPreferencesUtils;
import cn.hxgroup.www.hhu.ui.base.BaseActivity;
import cn.hxgroup.www.hhu.ui.component.RealDataView;

/**
 * Created by CXJ on 2016/5/22.
 * 实时数据显示界面
 */
public class RealDataActivity extends BaseActivity implements View.OnClickListener {

    private RealDataView mRealDataView;
    private View mBackBtn;
    private RealDataControl mControl;
    private View mDivisionLayout;
    private ListView listView;
    private Button saveBtn;

    @Override
    protected int getResourceId() {
        return R.layout.activity_realdata;
    }

    @Override
    protected void initView() {
        mBackBtn = findViewById(R.id.backBtn);
        mRealDataView = (RealDataView) findViewById(R.id.realDataView);
        mDivisionLayout = findViewById(R.id.divisionLayout);
        saveBtn = (Button) findViewById(R.id.realdata_savedata_btn);
        listView = (ListView) findViewById(R.id.realdata_listview);
    }

    /**
     * pn注解:DefaultConstant.PN    表示读取终端的数据
     * getIntent().getStringExtra("pn") 表示读取对应测量点的数据
     */
    public List<String> data = new ArrayList<>();
    public RealDataLVadapter adapter;
    public String pn = "";
    public String address = "";

    @Override
    protected void initData(Bundle saveedInstanceState) {
        mDivisionLayout.setFocusable(true);
        pn = SharedPreferencesUtils.getStringState(this, "pn");
        mDivisionLayout.setFocusableInTouchMode(true);
        mDivisionLayout.requestFocus();
        String tempPn = getIntent().getStringExtra("pn");
        address = getIntent().getStringExtra("address");
        if (tempPn == null) {
        } else {
            pn = tempPn;
        }
        mControl = new RealDataControl(this);
        WindowManager m = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display d = m.getDefaultDisplay();
        Point point = new Point();
        d.getSize(point);
        int minWidth = Math.min(point.x, point.y);
        minWidth = (int) (minWidth * 0.8);
        mDivisionLayout.getLayoutParams().width = minWidth;
        mDivisionLayout.getLayoutParams().height = minWidth;
        Resources res = getResources();
        String[] stringArray = res.getStringArray(R.array.realdata_listview);
        adapter = new RealDataLVadapter(stringArray, data, this);
        listView.setAdapter(adapter);
    }

    @Override
    protected void initListener() {
        mBackBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mControl.destroy();
        mControl.database.close();
        mControl = null;
    }

    @Override
    protected void onPause() {
        mControl.destroy();
        super.onPause();
    }

    /**
     * 更新相位角信息
     *
     * @param realdata
     */
    public void update(List<Float> realdata) {
        mRealDataView.update(realdata);
    }

    public void update(UIElectricInfo info) {
//        mElectricViewModule.update(info);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backBtn:
                finish();
                break;
            case R.id.realdata_savedata_btn:
                mControl.saveData();
                break;
        }
    }
}

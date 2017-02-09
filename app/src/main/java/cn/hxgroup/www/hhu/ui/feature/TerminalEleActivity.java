package cn.hxgroup.www.hhu.ui.feature;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.adapter.RealDataLVadapter;
import cn.hxgroup.www.hhu.control.model.UIElectricInfo;
import cn.hxgroup.www.hhu.ui.base.BaseActivity;

public class TerminalEleActivity extends BaseActivity implements View.OnClickListener {

    private View mBackBtn;
    private ListView listView;
    public List<String> data = new ArrayList<>();
    public RealDataLVadapter adapter;


    @Override
    protected int getResourceId() {
        return R.layout.activity_terminal_ele;
    }


    @Override
    protected void initView() {
        mBackBtn = findViewById(R.id.backBtn);
        listView = (ListView) findViewById(R.id.realdata_listview);
    }

    @Override
    protected void initData(Bundle saveedInstanceState) {
//        mControl = new RealDataControl(this);
        Resources res = getResources();
        String[] stringArray = res.getStringArray(R.array.realdata_listview);
        adapter = new RealDataLVadapter(stringArray, data, this);
        listView.setAdapter(adapter);
    }

    @Override
    protected void initListener() {
        mBackBtn.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 更新相位角信息
     *
     * @param realdata
     */
    public void update(List<Float> realdata) {
//        mRealDataView.update(realdata);
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
        }
    }
}

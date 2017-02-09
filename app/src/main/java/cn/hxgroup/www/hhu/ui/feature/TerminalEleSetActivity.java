package cn.hxgroup.www.hhu.ui.feature;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.adapter.AddMeterAdapter;
import cn.hxgroup.www.hhu.control.TerminalEleSetControl;
import cn.hxgroup.www.hhu.ui.base.BaseActivity;

public class TerminalEleSetActivity extends BaseActivity {
    private ImageView mBackIv;
    private ListView mListView;
    private Button mSetBtn;
    private List<String> mData = new ArrayList<>();
    private List<String> mParaData = new ArrayList<>();
    private AddMeterAdapter mAdapter;
    private TerminalEleSetControl mControl;

    @Override
    protected int getResourceId() {
        return R.layout.activity_terminal_ele_set;
    }

    @Override
    protected void initView() {
        mBackIv = (ImageView) findViewById(R.id.action_bar_back_iv);
        mListView = (ListView) findViewById(R.id.terminal_ele_lv);
        mSetBtn = (Button) findViewById(R.id.terminal_ele_set_btn);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mControl = new TerminalEleSetControl(this);
        Resources res = getResources();
        String[] stringArray = res.getStringArray(R.array.terminal_ele_set);
        mParaData.clear();
        for (int i = 0; i < stringArray.length; i++) {
            mParaData.add(stringArray[i]);
        }
        mAdapter = new AddMeterAdapter(this, mParaData, mData);
        mListView.setAdapter(mAdapter);
        mControl.requestData();
    }

    public void getData(List<String> data) {
        this.mData.clear();
        this.mData.addAll(data);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initListener() {
        mBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mSetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> data = mAdapter.getData();
                mControl.setTerminalData(data);
            }
        });
    }
}

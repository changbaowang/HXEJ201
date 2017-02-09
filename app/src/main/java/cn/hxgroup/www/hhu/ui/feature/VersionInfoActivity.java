package cn.hxgroup.www.hhu.ui.feature;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.adapter.CommonLVadapter;
import cn.hxgroup.www.hhu.control.VersionInfoControl;
import cn.hxgroup.www.hhu.ui.base.BaseActivity;

/**
 * Created by hex170 on 2016/8/31.
 * 终端版本信息
 */
public class VersionInfoActivity extends BaseActivity {
    private ImageView mBackIv;
    private ListView mListView;
    private CommonLVadapter mAdapter;
    private List<String> data = new ArrayList<>();
    private List<String> strData = new ArrayList<>();
    private VersionInfoControl mControl;

    @Override
    protected int getResourceId() {
        return R.layout.activity_version_info;
    }

    @Override
    protected void initView() {
        mBackIv = (ImageView) findViewById(R.id.terminal_version_info_back_iv);
        mListView = (ListView) findViewById(R.id.terminal_version_info_listview);
    }

    public void update(List<String> realdata) {
        data.clear();
        data.addAll(realdata);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mControl = new VersionInfoControl(this);
        Resources res = getResources();
        String[] stringArray = res.getStringArray(R.array.version_info_listview);
        strData.clear();
        for (int i = 0; i < stringArray.length; i++) {
            strData.add(stringArray[i]);
        }
        mAdapter = new CommonLVadapter(strData, data, this);
        mListView.setAdapter(mAdapter);

    }

    @Override
    protected void initListener() {
        mBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

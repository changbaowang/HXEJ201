package cn.hxgroup.www.hhu.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.business.AppConfig;
import cn.hxgroup.www.hhu.constant.TimeOut;
import cn.hxgroup.www.hhu.ui.base.BaseActivity;
import cn.hxgroup.www.hhu.ui.settings.TimeOutAdapter;

/**
 * Created by chenxiaojun1 on 2016/6/8.
 */
public class TimeOutSelectActivity extends BaseActivity
{
    private ListView mListView;
    private TimeOutAdapter mAdapter;

    @Override
    protected int getResourceId()
    {
        return R.layout.activity_timeout_select;
    }

    @Override
    protected void initView()
    {
        mListView = (ListView) findViewById(R.id.listview);
    }

    @Override
    protected void initData(Bundle saveedInstanceState)
    {
        int itemHeight = (int) getResources().getDimension(R.dimen.feature_item_height);
        mListView.getLayoutParams().height = 5 * itemHeight;
        mAdapter = new TimeOutAdapter(this, AppConfig.getInstance().getTimeOut());
        mListView.setAdapter(mAdapter);
    }

    @Override
    protected void initListener()
    {
        mListView.setOnItemClickListener(initOnItemClickListener());
    }

    private AdapterView.OnItemClickListener initOnItemClickListener()
    {
        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                TimeOut timeOut = mAdapter.getItem(position);
                int value = timeOut.getValue();
                AppConfig.getInstance().saveTimeOut(value);
                finish();
            }
        };
        return listener;
    }
}

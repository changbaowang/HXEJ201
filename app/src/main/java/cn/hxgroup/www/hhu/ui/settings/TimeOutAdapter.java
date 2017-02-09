package cn.hxgroup.www.hhu.ui.settings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.constant.TimeOut;
import cn.hxgroup.www.hhu.utils.CommonUtils;

/**
 * Created by chenxiaojun1 on 2016/6/8.
 */
public class TimeOutAdapter extends BaseAdapter
{

    private List<TimeOut> mDataList;
    private LayoutInflater mInflater;
    private Context mContext;
    private int mSelectValue;

    public TimeOutAdapter(Context context, int selectValue)
    {
        mContext = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mSelectValue = selectValue;
        initData();
    }

    private void initData()
    {
        mDataList = new ArrayList<>();
        mDataList.add(TimeOut.TIME_OUT_1);
        mDataList.add(TimeOut.TIME_OUT_2);
        mDataList.add(TimeOut.TIME_OUT_3);
        mDataList.add(TimeOut.TIME_OUT_4);
        mDataList.add(TimeOut.TIME_OUT_5);
    }

    @Override
    public int getCount()
    {
        return CommonUtils.isEmpty(mDataList) ? 0 : mDataList.size() ;
    }

    @Override
    public TimeOut getItem(int position)
    {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view = mInflater.inflate(R.layout.item_timeout_select, null);
        TextView tv = (TextView) view.findViewById(R.id.timeTv);
        ImageView iv = (ImageView) view.findViewById(R.id.timeIv);
        TimeOut timeOut = getItem(position);
        tv.setText("" + timeOut.getValue() + mContext.getResources().getString(R.string.kSecond));
        iv.setSelected(mSelectValue == timeOut.getValue());
        tv.setSelected(mSelectValue == timeOut.getValue());
        return view;
    }
}

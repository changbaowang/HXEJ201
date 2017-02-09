package cn.hxgroup.www.hhu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.hxgroup.www.hhu.R;

/**
 * Created by hex170 on 2016/9/22.
 * 引导页面适配器
 */
public class InstructionsAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> data;

    public InstructionsAdapter(Context mContext, List<String> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int i) {
        return data == null ? null : data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new MyViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.instruction_listview_item_view, null);
            viewHolder.textView = (TextView) view.findViewById(R.id.instructions_listview_item_tv);
            view.setTag(viewHolder);
        } else {
            viewHolder = (MyViewHolder) view.getTag();
        }
        viewHolder.textView.setText(data.get(i));
        return view;
    }

    private class MyViewHolder {
        private TextView textView;
    }
}

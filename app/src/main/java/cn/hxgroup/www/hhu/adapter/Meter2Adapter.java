package cn.hxgroup.www.hhu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.hxgroup.www.hhu.R;

/**
 * Created by hex170 on 2016/8/26.
 * 显示剩余测量点号的适配器
 */
public class Meter2Adapter extends BaseAdapter {
    private List<Integer> data = new ArrayList<>();
    private Context context;

    public Meter2Adapter(List<Integer> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return data != null ? data.get(i) : null;
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
            view = LayoutInflater.from(context).inflate(R.layout.meter2_gv_item_view, null);
            viewHolder.checkBox = (TextView) view.findViewById(R.id.gv_item_tv);
            view.setTag(viewHolder);
        } else {
            viewHolder = (MyViewHolder) view.getTag();
        }
        viewHolder.checkBox.setText(data.get(i) + "");
        return view;
    }

    class MyViewHolder {
        private TextView checkBox;
    }
}

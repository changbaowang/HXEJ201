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
 * Created by hex170 on 2016/8/3.
 * 公用适配器
 */
public class CommonLVadapter extends BaseAdapter {
    private List<String> strData = new ArrayList<>();
    private List<String> data = new ArrayList<>();
    private Context context;

    public CommonLVadapter(List<String> stringArray, List<String> data, Context context) {
        this.strData = stringArray;
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return strData != null ? strData.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return strData != null ? strData.get(i) : null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.realdata_listview_item_view, null);
            viewHolder = new MyViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (MyViewHolder) view.getTag();
        }
        viewHolder.nametv.setText(strData.get(i));
        if (data.size() > 0) {
            viewHolder.valuetv.setText(data.get(i));
        }
        return view;
    }

    class MyViewHolder {
        private TextView nametv;
        private TextView valuetv;

        public MyViewHolder(View view) {
            nametv = (TextView) view.findViewById(R.id.realdata_listview_item_name_tv);
            valuetv = (TextView) view.findViewById(R.id.realdata_listview_item_value_tv);
        }
    }
}

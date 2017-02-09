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
 * Created by hex170 on 2016/8/10.
 * Spinner 通用适配器
 */
public class SpinnerAdapter extends BaseAdapter {
    List<String> data;
    private Context context;

    public SpinnerAdapter(List<String> data, Context context) {
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
            view = LayoutInflater.from(context).inflate(R.layout.pinner_view, null);
            viewHolder.textView = (TextView) view.findViewById(R.id.spinner_item_tv);
            view.setTag(viewHolder);
        } else {
            viewHolder = (MyViewHolder) view.getTag();
        }
        viewHolder.textView.setText(data.get(i));
        return view;
    }

    class MyViewHolder {
        private TextView textView;
    }
}

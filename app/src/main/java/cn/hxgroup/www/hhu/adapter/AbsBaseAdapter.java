package cn.hxgroup.www.hhu.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by hex170 on 2016/8/3.
 */
abstract class AbsBaseAdapter<T> extends BaseAdapter {
    private List<T> data;

    public AbsBaseAdapter(List<T> getdata) {
        this.data = getdata;
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
    public abstract View getView(int i, View view, ViewGroup viewGroup);

    abstract class MyViewHolder {
    }
}

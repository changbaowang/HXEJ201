package cn.hxgroup.www.hhu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.ui.bean.MeterInfo;

/**
 * Created by hex170 on 2016/8/25.
 * 电表的详细信息适配器
 */
public class MeterInfoAdapter extends BaseAdapter {
    private Context context;
    private List<MeterInfo> data;

    public MeterInfoAdapter(Context context, List<MeterInfo> data) {
        this.context = context;
        this.data = data;
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
            view = LayoutInflater.from(context).inflate(R.layout.meterinfo_listview_item_view, null);
            viewHolder.idTv = (TextView) view.findViewById(R.id.meterinfo_item_id_tv);
            viewHolder.pnTv = (TextView) view.findViewById(R.id.meterinfo_item_pn_tv);
            viewHolder.typeTv = (TextView) view.findViewById(R.id.meterinfo_item_type_tv);
            view.setTag(viewHolder);
        } else {
            viewHolder = (MyViewHolder) view.getTag();
        }
        if (data.size() > i) {
            viewHolder.idTv.setText(data.get(i).getMeterId());
            viewHolder.pnTv.setText(data.get(i).getMeterPn());
            viewHolder.typeTv.setText(data.get(i).getMeterType());
        }
        return view;
    }

    class MyViewHolder {
        private TextView idTv;
        private TextView pnTv;
        private TextView typeTv;
    }
}

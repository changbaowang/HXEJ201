package cn.hxgroup.www.hhu.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.hxgroup.www.hhu.R;

/**
 * Created by hex170 on 2016/8/25.
 * 添加表计的适配器
 */
public class AddMeterAdapter extends BaseAdapter {
    private Context context;
    private List<String> data;
    private List<String> paramdata = new ArrayList<String>();

    public AddMeterAdapter(Context context, List<String> data, List<String> paramdata) {
        this.context = context;
        this.paramdata = paramdata;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.addmeter_lv_item, null);
            viewHolder = new MyViewHolder();
            viewHolder.nametv = (TextView) view.findViewById(R.id.addmeter_listview_nametv);
            viewHolder.paramet = (EditText) view.findViewById(R.id.addmeter_listview_paramtv);
            viewHolder.paramet.setTag(i);
            viewHolder.paramet.addTextChangedListener(new MyTextChangeListener(viewHolder));
            view.setTag(viewHolder);
        } else {
            viewHolder = (MyViewHolder) view.getTag();
            viewHolder.paramet.setTag(i);
        }
        viewHolder.nametv.setText(data.get(i));
        if (paramdata.size() != 0 && paramdata.size() != i) {
            viewHolder.paramet.setText(paramdata.get(i));
        }
        return view;
    }

    public class MyViewHolder {
        private TextView nametv;
        private EditText paramet;
    }

    private class MyTextChangeListener implements TextWatcher {
        private MyViewHolder holder;

        public MyTextChangeListener(MyViewHolder holder) {
            this.holder = holder;
        }

        @Override
        public void afterTextChanged(Editable s) {
            int position = (Integer) holder.paramet.getTag();
            if (!"".equals(s.toString())) {
                paramdata.set(position, s.toString());
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }
    }

    public List<String> getData() {
        return paramdata;
    }
}

package cn.hxgroup.www.hhu.ui.feature;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.utils.CommonUtils;

/**
 * Created by CXJ on 2016/6/11.
 */
public class FileAdapter extends BaseAdapter
{
    private List<File> mDataList;
    private LayoutInflater mInflater;
    private SimpleDateFormat mSimpleDateFormat;

    public FileAdapter(Context context, List<File> data)
    {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mDataList = data;
        mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    }

    @Override
    public int getCount()
    {
        return CommonUtils.isEmpty(mDataList) ? 0 : mDataList.size();
    }

    @Override
    public File getItem(int position)
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
        ViewHolder holder = null;
        if(convertView == null)
        {
            convertView = mInflater.inflate(R.layout.item_file_list, null);
            holder = new ViewHolder();
            convertView.setTag(holder);
            holder.tv_name = (TextView) convertView.findViewById(R.id.item_file_name_tv);
            holder.tv_date = (TextView) convertView.findViewById(R.id.item_file_date_tv);
            holder.tv_size = (TextView) convertView.findViewById(R.id.item_file_size_tv);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        File file = getItem(position);
        holder.tv_name.setText(file.getName());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(file.lastModified());
        holder.tv_date.setText(mSimpleDateFormat.format(calendar.getTime()));

        return convertView;
    }

    class ViewHolder
    {
        TextView tv_name;
        TextView tv_date;
        TextView tv_size;

    }
}

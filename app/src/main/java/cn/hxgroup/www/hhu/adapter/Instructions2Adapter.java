package cn.hxgroup.www.hhu.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import cn.hxgroup.www.hhu.R;

/**
 * Created by hex170 on 2016/9/14.
 * app功能介绍通用适配器
 */
public class Instructions2Adapter extends PagerAdapter {
    LinkedList<View> mCaches = new LinkedList<View>();
    private List<Integer> list;
    private List<String> data;
    private Context mContext;

    public Instructions2Adapter(Context mContext, List<String> data, List<Integer> list) {
        this.list = list;
        this.data = data;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup view, int position, Object object) {
        if (mCaches.size() > 0) {
            mCaches.clear();
        }
        view.removeView((View) object);
//        mCaches.add((View) object);
        mCaches.add((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View convertView = null;
        ViewHolder mHolder = null;
        if (mCaches.size() < 4) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.view_group_item, null);
            mHolder = new ViewHolder();
            mHolder.imageView = (ImageView) convertView.findViewById(R.id.view_group_iv);
            mHolder.textView = (TextView) convertView.findViewById(R.id.view_group_tv);
            convertView.setTag(mHolder);
        } else {
//            convertView = (View) mCaches.removerFirst();
            convertView = (View) mCaches.remove();
        }
        mHolder.imageView.setImageResource(list.get(position));
//        mHolder.textView.setText(data.get(position));
        ((ViewPager) container).addView(convertView, 0);
        return convertView;
    }

    private class ViewHolder {
        ImageView imageView;
        TextView textView;
    }
}


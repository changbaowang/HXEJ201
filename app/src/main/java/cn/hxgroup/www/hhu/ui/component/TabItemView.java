package cn.hxgroup.www.hhu.ui.component;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by CXJ on 2016/5/22.
 */
public class TabItemView extends LinearLayout
{

    private ImageView mImageView;
    private TextView mTextView;

    public TabItemView(Context context)
    {
        super(context);
    }

    public TabItemView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public TabItemView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context)
    {
        mImageView = new ImageView(context);
        mTextView = new TextView(context);
    }


    @Override
    public void setSelected(boolean selected)
    {
        super.setSelected(selected);
        if(mImageView != null)
        {
            mImageView.setSelected(selected);
        }

        if(mTextView != null)
        {
            mTextView.setSelected(selected);
        }
    }
}

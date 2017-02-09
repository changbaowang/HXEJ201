package cn.hxgroup.www.hhu.ui.base;

import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by CXJ on 2016/5/22.
 */
public class BaseFragment extends Fragment
{
    protected View mContentView;

    protected View findViewById(int resourceId)
    {
        return mContentView.findViewById(resourceId);
    }
}

package cn.hxgroup.www.hhu.ui.base;

import android.view.View;

/**
 * Created by CXJ on 2016/6/11.
 */
public abstract class BaseViewModule
{
    private View mRootView;

    public BaseViewModule(View rootView)
    {
        mRootView = rootView;
    }

    public void init()
    {
        initView();
    }

    protected abstract void initView();
    protected abstract void initData();
    protected abstract void initListener();

    protected View findViewById(int res)
    {
        if(mRootView == null)
        {
            return null;
        }
        return mRootView.findViewById(res);
    }
}

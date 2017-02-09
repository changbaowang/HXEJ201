package cn.hxgroup.www.hhu.ui.component;

import android.app.Dialog;
import android.content.Context;

/**
 * Created by CXJ on 2016/5/27.
 */
public class LoadingDialog extends Dialog
{

    public LoadingDialog(Context context)
    {
        super(context);
    }

    public LoadingDialog(Context context, int theme)
    {
        super(context, theme);
    }

    protected LoadingDialog(Context context, boolean cancelable, OnCancelListener cancelListener)
    {
        super(context, cancelable, cancelListener);
    }
}

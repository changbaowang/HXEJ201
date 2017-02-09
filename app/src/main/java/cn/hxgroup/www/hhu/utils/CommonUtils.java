package cn.hxgroup.www.hhu.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import java.io.File;
import java.util.Collection;

/**
 * Created by CXJ on 2016/5/13.
 * 公共工具类
 */
public class CommonUtils
{
    /**
     * 列表是否为空
     * @param list
     * @return
     */
    public static boolean isEmpty(Collection list)
    {
        if(list == null || list.size() == 0)
        {
            return true;
        }
        return false;
    }

    /**
     * 字符串是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str)
    {
        if(str == null || str.length() == 0)
        {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(File[] files)
    {
        if (files == null || files.length == 0)
        {
            return true;
        }
        return false;
    }

    public static void goActivityWithoutParam(Context context, Class activityClass)
    {
        Intent intent = new Intent();
        intent.setClass(context, activityClass);
        context.startActivity(intent);
    }

    public static void goActivityForResult(Activity activity, Class activityClass, int requestCode)
    {
        Intent intent = new Intent();
        intent.setClass(activity, activityClass);
        activity.startActivityForResult(intent, requestCode);
    }

    public static byte[] subArray(byte[] data, int startIndex, int len)
    {
        byte[] sub = new byte[len];
        try
        {
            System.arraycopy(data, startIndex, sub, 0, len);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return sub;
    }
}

package cn.hxgroup.www.hhu.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by hex170 on 2016/8/30.
 * SharedPreferences工具类
 */
public class SharedPreferencesUtils {

    public static void setStringState(Context context, String flag, String value) {
        SharedPreferences mySharedPreferences = context.getSharedPreferences("HHU", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString(flag, value);
        editor.commit();
    }

    public static String getStringState(Context context, String flag) {
        SharedPreferences mySharedPreferences = context.getSharedPreferences("HHU", Context.MODE_PRIVATE);
        return mySharedPreferences.getString(flag, "null");
    }

    public static void setBolleanState(Context context, String flag, boolean value) {
        SharedPreferences mySharedPreferences = context.getSharedPreferences("HHU", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putBoolean(flag, value);
        editor.commit();
    }

    public static boolean getBolleanState(Context context, String flag) {
        SharedPreferences mySharedPreferences = context.getSharedPreferences("HHU", Context.MODE_PRIVATE);
        return mySharedPreferences.getBoolean(flag, false);
    }

    public static void setIntState(Context context, String flag, int value) {
        SharedPreferences mySharedPreferences = context.getSharedPreferences("HHU", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putInt(flag, value);
        editor.commit();
    }

    public static Integer getIntState(Context context, String flag) {
        SharedPreferences mySharedPreferences = context.getSharedPreferences("HHU", Context.MODE_PRIVATE);
        return mySharedPreferences.getInt(flag, 0);
    }
}

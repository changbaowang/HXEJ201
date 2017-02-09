package cn.hxgroup.www.hhu.db;

import android.content.Context;

/**
 * Created by HEX170 on 2016/11/1.
 */
public class DBHelper {
    public static CommDB comDBHelper;

    public static void creatDB(Context context) {
        comDBHelper = new CommDB(context);
    }

    public static void open() {
        comDBHelper.open();
    }

    public static void close() {
        if (comDBHelper != null) {
            comDBHelper.close();
        }
    }
}

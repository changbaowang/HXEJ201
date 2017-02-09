package cn.hxgroup.www.hhu.tools;

import android.content.Context;
import android.telephony.TelephonyManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hex170 on 2016/8/30.
 * 读取SIM卡信息类
 */
public class SIMUtils {

    public static List<String> getSIMinfo(Context context) {
        List<String> data = new ArrayList<>();
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String imei = tm.getDeviceId();
        String ccid = tm.getSimSerialNumber();
        data.add(imei);
        data.add(ccid);
        return data;
    }
}

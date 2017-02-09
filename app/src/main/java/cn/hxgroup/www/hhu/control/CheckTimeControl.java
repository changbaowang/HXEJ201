package cn.hxgroup.www.hhu.control;

import android.os.Handler;
import android.os.Message;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.business.AppConfig;
import cn.hxgroup.www.hhu.control.base.BaseControl;
import cn.hxgroup.www.hhu.ui.feature.CheckTimeActivity;
import hexing.icomm.ICallbackres;
import hexing.icomm.IGetdataCallback;
import hexing.model.ConnPara;
import hexing.tools.GetTerminalInfoByParam;

/**
 * Created by chenxiaojun1 on 2016/6/4.
 * 对时
 */
public class CheckTimeControl extends BaseControl implements Observer {
    private CheckTimeActivity mActivity;
    public Timer mLocalTimer;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
//                requestData();
            }
        }
    };
    //    private boolean mIsWating;//是否在等待确认命令
//    Calendar mCalendar = Calendar.getInstance();
    SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private GetTerminalInfoByParam getTerminalInfoByParam;

    public CheckTimeControl(CheckTimeActivity activity) {
        this.mActivity = activity;
        getTerminalInfoByParam = new GetTerminalInfoByParam(mActivity);
        requestData();
        initLocalTime();
    }

    /**
     * 发送校时命令
     */
    public void checkTime(final String time) {
        Calendar calendar = Calendar.getInstance();
        Date date = null;
        try {
            date = mSimpleDateFormat.parse(time);
        } catch (ParseException e) {
            //提示用户输入的日期格式不正确
            mActivity.showToast(R.string.kCheckTimeInvalid);
            return;
        }
        calendar.setTime(date);
        List<String> timeData = new ArrayList<>();
        timeData.add(pullTimeData(time));
        boolean wifi = AppConfig.getInstance().getWifiState();
        if (wifi) {
            getTerminalInfoByParam.set("05", "31", timeData, new ICallbackres() {
                @Override
                public void succeed(String s,  ConnPara connPara) {
                    mActivity.showToast(R.string.setSucceed);
                    requestData();
                }
            });
        }
    }

    //解析时间格式数据
    private String pullTimeData(String data) {
        String temp = "";
        if (data.length() < 2) {
            return "";
        }
        for (int i = 2; i < data.length(); i++) {
            String substring = data.substring(i, i + 1);
            if ("-".equals(substring) || " ".equals(substring) || ":".equals(substring)) {

            } else {
                temp = temp + substring;
            }
        }
        temp = temp + "2";//2代表星期，但是终端有自动更新星期功能，可以不用管
        return temp;
    }

    private void initLocalTime() {
        mLocalTimer = new Timer();
        final Calendar calendar = Calendar.getInstance();
        mLocalTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                calendar.setTimeInMillis(System.currentTimeMillis());
                Date date = calendar.getTime();
                final String time = mSimpleDateFormat.format(date);
                mHandler.sendEmptyMessage(1);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mActivity.updateLocalTime(time);
                    }
                });
            }
        }, 300, 300);
    }

    /**
     * 获取设备时间
     */
    private void requestData() {
        boolean wifi = AppConfig.getInstance().getWifiState();
        if (wifi) {
            getTerminalInfoByParam.get("0C", "2", "", "", new IGetdataCallback() {
                @Override
                public void succeed(List<String> list) {
                    if (list != null && list.size() > 0) {
                        mActivity.updateDeviceTime(list.get(0));
                    }
                }
            });
        }
    }


    @Override
    public void destroy() {

        if (mLocalTimer != null) {
            mLocalTimer.cancel();
        }
    }

    @Override
    public void update(Observable observable, Object data) {

    }
}

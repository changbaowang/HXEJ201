package cn.hxgroup.www.hhu.control;

import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;
import java.util.List;

import cn.hxgroup.www.hhu.business.AppConfig;
import cn.hxgroup.www.hhu.control.base.BaseControl;
import cn.hxgroup.www.hhu.ui.feature.VersionManageActivity;
import hexing.icomm.IGetdataCallback;
import hexing.tools.GetTerminalInfoByParam;

/**
 * Created by chenxiaojun1 on 2016/6/4.
 */
public class VersionManageControl extends BaseControl {
    private VersionManageActivity mActivity;
    private GetTerminalInfoByParam getTerminalInfoByParam;
    private List<String> data = new ArrayList<>();
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                requestData2();
            }
        }
    };

    public VersionManageControl(VersionManageActivity activity) {
        mActivity = activity;
        requestData();
    }

    public void requestData() {
        boolean wifi = AppConfig.getInstance().getWifiState();
        if (wifi) {
            getTerminalInfoByParam = new GetTerminalInfoByParam(mActivity);
            getTerminalInfoByParam.get("09", "1", "", "", new IGetdataCallback() {
                @Override
                public void succeed(List<String> list) {
                    if (list != null && list.size() > 0) {
                        mActivity.setData(list);
                        mHandler.sendEmptyMessage(1);
                    }
                }
            });
        }
    }

    public void requestData2() {
        boolean wifi = AppConfig.getInstance().getWifiState();
        if (wifi) {
            getTerminalInfoByParam.get("C1", "21", "", "", new IGetdataCallback() {
                @Override
                public void succeed(List<String> list) {
                    if (list != null && list.size() > 0) {
                        String s = list.get(1);
                        mActivity.setData(s);
                    }
                }
            });
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}

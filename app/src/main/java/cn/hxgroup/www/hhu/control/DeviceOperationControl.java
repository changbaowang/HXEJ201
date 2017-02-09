package cn.hxgroup.www.hhu.control;

import java.util.ArrayList;
import java.util.List;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.business.AppConfig;
import cn.hxgroup.www.hhu.control.base.BaseControl;
import cn.hxgroup.www.hhu.ui.feature.DeviceOperationActivity;
import hexing.icomm.ICallbackres;
import hexing.icomm.IGetdataCallback;
import hexing.model.ConnPara;
import hexing.tools.GetTerminalInfoByParam;

/**
 * Created by chenxiaojun1 on 2016/6/4.
 * 继电器控制
 */
public class DeviceOperationControl extends BaseControl {

    private DeviceOperationActivity mActivity;
    private GetTerminalInfoByParam getTerminalInfoByParam;
    private List<String> data = new ArrayList<>();

    public DeviceOperationControl(DeviceOperationActivity activity) {
        mActivity = activity;
        getTerminalInfoByParam = new GetTerminalInfoByParam(mActivity);
        requestData();
    }

    //获取继电器当前状态
    private void requestData() {
        boolean wifi = AppConfig.getInstance().getWifiState();
        if (wifi) {
            getTerminalInfoByParam.get("0C", "246", "", "", new IGetdataCallback() {
                @Override
                public void succeed(List<String> list) {
                    if (list != null && list.size() > 0) {
                        mActivity.upData(list.get(0));
                    }
                }
            });
        }
    }

    //设置遥控合闸
    public void requestData(String delayTime) {
        boolean wifi = AppConfig.getInstance().getWifiState();
        if (wifi) {
            data.clear();
            data.add(delayTime + "0");
            getTerminalInfoByParam.set("05", "3", data, new ICallbackres() {
                @Override
                public void succeed(String s,  ConnPara connPara) {
                    mActivity.showToast(R.string.setSucceed);
                }
            });
        }
    }

    //设置遥控跳闸
    public void requestData(String restrictEleTime, String delayTime) {
        boolean wifi = AppConfig.getInstance().getWifiState();
        if (wifi) {
            data.clear();
            data.add(delayTime + restrictEleTime);
            getTerminalInfoByParam.set("05", "1", data, new ICallbackres() {
                @Override
                public void succeed(String s,  ConnPara connPara) {
                    mActivity.showToast(R.string.setSucceed);
                }
            });
        }
    }

    //设置允许合闸
    public void requestData1() {
        boolean wifi = AppConfig.getInstance().getWifiState();
        if (wifi) {
            data.clear();
            data.add("55");//补位数据
            getTerminalInfoByParam.set("05", "2", data, new ICallbackres() {
                @Override
                public void succeed(String s,  ConnPara connPara) {
                    mActivity.showToast(R.string.setSucceed);
                }
            });
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}

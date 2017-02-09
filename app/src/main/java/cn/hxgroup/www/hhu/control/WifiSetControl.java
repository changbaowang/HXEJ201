package cn.hxgroup.www.hhu.control;

import java.util.List;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.business.AppConfig;
import cn.hxgroup.www.hhu.control.base.BaseControl;
import cn.hxgroup.www.hhu.ui.feature.WIFIsetActivity;
import hexing.icomm.IGetdataCallback;
import hexing.tools.GetTerminalInfoByParam;

/**
 * Created by hex170 on 2016/8/8.
 * 终端WIFI设置
 */
public class WifiSetControl extends BaseControl {
    private WIFIsetActivity mActivity;
    private GetTerminalInfoByParam getTerminalInfoByParam;
    private String wifiName = "";

    public WifiSetControl(WIFIsetActivity activity) {
        this.mActivity = activity;
        getTerminalInfoByParam = new GetTerminalInfoByParam(mActivity);
        requestData();
    }

    //查询wifi名称
    private void requestData() {
        boolean wifi = AppConfig.getInstance().getWifiState();
        if (wifi) {
            getTerminalInfoByParam.wifi(GetTerminalInfoByParam.WifiType.getWifiName, "", new IGetdataCallback() {
                @Override
                public void succeed(List<String> list) {
                    if (list != null && list.size() > 0) {
                        String name = pullData(list);
                        if ("".equals(name)) {
                            wifiName = "hxej201_00000000";
                        } else {
                            wifiName = name;
                        }
                        mActivity.upDate(wifiName);
                    }
                }
            });
        }
    }

    //设置wifi名称
    public void requestWifiName(String mWifiname) {
        boolean wifi = AppConfig.getInstance().getWifiState();
        if (wifi) {
            if (mWifiname.equals(wifiName)) {
                mActivity.showToast(R.string.NameNotChange);
            } else {
                getTerminalInfoByParam.wifi(GetTerminalInfoByParam.WifiType.setWifiName, mWifiname, null);
                mActivity.showToast(R.string.NameChangeConnectAgain);
            }
        }
    }

    //设置终端wifi密码
    public void requestWifiPws(final String mOldpws, final String mNewpws, final String mNewpws2) {
        int length = mNewpws.length();
        if ("".equals(mOldpws)) {
            mActivity.showToast(R.string.OldPWS);
        } else if (length < 8) {
            mActivity.showToast(R.string.PWSlength);
        } else if ("".equals(mNewpws2)) {
            mActivity.showToast(R.string.newPWSnotnull);
        } else if (mNewpws.length() != mNewpws2.length()) {
            mActivity.showToast(R.string.newPWSlength);
        } else {
            boolean wifi = AppConfig.getInstance().getWifiState();
            if (wifi) {
                getTerminalInfoByParam.wifi(GetTerminalInfoByParam.WifiType.getWifiPAS, "", new IGetdataCallback() {
                    @Override
                    public void succeed(List<String> list) {
                        if (list != null && list.size() > 0) {
                            String getpws = pullData(list);
                            if ("".equals(getpws)) {
                                getpws = "www.hxgroup.cn";
                            }
                            if (mOldpws.equals(getpws) && mNewpws.equals(mNewpws2)) {
                                getTerminalInfoByParam.wifi(GetTerminalInfoByParam.WifiType.setWifiPAS, mNewpws, null);
                                mActivity.showToast(R.string.PwsChangeConnectAgain);
                            } else {
                                mActivity.showToast(R.string.pwswrong);
                            }
                        }
                    }
                });
            }
        }
    }

    //解析返回数据----去掉没用的0
    private String pullData(List<String> list) {
        String temp = "";
        for (int i = 0; i < list.size(); i++) {
            temp = temp + list.get(i);
        }
        int k = 0;
        for (int j = temp.length() - 1; j >= 0; j--) {
            if ("0".equals(temp.substring(j, j + 1))) {
                k++;
            } else {
                return temp.substring(0, j + 1);
            }
        }
        if (k == temp.length()) {
            return "";
        }
        return temp;
    }
}

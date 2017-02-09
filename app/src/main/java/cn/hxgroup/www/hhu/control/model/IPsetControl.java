package cn.hxgroup.www.hhu.control.model;

import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;
import java.util.List;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.business.AppConfig;
import cn.hxgroup.www.hhu.ui.feature.IPsetActivity;
import hexing.icomm.ICallbackres;
import hexing.icomm.IGetdataCallback;
import hexing.model.ConnPara;
import hexing.tools.GetTerminalInfoByParam;

/**
 * Created by hex170 on 2016/8/9.
 */
public class IPsetControl {
    private IPsetActivity mActivity;
    private GetTerminalInfoByParam getTerminalInfoByParam;
    private List<String> mMainData = new ArrayList<>();
    private List<String> mTerminalData = new ArrayList<>();
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
//                getTerminalIp();
            }
        }
    };

    public IPsetControl(IPsetActivity activity) {
        this.mActivity = activity;
        getTerminalInfoByParam = new GetTerminalInfoByParam(mActivity);
    }

    public void getMainIp() {
        boolean wifi = AppConfig.getInstance().getWifiState();
        if (wifi) {
            getTerminalInfoByParam.get("0A", "3", "", "", new IGetdataCallback() {
                @Override
                public void succeed(List<String> list) {
                    if (list != null && list.size() > 0) {
                        mMainData.clear();
                        mMainData.addAll(list);
                        String mainIp1 = list.get(0) + "." + list.get(1) + "." + list.get(2) + "." + list.get(3);
                        String mainIp2 = list.get(4);
                        String mainIp3 = list.get(5) + "." + list.get(6) + "." + list.get(7) + "." + list.get(8);
                        String mainIp4 = list.get(9);
                        String mainIp5 = list.get(10);
                        List<String> data = new ArrayList<String>();
                        data.add(mainIp1);
                        data.add(mainIp2);
                        data.add(mainIp3);
                        data.add(mainIp4);
                        data.add(mainIp5);
                        mActivity.setUpMainData(data);
                    }
                }
            });
        }
    }

//    public void getTerminalIp() {
//        boolean wifi = AppConfig.getInstance().getWifiState();
//        if (wifi) {
//            getTerminalInfoByParam.get("0A", "7", "", "", new IGetdataCallback() {
//                @Override
//                public void succeed(List<String> list) {
//                   if (list != null && list.size() > 0) {
//                    List<String> data = new ArrayList<String>();
//                    String terminalIp1 = list.get(0) + "." + list.get(1) + "." + list.get(2) + "." + list.get(3);
//                    String terminalIp2 = list.get(4) + "." + list.get(5) + "." + list.get(6) + "." + list.get(7);
//                    String terminalIp3 = list.get(8) + "." + list.get(9) + "." + list.get(10) + "." + list.get(11);
//                    String terminalIp4 = list.get(12);
//                    String terminalIp5 = list.get(13) + "." + list.get(14) + "." + list.get(15) + "." + list.get(16) + "." + list.get(17);
//                    String terminalIp6 = list.get(18);
//                    String terminalIp7 = "";
//                    if (" ".equals(list.get(20))) {
//                    } else {
//                        terminalIp7 = list.get(20);
//                    }
//                    String terminalIp8 = "";
//                    if (" ".equals(list.get(22))) {
//                    } else {
//                        terminalIp8 = list.get(22);
//                    }
//                    String terminalIp9 = list.get(23);
//                    data.add(terminalIp1);
//                    data.add(terminalIp2);
//                    data.add(terminalIp3);
//                    data.add(terminalIp4);
//                    data.add(terminalIp5);
//                    data.add(terminalIp6);
//                    data.add(terminalIp7);
//                    data.add(terminalIp8);
//                    data.add(terminalIp9);
//                    mActivity.setUpTerminalData(data);
//                }}
//            });
//        }
//    }

    public void setMianIp() {
        mMainData.clear();
        List<String> data = mActivity.getMainIpData();
        if (data != null) {
            List<String> data1 = pullIpData(data.get(0));
            List<String> data2 = pullIpData(data.get(2));
            mMainData.addAll(data1);
            mMainData.add(data.get(1));
            mMainData.addAll(data2);
            mMainData.add(data.get(3));
            mMainData.add(data.get(4));
            boolean wifi = AppConfig.getInstance().getWifiState();
            if (wifi) {
                getTerminalInfoByParam.set("04", "3", mMainData, new ICallbackres() {
                    @Override
                    public void succeed(String s,  ConnPara connPara) {
                        mActivity.showToast(R.string.setSucceed);
                    }
                });
            }
        }
    }

    private List<String> pullIpData(String data) {
        List<String> tempData = new ArrayList<>();
        List<Integer> numData = new ArrayList<>();
        for (int i = 0; i < data.length(); i++) {
            if (".".equals(data.substring(i, i + 1))) {
                numData.add(i);
            }
        }
        for (int j = 0; j < numData.size(); j++) {
            if (j == 0) {
                tempData.add(data.substring(0, numData.get(0)));
            } else if (j == numData.size() - 1) {
                tempData.add(data.substring(numData.get(j - 1) + 1, numData.get(j)));
                tempData.add(data.substring(numData.get(j) + 1, data.length()));
            } else {
                tempData.add(data.substring(numData.get(j - 1) + 1, numData.get(j)));
            }
        }
        return tempData;
    }

    //检测输入的数据是否合理
    public boolean checkData(String srt, int max) {
        List<String> data = pullIpData(srt);
        int num = 0;
        for (int i = 0; i < data.size(); i++) {
            try {
                num = Integer.parseInt(data.get(i));
            } catch (Exception e) {
                mActivity.showToast(R.string.kSetIpInvalid);
                return false;
            }
            if (num > max) {
                mActivity.showToast(R.string.kSetIpInvalid);
                return false;
            }
        }
        return true;
    }
//    public void setTerminalIp() {
//        mTerminalData.clear();
//        List<String> upTerminalData = mActivity.getUpTerminalData();
//        if (upTerminalData != null) {
//            mTerminalData.addAll(pullIpData(upTerminalData.get(0)));
//            mTerminalData.addAll(pullIpData(upTerminalData.get(1)));
//            mTerminalData.addAll(pullIpData(upTerminalData.get(2)));
//            mTerminalData.add(upTerminalData.get(3));
//            mTerminalData.addAll(pullIpData(upTerminalData.get(4)));
//            mTerminalData.add(upTerminalData.get(5));
//            mTerminalData.addAll(pullUserName(upTerminalData.get(6)));
//            mTerminalData.addAll(pullUserName(upTerminalData.get(7)));
//            mTerminalData.add(upTerminalData.get(8));
//            boolean wifi = AppConfig.getInstance().getWifiState();
//            if (wifi) {
//                getTerminalInfoByParam.set("04", "7", mTerminalData, new ICallbackres() {
//                    @Override
//                    public void succeed(String s, int i, String s1, String s2) {
//                        mActivity.showToast(R.string.setSucceed);
//                    }
//                });
//            }
//        }
//    }

    private List<String> pullUserName(String name) {
        List<String> data = new ArrayList<>();
        if ("".equals(name)) {
            data.add("0");
            data.add("");
        } else {
            data.add(name.length() + "");
            data.add(name);
        }
        return data;
    }
}

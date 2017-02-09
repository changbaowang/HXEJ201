package cn.hxgroup.www.hhu.control;

import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;
import java.util.List;

import cn.hxgroup.www.hhu.app.HHUApplication;
import cn.hxgroup.www.hhu.business.AppConfig;
import cn.hxgroup.www.hhu.tools.SharedPreferencesUtils;
import cn.hxgroup.www.hhu.ui.mainview.ConnectionFragment;
import hexing.icomm.IGetdataCallback;
import hexing.tools.GetTerminalInfoByParam;

/**
 * Created by hex170 on 2016/9/8.
 * 连接界面
 */
public class ConnectionControl {
    private ConnectionFragment fragment;
    private GetTerminalInfoByParam getTerminalInfoByParam;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                requestData();
            }
        }
    };

    public ConnectionControl(ConnectionFragment fragment) {
        this.fragment = fragment;
        getTerminalInfoByParam = new GetTerminalInfoByParam(fragment.getContext());
        start();
    }

    //是否得到终端地址标志位
    private boolean flag = false;

    private void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!flag) {
                    try {
                        mHandler.sendEmptyMessage(1);
                        Thread.sleep(6000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

    /**
     * 获取数据
     */
    private String terminalID;//wifi名称+终端逻辑地址

    private void requestData() {
        boolean chack = fragment.chack();
        if (!chack) {
            boolean wifi = AppConfig.getInstance().getWifiState();
            if (wifi) {
                getTerminalInfoByParam.get("0A", "89", "", "", new IGetdataCallback() {
                    @Override
                    public void succeed(List<String> list) {
                        if (list != null && list.size() > 0) {
                            terminalID = list.get(0);
                            getTerminalInfoByParam.wifi(GetTerminalInfoByParam.WifiType.getWifiName, "", new IGetdataCallback() {
                                @Override
                                public void succeed(List<String> list) {
                                    if (list != null && list.size() > 0) {
                                        HHUApplication hhuApplication = new HHUApplication();
                                        boolean setPn = hhuApplication.isSetPn();
                                        if (!setPn) {
                                            getPNdata();
                                        }
                                        String wifiname = pullData(list);
                                        if ("".equals(wifiname)) {
                                            terminalID = "hxej201_00000000" + "(" + terminalID + ")";
                                        } else {
                                            terminalID = wifiname + "(" + terminalID + ")";
                                        }
                                        fragment.upData(terminalID);
                                    }
                                }
                            });
                        }
                    }
                });
            }
        } else {
            flag = true;

        }
    }

    public void getPNdata() {
        boolean wifi = AppConfig.getInstance().getWifiState();
        if (wifi) {
            getTerminalInfoByParam.get("0A", "150", "", "", new IGetdataCallback() {
                @Override
                public void succeed(List<String> list) {
                    if (list != null && list.size() > 0) {
                        getTerminalInfoByParam.get("0A", "10", "", list.get(0), new IGetdataCallback() {
                            @Override
                            public void succeed(List<String> res) {
                                setpn(res);
                            }
                        });
                    }
                }
            });
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

    //检查返回测量点中是否含有交流采样装置通讯协议
    private void setpn(List<String> res) {
        if (res == null) {
            return;
        }
        try {
            int num = Integer.parseInt(res.get(0));
            if (num > 20) {
                num = 20;
            }
            List<String> data = new ArrayList<>();
            data.clear();
            for (int i = 0; i < num; i++) {
                data.add(res.get(i * 11 + 5));
            }
            for (int i = 0; i < data.size(); i++) {
                if ("AC".equals(data.get(i))) {
                    SharedPreferencesUtils.setStringState(fragment.getActivity(), "pn", res.get(i * 11 + 1));
                    return;
                }
            }
            SharedPreferencesUtils.setStringState(fragment.getActivity(), "pn", "0");
        } catch (Exception e) {
            e.printStackTrace();
            SharedPreferencesUtils.setStringState(fragment.getActivity(), "pn", "0");
        }
    }
}

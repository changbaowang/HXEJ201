package cn.hxgroup.www.hhu.control;

import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;
import java.util.List;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.business.AppConfig;
import cn.hxgroup.www.hhu.control.base.BaseControl;
import cn.hxgroup.www.hhu.tools.DialogUtils;
import cn.hxgroup.www.hhu.ui.bean.MeterInfo;
import cn.hxgroup.www.hhu.ui.feature.PointBitmapActivity;
import hexing.icomm.IGetdataCallback;
import hexing.tools.DecimalConversion;
import hexing.tools.GetTerminalInfoByParam;

/**
 * Created by hex170 on 2016/8/22.
 * 改为了表计管理
 */
public class PointBitmapControl extends BaseControl {
    private PointBitmapActivity mActivity;
    private GetTerminalInfoByParam getTerminalInfoByParam;
    private List<MeterInfo> meterData = new ArrayList<>();
    public List<MeterInfo> meterData1 = new ArrayList<>();
    DialogUtils dialogUtils = null;
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                dialogUtils.close();
            }
            if (msg.what == 2) {
                mActivity.showToast(mActivity.getResources().getString(R.string.NoData));
                mActivity.mListView.onRefreshComplete();
            }
            if (msg.what == 3) {
                mActivity.update2(meterData1);
                dialogUtils.close();
            }
            if (msg.what == 4) {
                mActivity.mListView.onRefreshComplete();
            }
        }
    };

    public PointBitmapControl(PointBitmapActivity activity) {
        this.mActivity = activity;
        dialogUtils = new DialogUtils(mActivity);
        getTerminalInfoByParam = new GetTerminalInfoByParam(mActivity);
        requestData();
    }

    public List<String> meData = new ArrayList<>();

    /**
     * 获取数据
     */
    public void requestData() {
        boolean wifi = AppConfig.getInstance().getWifiState();
        if (wifi) {
            String title = mActivity.getResources().getString(R.string.Meterhite);
            String content = mActivity.getResources().getString(R.string.MeterAddData);
            dialogUtils.show(title, content);
            start();
            page = 0;
            getTerminalInfoByParam.get("0A", "150", "", "", new IGetdataCallback() {
                @Override
                public void succeed(List<String> list) {
                    if (list != null && list.size() > 0) {
                        meData.clear();
                        meterData.clear();
                        meData.add(list.get(0));
                        text(0);
                    }
                }
            });
        }
    }

    int num = 0;
    public String total = "";
    private boolean isture = false;

    public void text(int k) {
        if (meData != null && meData.size() > 0 && meData.get(0).length() > 4) {
            if (k == 0) {
                meData.add(meData.get(0));
                total = meData.get(0).substring(2, 4) + meData.get(0).substring(0, 2);
                mActivity.totalTv.setText("TOTAL:" + total);
                try {
                    num = Integer.parseInt(total);
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
                String data = "";
                if (num - k * 20 > 20) {
                    data = "1400";
                    data = data + meData.get(0).substring(4, k * 80 + 84);
                    addData(data);
                } else {
                    addData(meData.get(0));
                    isture = true;
                }
            } else {
                String data = "";
                if (num - k * 20 > 20) {
                    data = "1400";
                    data = data + meData.get(0).substring(4 + k * 80, 84 + k * 80);
                    addData(data);
                } else {
                    if ((num - k * 20) <= 0) {
                        handler.sendEmptyMessageDelayed(2, 1000);
                    } else {
                        data = DecimalConversion.padLeft1((Integer.toHexString(num - k * 20) + ""), 2, "0") + "00" + meData.get(0).substring(4 + k * 80, meData.get(0).length());
                        addData(data);
                    }
                }
            }
        }
    }

    public int page = 0;

    public void addData(String data) {
        boolean wifi = AppConfig.getInstance().getWifiState();
        if (wifi) {
            getTerminalInfoByParam.get("0A", "10", "", data, new IGetdataCallback() {
                @Override
                public void succeed(List<String> res) {
                    if (res != null && res.size() > 0) {
                        pullData(res, page);
                        meterData1.addAll(meterData);
                        page++;
                        mActivity.update2(meterData1);
                        mActivity.adapter.notifyDataSetChanged();
                        mActivity.mListView.onRefreshComplete();
                        dialogUtils.close();
                    }
                }
            });
        }
    }


    //解析返回的数据
    private void pullData(List<String> res, int flag) {
        try {
            meterData.clear();
            if (flag == 0) {
                mActivity.rs485data.clear();
                mActivity.plcdata.clear();
            }
            int num = Integer.parseInt(res.get(0));
            for (int i = 0; i < num; i++) {
                List<String> mapData = new ArrayList<>();
                for (int j = 0; j < 11; j++) {
                    mapData.add(res.get(i * 11 + 1 + j));
                }
                mActivity.mapData.put(flag * 20 + i, mapData);
                MeterInfo meterInfo = null;
                String meterId = res.get(i * 11 + 6);
                String meterPn = res.get(i * 11 + 2);
                String type = res.get(i * 11 + 4);
                String meterType = "";
                if ("RS485".equals(type)) {
                    meterType = "RS485";
                    meterInfo = new MeterInfo(meterId, meterPn, meterType);
                    mActivity.rs485data.add(meterInfo);
                } else if ("RF/PLC".equals(type)) {
                    meterType = "RF/PLC";
                    meterInfo = new MeterInfo(meterId, meterPn, meterType);
                    mActivity.plcdata.add(meterInfo);
                } else {
                    meterInfo = new MeterInfo(meterId, meterPn, type);
                }
                meterData.add(meterInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 5;
                while (i > 0) {
                    try {
                        Thread.sleep(1000);
                        i--;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                handler.sendEmptyMessage(1);
            }
        }).start();
    }
}

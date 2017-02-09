package cn.hxgroup.www.hhu.control;

import java.util.ArrayList;
import java.util.List;

import cn.hxgroup.www.hhu.business.AppConfig;
import cn.hxgroup.www.hhu.control.base.BaseControl;
import cn.hxgroup.www.hhu.ui.bean.MeterInfo;
import cn.hxgroup.www.hhu.ui.feature.Meter2Activity;
import hexing.icomm.IGetdataCallback;
import hexing.protocol.GWHelp.Analysis;
import hexing.tools.GetTerminalInfoByParam;

/**
 * Created by hex170 on 2016/8/26.
 * 添加表计   --  选择测量点
 */
public class Meter2Control extends BaseControl {
    private Meter2Activity mActivity;
    private GetTerminalInfoByParam getTerminalInfoByParam;
    private List<MeterInfo> meterData = new ArrayList<>();

    public Meter2Control(Meter2Activity activity) {
        this.mActivity = activity;
        getTerminalInfoByParam = new GetTerminalInfoByParam(mActivity);
        requestData();
    }

    /**
     * 获取数据
     */
    private ArrayList<Integer> numData = new ArrayList<>();

    public void requestData() {
        boolean wifi = AppConfig.getInstance().getWifiState();
        if (wifi) {
            getTerminalInfoByParam.get("0A", "150", "", "", new IGetdataCallback() {
                        @Override
                        public void succeed(List<String> list) {
                            if (list != null && list.size() > 0) {
                                String numTemp1 = list.get(0);
                                if (0 == (numTemp1.length() % 4)) {
                                    String numTemp2 = Analysis.resverstr(numTemp1.substring(4, numTemp1.length()));
                                    for (int i = 0; i < (numTemp2.length() / 4); i++) {
                                        String s = pullData(numTemp2.substring(4 * i, 4 * i + 4));
                                        numData.add(Integer.parseInt(s, 16));
                                    }
                                }
                                pullData();
                            }
                        }
                    }
            );
        }
    }

    //解析返回数据----去掉没用的0
    private String pullData(String list) {
        String temp = "";
        int k = 0;
        for (int j = list.length() - 1; j >= 0; j--) {
            if ("0".equals(list.substring(j, j + 1))) {
                k++;
            } else {
                return list.substring(0, j + 1);
            }
        }
        if (k == temp.length()) {
            return "0";
        }
        return temp;
    }

    private void pullData() {
        for (int i = 2; i < 100 + numData.size(); i++) {
            boolean flage = false;
            for (int k = 0; k < numData.size(); k++) {
                if (i == numData.get(k)) {
                    flage = true;
                    k = numData.size();
                }
            }
            if (!flage) {
                mActivity.data.add(i);
            }
        }
        mActivity.adapter.notifyDataSetChanged();
    }
}
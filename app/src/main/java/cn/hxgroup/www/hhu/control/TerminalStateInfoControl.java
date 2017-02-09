package cn.hxgroup.www.hhu.control;


import java.util.ArrayList;
import java.util.List;

import cn.hxgroup.www.hhu.business.AppConfig;
import cn.hxgroup.www.hhu.control.base.BaseControl;
import cn.hxgroup.www.hhu.ui.feature.TerminalStateInfoActivity;
import hexing.icomm.IGetdataCallback;
import hexing.tools.GetTerminalInfoByParam;

/**
 * Created by hex170 on 2016/8/16.
 * 终端集中抄表状态信息及终端版本
 */
public class TerminalStateInfoControl extends BaseControl {
    private TerminalStateInfoActivity mActivity;
    private GetTerminalInfoByParam getTerminalInfoByParam;

    public TerminalStateInfoControl(TerminalStateInfoActivity activity) {
        this.mActivity = activity;
        getTerminalInfoByParam = new GetTerminalInfoByParam(mActivity);
        requestData();

    }

    /**
     * 获取数据
     */
    private void requestData() {
        boolean wifi = AppConfig.getInstance().getWifiState();
        if (wifi) {
            getTerminalInfoByParam.get("0C", "11", "", "", new IGetdataCallback() {
                @Override
                public void succeed(List<String> list) {
                    if (list != null && list.size() > 0) {
                        pullData(list);
                    }
                }
            });
        }
    }

    private List<String> data = new ArrayList<>();

    private void pullData(List<String> list) {
        for (int i = 0; i < Integer.parseInt(list.get(0)); i++) {
            int port = Integer.parseInt(list.get(i * 7 + 1));
            if (2 == port) {
                data.add(list.get(i * 7 + 2));
                data.add(list.get(i * 7 + 4));
            } else if (31 == port) {
                data.add(list.get(i * 7 + 2));
                data.add(list.get(i * 7 + 4));
            }
        }
        mActivity.update(data);
    }
}

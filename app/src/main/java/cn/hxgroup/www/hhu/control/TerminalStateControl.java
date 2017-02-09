package cn.hxgroup.www.hhu.control;


import java.util.List;

import cn.hxgroup.www.hhu.business.AppConfig;
import cn.hxgroup.www.hhu.control.base.BaseControl;
import cn.hxgroup.www.hhu.ui.feature.TerminalStateActivity;
import hexing.icomm.IGetdataCallback;
import hexing.tools.GetTerminalInfoByParam;

/**
 * Created by hex170 on 2016/8/16.
 * 终端当前状态
 */
public class TerminalStateControl extends BaseControl {
    private TerminalStateActivity mActivity;
    private GetTerminalInfoByParam getTerminalInfoByParam;

    public TerminalStateControl(TerminalStateActivity activity) {
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
            getTerminalInfoByParam.get("09", "240", "", "", new IGetdataCallback() {
                @Override
                public void succeed(List<String> list) {
                    if (list != null && list.size() > 0) {
                        mActivity.update(list);
                    }
                }
            });
        }
    }
}

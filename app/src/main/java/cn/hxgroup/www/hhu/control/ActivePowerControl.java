package cn.hxgroup.www.hhu.control;


import java.util.List;

import cn.hxgroup.www.hhu.business.AppConfig;
import cn.hxgroup.www.hhu.control.base.BaseControl;
import cn.hxgroup.www.hhu.ui.feature.ActivePowerActivity;
import hexing.icomm.IGetdataCallback;
import hexing.tools.GetTerminalInfoByParam;

/**
 * Created by hex170 on 2016/8/16.
 * 当前正向有功电能示值
 */
public class ActivePowerControl extends BaseControl {
    private ActivePowerActivity mActivity;
    private GetTerminalInfoByParam getTerminalInfoByParam;

    public ActivePowerControl(ActivePowerActivity activity) {
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
            getTerminalInfoByParam.get("0C", "129", mActivity.pn, "", new IGetdataCallback() {
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

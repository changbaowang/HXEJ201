package cn.hxgroup.www.hhu.control;

import cn.hxgroup.www.hhu.business.AppConfig;
import cn.hxgroup.www.hhu.constant.ResetType;
import cn.hxgroup.www.hhu.control.base.BaseControl;
import cn.hxgroup.www.hhu.ui.feature.ResetActivity;
import hexing.tools.GetTerminalInfoByParam;

/**
 * Created by CXJ on 2016/6/4.
 */
public class ResetControl extends BaseControl {
    private ResetActivity mActivity;
    private GetTerminalInfoByParam getTerminalInfoByParam;

    public ResetControl(ResetActivity activity) {
        mActivity = activity;
        getTerminalInfoByParam = new GetTerminalInfoByParam(mActivity);
    }


    public void reset(ResetType resetType) {
        boolean wifi = AppConfig.getInstance().getWifiState();
        if (wifi) {
            switch (resetType) {
                case RESET_TYPE_DEVICE:
                    getTerminalInfoByParam.resetting("01", "1");
                    break;
                case RESET_TYPE_DATA:
                    getTerminalInfoByParam.resetting("01", "2");
                    break;
                case RESET_TYPE_ALL:
                    getTerminalInfoByParam.resetting("01", "4");
                    break;
            }
        }
    }
}

package cn.hxgroup.www.hhu.control;

import java.util.List;

import cn.hxgroup.www.hhu.business.AppConfig;
import cn.hxgroup.www.hhu.control.base.BaseControl;
import cn.hxgroup.www.hhu.ui.feature.VersionInfoActivity;
import hexing.icomm.IGetdataCallback;
import hexing.tools.GetTerminalInfoByParam;

/**
 * Created by hex170 on 2016/8/31.
 */
public class VersionInfoControl extends BaseControl {
    private VersionInfoActivity mActivity;
    private GetTerminalInfoByParam getTerminalInfoByParam;

    public VersionInfoControl(VersionInfoActivity activity) {
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
            getTerminalInfoByParam.get("09", "10", "", "", new IGetdataCallback() {
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

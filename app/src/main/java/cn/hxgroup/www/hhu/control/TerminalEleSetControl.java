package cn.hxgroup.www.hhu.control;

import java.util.List;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.business.AppConfig;
import cn.hxgroup.www.hhu.control.base.BaseControl;
import cn.hxgroup.www.hhu.ui.feature.TerminalEleSetActivity;
import hexing.icomm.ICallbackres;
import hexing.icomm.IGetdataCallback;
import hexing.model.ConnPara;
import hexing.tools.GetTerminalInfoByParam;

/**
 * Created by hex170 on 2016/12/14.
 */

public class TerminalEleSetControl extends BaseControl {
    private TerminalEleSetActivity mActivity;
    private GetTerminalInfoByParam getTerminalInfoByParam;

    public TerminalEleSetControl(TerminalEleSetActivity mActivity) {
        this.mActivity = mActivity;
        getTerminalInfoByParam = new GetTerminalInfoByParam(mActivity);
    }

    /**
     * 获取数据
     */
    public void requestData() {
        boolean wifi = AppConfig.getInstance().getWifiState();
        if (wifi) {
            getTerminalInfoByParam.get("0A", "237", "", "", new IGetdataCallback() {
                @Override
                public void succeed(List<String> res) {
                    if (res != null && res.size() > 0) {
                        mActivity.getData(res);
                    }
                }
            });
        }
    }

    /**
     * 设置数据
     */
    public void setTerminalData(List<String> data) {
        boolean wifi = AppConfig.getInstance().getWifiState();
        if (wifi) {
            getTerminalInfoByParam.set("04", "237", data, new ICallbackres() {
                @Override
                public void succeed(String res,  ConnPara connPara) {
                    mActivity.showToast(mActivity.getResources().getString(R.string.setSucceed));
                }
            });
        }

    }
}

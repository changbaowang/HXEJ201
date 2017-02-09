package cn.hxgroup.www.hhu.control;

import java.util.List;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.business.AppConfig;
import cn.hxgroup.www.hhu.control.base.BaseControl;
import cn.hxgroup.www.hhu.ui.feature.GpsSetActivity;
import hexing.icomm.ICallbackres;
import hexing.icomm.IGetdataCallback;
import hexing.model.ConnPara;
import hexing.tools.GetTerminalInfoByParam;

/**
 * Created by hex170 on 2016/8/8.
 * GPS
 */
public class GpsSetControl extends BaseControl {
    private GpsSetActivity mActivity;
    private GetTerminalInfoByParam getTerminalInfoByParam;

    public GpsSetControl(GpsSetActivity activity) {
        this.mActivity = activity;
        getTerminalInfoByParam = new GetTerminalInfoByParam(mActivity);
        requestData();
    }

    //请求数据
    int k = 0;//是否刷新标志 第一次加载数据不需要显示Toast

    public void requestData() {
        boolean wifi = AppConfig.getInstance().getWifiState();
        if (wifi) {
            getTerminalInfoByParam.get("0A", "245", "", "", new IGetdataCallback() {
                @Override
                public void succeed(List<String> res) {
                    if (res != null && res.size() > 0) {
                        mActivity.upDate(res);
                        if (k > 0) {
                            mActivity.showToast(mActivity.getResources().getString(R.string.kFresh));
                        }
                        k++;
                    }
                }
            });
        }
    }

    //设置数据
    public void setData(List<String> data) {
        boolean wifi = AppConfig.getInstance().getWifiState();
        if (wifi) {
            getTerminalInfoByParam.set("04", "245", data, new ICallbackres() {
                @Override
                public void succeed(String res, ConnPara connPara) {
                    mActivity.showToast(mActivity.getResources().getString(R.string.setSucceed));
                }
            });
        }
    }
}

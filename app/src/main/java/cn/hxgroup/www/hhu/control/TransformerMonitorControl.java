package cn.hxgroup.www.hhu.control;

import java.util.List;

import cn.hxgroup.www.hhu.business.AppConfig;
import cn.hxgroup.www.hhu.control.base.BaseControl;
import cn.hxgroup.www.hhu.ui.feature.TransformerMonitoringActivity;
import hexing.icomm.IGetdataCallback;
import hexing.tools.GetTerminalInfoByParam;

/**
 * Created by hex170 on 2016/8/24.
 * 变压器监控终端状态量输入参数
 */
public class TransformerMonitorControl extends BaseControl {
    private TransformerMonitoringActivity mActivity;
    private GetTerminalInfoByParam param;

    public TransformerMonitorControl(TransformerMonitoringActivity mActivity) {
        this.mActivity = mActivity;
        param = new GetTerminalInfoByParam(mActivity);
        getData();

    }

//    private void getData() {
//        boolean wifi = AppConfig.getInstance().getWifiState();
//        if (wifi) {
//            param.get("09", "10", "", "", new IGetdataCallback() {
//                @Override
//                public void succeed(List<String> res) {
//                    if (null != res && 0 != res.size()) {
//                        mActivity.upDate(res);
//                    }
//                }
//            });
//        }
//    }
    private void getData() {
        boolean wifi = AppConfig.getInstance().getWifiState();
        if (wifi) {
            param.get("0A", "230", "", "", new IGetdataCallback() {
                @Override
                public void succeed(List<String> res) {
                    if (null != res && 0 != res.size()) {
                        mActivity.upDate(res);
                    }
                }
            });
        }
    }
}

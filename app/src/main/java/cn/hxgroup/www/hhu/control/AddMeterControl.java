package cn.hxgroup.www.hhu.control;


import java.util.ArrayList;
import java.util.List;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.business.AppConfig;
import cn.hxgroup.www.hhu.control.base.BaseControl;
import cn.hxgroup.www.hhu.tools.DialogUtils;
import cn.hxgroup.www.hhu.tools.SharedPreferencesUtils;
import cn.hxgroup.www.hhu.ui.feature.AddMeterActivity;
import hexing.icomm.ICallbackres;
import hexing.model.ConnPara;
import hexing.tools.GetTerminalInfoByParam;

/**
 * Created by hex170 on 2016/8/26.
 * 添加表计
 */
public class AddMeterControl extends BaseControl {
    private AddMeterActivity mActivity;
    private GetTerminalInfoByParam getTerminalInfoByParam;

    public AddMeterControl(AddMeterActivity activity) {
        this.mActivity = activity;
        getTerminalInfoByParam = new GetTerminalInfoByParam(mActivity);
    }

    //处理交测点问题
    public void addMeter(final List<String> data) {
        String pn = SharedPreferencesUtils.getStringState(mActivity, "pn");
        boolean wifi = AppConfig.getInstance().getWifiState();
        if (wifi) {
            if ("02" == data.get(4)) {//添加的点为交测点
                List<String> pndata = new ArrayList<>();
                pndata.add(pn);
                getTerminalInfoByParam.dealMeter(GetTerminalInfoByParam.MeterType.delete, pndata, new ICallbackres() {
                    @Override
                    public void succeed(String res,  ConnPara connPara) {
                        SharedPreferencesUtils.setStringState(mActivity, "pn", data.get(0));
                        addMeterData(data);

                    }
                });
            } else {
                addMeterData(data);
            }
        }
    }

    //添加表操作
    private void addMeterData(List<String> data) {
        if ("0".equals(data.get(2))) {
            if ("0".equals(data.get(1))) {
                data.set(2, 4 + "");
            } else {
                data.set(2, 6 + "");
            }
        }
        boolean wifi = AppConfig.getInstance().getWifiState();
        final DialogUtils dialogUtils = new DialogUtils(mActivity);
        String title = mActivity.getResources().getString(R.string.Meterhite);
        String content = mActivity.getResources().getString(R.string.MeterAddData);
        if (wifi) {
            dialogUtils.show(title, content);
            getTerminalInfoByParam.dealMeter(GetTerminalInfoByParam.MeterType.add, data, new ICallbackres() {
                @Override
                public void succeed(String res,  ConnPara connPara) {
                    dialogUtils.close();
                    mActivity.showToast(mActivity.getResources().getString(R.string.kMeterAddSucceed));
                    mActivity.finish();
                }
            });
        }
    }
}

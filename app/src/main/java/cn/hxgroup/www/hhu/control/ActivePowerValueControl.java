package cn.hxgroup.www.hhu.control;


import java.util.ArrayList;
import java.util.List;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.business.AppConfig;
import cn.hxgroup.www.hhu.control.base.BaseControl;
import cn.hxgroup.www.hhu.db.ActiveValueDB;
import cn.hxgroup.www.hhu.ui.feature.ActivePowerValueActivity;
import hexing.icomm.IGetdataCallback;
import hexing.protocol.GWHelp.Analysis;
import hexing.tools.GetTerminalInfoByParam;

/**
 * Created by hex170 on 2016/8/16.
 * 日冻结正向有功电能示值
 */
public class ActivePowerValueControl extends BaseControl {
    private ActivePowerValueActivity mActivity;
    private GetTerminalInfoByParam getTerminalInfoByParam;
    public ActiveValueDB database;
    private List<String> mData = new ArrayList<>();

    public ActivePowerValueControl(ActivePowerValueActivity activity) {
        this.mActivity = activity;
        getTerminalInfoByParam = new GetTerminalInfoByParam(mActivity);
        database = new ActiveValueDB(activity);
        database.open();
    }

    /**
     * 获取数据
     */
    public void requestData(final String data) {
        String[] split = data.split("-");
        String dataValue = "";
        for (int i = 0; i < split.length; i++) {
            dataValue = dataValue + split[i];
        }
        if (dataValue.length() != 8) {
            mActivity.showToast(R.string.TimeFormart);
        } else {
            String timeDate1 = dataValue.substring(2, dataValue.length());
            String timeDate2 = Analysis.resverstr(timeDate1);
            boolean wifi = AppConfig.getInstance().getWifiState();
            if (wifi && timeDate2.length() == 6) {
                getTerminalInfoByParam.get("0D", "161", mActivity.pn, timeDate2, new IGetdataCallback() {
                    @Override
                    public void succeed(List<String> list) {
                        if (list != null && list.size() > 0) {
                            mActivity.update(list);
                            mData.clear();
                            mData.addAll(list);
                        }
                    }
                });
            }
        }
    }

    public void saveData() {
        if (mData.size() > 5) {
            String address = "";
            if (mActivity.address == "") {
                address = "100000";
            } else {
                address = mActivity.address;
            }
            long num = database.addData(address, mData, "1");
            if (num >= 1) {
                mActivity.showToast(mActivity.getResources().getString(R.string.SaveSucceed));
            } else {
                mActivity.showToast(mActivity.getResources().getString(R.string.DataExist));
            }
        }
    }
}

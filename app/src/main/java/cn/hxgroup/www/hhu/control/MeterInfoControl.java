package cn.hxgroup.www.hhu.control;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;

import java.util.ArrayList;
import java.util.List;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.business.AppConfig;
import cn.hxgroup.www.hhu.control.base.BaseControl;
import cn.hxgroup.www.hhu.tools.SharedPreferencesUtils;
import cn.hxgroup.www.hhu.ui.bean.MeterInfo;
import cn.hxgroup.www.hhu.ui.feature.MeterInfoActivity;
import hexing.icomm.ICallbackres;
import hexing.icomm.IGetdataCallback;
import hexing.model.ConnPara;
import hexing.tools.GetTerminalInfoByParam;

/**
 * Created by hex170 on 2016/8/27.
 * 表计信息
 */
public class MeterInfoControl extends BaseControl {
    private MeterInfoActivity mActivity;
    private GetTerminalInfoByParam getTerminalInfoByParam;
    private List<MeterInfo> meterData = new ArrayList<>();

    public MeterInfoControl(MeterInfoActivity activity) {
        this.mActivity = activity;
        getTerminalInfoByParam = new GetTerminalInfoByParam(mActivity);

    }

    //删除表操作
    public void delMeter(String pn, String type) {
        String s = Integer.toHexString(Integer.parseInt(pn));
        List<String> data = new ArrayList<>();
        data.add(s);
        if ("2".equals(type)) {
            SharedPreferencesUtils.setStringState(mActivity, "pn", "0");
        }
        boolean wifi = AppConfig.getInstance().getWifiState();
        if (wifi) {
            show();
            getTerminalInfoByParam.dealMeter(GetTerminalInfoByParam.MeterType.delete, data, new ICallbackres() {
                @Override
                public void succeed(String res,  ConnPara connPara) {
                    mActivity.showToast(mActivity.getResources().getString(R.string.kMeterDelSucceed));
                    pDialog.cancel();
                    mActivity.setResult(1);
                    mActivity.finish();
                }
            });
        }
    }


    //删除表操作
    public void getd() {
        boolean wifi = AppConfig.getInstance().getWifiState();
        if (wifi) {
            getTerminalInfoByParam.get("0C", "129", "3", "", new IGetdataCallback() {
                @Override
                public void succeed(List<String> res) {
                    for (int i = 0; i < res.size(); i++) {
                        mActivity.showToast(res.get(i) + "aaaa");
                    }
                }
            });
        }
    }

    //是否确认删除弹出对话框
    public void dialog(final String data, final String type) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setMessage(mActivity.getResources().getString(R.string.MeterdelMeter));
        builder.setTitle(mActivity.getResources().getString(R.string.Meterhite));
        builder.setPositiveButton(mActivity.getResources().getString(R.string.kOk), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                delMeter(data, type);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(mActivity.getResources().getString(R.string.kCancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private ProgressDialog pDialog;

    private void show() {
        pDialog = new ProgressDialog(mActivity);
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.setTitle(mActivity.getResources().getString(R.string.Meterhite));
        pDialog.setMessage(mActivity.getResources().getString(R.string.MeterDelData));
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
    }
}

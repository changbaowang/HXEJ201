package cn.hxgroup.www.hhu.control;

import android.os.Environment;
import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;
import java.util.List;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.business.AppConfig;
import cn.hxgroup.www.hhu.constant.DefaultConstant;
import cn.hxgroup.www.hhu.control.base.BaseControl;
import cn.hxgroup.www.hhu.db.RealDataDB;
import cn.hxgroup.www.hhu.tools.DialogUtils;
import cn.hxgroup.www.hhu.tools.FileUtil;
import cn.hxgroup.www.hhu.ui.feature.RealDataActivity;
import hexing.icomm.IGetdataCallback;
import hexing.tools.GetTerminalInfoByParam;

/**
 * Created by HEX170 on 2016/8/3.
 * 实时数据
 */
public class RealDataControl extends BaseControl {
    private RealDataActivity mActivity;
    private boolean wifi = true;
    private GetTerminalInfoByParam getTerminalInfoByParam;
    public RealDataDB database;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                if (DialogNum != 0) {
                    dialogUtils.close();
                }
                requestElectricData();
            }
            if (msg.what == 2) {
                if (DialogNum != 0) {
                    dialogUtils.close();
                }
            }
        }
    };

    public RealDataControl(RealDataActivity activity) {
        mActivity = activity;
        getTerminalInfoByParam = new GetTerminalInfoByParam(activity);
        database = new RealDataDB(activity);
        database.open();
        start();
    }

    public void destroy() {
        wifi = false;
    }

    //开始获取实时数据
    private void start() {
        new Thread() {
            @Override
            public void run() {
                while (wifi) {
                    try {
                        mHandler.sendEmptyMessage(1);
                        Thread.sleep(DefaultConstant.DEFAULT_REAL_DATA_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    //第一次加载数据显示弹出框
    private int DialogNum = 0;
    private DialogUtils dialogUtils;

    private void requestElectricData() {
        wifi = AppConfig.getInstance().getWifiState();
        dialogUtils = new DialogUtils(mActivity);
        if (DialogNum != 0) {
            dialogUtils.close();
        }
        if (wifi) {
            if (DialogNum == 0) {
                String title = mActivity.getResources().getString(R.string.Meterhite);
                String content = mActivity.getResources().getString(R.string.MeterAddData);
                dialogUtils.show(title, content);
            }
            DialogNum++;
            getTerminalInfoByParam.get("0C", "25", mActivity.pn, "", new IGetdataCallback() {
                        @Override
                        public void succeed(List<String> list) {
                            if (list != null) {
                                mActivity.data.clear();
                                mActivity.data.addAll(list);
                                getData();
                            }
                        }
                    }
            );
        }
    }

    //得到相角数据
    private void getData() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (wifi) {
            getTerminalInfoByParam.get("0C", "49", mActivity.pn, "", new IGetdataCallback() {
                @Override
                public void succeed(List<String> list) {
                    if (list != null && list.size() > 0) {
                        List<Float> realdata = new ArrayList<>();
                        for (int i = 0; i < list.size(); i++) {
                            mActivity.data.add(list.get(i) + "°");
                            realdata.add(Float.parseFloat(list.get(i)));
                        }
                        mHandler.sendEmptyMessage(2);
                        mActivity.adapter.notifyDataSetChanged();
                        mActivity.update(realdata);
                    }
                }
            });
        }
    }

    public void saveData() {
        if (mActivity.data.size() > 29) {
            String address = "";
            if (mActivity.address == null) {
                address = "100000";
            } else {
                address = mActivity.address;
            }
            database.deleteByName(address);
            database.insert(address, mActivity.data);
            mActivity.showToast(mActivity.getResources().getString(R.string.SaveSucceed));

        }
    }

    public void getData1() {
        String realdata = database.fetchAll();
        try {
            FileUtil.contentToTxt(Environment.getExternalStorageDirectory() + "/HHU/LocalUpdate/realdata.xls", realdata);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package cn.hxgroup.www.hhu.control;


import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.business.AppConfig;
import cn.hxgroup.www.hhu.constant.DefaultConstant;
import cn.hxgroup.www.hhu.control.base.BaseControl;
import cn.hxgroup.www.hhu.ui.component.MessageDialog;
import cn.hxgroup.www.hhu.ui.feature.LocalUploadActivity;
import hexing.icomm.IGetdataCallback;
import hexing.model.Constant;
import hexing.tools.GetTerminalInfoByParam;

/**
 * Created by HEX170 on 2016/10/24.
 */
public class LocalUploadControl extends BaseControl {
    private LocalUploadActivity mActivity;
    private GetTerminalInfoByParam getTerminalInfoByParam;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    progressDlg.dismiss();
                    mActivity.updateVersion(mActivity.getResources().getString(R.string.kLatestVersion));
                    break;
                case 2:
                    break;
            }
        }
    };

    public LocalUploadControl(LocalUploadActivity activity) {
        this.mActivity = activity;
        getTerminalInfoByParam = new GetTerminalInfoByParam(mActivity);
        requestData();
    }

    private void requestData() {
        boolean wifi = AppConfig.getInstance().getWifiState();
        if (wifi) {
            getTerminalInfoByParam.get("09", "1", "", "", new IGetdataCallback() {
                @Override
                public void succeed(List<String> res) {
                    if (res != null && res.size() > 0) {
                        mActivity.updateVersion(checkVersion(res.get(2)));
                    }
                }
            });
        }
    }

    private int isUpdate = 0;//标记位

    public String checkVersion(String version) {
        if (DefaultConstant.TERMINAL_VERSION.equals(version)) {
            return mActivity.getResources().getString(R.string.kLatestVersion);
        }
        isUpdate = 1;
        return mActivity.getResources().getString(R.string.kNewVersion);
    }

    public void pullData() {
        String context = "";
        String leftText = "";
        String rightText = "";
        if (isUpdate == 1) {
            context = mActivity.getResources().getString(R.string.kUpdateInfo);
            leftText = mActivity.getResources().getString(R.string.kLater);
            rightText = mActivity.getResources().getString(R.string.kUpdateImmediately);
            showTipDialog(context, leftText, rightText, 1);
        } else {
            context = mActivity.getResources().getString(R.string.kNoUpdate);
            leftText = mActivity.getResources().getString(R.string.kCancel);
            rightText = mActivity.getResources().getString(R.string.kOk);
            showTipDialog(context, leftText, rightText, 0);
        }
    }

    /**
     * 点击按钮后的提示对话框
     */
    private void showTipDialog(String context, String leftText, String rightText, final int type) {
        MessageDialog dialog = new MessageDialog(mActivity, R.style.CustomDialogStyle);
        dialog.setTitle(mActivity.getResources().getString(R.string.kClient));
        dialog.setMessage(context);
        dialog.setOnLeftBtnClickListener(leftText, null);
        dialog.setOnRightBtnClickListener(rightText, new MessageDialog.Callback() {
            @Override
            public void callback() {
                if (type == 1) {
                    showProgerss();
//                    sendFile();
                }
            }
        });
        dialog.show();
    }

    private ProgressDialog progressDlg;

    private void showProgerss() {
        progressDlg = new ProgressDialog(mActivity);
        progressDlg.setTitle(mActivity.getResources().getString(R.string.kUpgrade));
        progressDlg.setMessage(mActivity.getResources().getString(R.string.kTransmit));
        progressDlg.setIcon(R.mipmap.ic_launcher);
        progressDlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDlg.setCancelable(false);
        progressDlg.show();
    }

    private void sendFile() {
        Log.d("xxxxxxxxxxx", "123123");
        new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream inputStream = null;
                OutputStream outputStream = null;
                Socket socket = null;
                try {
                    socket = new Socket(Constant.HOST, Constant.PORT);
                    inputStream = mActivity.getResources().getAssets().open("HXEJ200.bin");
//                    inputStream = mActivity.getResources().openRawResource(R.raw.HXEJ200);
                    outputStream = socket.getOutputStream();
                    byte[] bytes = new byte[1024];
                    int len = 0;
                    while ((len = inputStream.read(bytes)) != -1) {
                        outputStream.write(bytes, 0, len);
                        handler.sendEmptyMessage(2);
                    }
                    handler.sendEmptyMessage(1);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        if (outputStream != null) {
                            outputStream.close();
                        }
                        if (socket != null) {
                            socket.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}

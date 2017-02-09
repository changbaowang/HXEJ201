package cn.hxgroup.www.hhu.control;

import android.os.Handler;
import android.os.Message;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

import cn.hxgroup.www.hhu.business.AppConfig;
import cn.hxgroup.www.hhu.constant.DefaultConstant;
import cn.hxgroup.www.hhu.control.base.BaseControl;
import cn.hxgroup.www.hhu.ui.feature.LocalUploadActivity;
import hexing.icomm.IGetdataCallback;
import hexing.tools.GetTerminalInfoByParam;

/**
 * Created by hex170 on 2016/9/13.
 */
public class TerminalVersionControl extends BaseControl {
    private LocalUploadActivity mActivity;
    private GetTerminalInfoByParam getTerminalInfoByParam;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
//                tv.setText("成功");
            }
            if (msg.what == 2) {
//                i++;
//                tv.setText("正在传输" + i);
            }
        }
    };

    public TerminalVersionControl(LocalUploadActivity activity) {
        this.mActivity = activity;
        getTerminalInfoByParam = new GetTerminalInfoByParam(mActivity);
        //requestData();

    }

    /**
     * 获取数据
     */
    private void requestData() {
        boolean wifi = AppConfig.getInstance().getWifiState();
        if (wifi) {
            getTerminalInfoByParam.get("0C", "11", "", "", new IGetdataCallback() {
                @Override
                public void succeed(List<String> list) {
                    if (list != null && list.size() > 0) {
                        pullData(list);
                    }
                }
            });
        }
    }

    private void pullData(List<String> list) {
        if (list != null && list.size() > 0) {
            if (DefaultConstant.TERMINAL_VERSION.equals(list.get(0))) {
                //终端版本已经是最高版本
            } else {
            }
        }
    }

    //发送升级文件
    private void sendFile() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream inputStream = null;
                OutputStream outputStream = null;
                Socket socket = null;
                try {
                    socket = new Socket("11.11.11.254", 1000);
//                    socket = new Socket("172.16.7.39", 9000);
//                    inputStream = mActivity.getResources().openRawResource(R.raw.hexj201);
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

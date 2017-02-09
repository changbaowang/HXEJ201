package hexing.comm;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import hexing.icomm.ICallbackBytaData;
import hexing.model.CommPara;
import hexing.model.ConnPara;
import hexing.model.Constant;
import hexing.tools.DecimalConversion;

/**
 * Created by hex170 on 2016/7/20.
 * wifi工具类，实现对终端设备数据的读取设置
 */
public class CommWifi {
    private Socket socket = null;
    private InputStream in = null;
    private OutputStream out = null;
    static ExecutorService executorService;

    static {
        executorService = Executors.newSingleThreadExecutor();
    }

    public synchronized void getData(final byte[] sndByte, final ConnPara connPara, final ICallbackBytaData callback) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                byte[] sndByte1 = sndByte;
                ConnPara connPara1 = null;
                if (connPara != null) {
                    connPara1 = connPara;
                }
                ICallbackBytaData callback1 = callback;
                try {
                    CommPara commPara = new CommPara();
                    commPara.ip = Constant.HOST;
                    commPara.port = Constant.PORT;
                    if (socket == null || (!socket.isConnected())) {
                        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
                        socket = new Socket(commPara.ip, commPara.port);
                        in = socket.getInputStream();
                        out = socket.getOutputStream();
                        socket.setSoTimeout(1000 * 5);
                    }
                    out.write(sndByte1);
                    out.flush();
                    String str = DecimalConversion.byteToHexStr(sndByte1);
                    Log.d("xxxxxxxxSend--", " 376.1 Send:" + str);
                    byte[] bbuf = new byte[1024];
                    int count = in.read(bbuf);
                    String Msg1 = "";
                    if (count > 0) {
                        byte[] temp1 = Arrays.copyOfRange(bbuf, 0, count);
                        Msg1 = Msg1 + DecimalConversion.byteToHexStr(temp1);
                        if (temp1.length > 0) {
                            //计算校验和  和  这帧倒数第二个去判断
                            //未加校验和，加了校验和，有异常未解决
                            while (!(temp1[count - 1] == 22)) {
                                Log.d("xxxxxxxxRec--", "--376.1 Rec1111:" + "有问题" + Msg1);
                                try {
                                    count = in.read(bbuf);
                                    temp1 = Arrays.copyOfRange(bbuf, 0, count);
                                    Msg1 = Msg1 + DecimalConversion.byteToHexStr(temp1);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    break;
                                }
                            }
                        }
                        Log.d("xxxxxxxxRec--", "--376.1 Rec1111:" + Msg1);
                        byte[] rec = DecimalConversion.convertHexToImageBytes(Msg1.replace(" ", ""));
                        Message message = new Message();
                        message.obj = callback1;
                        Bundle bundle = new Bundle();
                        bundle.putByteArray("rec", rec);
                        if (connPara1 != null) {
                            bundle.putSerializable("conn", connPara1);
                        }
                        message.setData(bundle);
                        mHandler.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (in != null) {
                            in.close();
                        }
                        if (out != null) {
                            out.close();
                        }
                        if (socket != null) {
                            socket.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        executorService.execute(thread);
    }

    public Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ICallbackBytaData callBack = (ICallbackBytaData) msg.obj;
            Bundle data = msg.getData();
            byte[] recs = data.getByteArray("rec");
            ConnPara connPara = (ConnPara) data.getSerializable("conn");
            callBack.succeed(recs, connPara);
        }
    };
}

package hexing.protocol;


import hexing.comm.CommWifi;
import hexing.icomm.ICallbackBytaData;
import hexing.icomm.ICallbackres;
import hexing.icomm.IGetdataCallback;
import hexing.model.CommPara;
import hexing.model.ConnPara;
import hexing.model.Constant;
import hexing.model.HXFramePara;
import hexing.protocol.GW376_1.GW376_1Frame;
import hexing.protocol.GWHelp.Analysis;
import hexing.tools.DecimalConversion;

/**
 * Created by hex170 on 2016/7/19.
 */
public class GetDataForPara {
    public void readData(final String afn, final String fn, final String pn, IGetdataCallback getdatacallback, String data, final ICallbackres iCallbackres) {
        HXFramePara framePara = new HXFramePara();
        CommWifi icomm = new CommWifi();
        framePara.TermiAddr = "22114433";//      1651572
        framePara.TermiAFN = afn;
        framePara.TermiFN = fn;
        if ("".equals(pn)) {
            framePara.TermiPN = "0";
        } else {
            framePara.TermiPN = pn;
        }
        framePara.TermiDATA = data;
        framePara.TermiPWD = "";
        framePara.TermiCMD = "7B";
        framePara.TermiSEQ = "6E";
        framePara.TermiTP = "";
        framePara.TermiEC = "";
        byte[] sndByt = null;
        GW376_1Frame frame = new GW376_1Frame();
        sndByt = frame.BuildTermiFrame(framePara);
        ConnPara connPara = new ConnPara();
        connPara.connfn = fn;
        connPara.connafn = afn;
        connPara.callback = iCallbackres;
        connPara.getdatacallback = getdatacallback;
        icomm.getData(sndByt, connPara, new ICallbackBytaData() {
            @Override
            public void succeed(byte[] res, ConnPara conn) {
                handleResData(res, conn);
            }
        });
    }

    public static boolean CheckSumFrame(byte[] returnbytes) {
        int iSUM = 0;
        byte SUMreturnbyte = 0;
        try {
            for (int i = 6; i < returnbytes.length - 2; i++) {
                //以下需要计算SUM
                iSUM = iSUM + (byte) returnbytes[i];
            }
            SUMreturnbyte = returnbytes[returnbytes.length - 2];
            if ((byte) iSUM != SUMreturnbyte) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void getWifiNmae(final ICallbackres callback) {
        byte[] data = Constant.GET_WIFI_NAME;
        CommWifi commWifi = new CommWifi();
//        commWifi.execu(data, new ICallbackBytaData() {
//            @Override
//            public void succeed(byte[] res) {
//                String str = DecimalConversion.byteToHexStr(res);
//                callback.succeed(str, res.length, "", "");
////                handleResData(res, callback, "", "");
//            }
//        });
        commWifi.getData(data, null, new ICallbackBytaData() {
            @Override
            public void succeed(byte[] res, ConnPara connPara) {
                String str = DecimalConversion.byteToHexStr(res);
                callback.succeed(str, null);
//                handleResData(res, callback, "", "");
            }
        });
    }

    //查询wifi密码
    public void getWifi(final ICallbackres callback) {
        byte[] data = Constant.GET_WIFI;
        CommWifi commWifi = new CommWifi();
        commWifi.getData(data, null, new ICallbackBytaData() {
            @Override
            public void succeed(byte[] res, ConnPara connPara) {
                String str = DecimalConversion.byteToHexStr(res);
                callback.succeed(str, null);
            }
        });

    }

    public void setWifiName(String data, final ICallbackres callback) {
        GW376_1Frame frame = new GW376_1Frame();
        byte[] data1 = Constant.SET_WIFI_NAME1;
        String s1 = DecimalConversion.byteToHexStr(data1);
        byte[] data2 = Constant.SET_WIFI_NAME2;
        String s2 = DecimalConversion.byteToHexStr(data2);
        String s3 = frame.GetSumCS(s2 + data);
        String s4 = s1 + s2 + data + s3 + "16";
        CommWifi commWifi = new CommWifi();
        byte[] bytes = DecimalConversion.convertHexToImageBytes(s4);
        commWifi.getData(bytes, null, new ICallbackBytaData() {
            @Override
            public void succeed(byte[] res, ConnPara connPara) {
                String str = DecimalConversion.byteToHexStr(res);
                callback.succeed(str, null);
            }
        });
    }

    //    设置wifi密码
    public void setWifi(String data, final ICallbackres callback) {
        GW376_1Frame frame = new GW376_1Frame();
        byte[] data1 = Constant.SET_WIFI1;
        String s1 = DecimalConversion.byteToHexStr(data1);
        byte[] data2 = Constant.SET_WIFI2;
        String s2 = DecimalConversion.byteToHexStr(data2);
        String s3 = frame.GetSumCS(s2 + data);
        String s4 = s1 + s2 + data + s3 + "16";
        CommWifi commWifi = new CommWifi();
        byte[] bytes = DecimalConversion.convertHexToImageBytes(s4);
        commWifi.getData(bytes, null, new ICallbackBytaData() {
            @Override
            public void succeed(byte[] res, ConnPara connPara) {
                String str = DecimalConversion.byteToHexStr(res);
                callback.succeed(str, null);
            }
        });
    }

    //处理返回数据
    private void handleResData(byte[] recByt, ConnPara connPara) {
        ICallbackres iCallbackres = connPara.callback;
//        String afn = connPara.connafn;
//        String fn = connPara.connfn;
        GW376_1Frame frame = new GW376_1Frame();
        String recstr = "";
        int pot = 0;
        int i = 0;
        int dlen = 0;
        String Recdata = "";
        try {
            boolean checksum = CheckSumFrame(recByt);
            if (!checksum) {
                //paraModel.ErrTxt = MessageEr("Er5");//返回错误代码，校验错误
            }
            if ((recByt[0] == 104) && (recByt[recByt.length - 1] == 22)) {
                while (i < recByt.length) {
                    if ((recByt[i] & 0xff) == 104) {
//                    dlen = (((recByt[i + 2] & 0xff) * 0xff + (recByt[i + 1] & 0xff)) >> 2) - 8 - 4;      //数据区长度
                        String string = Integer.toHexString(recByt[i + 2] & 0xff) + DecimalConversion.padLeft1(Integer.toHexString(recByt[i + 1] & 0xff), 2, "0");
                        String k5 = DecimalConversion.hex16tobin(string);
                        dlen = Integer.parseInt(k5.substring(0, k5.length() - 2), 2) - 12;
                        pot = pot + (dlen + i + 20);                                 //后续帧的起始68H
                        if (dlen <= 0) {
                            iCallbackres.succeed(recstr, connPara);
                            return;
                        }
                        for (int j = i + 18; j < dlen + i + 18; j++) //读写取数 从数据单元开始
                        {
                            byte k = recByt[j];
                            byte k1 = (byte) (recByt[j] & 0xff);
                            Recdata = Recdata + frame.padLeft(Integer.toHexString((recByt[j] & 0xff)), 2, '0');
                        }
                        //判断是否有后续帧  SEQ=recByt[i+13]
                        String er2 = frame.padLeft(Integer.toBinaryString((recByt[i + 13] & 0xff)), 8, '0').substring(1, 3);//Convert.ToString(recByt[i + 13], 2).PadLeft(8,'0').substring(1, 3);
                        if (er2 == "00")//多帧：中间帧
                        {
                            if (frame.padLeft(Integer.toHexString((recByt[i + 12] & 0xff)), 2, '0') == "0E")//事件记录  用“；”分割开
                                recstr = recstr + Recdata + ";";
                            else
                                recstr = recstr + Recdata;
                            //goto rep;
                            i = pot;
                        } else if (er2 == "10")//多帧：第1帧，有后续帧。
                        {
                            if (frame.padLeft(Integer.toHexString((recByt[i + 12] & 0xff)), 2, '0') == "0E")//事件记录  用“；”分割开
                                recstr = recstr + Recdata + ";";
                            else
                                recstr = recstr + Recdata;
                            //goto rep;
                            i = pot;
                        } else if (er2 == "01")//多帧：结束帧
                        {
                            if (frame.padLeft(Integer.toHexString((recByt[i + 12] & 0xff)), 2, '0') == "0E")//事件记录  用“；”分割开
                                recstr = recstr + Recdata + ";";
                            else
                                recstr = recstr + Recdata;
                            break;
                        } else {
                            if (frame.padLeft(Integer.toHexString((recByt[i + 12] & 0xff)), 2, '0') == "0E")//事件记录  用“；”分割开
                                recstr = recstr + Recdata + ";";
                            else
                                recstr = recstr + Recdata;
                            break;
                        }
                    }
                }
            }
        } catch (Exception E) {
            iCallbackres.succeed(null, connPara);
        }
        iCallbackres.succeed(recstr, connPara);
    }


    public void setparam(final String afn, final String fn, String data, final ICallbackres iCallbackres) {
        CommWifi icomm = new CommWifi();
        CommPara commpara = new CommPara();
        commpara.ip = Constant.HOST;
        commpara.port = Constant.PORT;
        HXFramePara framePara = new HXFramePara();
        framePara.TermiAddr = "22114433";//      1651572
        framePara.TermiAFN = afn;
        framePara.TermiFN = fn;
        framePara.TermiPN = "0";
        framePara.TermiDATA = data;
        framePara.TermiPWD = Analysis.resverstr(Constant.PASSWORD);
        framePara.TermiCMD = "7B";
        framePara.TermiSEQ = "6E";
        framePara.TermiTP = "";
        framePara.TermiEC = "";
        GW376_1Frame frame = new GW376_1Frame();
        byte[] sndByt = frame.BuildTermiFrame(framePara);
        ConnPara connPara = new ConnPara();
        connPara.connfn = fn;
        connPara.connafn = afn;
        connPara.callback = iCallbackres;
        icomm.getData(sndByt, connPara, new ICallbackBytaData() {
            @Override
            public void succeed(byte[] res, ConnPara conn) {
                handleResData(res, conn);
            }
        });
    }
}

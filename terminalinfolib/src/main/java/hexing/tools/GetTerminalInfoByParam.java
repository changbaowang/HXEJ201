package hexing.tools;

import android.content.Context;
import android.widget.Toast;

import com.example.terminalinfolib.R;

import java.util.ArrayList;
import java.util.List;

import hexing.icomm.ICallbackres;
import hexing.icomm.IGetdataCallback;
import hexing.model.ConnPara;
import hexing.model.Constant;
import hexing.protocol.GWHelp.Analysis;
import hexing.protocol.GWHelp.AnalysisType;
import hexing.protocol.GetDataForPara;

/**
 * Created by hex170 on 2016/7/27.
 * 对终端数据设置读取工具类
 */
public class GetTerminalInfoByParam {
    private GetDataForPara getDataForPara;
    private List<String> data = new ArrayList<String>();//存放解析后的数据
    private AnalysisType Display;
    private Context context;
    private String mSucceedStr = "";
    private String mNoDataStr = "";
    private String mFailedStr = "";
    private String mSpare = "";

    public enum MeterType {
        delete, add
    }

    public enum WifiType {
        getWifiPAS, setWifiPAS, getWifiName, setWifiName
    }

    public GetTerminalInfoByParam(Context context) {
        this.context = context;
        mSucceedStr = context.getResources().getString(R.string.kSetSuccess);
        mNoDataStr = context.getResources().getString(R.string.kInvalidData);
        mFailedStr = context.getResources().getString(R.string.kSettingFailed);
        mSpare = context.getResources().getString(R.string.kSpare);

    }

    public void resetting(final String nfc, final String fn) {
        getDataForPara = new GetDataForPara();
        String password = Constant.PASSWORD;
        getDataForPara.readData(nfc, fn, "", null, password, new ICallbackres() {
            @Override
            public void succeed(String res, ConnPara connPara) {
                String fn = connPara.getConnfn();
                int fntemp = Integer.parseInt(fn);
                switch (fntemp) {
                    case 1:
                        Toast.makeText(context, mSucceedStr, Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(context, mSucceedStr, Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(context, mSucceedStr, Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(context, mNoDataStr, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //处理表的增加,删除,修改
    public void dealMeter(MeterType type, List<String> setdata, final ICallbackres callbackres) {
        getDataForPara = new GetDataForPara();
        try {
            switch (type) {
                case delete:
                    delMeter(setdata, callbackres);
                    break;
                case add:
                    addMeter(setdata, callbackres);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void set(final String nfc, final String fn, List<String> setdata, final ICallbackres callbackres) {
        getDataForPara = new GetDataForPara();
        Display = new AnalysisType();
        int fntemp = Integer.parseInt(fn);
        try {
            if ("04".equals(nfc) && setdata != null && setdata.size() > 0) {
                switch (fntemp) {
                    case 3:
                        setF3(setdata, callbackres);
                        break;
                    case 7:
                        setF7(setdata, callbackres);
                        break;
                    case 230:
                        setF230(setdata, callbackres);
                        break;
                    case 237:
                        setF237(setdata, callbackres);
                        break;
                    case 245:
                        setF245(setdata, callbackres);
                        break;
                }
            } else if ("05".equals(nfc) && setdata != null && setdata.size() > 0) {
                switch (fntemp) {
                    case 1:
                        set05F1(setdata, callbackres);
                        break;
                    case 2:
                        set05F2(callbackres);
                        break;
                    case 3:
                        set05F3(setdata, callbackres);
                        break;
                    case 31:
                        set05F31(setdata, callbackres);//传入数据格式未定，待处理
                        break;
                }
            } else {
                Toast.makeText(context, context.getResources().getString(R.string.kParameterError), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void get(String afn, String fn, String pn, String data, IGetdataCallback callback) {
        getDataForPara = new GetDataForPara();
        Display = new AnalysisType();
        try {
            getDataForPara.readData(afn, fn, pn, callback, data, new ICallbackres() {
                @Override
                public void succeed(String res, ConnPara connPara) {
                    String fn = connPara.getConnfn();
                    String afn = connPara.getConnafn();
                    IGetdataCallback callback = connPara.getGetdatacallback();
                    if (callback != null) {
                        int fntemp = Integer.parseInt(fn);
                        if ("0C".equals(afn) && res.length() > 0) {
                            switch (fntemp) {
                                case 2:
                                    pullF2(res, callback);
                                    break;
                                case 11:
                                    pullF11(res, callback);
                                    break;
                                case 25:
                                    pullF25(res, callback);
                                    break;
                                case 49:
                                    pullF49(res, callback);
                                    break;
                                case 129:
                                    pullF129(res, callback);
                                    break;
                                case 246:
                                    pullF246(res, callback);
                                    break;
                                case 247:
                                    pullF247(res, callback);
                                    break;
                                case 250:
                                    pullF250(res, callback);
                                    break;
                                case 251:
                                    pullF251(res, callback);
                                    break;
                                case 252:
                                    pullF252(res, callback);
                                    break;
                                case 253:
                                    pullF253(res, callback);
                                    break;
                            }
                        } else if ("0A".equals(afn) && res.length() > 0) {
                            switch (fntemp) {
                                case 3:
                                    pullF3(res, callback);
                                    break;
                                case 7:
                                    pullF7(res, callback);
                                    break;
                                case 10:
                                    pullF10(res, callback);
                                    break;
                                case 89:
                                    pullF89(res, callback);
                                    break;
                                case 150:
                                    pullF150(res, callback);
                                    break;
                                case 230:
                                    pullF230(res, callback);
                                    break;
                                case 237:
                                    pullF237(res, callback);
                                    break;
                                case 245:
                                    pullF245(res, callback);
                                    break;
                            }
                        } else if ("09".equals(afn) && res.length() > 0) {
                            switch (fntemp) {
                                case 1:
                                    pull09F1(res, callback);
                                    break;
                                case 10:
                                    pull09F10(res, callback);
                                    break;
                                case 240:
                                    pullF240(res, callback);
                                    break;
                            }
                        } else if ("0D".equals(afn) && res.length() > 0) {
                            switch (fntemp) {
                                case 161:
                                    pullF161(res, callback);
                                    break;
                            }
                        } else if ("C1".equals(afn) && res.length() > 0) {
                            switch (fntemp) {
                                case 21:
                                    pullF21(res, callback);
                                    break;
                            }
                        } else {
                            Toast.makeText(context, mNoDataStr, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * wifi设置方法
     *
     * @param type     =0 表示读取wifi密码，i=1 表示设置wifi密码，i=2表示读取wifi名称，i=3，表示设置wifi名称
     * @param strdata  设置时输入的数据
     * @param callback 成功状态的返回
     */
    public void wifi(WifiType type, String strdata, final IGetdataCallback callback) {
        getDataForPara = new GetDataForPara();
        data.clear();
        try {
            switch (type) {
                case getWifiPAS:
                    getWifiPAS(callback);
                    break;
                case setWifiPAS:
                    setWifiPAS(strdata);
                    break;
                case getWifiName:
                    getWifiName(callback);
                    break;
                case setWifiName:
                    setWifiName(strdata);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //解析终端逻辑地址
    private void pullF89(String res, IGetdataCallback callback) {
        List<String> data = new ArrayList<>();
        String temp = "";
        try {
            if (res.length() > 7) {
                temp = Analysis.resverstr(res.substring(0, 4)) + Analysis.resverstr(res.substring(4, 8));
            }
            data.add(temp);
        } catch (Exception e) {
            e.printStackTrace();
            data.clear();
            callback.succeed(data);
        }
        callback.succeed(data);
    }

    private void pull09F10(String res, IGetdataCallback callback) {
        List<String> data = new ArrayList<>();
        try {
            data.add(res.substring(0, 12));
            Display.optType = 0;
            Display.DataType = "ASCII";
            Display.DataLength = 6;
            Display.Data = res.substring(12, 16);
            data.add(Analysis.Parser(Display));
            Display.Data = res.substring(16, 20);
            data.add(Analysis.Parser(Display));
            Display.DataType = "A20";
            Display.Data = res.substring(20, 26);
            data.add(Analysis.Parser(Display));
            data.add(res.substring(26, 30));
        } catch (Exception e) {
            e.printStackTrace();
            data.clear();
            callback.succeed(data);
        }

        callback.succeed(data);
    }

    private void pullF49(String res, IGetdataCallback callback) {
        List<String> data = new ArrayList<>();
        try {
            Display.optType = 0;
            Display.DataType = "A5";
            Display.DataLength = 6;
            for (int i = 0; i < res.length() / 4; i++) {
                Display.Data = res.substring(i * 4, i * 4 + 4);
                data.add(Analysis.Parser(Display));
            }
        } catch (Exception e) {
            e.printStackTrace();
            data.clear();
            callback.succeed(data);
        }
        callback.succeed(data);
    }

    private void pullF150(String res, IGetdataCallback callback) {
        data.clear();
        try {
            if (res.length() == 1024) {
                for (int i = 0; i < 2; i++) {
                    String temp = "";
                    int t = 0;
                    for (int j = 0; j < res.length() / 2; j = j + 2) {
                        String f150param1 = DecimalConversion.hex16tobin(res.substring(256 * i + j, 256 * i + j + 2));
                        String f150param2 = "";
                        for (int l = 0; l < f150param1.length(); l++) {
                            f150param2 = f150param1.substring(l, l + 1) + f150param2;
                        }
                        for (int k = 0; k < f150param2.length(); k++) {
                            if ("1".equals(f150param2.substring(k, k + 1))) {
                                t = t + 1;
                                int numStr = ((256 * i + j) * 4 + k);
                                String numStr1 = Analysis.resverstr(DecimalConversion.padLeft1(Integer.toHexString(numStr), 4, "0"));
                                temp = temp + numStr1;
                            }
                        }
                    }
                    if ("".equals(temp)) {
                        temp = "0";

                    } else {
                        String num = Analysis.resverstr(DecimalConversion.padLeft1(t + "", 4, "0"));
                        temp = num + temp;
                    }
                    data.add(temp);
                }
                callback.succeed(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
            data.clear();
            callback.succeed(data);
        }
    }

    //查询    wifi名称
    private void getWifiName(final IGetdataCallback callback) {
        getDataForPara.getWifiNmae(new ICallbackres() {
            @Override
            public void succeed(String res, ConnPara connPara) {
                if (res.length() < 120) {
                    Toast.makeText(context, mNoDataStr, Toast.LENGTH_SHORT).show();
                    return;
                }
                String temp = res.substring(52, 116);
                for (int k = 0; k < temp.length(); k = k + 2) {
                    int i1 = Integer.parseInt(temp.substring(k, k + 2), 16);
                    if (i1 == 0) {
                        data.add("0");
                    } else {
                        char temp2 = (char) i1;
                        data.add(temp2 + "");
                    }
                }
                callback.succeed(data);
            }
        });

    }

    //设置    wifi名称
    private void setWifiName(String data) {
        String temp = "";
        String temp1 = "";
        char[] chars = data.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            int k = (int) c;
            String s = Integer.toHexString(k);
            if (s.length() < 2) {
                temp = temp + "0" + s;
                continue;
            }
            temp = temp + s;
        }
        temp1 = temp;
        for (int j = 0; j < 32 - (temp.length() / 2); j++) {
            temp1 = temp1 + "00";
        }
        getDataForPara.setWifiName(temp1, new ICallbackres() {
            @Override
            public void succeed(String res, ConnPara connPara) {
                if (res.length() > 8) {
                    int i = Integer.parseInt(res.substring(res.length() - 6, res.length() - 4), 16);
                    if (i == 0) {
                        Toast.makeText(context, mSucceedStr, Toast.LENGTH_SHORT).show();
                    } else if (i == 1) {
                        Toast.makeText(context, mFailedStr, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, mNoDataStr, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    //查询    wifi密码
    private void getWifiPAS(final IGetdataCallback callback) {
        getDataForPara.getWifi(new ICallbackres() {
            @Override
            public void succeed(String res, ConnPara connPara) {
                if (res.length() < 184) {
                    Toast.makeText(context, mNoDataStr, Toast.LENGTH_SHORT).show();
                    return;
                }
                String temp = res.substring(52, 180);
                for (int k = 0; k < temp.length(); k = k + 2) {
                    int i1 = Integer.parseInt(temp.substring(k, k + 2), 16);
                    if (i1 == 0) {
                        data.add("0");
                    } else {
                        char temp2 = (char) i1;
                        data.add(temp2 + "");
                    }
                }
                callback.succeed(data);
            }
        });

    }

    //设置    wifi密码
    private void setWifiPAS(String data) {
        String temp = "";
        String temp1 = "";
        if (data.length() < 8) {
            Toast.makeText(context, context.getResources().getString(R.string.kPasswordLengthAtLeast8), Toast.LENGTH_SHORT).show();
            return;
        }
        char[] chars = data.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            int k = (int) c;
            String s = Integer.toHexString(k);
            if (s.length() < 2) {
                temp = temp + "0" + s;
                continue;
            }
            temp = temp + s;
        }
        temp1 = temp;
        for (int j = 0; j < 64 - (temp.length() / 2); j++) {
            temp1 = temp1 + "00";
        }
        getDataForPara.setWifi(temp1, new ICallbackres() {
            @Override
            public void succeed(String res, ConnPara connPara) {
                if (res.length() > 8) {
                    int i = Integer.parseInt(res.substring(res.length() - 6, res.length() - 4), 16);
                    if (i == 0) {
                        Toast.makeText(context, mSucceedStr, Toast.LENGTH_SHORT).show();
                    } else if (i == 1) {
                        Toast.makeText(context, mFailedStr, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, mNoDataStr, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    //设置    遥控跳闸
    // NFC="05"   FN = "1"
    private void set05F1(List<String> setdata, ICallbackres callbackres) {
        if (setdata.size() < 1) {
            Toast.makeText(context, mNoDataStr, Toast.LENGTH_SHORT).show();
            return;
        }
        String s2 = setdata.get(0);
        ;
        getDataForPara.setparam("05", "1", s2, callbackres);
    }

    //设置    允许合闸
    // NFC="05"   FN = "2"
    private void set05F2(ICallbackres callbackres) {
        getDataForPara.setparam("05", "2", "", callbackres);
    }

    //设置    遥控合闸
    // NFC="05"   FN = "3"
    private void set05F3(List<String> setdata, ICallbackres callbackres) {
        if (setdata.size() < 1) {
            Toast.makeText(context, mNoDataStr, Toast.LENGTH_SHORT).show();
            return;
        }
        String s2 = setdata.get(0);
        getDataForPara.setparam("05", "3", s2, callbackres);
    }

    //设置    对时
    // NFC="05"   FN = "31"
    private void set05F31(List<String> setdata, ICallbackres callbackres) {
        AnalysisType type = new AnalysisType();
        type.Data = setdata.get(0);
        type.DataLength = 7;
        type.DataType = "A1";
        type.optType = 1;
        String s2 = Analysis.Parser(type);
        getDataForPara.setparam("05", "31", s2, callbackres);
    }

    //解析  电能表装置配置参数
    // NFC="0A"   FN = "10"
    private void pullF10(String res, IGetdataCallback callback) {
        data.clear();
        try {
            int num = Integer.parseInt(Analysis.resverstr(res.substring(0, 4)), 16);
            data.add(num + "");
            for (int i = 0; i < num; i++) {
                data.add(Integer.parseInt(Analysis.resverstr(res.substring(i * 54 + 4, i * 54 + 8)), 16) + "");
                data.add(Integer.parseInt(Analysis.resverstr(res.substring(i * 54 + 8, i * 54 + 12)), 16) + "");
                //  分解通信速率,端口号
                String temp = DecimalConversion.hex16tobin(res.substring(i * 54 + 12, i * 54 + 14));
                int rate = Integer.parseInt(temp.substring(0, 3), 2);
                switch (rate) {
                    case 1:
                        data.add(600 + "");
                        break;
                    case 2:
                        data.add(1200 + "");
                        break;
                    case 3:
                        data.add(2400 + "");
                        break;
                    case 4:
                        data.add(4800 + "");
                        break;
                    case 5:
                        data.add(7200 + "");
                        break;
                    case 6:
                        data.add(9600 + "");
                        break;
                    case 7:
                        data.add(19200 + "");
                        break;
                    default:
                        data.add(0 + "");
                }
//            data.add(Integer.parseInt(temp.substring(3, temp.length()), 2) + "");
                int type = Integer.parseInt(temp.substring(3, temp.length()), 2);
                if (2 == type) {
                    data.add("RS485");
                } else if (31 == type) {
                    data.add("RF/PLC");
                } else {
                    data.add(context.getResources().getString(R.string.kSpare));
                }
                //规约
                int rules = Integer.parseInt(res.substring(i * 54 + 14, i * 54 + 16), 16);
                switch (rules) {
                    case 1:
                        data.add("DL/T 645—1997");
                        break;
                    case 2:
                        data.add("AC");
                        break;
                    case 30:
                        data.add("DL/T 645—2007");
                        break;
                    case 50:
                        data.add("DLMS");
                        break;
                    case 52:
                        data.add("HX645");
                        break;
                    case 53:
                        data.add("ABNT");
                        break;
                    case 54:
                        data.add("MODBUS");
                        break;
                    case 55:
                        data.add("21");
                        break;
                    case 56:
                        data.add("ANSI");
                        break;
                    case 80:
                        data.add("ACTARIS-DLMS");
                        break;
                    case 90:
                        data.add("MK6E-EDMI");
                        break;
                    case 100:
                        data.add("采集器HX645");
                        break;
                    case 200:
                        data.add("级联采集器");
                        break;
                    default:
                        data.add("其他");
                }
                data.add(Analysis.resverstr(res.substring(i * 54 + 16, i * 54 + 28)));
                data.add(Analysis.resverstr(res.substring(i * 54 + 28, i * 54 + 40)));
                data.add(Integer.parseInt(DecimalConversion.hex16tobin(res.substring(i * 54 + 40, i * 54 + 42)).substring(0, 5), 2) + "");
                int f10param1 = Integer.parseInt(DecimalConversion.hex16tobin(res.substring(i * 54 + 42, i * 54 + 44)).substring(0, 2), 2) + 1;
                int f10param2 = Integer.parseInt(DecimalConversion.hex16tobin(res.substring(i * 54 + 42, i * 54 + 44)).substring(2, 4), 2) + 4;
                data.add(f10param2 + "," + f10param1);
                data.add(Analysis.resverstr(res.substring(i * 54 + 44, i * 54 + 56)));
                int f10param3 = Integer.parseInt(DecimalConversion.hex16tobin(res.substring(i * 54 + 56, i * 54 + 58)).substring(0, 4), 2);
                int f10param4 = Integer.parseInt(DecimalConversion.hex16tobin(res.substring(i * 54 + 56, i * 54 + 58)).substring(4, 7), 2);
                data.add(f10param4 + "," + f10param3);
            }
        } catch (Exception e) {
            e.printStackTrace();
//            data.clear();
//            callback.succeed(data);
        }
        callback.succeed(data);
    }

    //删除表计
    private void delMeter(List<String> setdata, ICallbackres callbackres) {
        String s2 = "0100";
        s2 = s2 + Analysis.resverstr(DecimalConversion.padLeft1(setdata.get(0), 4, "0"));
        s2 = s2 + "00003F32160100004001000000000000000500000000000031";
        getDataForPara.setparam("04", "10", s2, callbackres);

    }

    //添加表计-----修改表计
    private void addMeter(List<String> setdata, ICallbackres callbackres) {
//        String s2 = "0100020000003F32160100004001000000000000000500000000000031";
        String s2 = "0100";//添加一个
        //序号，测量点号
        s2 = s2 + Analysis.resverstr(DecimalConversion.padLeft1(Integer.toHexString(Integer.parseInt(setdata.get(0))), 4, "0"));
        s2 = s2 + Analysis.resverstr(DecimalConversion.padLeft1(Integer.toHexString(Integer.parseInt(setdata.get(0))), 4, "0"));
        //端口号，通讯速率
        int port = Integer.parseInt(setdata.get(1));
        String portStr = "";
        if (port == 0) {
            portStr = "00010";
        }
        if (port == 1) {
            portStr = "11111";
        }
        String rate2 = Integer.toBinaryString(Integer.parseInt(setdata.get(2))) + portStr;
        s2 = s2 + DecimalConversion.txttohex16(rate2);
        //通讯协议类型  默认为32 即DLMS协议
        s2 = s2 + setdata.get(4);
        //通讯地址
        String address = setdata.get(3);
        if (address.length() > 12) {
            address = address.substring(0, 12);
        }
        s2 = s2 + Analysis.resverstr(DecimalConversion.padLeft1(address, 12, "0"));
        s2 = s2 + "000000000000000500000000000031";
        getDataForPara.setparam("04", "10", s2, callbackres);

    }

    //解析  下行通讯模块内部测试软件版本号
    // NFC="C1"   FN = "21"
    private void pullF21(String res, IGetdataCallback callback) {
        data.clear();
        try {
            int f21param1 = Integer.parseInt(res.substring(0, 2), 16);
            data.add(f21param1 + "");
            Display.optType = 0;
            Display.DataType = "ASCII";
            Display.DataLength = 6;
            Display.Data = res.substring(2, f21param1 + 2);
            data.add(Analysis.Parser(Display));
        } catch (Exception e) {
            e.printStackTrace();
            data.clear();
            callback.succeed(data);
        }
        callback.succeed(data);
    }

    //解析  日冻结正向有功电能示值
    // NFC="0D"   FN = "161"
    private void pullF161(String res, IGetdataCallback callback) {
        data.clear();
        try {
            int f161param2 = Integer.parseInt(res.substring(16, 18), 16); //判断有多少种费率
            int f161param3 = 1;   //每条数据的长度
            switch (f161param2) {
                case 1:
                    f161param3 = 76 - 30;      //每个费率5个字节
                    break;
                case 2:
                    f161param3 = 76 - 20;
                    break;
                case 3:
                    f161param3 = 76 - 10;
                    break;
                case 4:
                    f161param3 = 76;
                    break;
                default:
                    f161param3 = 76;
            }
            int length = res.length() + 8;
            for (int i = 0; i < length / f161param3; i++) {
                String temp = res.substring(i * 76, i * 76 + 68);
                Display.optType = 0;
                Display.DataType = "A20";
                Display.DataLength = 6;
                Display.Data = temp.substring(0, 6);
                data.add(Analysis.Parser(Display));
                Display.DataType = "A15";
                Display.Data = temp.substring(6, 16);
                data.add(Analysis.Parser(Display));
                int f161param1 = Integer.parseInt(temp.substring(16, 18), 16);
                data.add(f161param1 + "");
                Display.DataType = "A14";
                Display.Data = temp.substring(18, 28);
                data.add(Analysis.Parser(Display));
                for (int j = 0; j < f161param1; j++) {
                    String strtemp = temp.substring(j * 10 + 28, j * 10 + 38);
                    if ("eeeeeeeeee".equals(strtemp)) {
                        data.add("ERROR DATA");
                    } else {
                        Display.Data = temp.substring(j * 10 + 28, j * 10 + 38);
                        data.add(Analysis.Parser(Display));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            data.clear();
            callback.succeed(data);
        }
        callback.succeed(data);
    }

    //解析  终端当前状态
    // NFC="09"   FN = "240"
    private void pullF240(String res, IGetdataCallback callback) {
        data.clear();
        try {
            int f240param1 = Integer.parseInt(res.substring(0, 1), 16);
            switch (f240param1) {
                case 0:
                    data.add(context.getResources().getString(R.string.kConnectedAndTransmission));
                    break;
                case 1:
                    data.add(context.getResources().getString(R.string.kResetModule));
                    break;
                case 2:
                    data.add(context.getResources().getString(R.string.kBeingRegisteredNetwork));
                    break;
                case 3:
                    data.add(context.getResources().getString(R.string.kCanConnectMainStation));
                    break;
                case 4:
                    data.add(context.getResources().getString(R.string.kLandingOrHeartbeat));
                    break;
                case 5:
                    data.add(context.getResources().getString(R.string.kOnLineOrInPassiveState));
                    break;
                default:
                    data.add(mSpare);
            }
            int f240param2 = Integer.parseInt(res.substring(1, 2), 16);
            switch (f240param2) {
                case 0:
                    data.add(context.getResources().getString(R.string.kModuleInteractionIsNormal));
                    break;
                case 1:
                    data.add(context.getResources().getString(R.string.kSearchTable));
                    break;
                case 2:
                    data.add(context.getResources().getString(R.string.kCantRecognizeModule));
                    break;
                case 3:
                    data.add(context.getResources().getString(R.string.kNetworkIn));
                    break;
                default:
                    data.add(mSpare);
            }
            int f240param3 = Integer.parseInt(res.substring(2, 3), 16);
            switch (f240param3) {
                case 0:
                    data.add(context.getResources().getString(R.string.kCopyTheCarrierSuccessfullyAndEnd));
                    break;
                case 1:
                    data.add(context.getResources().getString(R.string.kBeingMeterReading));
                    break;
                case 2:
                    data.add(context.getResources().getString(R.string.kMeterReadingFailure));
                    break;
                default:
                    data.add(mSpare);
            }
            int f240param4 = Integer.parseInt(res.substring(3, 4), 16);
            switch (f240param4) {
                case 0:
                    data.add(context.getResources().getString(R.string.kMeterReadingSuccessAndEnd));
                    break;
                case 1:
                    data.add(context.getResources().getString(R.string.kBeingMeterReading));
                    break;
                case 2:
                    data.add(context.getResources().getString(R.string.kMeterReadingFailure2));
                    break;
                default:
                    data.add(mSpare);
            }
        } catch (Exception e) {
            e.printStackTrace();
            data.clear();
            callback.succeed(data);
        }
        callback.succeed(data);
    }

    //解析  终端版本信息
    // NFC="09"   FN = "1"
    private void pull09F1(String res, IGetdataCallback callback) {
        data.clear();
        try {
            Display.optType = 0;
            Display.DataType = "ASCII";
            Display.DataLength = 6;
            Display.Data = res.substring(0, 8);
            data.add(Analysis.Parser(Display));
            Display.Data = res.substring(8, 24);
            data.add(Analysis.Parser(Display));
            Display.Data = res.substring(24, 32);
            data.add(Analysis.Parser(Display));
            Display.DataType = "A20";
            Display.Data = res.substring(32, 38);
            data.add(Analysis.Parser(Display));
            Display.DataType = "ASCII";
            Display.Data = res.substring(38, 60);
            data.add(Analysis.Parser(Display));
            Display.Data = res.substring(60, 68);
            data.add(Analysis.Parser(Display));
            Display.Data = res.substring(68, 76);
            data.add(Analysis.Parser(Display));
            Display.DataType = "A20";
            Display.Data = res.substring(76, 82);
            data.add(Analysis.Parser(Display));
        } catch (Exception e) {
            e.printStackTrace();
            data.clear();
            callback.succeed(data);
        }
        callback.succeed(data);
    }

    //解析  终端电量底度设置
    // NFC="0A"   FN = "237"
    private void pullF237(String res, IGetdataCallback callback) {
        data.clear();
        try {
            Display.optType = 0;
            Display.DataType = "A14";
            Display.DataLength = 6;
            for (int i = 0; i < 6; i++) {
                Display.Data = res.substring(i * 10, i * 10 + 10);
                data.add(Analysis.Parser(Display));
            }
            for (int j = 0; j < 12; j++) {
                Display.Data = res.substring(60 + j * 8, j * 8 + 68);
                Display.DataType = "A11";
                data.add(Analysis.Parser(Display));
            }
        } catch (Exception e) {
            e.printStackTrace();
            data.clear();
            callback.succeed(data);
        }
        callback.succeed(data);
    }

    //设置    终端电量底度设置
    // NFC="04"   FN = "237"
    private void setF237(List<String> setdata, ICallbackres callbackres) {
        if (setdata.size() < 18) {
            Toast.makeText(context, mNoDataStr, Toast.LENGTH_SHORT).show();
        }
        String s2 = "";
        Display.optType = 1;
        Display.DataType = "A14";
        Display.DataLength = 6;
        for (int i = 0; i < 6; i++) {
            Display.Data = setdata.get(i);
            s2 = s2 + Analysis.Parser(Display);
        }
        Display.DataType = "A11";
        for (int j = 6; j < 18; j++) {
            Display.Data = setdata.get(j);
            s2 = s2 + Analysis.Parser(Display);
        }
        getDataForPara.setparam("04", "237", s2, callbackres);
    }

    //解析  变压器监控终端状态量输入参数
    // NFC="0A"   FN = "230"
    private void pullF230(String res, IGetdataCallback callback) {
        data.clear();
        try {
            int f230param1 = Integer.parseInt(res.substring(0, 2), 16);
            String temp1 = context.getResources().getString(R.string.kNormallyClosedContact);
            String temp2 = context.getResources().getString(R.string.kNormallyOpenContact);
            String f230param2 = Analysis.judgement(f230param1, temp1, temp2);
            data.add(f230param2);
            int f230param3 = Integer.parseInt(res.substring(2, 4), 16);
            switch (f230param3) {
                case 1:
                    data.add(context.getResources().getString(R.string.kInternalTemperatureOfTransformer));
                    break;
                case 2:
                    data.add(context.getResources().getString(R.string.kNormallyOpenContact));
                    break;
                case 3:
                    data.add(context.getResources().getString(R.string.kLowLevelSignalDetection));
                    break;
                case 4:
                    data.add(context.getResources().getString(R.string.kHighLevelSignalDetection));
                    break;
                case 5:
                    data.add(context.getResources().getString(R.string.kTransformerInternalPressure));
                    break;
                case 6:
                    data.add(context.getResources().getString(R.string.kBreakerSwitchStatus));
                    break;
                case 7:
                    data.add(context.getResources().getString(R.string.kGateContact));
                    break;
                case 8:
                    data.add(context.getResources().getString(R.string.kLightGasAlarmSignal));
                    break;
                case 9:
                    data.add(context.getResources().getString(R.string.kPressureReleaseStatus));
                    break;
                case 10:
                    data.add(context.getResources().getString(R.string.kTheMiddleLevelDetectionSignal));
                    break;
                default:
                    data.add(context.getResources().getString(R.string.kInvalidStatus));
            }
        } catch (Exception e) {
            e.printStackTrace();
            data.clear();
            callback.succeed(data);
        }

        callback.succeed(data);
    }

    //设置    变压器监控终端状态量输入参数
    // NFC="04"   FN = "230"
    private void setF230(List<String> setdata, ICallbackres callbackres) {
        if (setdata.size() < 2) {
            Toast.makeText(context, mNoDataStr, Toast.LENGTH_SHORT).show();
        }
        ;
        String s2 = "";
        s2 = DecimalConversion.stringtoHex(Integer.toHexString(Integer.parseInt(setdata.get(0))));
        s2 = s2 + DecimalConversion.stringtoHex(Integer.toHexString(Integer.parseInt(setdata.get(1))));
        getDataForPara.setparam("04", "230", s2, callbackres);
    }

    //解析  当前终端时间
    // NFC="0C"   FN = "2"
    private void pullF2(String res, IGetdataCallback callback) {
        data.clear();
        try {
            Display.Data = res;
            Display.optType = 0;
            Display.DataType = "A1";
            Display.DataLength = 6;
            data.add(Analysis.Parser(Display));
        } catch (Exception e) {
            e.printStackTrace();
            data.clear();
            callback.succeed(data);
        }
        callback.succeed(data);
    }

    //解析  终端IP
    // NFC="0A"   FN = "7"
    private void pullF7(String res, IGetdataCallback callback) {
        data.clear();
        //a8 c0 00 00 0b 00 00 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 67 67
        try {
            int len = res.length() / 2;
            for (int i = 0; i < 2 * len; i = i + 2) {
                if (i == 2 * len - 4 || i == 34) {
                    String sub1 = res.substring(i, i + 2);
                    String sub2 = res.substring(i + 2, i + 4);
                    String temp = sub2 + sub1;
                    data.add(Integer.parseInt(temp, 16) + "");
                    i = i + 2;
                } else if (i == 40) {
                    int k = Integer.parseInt(res.substring(40, 42), 16);
                    if (k == 0) {
                        data.add("0");
                        data.add(" ");
                        int t = Integer.parseInt(res.substring(42, 44), 16);
                        if (t == 0) {
                            data.add("0");
                            data.add(" ");
                        } else {
                            data.add(t + "");
                            String pws = "";
                            for (int m = 0; m < t; m++) {
                                pws = pws + DecimalConversion.asciiToString2(res.substring(44 + 2 * m, 46 + 2 * m));
                            }
                            data.add(pws);
                        }
                    } else {
                        data.add(k + "");
                        String pws = "";
                        for (int m = 0; m < k; m++) {
                            pws = pws + DecimalConversion.asciiToString2(res.substring(42 + 2 * m, 44 + 2 * m));
                        }
                        data.add(pws);
                        int t = Integer.parseInt(res.substring((2 * k + 42), (2 * k + 44)), 16);
                        if (t == 0) {
                            data.add("0");
                            data.add(" ");
                        } else {
                            data.add(t + "");
                            String pws2 = "";
                            for (int m = 0; m < t; m++) {
                                pws2 = pws2 + DecimalConversion.asciiToString2(res.substring(44 + 2 * m + k * 2, 46 + 2 * m + k * 2));
                            }
                            data.add(pws2);
                        }
                    }
                    i = 2 * len - 6;
                } else {
                    data.add(Integer.parseInt(res.substring(i, i + 2), 16) + "");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            data.clear();
            callback.succeed(data);
        }

        callback.succeed(data);
    }

    //设置 终端IP
    // NFC="04"   FN = "7"
    private void setF7(List<String> setdata, ICallbackres callbackres) {
        String s2 = "";
        if (setdata.size() < 22) {
            Toast.makeText(context, mNoDataStr, Toast.LENGTH_SHORT).show();
            return;
        }
        for (int j = 0; j < setdata.size(); j++) {
            String s = Integer.toHexString(Integer.parseInt(setdata.get(j)));
            if (j == setdata.size() - 7 || j == setdata.size() - 1) {
                if (s.length() <= 2) {
                    s2 = s2 + DecimalConversion.stringtoHex(s);
                    s2 = s2 + "00";
                } else {
                    s2 = s2 + DecimalConversion.stringtoHex(s.substring(s.length() - 2, s.length()));
                    s2 = s2 + DecimalConversion.stringtoHex(s.substring(0, s.length() - 2));
                }
            } else if (j == setdata.size() - 5 || j == setdata.size() - 3) {
                if (setdata.get(setdata.size() - 5).equals("0") || setdata.get(setdata.size() - 3).equals("0")) {
                    s2 = s2 + DecimalConversion.stringtoHex(s.substring(0, s.length()));
                    j = j + 1;
                } else {
                    s2 = s2 + DecimalConversion.stringtoHex(s.substring(0, s.length()));
                    s2 = s2 + DecimalConversion.stringToAscii(setdata.get(j + 1));
                    j = j + 1;
                }
            } else {
                s2 = s2 + DecimalConversion.stringtoHex(s.substring(0, s.length()));
            }
        }
        getDataForPara.setparam("04", "7", s2, callbackres);
    }

    //解析  当前变压器内部温度值
    // NFC="0C"   FN = "253"
    private void pullF253(String res, IGetdataCallback callback) {
        data.clear();
        try {
            int f251param1 = Integer.parseInt(res.substring(0, 2), 16);
            switch (f251param1) {
                case 0:
                    data.add(context.getResources().getString(R.string.kNormal));
                    break;
                case 1:
                    data.add(context.getResources().getString(R.string.kTooHigh));
                    break;
                default:
                    data.add(mNoDataStr);
            }
            data.add(Integer.parseInt(Analysis.resverstr(res.substring(2, 6)), 16) + "°C");
        } catch (Exception e) {
            e.printStackTrace();
            data.clear();
            callback.succeed(data);
        }
        callback.succeed(data);
    }

    //解析  当前变压器内部气压值
    // NFC="0C"   FN = "252"
    private void pullF252(String res, IGetdataCallback callback) {
        data.clear();
        try {
            int f251param1 = Integer.parseInt(res.substring(0, 2), 16);
            switch (f251param1) {
                case 0:
                    data.add(context.getResources().getString(R.string.kNormal));
                    break;
                case 1:
                    data.add(context.getResources().getString(R.string.kTooHigh));
                    break;
                default:
                    data.add(mNoDataStr);
            }
            data.add(Integer.parseInt(Analysis.resverstr(res.substring(2, 8)), 16) + "Pa");
        } catch (Exception e) {
            e.printStackTrace();
            data.clear();
            callback.succeed(data);
        }

        callback.succeed(data);
    }

    //解析  当前变压器油位值
    // NFC="0C"   FN = "251"
    private void pullF251(String res, IGetdataCallback callback) {
        data.clear();
        int f251param1 = Integer.parseInt(res.substring(0, 2), 16);
        switch (f251param1) {
            case 0:
                data.add(context.getResources().getString(R.string.kNormal));
                break;
            case 1:
                data.add(context.getResources().getString(R.string.kTooLow));
                break;
            case 2:
                data.add(context.getResources().getString(R.string.kBelowTheMiddleLevel));
                break;
            case 3:
                data.add(context.getResources().getString(R.string.kTooHigh));
                break;
            default:
                data.add(mNoDataStr);
        }
        data.add(Integer.parseInt(Analysis.resverstr(res.substring(2, 6)), 16) + "mm");
        callback.succeed(data);
    }

    //解析  终端状态量类型及状态变位标志
    // NFC="0C"   FN = "250"
    private void pullF250(String res, IGetdataCallback callback) {
        data.clear();
        try {
            String f250param1 = DecimalConversion.hex16tobin(res.substring(0, 2));
            String f250param3 = DecimalConversion.hex16tobin(res.substring(2, 4));
            String f250param2 = "";
            String f250param4 = "";
            String separate = context.getResources().getString(R.string.kNfc0CF250_Separate);
            String close = context.getResources().getString(R.string.kNfc0CF250_Close);
            String no = context.getResources().getString(R.string.kNfc0CF250_No);
            String yes = context.getResources().getString(R.string.kNfc0CF250_Yes);
            for (int i = 0; i < f250param1.length(); i++) {
                f250param2 = f250param2 + Analysis.judgement(Integer.parseInt(f250param1.substring(i, i + 1)), separate, close);
                f250param4 = f250param4 + Analysis.judgement(Integer.parseInt(f250param3.substring(i, i + 1)), no, yes);
            }
            data.add(f250param2);
            data.add(f250param4);
            for (int j = 0; j < 8; j++) {
                int f250param5 = Integer.parseInt(res.substring(j * 2 + 4, j * 2 + 6), 16);
                switch (f250param5) {
                    case 1:
                        data.add(context.getResources().getString(R.string.kInternalTemperatureOfTransformer));
                        break;
                    case 2:
                        data.add(context.getResources().getString(R.string.kLowLevelSignalDetection));
                        break;
                    case 3:
                        data.add(context.getResources().getString(R.string.kHighLevelSignalDetection));
                        break;
                    case 4:
                        data.add(context.getResources().getString(R.string.kTransformerInternalPressure));
                        break;
                    case 5:
                        data.add(context.getResources().getString(R.string.kBreakerSwitchStatus));
                        break;
                    case 6:
                        data.add(context.getResources().getString(R.string.kGateContact));
                        break;
                    case 7:
                        data.add(context.getResources().getString(R.string.kLightGasAlarmSignal));
                        break;
                    case 8:
                        data.add(context.getResources().getString(R.string.kHeavyGasAlarmSignal));
                        break;
                    case 9:
                        data.add(context.getResources().getString(R.string.kPressureReleaseStatus));
                        break;
                    case 10:
                        data.add(context.getResources().getString(R.string.kTheMiddleLevelDetectionSignal));
                        break;
                    default:
                        data.add(context.getResources().getString(R.string.kInvalidStatus));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            data.clear();
            callback.succeed(data);
        }
        callback.succeed(data);
    }

    //解析  变压器监控(监测)终端运行状态字（外销扩展）
    // NFC="0C"   FN = "247"
    private void pullF247(String res, IGetdataCallback callback) {
        data.clear();
        try {
            String f247Param = DecimalConversion.hex16tobin(res);
            String temp1 = context.getResources().getString(R.string.kNormal);
            String temp2 = context.getResources().getString(R.string.kAbnormal);
            data.add(Analysis.judgement(Integer.parseInt(f247Param.substring(0, 1)), temp1, temp2));
            data.add(Analysis.judgement(Integer.parseInt(f247Param.substring(1, 2)), temp1, temp2));
            data.add(Analysis.judgement(Integer.parseInt(f247Param.substring(2, 3)), temp1, temp2));
            int f247Param2 = Integer.parseInt(f247Param.substring(3, 4));
            if (f247Param2 == 0) {
                data.add(context.getResources().getString(R.string.kBreak));
            } else {
                data.add(context.getResources().getString(R.string.kOnLine));
            }
            data.add(Integer.parseInt(res.substring(1, 2), 16) + "");
        } catch (Exception e) {
            e.printStackTrace();
            data.clear();
            callback.succeed(data);
        }

        callback.succeed(data);
    }

    //解析  当前断路器开关状态
    // NFC="0C"   FN = "246"
    private void pullF246(String res, IGetdataCallback callback) {
        try {
            if (res.length() != 2) {
                Toast.makeText(context, mNoDataStr, Toast.LENGTH_SHORT).show();
                return;
            }
            int i = Integer.parseInt(res, 16);
            data.clear();
            if (i == 0) {
                data.add(context.getResources().getString(R.string.kSwitchOff));
            } else {
                data.add(context.getResources().getString(R.string.kSwitchOn));
            }
        } catch (Exception e) {
            e.printStackTrace();
            data.clear();
            callback.succeed(data);
        }
        callback.succeed(data);
    }

    //解析  当前正向有功电能示值（总、费率1～M）	测量点号
    // NFC="0C"   FN = "129"
    private void pullF129(String res, IGetdataCallback callback) {
        data.clear();
        try {
            Display.Data = res.substring(0, 10);
            Display.DataType = "A15";
            Display.optType = 0;
            Display.DataLength = 6;
            data.add(Analysis.Parser(Display)); //终端抄表时间
            data.add(Integer.parseInt(res.substring(10, 12)) + ""); //费率数M
            Display.Data = res.substring(12, 22);
            Display.DataType = "A14";
            data.add(Analysis.Parser(Display)); //正向有功总电能示值
            int m = Integer.parseInt(res.substring(10, 12));
            for (int i = 0; i < m; i++) {
                String strtemp = res.substring(i * 10 + 22, i * 10 + 32);
                if ("eeeeeeeeee".equals(strtemp)) {
                    data.add("ERROR DATA");
                } else {
                    Display.Data = res.substring(i * 10 + 22, i * 10 + 32);
                    data.add(Analysis.Parser(Display)); //费率n正向有功总电能示值
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            data.clear();
            callback.succeed(data);
        }
        callback.succeed(data);
    }

    //解析  当前三相及总有/无功功率、功率因数，三相电压、电流、零序电流、视在功率(instantaneous测量点号
    // NFC="0C"   FN = "25"
    private void pullF25(String res, IGetdataCallback callback) {
        data.clear();
        try {
            Display.Data = res.substring(0, 10);  //终端抄表时间
            Display.optType = 0;
            Display.DataType = "A15";
            Display.DataLength = 6;
            data.add(Analysis.Parser(Display));
            Display.DataType = "A9";
            Display.Data = res.substring(10, 16); //当前总有功功率
            data.add(Analysis.Parser(Display) + "kW");
            Display.Data = res.substring(16, 22); //当前A相有功功率
            data.add(Analysis.Parser(Display) + "kW");
            Display.Data = res.substring(22, 28); //当前B相有功功率
            data.add(Analysis.Parser(Display) + "kW");
            Display.Data = res.substring(28, 34); //当前C相有功功率
            data.add(Analysis.Parser(Display) + "kW");
            Display.Data = res.substring(34, 40); //当前总无功功率
            data.add(Analysis.Parser(Display) + "kvar");
            Display.Data = res.substring(40, 46); //当前A相无功功率
            data.add(Analysis.Parser(Display) + "kvar");
            Display.Data = res.substring(46, 52); //当前B相无功功率
            data.add(Analysis.Parser(Display) + "kvar");
            Display.Data = res.substring(52, 58); //当前C相无功功率
            data.add(Analysis.Parser(Display) + "kvar");
            Display.DataType = "A5";
            Display.Data = res.substring(58, 62); //当前总功率因数
            data.add(Analysis.Parser(Display) + "%");
            Display.Data = res.substring(62, 66); //当前A相功率因数
            data.add(Analysis.Parser(Display) + "%");
            Display.Data = res.substring(66, 70); //当前B相功率因数
            data.add(Analysis.Parser(Display) + "%");
            Display.Data = res.substring(70, 74); //当前C相功率因数
            data.add(Analysis.Parser(Display) + "%");
            Display.DataType = "A7";
            Display.Data = res.substring(74, 78); //当前A相电压
            data.add(Analysis.Parser(Display) + "V");
            Display.Data = res.substring(78, 82); //当前B相电压
            data.add(Analysis.Parser(Display) + "V");
            Display.Data = res.substring(82, 86); //当前C相电压
            data.add(Analysis.Parser(Display) + "V");
            Display.DataType = "A25";
            Display.Data = res.substring(86, 92); //当前A相电流
            data.add(Analysis.Parser(Display) + "A");
            Display.Data = res.substring(92, 98); //当前B相电流
            data.add(Analysis.Parser(Display) + "A");
            Display.Data = res.substring(98, 104); //当前C相电流
            data.add(Analysis.Parser(Display) + "A");
            Display.Data = res.substring(104, 110); //当前零序电流
            data.add(Analysis.Parser(Display) + "A");
            Display.DataType = "A9";
            Display.Data = res.substring(110, 116); //当前总视在功率
            data.add(Analysis.Parser(Display) + "kVA");
            Display.Data = res.substring(116, 122); //当前A相视在功率
            data.add(Analysis.Parser(Display) + "kVA");
            Display.Data = res.substring(122, 128); //当前B相视在功率
            data.add(Analysis.Parser(Display) + "kVA");
            Display.Data = res.substring(128, 134); //当前C相视在功率
            data.add(Analysis.Parser(Display) + "kVA");
        } catch (Exception e) {
            e.printStackTrace();
            data.clear();
            callback.succeed(data);
        }
        callback.succeed(data);
    }

    //解析   终端集中抄表状态信息  NFC="0C"   FN = "11"
    private void pullF11(String res, IGetdataCallback callback) {
        data.clear();
        try {
            int num = Integer.parseInt(res.substring(0, 2), 16);//获取有多少个模块
            data.add(num + "");
            for (int i = 0; i < num; i++) {
                data.add(Integer.parseInt(res.substring(i * 38 + 2, i * 38 + 4), 16) + "");//终端通信端口号
                data.add(Integer.parseInt(Analysis.resverstr(res.substring(i * 38 + 4, i * 38 + 8)), 16) + "");//要抄电表总数
                //当前抄表工作状态标志
                String terminal1 = DecimalConversion.hex16tobin(res.substring(i * 38 + 8, i * 38 + 10));
                int terminal2 = Integer.parseInt(terminal1.substring(terminal1.length() - 2, terminal1.length()), 2);
                switch (terminal2) {
                    case 0:
                        data.add(context.getResources().getString(R.string.kNotCompletedNotReading));
                        break;
                    case 1:
                        data.add(context.getResources().getString(R.string.kNotCompletedIsReading));
                        break;
                    case 2:
                        data.add(context.getResources().getString(R.string.kCompletedNotReading));
                        break;
                    case 3:
                        data.add(context.getResources().getString(R.string.kCompletedIsReading));
                        break;
                }
                data.add(Integer.parseInt(Analysis.resverstr(res.substring(i * 38 + 10, i * 38 + 14)), 16) + "");//抄表成功块数
                data.add(Integer.parseInt(res.substring(i * 38 + 14, i * 38 + 16), 16) + "");//抄重点表成功块数
                //抄表开始时间
                Display.Data = res.substring(i * 38 + 16, i * 38 + 28);
                Display.optType = 0;
                Display.DataType = "A1";
                Display.DataLength = 6;
                data.add(Analysis.Parser(Display));
                //抄表结束时间
                Display.Data = res.substring(i * 38 + 28, i * 38 + 40);
                data.add(Analysis.Parser(Display));
            }
        } catch (Exception e) {
            e.printStackTrace();
            data.clear();
            callback.succeed(data);
        }
        callback.succeed(data);
    }

    //解析   主站IP  NFC="0A"   FN = "3"
    private void pullF3(String res, IGetdataCallback callback) {
        try {
            for (int i = 0; i < 24; i = i + 2) {
                if (i == 8 || i == 20) {
                    String sub1 = res.substring(i, i + 2);
                    String sub2 = res.substring(i + 2, i + 4);
                    String temp = sub2 + sub1;
                    data.add(Integer.parseInt(temp, 16) + "");
                    i = i + 2;
                } else {
                    data.add(Integer.parseInt(res.substring(i, i + 2), 16) + "");
                }
            }
            String apnstr = "";
            for (int j = 24; j < 24 + (Constant.APN_LENGTH * 2); j = j + 2) {
                int i = Integer.parseInt(res.substring(j, j + 2), 16);
                if (i != 0) {
                    char b = (char) i;//int转化为ASICC
                    apnstr = apnstr + b;
                }
            }
            data.add(apnstr);
        } catch (Exception e) {
            e.printStackTrace();
            data.clear();
            callback.succeed(data);
        }
        callback.succeed(data);
    }

    //设置   主站IP  NFC="04"   FN = "3"
    private void setF3(List<String> setdata, ICallbackres callbackres) {
        //res[192, 168, 0, 0, 2020, 192, 168, 9, 27, 2122, CNMAAA]
        if (setdata.size() < 11) {
            Toast.makeText(context, mNoDataStr, Toast.LENGTH_SHORT).show();
            return;
        }
        String s2 = "";
        for (int j = 0; j < setdata.size() - 1; j++) {
            String s = Integer.toHexString(Integer.parseInt(setdata.get(j)));
            if (j == 4 || j == 9) {
                if (s.length() <= 2) {
                    s2 = s2 + DecimalConversion.stringtoHex(s);
                    s2 = s2 + "00";
                } else {
                    s2 = s2 + DecimalConversion.stringtoHex(s.substring(s.length() - 2, s.length()));
                    s2 = s2 + DecimalConversion.stringtoHex(s.substring(0, s.length() - 2));
                }
            } else {
                s2 = s2 + DecimalConversion.stringtoHex(s.substring(0, s.length()));
            }
        }
        s2 = s2 + DecimalConversion.pullAPNData(setdata);
        getDataForPara.setparam("04", "3", s2, callbackres);
    }

    //解析   GPS  NFC="0A"   FN = "245"
    private void pullF245(String res, IGetdataCallback callback) {
        data.clear();
        try {
            for (int i = 0; i < 2; i++) {
                String param1 = res.substring(i * 10, i * 10 + 2);
                String param2 = "";
                if ("00".equals(param1) && i == 0) {
//                param2 = context.getResources().getString(R.string.kEastLongitude) + ":";
                } else if ("01".equals(param1) && i == 0) {
//                param2 = context.getResources().getString(R.string.kWestLongitude) + ":";
                    param2 = "-";
                } else if ("00".equals(param1) && i == 1) {
//                param2 = context.getResources().getString(R.string.kSouthLongitude) + ":";
                } else if ("01".equals(param1) && i == 1) {
                    param2 = "-";
//                param2 = context.getResources().getString(R.string.kNorthLatitude) + ":";
                } else {
                }
                String apnstr = Analysis.resverstr(res.substring(i * 10 + 2, i * 10 + 10));
                String apn = "";
                apn = apnstr.substring(0, 4) + "." + apnstr.substring(4, 8);
                apn = param2 + DecimalConversion.delLeftZero(apn);
                data.add(apn);
            }
        } catch (Exception e) {
            e.printStackTrace();
            data.clear();
            callback.succeed(data);
        }
        callback.succeed(data);
    }

    //设置  GPS  NFC="04"   FN = "245"
    private void setF245(List<String> setdata, ICallbackres callbackres) {
        String str = "";
        String sendStr = "";
        for (int k = 0; k < 2; k++) {
            String temp1 = setdata.get(k);
            int startNmu = 0;
            String flag = "00";
            for (int i = 0; i < temp1.length(); i++) {
                if ("-".equals(temp1.substring(0, 1))) {
                    flag = "01";
                    startNmu = 1;
                }
                if (".".equals(temp1.substring(i, i + 1))) {
                    String temp3 = temp1.substring(startNmu, i);
                    for (int j = 0; j < 4 - temp3.length(); ) {
                        temp3 = "0" + temp3;
                    }
                    String value = temp3 + temp1.substring(i + 1, temp1.length());
                    str = Analysis.pullData(flag, value);
                }
            }
            sendStr = sendStr + str;
        }
        getDataForPara.setparam("04", "245", sendStr, callbackres);
    }
}

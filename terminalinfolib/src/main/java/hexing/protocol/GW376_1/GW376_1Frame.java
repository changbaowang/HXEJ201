package hexing.protocol.GW376_1;


import hexing.model.HXFramePara;

/**
 * @author 王昌豹
 * @version 1.0
 * @Title: 国网协议桢类
 * @Description: 组桢
 * @Copyright: Copyright (c) 2016
 * @Company 杭州海兴电力科技
 */
public class GW376_1Frame {

    /**
     * 构建桢
     *
     * @param fpara
     * @return
     */
    public byte[] BuildTermiFrame(HXFramePara fpara) {
        String SendData = "";
        int DataLen = 0;
        byte[] TmpArr;
        int Index = 0;
        String Tempstr = "";
        String StrData = "";
        SendData = SendData + fpara.TermiCMD;
        SendData = SendData + getaddrmark(fpara.TermiAddr);
        SendData = SendData + fpara.TermiAFN;
        SendData = SendData + fpara.TermiSEQ;
        StrData = GetDADT(fpara.TermiFN, fpara.TermiPN) + fpara.TermiDATA + FormatTermiPwd(fpara.TermiPWD);
        SendData = SendData + StrData;
        SendData = SendData + fpara.TermiEC;
        SendData = SendData + DealHistoryDensity(fpara.TermiTP, fpara.TermiDensity, fpara.TermiTimeMark, fpara.TermiPoint);
        DataLen = (SendData.length() / 2) * 4 + 2;
//        Tempstr = padRight(Integer.toHexString(DataLen), 4, '0');
        Tempstr = getLength(Integer.toHexString(DataLen), '0');
        SendData = "68" + Tempstr + Tempstr + "68" + SendData + GetSumCS(SendData);
        SendData = SendData + "16";
        fpara.SendFrame = SendData;
        Index = SendData.length() / 2;
        byte[] rtnArr = new byte[Index + 1];
        rtnArr = ConvertHexToImageBytes(SendData);
        String s = GetSumCS(SendData);
        return rtnArr;
    }

    /**
     * SUM校验
     *
     * @param SendStr
     * @return
     */
    public String GetSumCS(String SendStr) {
        String result = null;
        int res = 0;
        int i = 0;
        for (i = 0; i < SendStr.length() / 2; i++) {
            res = res + Integer.valueOf(SendStr.substring(i * 2, i * 2 + 2), 16);
        }
        result = padLeft(Integer.toHexString(res), 2, '0');
        return result.substring(result.length() - 2, result.length());
    }

    private String DealHistoryDensity(String TP, String Density, String TimeMark, String Point) {
        String result = null;
        String tempstr = null;
        if (TimeMark == "" || TimeMark == null) {
            result = "";
        } else {
            if (Density == "15min")
                tempstr = "01";
            else if (Density == "30min")
                tempstr = "02";
            else if (Density == "60min")
                tempstr = "03";
            else if (Density == "5min")
                tempstr = "FE";
            else if (Density == "1min")
                tempstr = "FF";
            else if (Density == "no freeze")
                tempstr = "00";
            if (Point == "" || Point == null) {
                result = resverstr(TimeMark) + tempstr + "00";
            } else
                result = resverstr(TimeMark) + tempstr + padLeft(Integer.toHexString(Integer.parseInt(Point)), 2, '0');
        }
        if (TP == "Day Freeze")
            result = result.substring(4, 10);
        else if (TP == "Month Freeze")
            result = result.substring(6, 10);
        else if (TP == "Profile Freeze")
            result = result;
        return result;
    }


    /**
     * 通讯地址处理
     *
     * @param AddrStr
     * @return
     */
    public String getaddrmark(String AddrStr) {
        String result = null;
        if (AddrStr == null || AddrStr == "") {
            AddrStr = "";
            return result;
        } else {
            AddrStr = padLeft(AddrStr, 8, '0');
        }
        if (AddrStr.toUpperCase() == "FFFFFFFF") {
            AddrStr = AddrStr + "03";
        } else {
            AddrStr = AddrStr.substring(2, 4) + AddrStr.substring(0, 2) + AddrStr.substring(6, 8) + AddrStr.substring(4, 6) + "02";
        }
        result = AddrStr;
        return result;
    }


    /**
     * 右补位
     *
     * @param oriStr
     * @param len
     * @param alexin
     * @return
     */
    public String padRight(String oriStr, int len, char alexin) {
        int strlen = oriStr.length();
        String str = "";
        if (strlen < len) {
            for (int i = 0; i < len - strlen; i++) {
                str = str + alexin;
            }
        }
        str = oriStr + str;
        return str;
    }

    public String getLength(String oriStr, char alexin) {
        int strlen = oriStr.length();
        String str = "";
        if (strlen > 0) {
            switch (strlen) {
                case 1:
                    str = alexin + oriStr + alexin + alexin;
                    break;
                case 2:
                    str = oriStr + alexin + alexin;
                    break;
                case 3:
                    str = resverstr(alexin + oriStr.substring(0, 1) + oriStr.substring(1, 3));
                    break;
                case 4:
                    str = resverstr(oriStr);
                    break;
            }
        }
        return str;
    }

    /**
     * 左补位，右对齐
     *
     * @param oriStr 原字符串
     * @param len    目标字符串长度
     * @param alexin 补位字符
     * @return 目标字符串
     */
    public String padLeft(String oriStr, int len, char alexin) {
        int strlen = oriStr.length();
        String str = "";
        if (strlen < len) {
            for (int i = 0; i < len - strlen; i++) {
                str = str + alexin;
            }
        }
        str = str + oriStr;
        return str;
    }

    /**
     * 字节数组转16进制字符串
     *
     * @param bytes
     * @return
     */
    public String byteToHexStr(byte[] bytes) {
        StringBuffer sb = new StringBuffer(bytes.length);
        String sTemp;
        for (int i = 0; i < bytes.length; i++) {
            sTemp = Integer.toHexString(0xFF & bytes[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 十六进制字符串转换成byte[]
     *
     * @param _HexText
     * @return
     */
    public static byte[] ConvertHexToImageBytes(String _HexText) {
        byte[] _ImageBytes = new byte[_HexText.length() / 2];

        for (int i = 0; i != _ImageBytes.length; i++) {
            int temp = Integer.valueOf(_HexText.substring(i * 2, i * 2 + 2), 16);
            _ImageBytes[i] = (byte) temp;
        }
        return _ImageBytes;
    }

    /**
     * 通讯密码处理
     *
     * @param PwdStr
     * @return
     */
    private String FormatTermiPwd(String PwdStr) {
        String result = null;
        if (PwdStr == "") {
            result = "";
        } else {
            result = resverstr(padLeft(PwdStr, 32, '0'));
        }
        return result;
    }

    /**
     * 字符串反转
     *
     * @param str
     * @return
     */
    public String resverstr(String str) {
        int k, flag;
        flag = str.length() / 2;
        k = str.length() - 2;
        String ss = "";
        while (k > -2) {
            ss = ss + str.substring(k, k + 2);
            k = k - 2;
        }
        ss = ss.substring(0, flag * 2);
        return ss;
    }

    /**
     * FN PN 转化为DA DT单元处理
     *
     * @param ffn
     * @param ppn
     * @return
     */
    private String GetDADT(String ffn, String ppn) {
        String DA = null;
        String DT = null;
        String result = null;
        int f, p;
        int fn = Integer.parseInt(ffn);
        int pn = Integer.parseInt(ppn);
        if (pn == 0)
            DA = "0000";
        else {
            pn = pn - 1;
            p = pn - (pn / 8) * 8;
            if (p == 0)
                DA = "01";
            else if (p == 1)
                DA = "02";
            else if (p == 2)
                DA = "04";
            else if (p == 3)
                DA = "08";
            else if (p == 4)
                DA = "10";
            else if (p == 5)
                DA = "20";
            else if (p == 6)
                DA = "40";
            else if (p == 7)
                DA = "80";

            DA = DA + padLeft(Integer.toHexString((pn / 8) + 1), 2, '0');
        }
        if (fn == 0)
            DT = "0000";
        else {
            fn = fn - 1;
            f = fn - (fn / 8) * 8;
            if (f == 0)
                DT = "01";
            else if (f == 1)
                DT = "02";
            else if (f == 2)
                DT = "04";
            else if (f == 3)
                DT = "08";
            else if (f == 4)
                DT = "10";
            else if (f == 5)
                DT = "20";
            else if (f == 6)
                DT = "40";
            else if (f == 7)
                DT = "80";
            DT = DT + padLeft(Integer.toHexString(fn / 8), 2, '0');
        }
        result = DA + DT;
        return result;
    }
}

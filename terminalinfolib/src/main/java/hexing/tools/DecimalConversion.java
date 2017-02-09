package hexing.tools;

import java.util.List;

import hexing.model.Constant;

/**
 * Created by hex170 on 2016/7/21.
 */
public class DecimalConversion {
    /**
     * 字节数组转16进制字符串
     *
     * @param bytes
     * @return
     */
    public static String byteToHexStr(byte[] bytes) {
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
     * @param HexText
     * @return
     */
    public static byte[] convertHexToImageBytes(String HexText) {
        byte[] ImageBytes = new byte[HexText.length() / 2];

        for (int i = 0; i < ImageBytes.length; i++) {
            String testStr = HexText.substring(i * 2, i * 2 + 2);
            int tesetInt = Integer.valueOf(testStr, 16);
            ImageBytes[i] = (byte) (tesetInt & 0xff);
        }
        return ImageBytes;
    }

    /**
     * String转十六进制补位
     */
    public static String stringtoHex(String s) {
        if (s.length() % 2 == 1) {
            s = "0" + s;
        }
        return s;
    }

    /**
     * 十六进制转二进制
     *
     * @param strr
     * @return
     */
    public static String hex16tobin(String strr) {
        String Result = "";
        int n = Integer.valueOf(strr, 16);
        Result = Integer.toBinaryString(n);
        return padLeft(Result, 8, '0');
    }

    /**
     * 二进制转十六进制
     *
     * @param strr
     * @return
     */
    public static String txttohex16(String strr) {
        int Result = 0;
        Result = Integer.valueOf(strr, 2);
        return padLeft(Integer.toHexString(Result), 2, '0');
    }

    /**
     * ASCII码转字符
     *
     * @param value
     * @return
     */
    public static String asciiToString(String value) {
        StringBuffer sbu = new StringBuffer();
        String[] chars = value.split(",");
        for (int i = 0; i < chars.length; i++) {
            sbu.append((char) Integer.parseInt(chars[i]));
        }
        return sbu.toString();
    }

    /**
     * (十六进制的)ASCII码转字符
     *
     * @param value
     * @return
     */
    public static String asciiToString2(String value) {
        StringBuffer sbu = new StringBuffer();
        String[] chars = value.split(",");
        for (int i = 0; i < chars.length; i++) {
            sbu.append((char) Integer.parseInt(chars[i], 16));
        }
        return sbu.toString();
    }

    /**
     * 字符串转ASCII码
     *
     * @param value
     * @return
     */
    public static String stringToAscii(String value) {
        StringBuffer sbu = new StringBuffer();
        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            String strTemp = padLeft(Integer.toHexString((int) chars[i]), 2, '0');
            sbu.append(strTemp);
        }
        return sbu.toString();
    }

    public static String padLeft(String oriStr, int len, char alexin) {
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


    public static String padLeft1(String oriStr, int len, String alexin) {
        int strlen = oriStr.length();
        String str = "";
        if (strlen < len) {
            for (int i = 0; i < len - strlen; i++) {
                str = str + alexin;
            }
            str = str + oriStr;

        } else {
            str = oriStr.substring(0, len);
        }

        return str;
    }

    public static String padRight(String oriStr, int len, char alexin) {
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

    //去掉字符串前面没用的0
    public static String delLeftZero(String oriStr) {
        String str = "";
        for (int i = 0; i < oriStr.length(); i++) {
            String temp = oriStr.substring(i, i + 1);
            if ("0".equals(temp)) {

            } else {
                return oriStr.substring(i, oriStr.length());
            }
        }
        return str;
    }

    // 解析用户输入的APN数据(用户输入的为ASCII----〉十进制---〉转化为十六进制)
    //APN为64字节，位数不够用"00"补齐
    public static String pullAPNData(List<String> setdata) {
        String apnstr = "";
        String apnstr1 = setdata.get(setdata.size() - 1);
        for (int k = 0; k < apnstr1.length(); k++) {
            int f = apnstr1.charAt(k);
            apnstr = apnstr + DecimalConversion.stringtoHex(Integer.toHexString(f));
        }
        if (apnstr.length() < (Constant.APN_LENGTH * 2)) {
            for (int i = 0; i < (Constant.APN_LENGTH * 2) - apnstr.length(); ) {
                apnstr = apnstr + "00";
            }
        }
        return apnstr;
    }

    //判断返回数据是否有效
//    public static boolean checkData(String res) {
//        int num = 0;
//        if (res.length() > 10) {
//            String temp = res.substring(10, res.length());
//            for (int i = 0; i < temp.length(); i = i + 2) {
//                if ("ee".equals(temp.substring(i, i + 2))) {
//                    num++;
//                }
//            }
//            if ((num + 1) > temp.length() / 2) {
//                return false;
//            }
//        }
//        return true;
//    }
}
package hexing.protocol.GWHelp;


import hexing.tools.DecimalConversion;

/**
 * Created by mc on 16/7/11.
 */
public class Analysis {
    /**
     * 解析函数
     *
     * @param Type
     * @return
     */
    public static String Parser(AnalysisType Type) {
        String strResult = "";
        String strTemp = "";
        try {
            if (Type.DataType == "ASCII") {
                if (Type.optType == 0) {
                    for (int i = 0; i < Type.Data.length() / 2; i++) {
                        strTemp = strTemp + DecimalConversion.asciiToString(Integer.valueOf(Type.Data.substring(i * 2, i * 2 + 2), 16).toString());
                    }
                } else if (Type.optType == 1) {
                    for (int i = 0; i < Type.Data.length(); i++) {
                        strTemp = strTemp + DecimalConversion.stringToAscii(Type.Data.substring(i, i + 1));
                    }
                    strTemp = DecimalConversion.padRight(strTemp, Type.DataLength * 2, '0');
                }
                strResult = strTemp;
            } else if (Type.DataType == "BCD") {

            } else if (Type.DataType == "A5") {
                if (Type.optType == 0) {
                    strTemp = resverstr(Type.Data);
                    String s = DecimalConversion.hex16tobin(strTemp.substring(0, 1));
                    int flag = Integer.parseInt(s.substring(4, 5));
                    if (flag == 0) {
                    } else {
                        strResult = "-";
                    }
                    String temp = DecimalConversion.txttohex16(s.substring(5, 8)) + strTemp.substring(1, 3);
                    strResult = strResult + Integer.parseInt(temp) + "." + Integer.parseInt(strTemp.substring(3, 4));
                    return strResult;
                } else if (Type.optType == 1) {
                    return null;
                }
            } else if (Type.DataType == "A7") {
                if (Type.optType == 0) {
                    strTemp = resverstr(Type.Data);
                    strResult = Integer.parseInt(strTemp.substring(0, 3)) + "." + Integer.parseInt(strTemp.substring(3, 4));
                    return strResult;
                } else if (Type.optType == 1) {
                    return null;
                }
            } else if (Type.DataType == "A8") {
                if (Type.optType == 0) {
                    strTemp = resverstr(Type.Data);
                    strResult = strTemp;
                    return strResult;
                } else if (Type.optType == 1) {
                    return null;
                }
            } else if (Type.DataType == "A9") {
                if (Type.optType == 0) {
                    strTemp = resverstr(Type.Data);
                    String s = DecimalConversion.hex16tobin(strTemp.substring(0, 1));
                    int flag = Integer.parseInt(s.substring(4, 5));
                    if (flag == 0) {
//                        strResult = "+";
                    } else {
                        strResult = "-";
                    }
                    String temp = DecimalConversion.txttohex16(s.substring(5, 8)) + strTemp.substring(1, 2);
                    strResult = strResult + Integer.parseInt(temp) +
                            "." + Integer.parseInt(strTemp.substring(2, 6));
                    return strResult;
                } else if (Type.optType == 1) {
                    return null;
                }
            } else if (Type.DataType == "A11") {
                if (Type.optType == 0) {
                    strTemp = resverstr(Type.Data);
                    strResult = Integer.parseInt(strTemp.substring(0, 6)) +
                            "." + Integer.parseInt(strTemp.substring(6, 8));
                    return strResult;
                } else if (Type.optType == 1) {
                    return resverstr1(Type.Data, 6, 8);
                }
            } else if (Type.DataType == "A12") {
                if (Type.optType == 0) {
                    strTemp = resverstr(Type.Data);
                    return strTemp;
                } else if (Type.optType == 1) {
                    strTemp = resverstr(Type.Data);
                    return strTemp;
                }
            } else if (Type.DataType == "A14") {
                if (Type.optType == 0) {
                    strTemp = resverstr(Type.Data);
                    strResult = Integer.parseInt(strTemp.substring(0, 6)) + "." + Integer.parseInt(strTemp.substring(6, 10));
                    return strResult;
                } else if (Type.optType == 1) {
                    return resverstr1(Type.Data, 6, 10);
                }
            } else if (Type.DataType == "A15") {
                if (Type.optType == 0) {
                    strTemp = resverstr(Type.Data);
                    strTemp = strTemp.substring(0, 2) + "-" + strTemp.substring(2, 4) + "-" + strTemp.substring(4, 6) + "  "
                            + strTemp.substring(6, 8) + ":" + strTemp.substring(8, 10);
                    return strTemp;
                } else if (Type.optType == 1) {
                    return null;
                }
            } else if (Type.DataType == "A20") {
                if (Type.optType == 0) {
                    strTemp = resverstr(Type.Data);
                    strTemp = strTemp.substring(0, 2) + "-" + strTemp.substring(2, 4) + "-" + strTemp.substring(4, 6);
                    return strTemp;
                } else if (Type.optType == 1) {
                    return null;
                }
            } else if (Type.DataType == "A25") {
                if (Type.optType == 0) {
                    strTemp = resverstr(Type.Data);
                    String s = DecimalConversion.hex16tobin(strTemp.substring(0, 1));
                    int flag = Integer.parseInt(s.substring(4, 5));
                    if (flag == 0) {
                    } else {
                        strResult = "-";
                    }
                    String temp = DecimalConversion.txttohex16(s.substring(5, 8)) + strTemp.substring(1, 3);
                    strResult = strResult + Integer.parseInt(temp) +
                            "." + Integer.parseInt(strTemp.substring(3, 6));
                    return strResult;
                } else if (Type.optType == 1) {
                    return null;
                }
            } else if (Type.DataType == "A1") {
                if (Type.optType == 0) {
                    if ("ee".equals(Type.Data.substring(0, 2))) {
                        return "Error Data";
                    }
                    strTemp = resverstr(Type.Data);
                    // strTemp0  表示星期
                    String strTemp0 = DecimalConversion.txttohex16("0" + DecimalConversion.hex16tobin(strTemp.substring(2, 4)).substring(0, 3));
                    strTemp = "20" + strTemp.substring(0, 2) + "-" + DecimalConversion.hex16tobin(strTemp.substring(2, 4)).substring(3, 4) +
                            strTemp.substring(3, 4) + "-" + strTemp.substring(4, 6) + " " +
                            strTemp.substring(6, 8) + ":" + strTemp.substring(8, 10) + ":" +
                            strTemp.substring(10, 12);
                    return strTemp;
                } else if (Type.optType == 1) {
                    //+最后一位代表星期
                    String strTemp4 = "";
                    String strTemp1 = DecimalConversion.hex16tobin(Type.Data.substring(Type.Data.length() - 2, Type.Data.length() - 1));
                    int intTemp1 = Integer.parseInt(Type.Data.substring(2, 4));
                    String strTemp3 = "";
                    if (intTemp1 > 9) {
                        String strTemp2 = DecimalConversion.txttohex16(strTemp1.substring(1, 4) + "1");
                        strTemp3 = strTemp2.substring(1, 2) + Type.Data.substring(3, 4);
                    } else {
                        String strTemp2 = DecimalConversion.txttohex16(strTemp1.substring(1, 4) + "0");
                        strTemp3 = strTemp2.substring(1, 2) + Type.Data.substring(3, 4);
                    }
                    strTemp4 = Type.Data.substring(0, 2) + strTemp3 + Type.Data.substring(4, 12);
                    strResult = resverstr(strTemp4);
                    return strResult;
                }
            }
        } catch (Exception ex) {
            throw ex;
        }
        return strResult;
    }

    //反转 xx.xx型数据
    //totallen 反转后数据的长度
    //length "."之前数据的长度         //0              6          10
    private static String resverstr1(String str, int length, int totallen) {
        if (str.length() > totallen) {
            str = str.substring(0, totallen);
        }
        boolean istrue = true;
        String tempstr = "";
        String temp1 = "";
        for (int i = 0; i < str.length(); i++) {
            if (".".equals(str.substring(i, i + 1))) {
                temp1 = DecimalConversion.padLeft1(str.substring(0, i), length, "0");
                tempstr = resverstr(temp1 + DecimalConversion.padLeft1(str.substring(i + 1, str.length()), totallen - length, "0"));
                istrue = false;
            }
        }
        if (istrue) {
            String temp = "";    //temp=0000
            for (int i = 0; i < totallen - length; i++) {
                temp = temp + "0";
            }
            tempstr = resverstr(DecimalConversion.padLeft1(str, length, "0") + temp);
        }
        return tempstr;
    }

    //反转数据
    public static String resverstr(String str) {
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

    //输入1表示异常，0表示正常-----判断是否正常
    public static String judgement(int k, String normal, String abnormal) {
        if (k == 0) {
            return normal;
        }
        return abnormal;
    }

    public static String pullData(String flag, String value) {
        String sendStr = "";
        if ("".equals(flag)) {
            sendStr = sendStr + "00";
        } else {
            sendStr = sendStr + DecimalConversion.stringtoHex(flag);
        }
        if ("".equals(value)) {
            sendStr = sendStr + "00000000";
        } else {
            if (value.length() < 8) {
                String tempstr = "";
                for (int i = 0; i < 8 - value.length(); i++) {
                    tempstr = tempstr + "0";
                }
                value = value + tempstr;
            }
            String wdparam1 = value.substring(0, 2);
            String wdparam2 = value.substring(2, 4);
            String wdparam3 = value.substring(4, 6);
            String wdparam4 = value.substring(6, 8);
            sendStr = sendStr + wdparam4 + wdparam3 + wdparam2 + wdparam1;
        }
        return sendStr;
    }
}

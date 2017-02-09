package cn.hxgroup.www.hhu.control;

import android.text.Html;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.business.AppConfig;
import cn.hxgroup.www.hhu.control.base.BaseControl;
import cn.hxgroup.www.hhu.db.ActiveValueDB;
import cn.hxgroup.www.hhu.tools.DialogUtils;
import cn.hxgroup.www.hhu.tools.SharedPreferencesUtils;
import cn.hxgroup.www.hhu.ui.feature.TermFroDataActivity;
import cn.hxgroup.www.hhu.ui.interfaces.IGetDialogDatepicker;
import hexing.icomm.IGetdataCallback;
import hexing.protocol.GWHelp.Analysis;
import hexing.tools.DecimalConversion;
import hexing.tools.GetTerminalInfoByParam;

import static cn.hxgroup.www.hhu.ui.selfview.DoubleDatePickerDialog.MaxDayFromDay_OF_MONTH;
import static cn.hxgroup.www.hhu.ui.selfview.DoubleDatePickerDialog.padLeft;

/**
 * Created by hex170 on 2016/12/16.
 */

public class TermFroDataControl extends BaseControl {
    private TermFroDataActivity mActivity;
    private GetTerminalInfoByParam terminalInfoByParam;
    private List<String> saveData = new ArrayList<>();
    public ActiveValueDB database;

    public TermFroDataControl(TermFroDataActivity mActivity) {
        this.mActivity = mActivity;
        terminalInfoByParam = new GetTerminalInfoByParam(mActivity);
    }

    DialogUtils dialog;

    public void getData(List<String> data) {
        boolean wifi = AppConfig.getInstance().getWifiState();
        if (wifi) {
            dialog = new DialogUtils(mActivity);
            String timeStr = "";
            String pn = SharedPreferencesUtils.getStringState(mActivity, "pn");
            String dataTemp = GetDADT("161", pn);
            if (data != null) {
                for (int i = 0; i < data.size(); i++) {
                    String temp = data.get(i).substring(2, data.get(i).length());
                    if (i == data.size() - 1) {
                        timeStr = timeStr + Analysis.resverstr(temp);
                    } else {
                        timeStr = timeStr + Analysis.resverstr(temp) + dataTemp;
                    }
                }
            }
            dialog.show(mActivity.getResources().getString(R.string.ReadingData),
                    mActivity.getResources().getString(R.string.PleaseWait));
            terminalInfoByParam.get("0D", "161", pn, timeStr, new IGetdataCallback() {
                @Override
                public void succeed(List<String> res) {
                    if (res != null && res.size() > 0) {
                        int num = res.size() / 8;
                        String temp = mActivity.getResources().getString(R.string.TotleCounter) + "：<font color='#FF0000'> " + num + "</font>";
                        mActivity.numTv.setVisibility(View.VISIBLE);
                        mActivity.saveBtn.setVisibility(View.VISIBLE);
                        mActivity.numTv.setText(Html.fromHtml(temp));
                        dialog.close();
                        mActivity.update(res);
                        saveData.clear();
                        saveData.addAll(res);
                    }
                }
            });
        } else {
            mActivity.showToast(mActivity.getString(R.string.kConnectionWifi));
        }
    }

    private DatePicker strDatePicker;
    private DatePicker endDatePicker;

    public void getTime(final int flag) {
        DialogUtils dialogUtils = new DialogUtils(mActivity);
        dialogUtils.showAlertDialog(new IGetDialogDatepicker() {
            @Override
            public void getDatePicker(DatePicker datePicker) {
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth() + 1;
                int year = datePicker.getYear();
                String time = year + "-" + month + "-" + day;
                if (flag == 0) {
                    strDatePicker = datePicker;
                    mActivity.strDateTv.setText(time);
                    SharedPreferencesUtils.setStringState(mActivity, "strDate", time);
                } else {
                    endDatePicker = datePicker;
                    mActivity.endDataTv.setText(time);
                    SharedPreferencesUtils.setStringState(mActivity, "endDate", time);
                }
            }
        });
    }

    public void query() {
        if (strDatePicker == null || endDatePicker == null) {
            mActivity.showToast(mActivity.getString(R.string.ChoiceDate));
        } else {
            List<String> timeData = queryData();
            if (timeData != null) {
                if (timeData.size() > 10) {
                    List<String> tempData = timeData.subList(0, 10);
                    getData(tempData);
                } else {
                    getData(timeData);
                }
            }
        }
    }

    public void saveData() {
        if (saveData.size() == 0) {
            mActivity.showToast(mActivity.getResources().getString(R.string.NoData));
        } else {
            database = new ActiveValueDB(mActivity);
            database.open();
            pullSaveData();
        }
    }

    private long num = 0;

    private void pullSaveData() {
        int rate = Integer.parseInt(saveData.get(2));
        int length = 8 - (4 - rate);
        for (int i = 0; i < saveData.size() / length; i++) {
            List<String> dataTemp = new ArrayList<>();
            dataTemp.addAll(saveData.subList(i * length, i * length + length));
            String address = "100000";
            num = num + database.addData(address, dataTemp, "0");
        }
        if (num >= 1) {
            mActivity.showToast(mActivity.getResources().getString(R.string.SaveSucceed));
        } else {
            mActivity.showToast(mActivity.getResources().getString(R.string.DataExist));
        }
        database.close();
    }

    private List<String> queryData() {
        List<String> timeData = new ArrayList<>();
        int stryear = strDatePicker.getYear();
        int strmouth = strDatePicker.getMonth() + 1;
        int strday = strDatePicker.getDayOfMonth();
        int endyear = endDatePicker.getYear();
        int endmouth = endDatePicker.getMonth() + 1;
        int endday = endDatePicker.getDayOfMonth();
        if (endyear < stryear || endmouth < strmouth && stryear == endyear || endday < strday && strmouth == endmouth) {
            Toast.makeText(mActivity, mActivity.getResources().getString(R.string.ImpDateRule), Toast.LENGTH_SHORT).show();
            return null;
        }
        if (endyear - stryear > 1 && strmouth != 12) {
            //输入时间有误
            return null;
        } else if (endyear - stryear == 1) {
            for (int i = strday; i <= MaxDayFromDay_OF_MONTH(stryear, strmouth); i++) {
                String temp = stryear + "" + strmouth + "" + i;
                timeData.add(temp);
            }
            for (int j = 1; j <= endday; j++) {
                String temp = endyear + "" + "1" + j;
                timeData.add(temp);
            }
        } else if (endmouth > strmouth) {
            if (endmouth - strmouth > 1) {
                //输入时间有误
                return null;
            }
            for (int i = strday; i <= MaxDayFromDay_OF_MONTH(stryear, strmouth); i++) {
                String temp = stryear + "" + padLeft("" + strmouth, 2, "0") + "" + padLeft("" + i, 2, "0");
                timeData.add(temp);
            }
            for (int j = 1; j <= endday; j++) {
                String temp = endyear + "" + padLeft("" + (strmouth + 1), 2, "0") + "" + padLeft("" + j, 2, "0");
                timeData.add(temp);
            }
        } else {
            for (int i = strday; i <= endday; i++) {
                String temp = stryear + "" + padLeft("" + strmouth, 2, "0") + "" + padLeft("" + i, 2, "0");
                timeData.add(temp);
            }
        }
        return timeData;
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
        int pn = 0;
        if (null != ppn) {
            pn = Integer.parseInt(ppn);
        }
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

            DA = DA + DecimalConversion.padLeft1(Integer.toHexString((pn / 8) + 1), 2, "0");
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
            DT = DT + DecimalConversion.padLeft1(Integer.toHexString(fn / 8), 2, "0");
        }
        result = DA + DT;
        return result;
    }
}

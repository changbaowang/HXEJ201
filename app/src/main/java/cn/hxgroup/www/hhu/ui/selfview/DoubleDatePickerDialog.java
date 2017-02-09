package cn.hxgroup.www.hhu.ui.selfview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.hxgroup.www.hhu.R;


/**
 * Created by hex170 on 2016/12/16.
 */

public class DoubleDatePickerDialog extends AlertDialog implements OnClickListener, OnDateChangedListener {
    private static final String START_YEAR = "start_year";
    private static final String END_YEAR = "end_year";
    private static final String START_MONTH = "start_month";
    private static final String END_MONTH = "end_month";
    private static final String START_DAY = "start_day";
    private static final String END_DAY = "end_day";

    private final DatePicker mDatePicker_start;
    private final DatePicker mDatePicker_end;
    private final OnDateSetListener mCallBack;
    private Context context;

    /**
     * The callback used to indicate the user is done filling in the date.
     */
    public interface OnDateSetListener {

        /**
         * // * @param view
         * The view associated with this listener.
         * //  * @param year
         * The year that was set.
         * //* @param monthOfYear
         * The month that was set (0-11) for compatibility with
         * {@link Calendar}.
         * // * @param dayOfMonth
         * The day of the month that was set.
         */
        void onDateSet(List<String> data);
    }

    /**
     * @param context     The context the dialog is to run in.
     * @param callBack    How the parent is notified that the date is set.
     * @param year        The initial year of the dialog.
     * @param monthOfYear The initial month of the dialog.
     * @param dayOfMonth  The initial day of the dialog.
     */
    public DoubleDatePickerDialog(Context context, OnDateSetListener callBack, int year, int monthOfYear, int dayOfMonth) {
        this(context, 0, callBack, year, monthOfYear, dayOfMonth);
    }

    public DoubleDatePickerDialog(Context context, int theme, OnDateSetListener callBack, int year, int monthOfYear,
                                  int dayOfMonth) {
        this(context, 0, callBack, year, monthOfYear, dayOfMonth, true);
    }

    /**
     * @param context     The context the dialog is to run in.
     * @param theme       the theme to apply to this dialog
     * @param callBack    How the parent is notified that the date is set.
     * @param year        The initial year of the dialog.
     * @param monthOfYear The initial month of the dialog.
     * @param dayOfMonth  The initial day of the dialog.
     */
    public DoubleDatePickerDialog(Context context, int theme, OnDateSetListener callBack, int year, int monthOfYear,
                                  int dayOfMonth, boolean isDayVisible) {
        super(context, theme);
        this.context = context;
        mCallBack = callBack;

        Context themeContext = getContext();
        setButton(BUTTON_POSITIVE, "确 定", this);
        setButton(BUTTON_NEGATIVE, "取 消", this);
        // setButton(BUTTON_POSITIVE,
        // themeContext.getText(android.R.string.date_time_done), this);
        setIcon(0);

        LayoutInflater inflater = (LayoutInflater) themeContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.date_picker_dialog, null);
        setView(view);
        mDatePicker_start = (DatePicker) view.findViewById(R.id.datePickerStart);
        mDatePicker_end = (DatePicker) view.findViewById(R.id.datePickerEnd);
        mDatePicker_start.init(year, monthOfYear, dayOfMonth, this);
        mDatePicker_end.init(year, monthOfYear, dayOfMonth, this);
        // updateTitle(year, monthOfYear, dayOfMonth);

        // 如果要隐藏当前日期，则使用下面方法。
        if (!isDayVisible) {
//                        hidDay(mDatePicker_start);
//                      hidDay(mDatePicker_end);
        }
    }

    // 隐藏DatePicker中的日期显示
//               private void hidDay(DatePicker mDatePicker) {
//                 Field[] datePickerfFields = mDatePicker.getClass().getDeclaredFields();
//                for (Field datePickerField : datePickerfFields) {
//                      if ("mDaySpinner".equals(datePickerField.getName())) {
//                                datePickerField.setAccessible(true);
//                               Object dayPicker = new Object();
//                              try {
//                                        dayPicker = datePickerField.get(mDatePicker);
//                                     } catch (IllegalAccessException e) {
//                                        e.printStackTrace();
//                                     } catch (IllegalArgumentException e) {
//                                      e.printStackTrace();
//                                 }
//                              // datePicker.getCalendarView().setVisibility(View.GONE);
//                                ((View) dayPicker).setVisibility(View.GONE);
//                             }
//                }
//            }
    public void onClick(DialogInterface dialog, int which) {
        // Log.d(this.getClass().getSimpleName(), String.format("which:%d",
        // which));
        // 如果是“取 消”按钮，则返回，如果是“确 定”按钮，则往下执行
        if (which == BUTTON_POSITIVE)
            tryNotifyDateSet();
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int month, int day) {
        if (view.getId() == R.id.datePickerStart)
            mDatePicker_start.init(year, month, day, this);
        if (view.getId() == R.id.datePickerEnd)
            mDatePicker_end.init(year, month, day, this);
        // updateTitle(year, month, day);
    }

    /**
     * 获得开始日期的DatePicker
     *
     * @return The calendar view.
     */
    public DatePicker getDatePickerStart() {
        return mDatePicker_start;
    }

    /**
     * 获得结束日期的DatePicker
     *
     * @return The calendar view.
     */
    public DatePicker getDatePickerEnd() {
        return mDatePicker_end;
    }

    /**
     * Sets the start date.
     *
     * @param year        The date year.
     * @param monthOfYear The date month.
     * @param dayOfMonth  The date day of month.
     */
    public void updateStartDate(int year, int monthOfYear, int dayOfMonth) {
        mDatePicker_start.updateDate(year, monthOfYear, dayOfMonth);
    }

    /**
     * Sets the end date.
     *
     * @param year        The date year.
     * @param monthOfYear The date month.
     * @param dayOfMonth  The date day of month.
     */
    public void updateEndDate(int year, int monthOfYear, int dayOfMonth) {
        mDatePicker_end.updateDate(year, monthOfYear, dayOfMonth);
    }

    private void tryNotifyDateSet() {
        if (mCallBack != null) {
            mDatePicker_start.clearFocus();
            mDatePicker_end.clearFocus();
            List<String> allDate = getAllDate(mDatePicker_start, mDatePicker_end);
            mCallBack.onDateSet(allDate);
        }
    }

    @Override
    protected void onStop() {
        // tryNotifyDateSet();
        super.onStop();
    }

    @Override
    public Bundle onSaveInstanceState() {
        Bundle state = super.onSaveInstanceState();
        state.putInt(START_YEAR, mDatePicker_start.getYear());
        state.putInt(START_MONTH, mDatePicker_start.getMonth());
        state.putInt(START_DAY, mDatePicker_start.getDayOfMonth());
        state.putInt(END_YEAR, mDatePicker_end.getYear());
        state.putInt(END_MONTH, mDatePicker_end.getMonth());
        state.putInt(END_DAY, mDatePicker_end.getDayOfMonth());
        return state;
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int start_year = savedInstanceState.getInt(START_YEAR);
        int start_month = savedInstanceState.getInt(START_MONTH);
        int start_day = savedInstanceState.getInt(START_DAY);
        mDatePicker_start.init(start_year, start_month, start_day, this);

        int end_year = savedInstanceState.getInt(END_YEAR);
        int end_month = savedInstanceState.getInt(END_MONTH);
        int end_day = savedInstanceState.getInt(END_DAY);
        mDatePicker_end.init(end_year, end_month, end_day, this);

    }

    private List<String> getAllDate(DatePicker startDatePicker, DatePicker endDatePicker) {
        List<String> timeData = new ArrayList<>();
        int stryear = startDatePicker.getYear();
        int strmouth = startDatePicker.getMonth()+1;
        int strday = startDatePicker.getDayOfMonth();
        int endyear = endDatePicker.getYear();
        int endmouth = endDatePicker.getMonth()+1;
        int endday = endDatePicker.getDayOfMonth();
        if (endyear < stryear || endmouth < strmouth && stryear == endyear || endday < strday && strmouth == endmouth) {
            Toast.makeText(context, "结束时间不能小于开始时间", Toast.LENGTH_SHORT).show();
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

    public static String padLeft(String oriStr, int len, String alexin) {
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
     * 得到当年当月的最大日期
     **/
    public static int MaxDayFromDay_OF_MONTH(int year, int month) {
        Calendar time = Calendar.getInstance();
        time.clear();
        time.set(Calendar.YEAR, year);
        time.set(Calendar.MONTH, month - 1);//注意,Calendar对象默认一月为0
        int day = time.getActualMaximum(Calendar.DAY_OF_MONTH);//本月份的天数
        return day;
    }
}

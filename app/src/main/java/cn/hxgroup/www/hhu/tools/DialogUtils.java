package cn.hxgroup.www.hhu.tools;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.ui.interfaces.IGetDialogDatepicker;

/**
 * Created by hex170 on 2016/9/5.
 * ProgressDialog 工具类
 */
public class DialogUtils {
    private ProgressDialog xh_pDialog;
    private Context context;

    public DialogUtils(Context context) {
        this.context = context;
    }

    public void show(String title, String content) {
        // 创建ProgressDialog对象
        xh_pDialog = new ProgressDialog(context);
        // 设置进度条风格，风格为圆形，旋转的
        xh_pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // 设置ProgressDialog 标题
        xh_pDialog.setTitle(title);
        // 设置ProgressDialog提示信息
        xh_pDialog.setMessage(content);
        xh_pDialog.setIndeterminate(false);
        // 设置ProgressDialog 是否可以按退回键取消
        xh_pDialog.setCancelable(true);
        // 让ProgressDialog显示
        xh_pDialog.show();
    }

    public void close() {
        if (null != xh_pDialog) {
            xh_pDialog.cancel();
        }
    }

    private AlertDialog.Builder dialog;
    private DatePicker datePicker;

    public void showAlertDialog(final IGetDialogDatepicker iCallback) {
        dialog = new AlertDialog.Builder(context);
        View inflate = LayoutInflater.from(context).inflate(R.layout.active_power_time_choise, null);
        datePicker = (DatePicker) inflate.findViewById(R.id.activite_datePicker);
        dialog.setView(inflate);
        dialog.setPositiveButton(context.getResources().getString(R.string.kOk), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                int year = datePicker.getYear();
//                int month = datePicker.getMonth() + 1;
//                int dayOfMonth = datePicker.getDayOfMonth();
//                String temp = year + "-" + DecimalConversion.padLeft1(month + "", 2, "0") + "-" + DecimalConversion.padLeft1(dayOfMonth + "", 2, "0");
//                mTimeEt.setText(temp);
                iCallback.getDatePicker(datePicker);
            }
        });
        dialog.setNegativeButton(context.getResources().getString(R.string.kCancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dialog.show();
    }
}

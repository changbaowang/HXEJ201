package cn.hxgroup.www.hhu.app;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;

import java.io.File;

import cn.hxgroup.www.hhu.business.AppConfig;
import cn.hxgroup.www.hhu.db.DBHelper;


/**
 * Created by CXJ on 2016/5/13.
 */
public class HHUApplication extends Application {
    BroadcastReceiver mNetWorkReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        AppConfig.getInstance().init(this);
        DBHelper.creatDB(this);
        DBHelper.open();
        initDirectory();
//        test();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

    }

    public boolean isSetPn() {
        return setPn;
    }

    public void setSetPn(boolean setPn) {
        this.setPn = setPn;
    }

    private boolean setPn = false;

    private void initDirectory() {
        File dir = new File(Environment.getExternalStorageDirectory() + "/HHU/LocalUpdate");
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    private void test() {
        new Thread() {
            @Override
            public void run() {
//                AppControl.getInstance().getClient().connect();
            }
        }.start();
    }
}

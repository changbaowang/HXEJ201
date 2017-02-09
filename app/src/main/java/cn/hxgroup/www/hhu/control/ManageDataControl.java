package cn.hxgroup.www.hhu.control;

import android.os.Environment;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.control.base.BaseControl;
import cn.hxgroup.www.hhu.db.ActiveValueDB;
import cn.hxgroup.www.hhu.db.RealDataDB;
import cn.hxgroup.www.hhu.tools.FileUtil;
import cn.hxgroup.www.hhu.ui.feature.ManageDataActivity;

/**
 * Created by hex170 on 2016/11/3.
 */
public class ManageDataControl extends BaseControl {
    private ManageDataActivity mActivity;
    public RealDataDB realDatabase;
    public ActiveValueDB database;

    public ManageDataControl(ManageDataActivity mActivity) {
        this.mActivity = mActivity;
        realDatabase = new RealDataDB(mActivity);
        realDatabase.open();
        database = new ActiveValueDB(mActivity);
        database.open();
    }

    public void getRealData() {
        String realdata = realDatabase.fetchAll();
        String temp = "addressid" + "\t" + "CurrentTotalActivePower" + "\t" + "CurrentAPhaseActivPower" +
                "\t" + "CurrentBPhaseActivPower" + "\t" + "CurrentCPhaseActivPower" +
                "\t " + " CurrentTotalReactivePower" + "\t" + "CurrentAPhaseReactivePower " +
                "\t" + "CurrentBPhaseReactivePower" + "\t" + "CurrentCPhaseReactivePower " +
                "\t" + "CurrentTotalPoweFactor" + "\t" + "CurrentAPhasePowerFactor " +
                "\t" + "CurrentBPhasePowerFactor" + "\t" + "CurrentCPhasePowerFactor " +
                "\t" + "CurrentAPhaseVoltage" + "\t" + "CurrentBPhaseVoltage " +
                "\t" + "CurrentCPhaseVoltage" + "\t" + "CurrentAPhaseCurrent " +
                "\t" + "CurrentBPhaseCurrent" + "\t" + "CurrentCPhaseCurrent " +
                "\t" + "CurrentZeroSequenceCurrent" + "\t" + "CurrentTotalApparentPower " +
                "\t" + "TheCurrentAPhaseApparentPower" + "\t" + "TheCurrentBPhaseApparentPower " +
                "\t" + "TheCurrentCPhaseApparentPower" + "\t" + "UaPhaseAngle " +
                "\t" + "UbPhaseAngle" + "\t" + "UcPhaseAngle " +
                "\t" + "IaPhaseAngle" + "\t" + "IbPhaseAngle " + "\t" + "IcPhaseAngle " + "\n";
        if ("".equals(realdata)) {
            mActivity.showToast(mActivity.getResources().getString(R.string.NoData));
            return;
        }
        try {
            FileUtil.contentToTxt(Environment.getExternalStorageDirectory() + "/HHU/LocalUpdate/realdata.xls", temp + realdata);
            mActivity.showToast(mActivity.getResources().getString(R.string.SaveSucceed));
        } catch (Exception e) {
            e.printStackTrace();
            mActivity.showToast(mActivity.getResources().getString(R.string.AbnormalOperation));
        }
    }

    public void getActiveValue() {
        String realdata = database.fetchAll();
        String temp = "flag" + "\t " + "addressid" + "\t " + "dataTime" + "\t " + "readingTime" + "\t " + "RateNumber" + "\t " + "PowerIndication" + "\t " + "Rate" + "\n";
        if ("".equals(realdata)) {
            mActivity.showToast(mActivity.getResources().getString(R.string.NoData));
            return;
        }
        try {
            FileUtil.contentToTxt(Environment.getExternalStorageDirectory() + "/HHU/LocalUpdate/cativeValue.xls", temp + realdata);
            mActivity.showToast(mActivity.getResources().getString(R.string.SaveSucceed));
        } catch (Exception e) {
            e.printStackTrace();
            mActivity.showToast(mActivity.getResources().getString(R.string.AbnormalOperation));
        }

    }

    public void clearData() {
        boolean b = database.deleteAll();
        boolean b1 = realDatabase.deleteAll();
        if (b || b1) {
            mActivity.showToast(mActivity.getResources().getString(R.string.kMeterDelSucceed));
        } else {
            mActivity.showToast(mActivity.getResources().getString(R.string.NoData));
        }
    }

    public void clearTerminalData() {
        boolean b = database.deleteAll();
        boolean b1 = realDatabase.deleteAll();
        if (b || b1) {
            mActivity.showToast(mActivity.getResources().getString(R.string.kMeterDelSucceed));
        } else {
            mActivity.showToast(mActivity.getResources().getString(R.string.NoData));
        }
    }

    public void clearMeterData() {
        boolean b = database.deleteAll();
        boolean b1 = realDatabase.deleteAll();
        if (b || b1) {
            mActivity.showToast(mActivity.getResources().getString(R.string.kMeterDelSucceed));
        } else {
            mActivity.showToast(mActivity.getResources().getString(R.string.NoData));
        }
    }

    public void clearRealData() {
        boolean b = realDatabase.deleteAll();
        if (b) {
            mActivity.showToast(mActivity.getResources().getString(R.string.kMeterDelSucceed));
        } else {
            mActivity.showToast(mActivity.getResources().getString(R.string.NoData));
        }
    }
}

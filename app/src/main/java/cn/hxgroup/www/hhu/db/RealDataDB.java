package cn.hxgroup.www.hhu.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

/**
 * Created by HEX170 on 2016/11/1.
 */
public class RealDataDB {
    public static final String KEY_AddressId = "AddressId";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;
    static final String SQLITE_TABLE = "RealData";
    private final Context mCtx;

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, CommDB.DATABASE_NAME, null, CommDB.DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + SQLITE_TABLE);
            onCreate(db);
        }
    }

    public RealDataDB(Context ctx) {
        this.mCtx = ctx;
    }

    public RealDataDB open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long insert(String address, List<String> data) {
        DBHelper.open();
        open();
        long createResult = 0;
        if (data.size() > 28) {
            ContentValues values = new ContentValues();
            values.put("AddressId", address);
            values.put("CurrentTotalActivePower", data.get(1));
            values.put("CurrentAPhaseActivPower", data.get(2));
            values.put("CurrentBPhaseActivPower", data.get(3));
            values.put("CurrentCPhaseActivPower", data.get(4));
            values.put("CurrentTotalReactivePower", data.get(5));
            values.put("CurrentAPhaseReactivePower", data.get(6));
            values.put("CurrentBPhaseReactivePower", data.get(7));
            values.put("CurrentCPhaseReactivePower", data.get(8));
            values.put("CurrentTotalPoweFactor", data.get(9));
            values.put("CurrentAPhasePowerFactor", data.get(10));
            values.put("CurrentBPhasePowerFactor", data.get(11));
            values.put("CurrentCPhasePowerFactor", data.get(12));
            values.put("CurrentAPhaseVoltage", data.get(13));
            values.put("CurrentBPhaseVoltage", data.get(14));
            values.put("CurrentCPhaseVoltage", data.get(15));
            values.put("CurrentAPhaseCurrent", data.get(16));
            values.put("CurrentBPhaseCurrent", data.get(17));
            values.put("CurrentCPhaseCurrent", data.get(18));
            values.put("CurrentZeroSequenceCurrent", data.get(19));
            values.put("CurrentTotalApparentPower", data.get(20));
            values.put("TheCurrentAPhaseApparentPower", data.get(21));
            values.put("TheCurrentBPhaseApparentPower", data.get(22));
            values.put("TheCurrentCPhaseApparentPower", data.get(23));
            values.put("UaPhaseAngle", data.get(24));
            values.put("UbPhaseAngle", data.get(25));
            values.put("UcPhaseAngle", data.get(26));
            values.put("IaPhaseAngle", data.get(27));
            values.put("IbPhaseAngle", data.get(28));
            values.put("IcPhaseAngle", data.get(29));
            try {
                createResult = mDb.insert(SQLITE_TABLE, null, values);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                close();
                DBHelper.close();
            }
        }
        return createResult;
    }

    /**
     * 删除表的全部字段数据
     *
     * @return
     */
    public boolean deleteAll() {
        int doneDelete = 0;
        try {
            doneDelete = mDb.delete(SQLITE_TABLE, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doneDelete > 0;
    }

    /**
     * 根据名称删除表中的数据
     *
     * @param name    表计地址
     * @return
     */
    public boolean deleteByName(String name) {
        DBHelper.open();
        open();
        int isDelete = 0;
        String[] tName;
        tName = new String[]{name};
        try {
            isDelete = mDb.delete(SQLITE_TABLE, KEY_AddressId + "=?", tName);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            DBHelper.close();
            close();
        }
        return isDelete > 0;
    }

    /**
     * 获取表中的所有字段
     *
     * @return
     */
    public String fetchAll() {
        DBHelper.open();
        open();
        Cursor mCursor = null;
        String sql = "SELECT * FROM " + SQLITE_TABLE;
        mCursor = mDb.rawQuery(sql, null);
        String temp = "";
        try {
            if (mCursor != null) {
                if (mCursor.moveToFirst()) {
                    do {
                        String addressid = mCursor.getString(mCursor.getColumnIndexOrThrow("AddressId"));
                        String CurrentTotalActivePower = mCursor.getString(mCursor.getColumnIndex("CurrentTotalActivePower"));
                        String CurrentAPhaseActivPower = mCursor.getString(mCursor.getColumnIndex("CurrentAPhaseActivPower"));
                        String CurrentBPhaseActivPower = mCursor.getString(mCursor.getColumnIndex("CurrentBPhaseActivPower"));
                        String CurrentCPhaseActivPower = mCursor.getString(mCursor.getColumnIndex("CurrentCPhaseActivPower"));
                        String CurrentTotalReactivePower = mCursor.getString(mCursor.getColumnIndex("CurrentTotalReactivePower"));
                        String CurrentAPhaseReactivePower = mCursor.getString(mCursor.getColumnIndex("CurrentAPhaseReactivePower"));
                        String CurrentBPhaseReactivePower = mCursor.getString(mCursor.getColumnIndex("CurrentBPhaseReactivePower"));
                        String CurrentCPhaseReactivePower = mCursor.getString(mCursor.getColumnIndex("CurrentCPhaseReactivePower"));
                        String CurrentTotalPoweFactor = mCursor.getString(mCursor.getColumnIndex("CurrentTotalPoweFactor"));
                        String CurrentAPhasePowerFactor = mCursor.getString(mCursor.getColumnIndex("CurrentAPhasePowerFactor"));
                        String CurrentBPhasePowerFactor = mCursor.getString(mCursor.getColumnIndex("CurrentBPhasePowerFactor"));
                        String CurrentCPhasePowerFactor = mCursor.getString(mCursor.getColumnIndex("CurrentCPhasePowerFactor"));
                        String CurrentAPhaseVoltage = mCursor.getString(mCursor.getColumnIndex("CurrentAPhaseVoltage"));
                        String CurrentBPhaseVoltage = mCursor.getString(mCursor.getColumnIndex("CurrentBPhaseVoltage"));
                        String CurrentCPhaseVoltage = mCursor.getString(mCursor.getColumnIndex("CurrentCPhaseVoltage"));
                        String CurrentAPhaseCurrent = mCursor.getString(mCursor.getColumnIndex("CurrentAPhaseCurrent"));
                        String CurrentBPhaseCurrent = mCursor.getString(mCursor.getColumnIndex("CurrentBPhaseCurrent"));
                        String CurrentCPhaseCurrent = mCursor.getString(mCursor.getColumnIndex("CurrentCPhaseCurrent"));
                        String CurrentZeroSequenceCurrent = mCursor.getString(mCursor.getColumnIndex("CurrentZeroSequenceCurrent"));
                        String CurrentTotalApparentPower = mCursor.getString(mCursor.getColumnIndex("CurrentTotalApparentPower"));
                        String TheCurrentAPhaseApparentPower = mCursor.getString(mCursor.getColumnIndex("TheCurrentAPhaseApparentPower"));
                        String TheCurrentBPhaseApparentPower = mCursor.getString(mCursor.getColumnIndex("TheCurrentBPhaseApparentPower"));
                        String TheCurrentCPhaseApparentPower = mCursor.getString(mCursor.getColumnIndex("TheCurrentCPhaseApparentPower"));
                        String UaPhaseAngle = mCursor.getString(mCursor.getColumnIndex("UaPhaseAngle"));
                        String UbPhaseAngle = mCursor.getString(mCursor.getColumnIndex("UbPhaseAngle"));
                        String UcPhaseAngle = mCursor.getString(mCursor.getColumnIndex("UcPhaseAngle"));
                        String IaPhaseAngle = mCursor.getString(mCursor.getColumnIndex("IaPhaseAngle"));
                        String IbPhaseAngle = mCursor.getString(mCursor.getColumnIndex("IbPhaseAngle"));
                        String IcPhaseAngle = mCursor.getString(mCursor.getColumnIndex("IcPhaseAngle"));
                        temp = temp + addressid + "\t " + CurrentTotalActivePower + "\t " + CurrentAPhaseActivPower
                                + "\t " + CurrentBPhaseActivPower + "\t " + CurrentCPhaseActivPower + "\t " + CurrentTotalReactivePower +
                                "\t " + CurrentAPhaseReactivePower + "\t " + CurrentBPhaseReactivePower +
                                "\t " + CurrentCPhaseReactivePower + "\t " + CurrentTotalPoweFactor +
                                "\t " + CurrentAPhasePowerFactor + "\t " + CurrentBPhasePowerFactor +
                                "\t " + CurrentCPhasePowerFactor + "\t " + CurrentAPhaseVoltage +
                                "\t " + CurrentBPhaseVoltage + "\t " + CurrentCPhaseVoltage +
                                "\t " + CurrentAPhaseCurrent + "\t " + CurrentBPhaseCurrent +
                                "\t " + CurrentCPhaseCurrent + "\t " + CurrentZeroSequenceCurrent +
                                "\t " + CurrentTotalApparentPower + "\t " + TheCurrentAPhaseApparentPower +
                                "\t " + TheCurrentBPhaseApparentPower + "\t " + TheCurrentCPhaseApparentPower +
                                "\t " + UaPhaseAngle + "\t " + UbPhaseAngle +
                                "\t " + UcPhaseAngle + "\t " + IaPhaseAngle +
                                "\t " + IbPhaseAngle + "\t " + IcPhaseAngle + "\n";
                    } while (mCursor.moveToNext());
                }
            }
            if (mCursor != null && !mCursor.isClosed()) {
                mCursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DBHelper.close();
        close();
        return temp;
    }
}

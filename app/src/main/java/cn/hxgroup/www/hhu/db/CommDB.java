package cn.hxgroup.www.hhu.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HEX170 on 2016/11/1.
 */

public class CommDB {

    public static final String DATABASE_NAME = "Database"; //数据库名称

    public static final int DATABASE_VERSION = 1;    //版本号
    //创建实时数据表
    private static final String CREATE_TABLE_RealData =
            "create table " + "RealData " + "(AddressId String PRIMARY KEY ," +
                    "CurrentTotalActivePower text," +
                    "CurrentAPhaseActivPower text," +
                    "CurrentBPhaseActivPower text," +
                    "CurrentCPhaseActivPower text," +
                    "CurrentTotalReactivePower text," +
                    "CurrentAPhaseReactivePower text," +
                    "CurrentBPhaseReactivePower text," +
                    "CurrentCPhaseReactivePower text," +
                    "CurrentTotalPoweFactor text," +
                    "CurrentAPhasePowerFactor text," +
                    "CurrentBPhasePowerFactor text," +
                    "CurrentCPhasePowerFactor text," +
                    "CurrentAPhaseVoltage text," +
                    "CurrentBPhaseVoltage text," +
                    "CurrentCPhaseVoltage text," +
                    "CurrentAPhaseCurrent text," +
                    "CurrentBPhaseCurrent text," +
                    "CurrentCPhaseCurrent text," +
                    "CurrentZeroSequenceCurrent text," +
                    "CurrentTotalApparentPower text," +
                    "TheCurrentAPhaseApparentPower text," +
                    "TheCurrentBPhaseApparentPower text," +
                    "TheCurrentCPhaseApparentPower text," +
                    "UaPhaseAngle text," +
                    "UbPhaseAngle text," +
                    "UcPhaseAngle text," +
                    "IaPhaseAngle text," +
                    "IbPhaseAngle text," +
                    "IcPhaseAngle text)";
    //创建日冻结表
    private static final String CREATE_TABLE_ActiveValue =
            "create table " + "ActiveValue " + "(Id String PRIMARY KEY ," +
                    "Flag text," +
                    "Address text," +
                    "DataTime text," +
                    "ReadingTime text," +
                    "RateNumber text," +
                    "PowerIndication text," +
                    "Rate text)";
    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public CommDB(Context ctx) {
        this.context = ctx;
        this.DBHelper = new DatabaseHelper(this.context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_RealData);//创建实时数据表
            db.execSQL(CREATE_TABLE_ActiveValue);//创建日冻结数据表
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion) {
        }
    }

    public CommDB open() throws SQLException {
        this.db = this.DBHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        this.DBHelper.close();
    }

}

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
public class ActiveValueDB {
    public static final String KEY_Address = "AddressId";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;
    static final String SQLITE_TABLE = "ActiveValue";
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

    public ActiveValueDB(Context ctx) {
        this.mCtx = ctx;
    }

    public ActiveValueDB open() throws SQLException {

        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    /**
     * @param address 表计地址  终端地址默认为100000
     * @param data
     * @param flag    表计 0 表示终端数据，1表示表计数据
     * @return
     */
    public long addData(String address, List<String> data, String flag) {
        DBHelper.open();
        open();
        long createResult = 0;
        ContentValues values = new ContentValues();
        String[] split = data.get(0).split("-");
        String time = "";
        for (int i = 0; i < split.length; i++) {
            time = time + split[i];
        }
        values.put("Id", time + address);
        values.put("Address", address);
        values.put("Flag", flag);
        values.put("DataTime", data.get(0));
        values.put("ReadingTime", data.get(1));
        values.put("RateNumber", data.get(2));
        values.put("PowerIndication", data.get(3));
        int num = Integer.parseInt(data.get(2));
        String rate = "";
        for (int i = 0; i < num; i++) {
            rate = rate + " Rate" + i + ": " + data.get(4 + i);
        }
        values.put("Rate", rate);
        try {
            createResult = mDb.insert(SQLITE_TABLE, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
            DBHelper.close();
        }
        return createResult;
    }

    public boolean deleteAll() {
        DBHelper.open();
        open();
        int doneDelete = 0;
        try {
            doneDelete = mDb.delete(SQLITE_TABLE, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        close();
        DBHelper.close();
        return doneDelete > 0;
    }

    public boolean deleteByName(String addressId) {
        DBHelper.open();
        open();
        int isDelete = 0;
        String[] tName;
        tName = new String[]{addressId};
        try {
            isDelete = mDb.delete(SQLITE_TABLE, KEY_Address + "=?", tName);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            close();
            DBHelper.close();
        }
        return isDelete > 0;
    }

    public String fetchAll() {
        DBHelper.open();
        open();
        Cursor mCursor = null;
//        mCursor = mDb.query(SQLITE_TABLE, new String[]{"AddressId", "DataTime",
//                "ReadingTime", "RateNumber", "PowerIndication", "Rate"}, null, null, null, null, null);
        //+ " WHERE Flag=" + flag
        String sql = "SELECT * FROM " + SQLITE_TABLE;
        mCursor = mDb.rawQuery(sql, null);
        String temp = "";
        if (mCursor.moveToFirst()) {
            do {
                String id = mCursor.getString(mCursor.getColumnIndexOrThrow("Id"));
                String flagtepm = mCursor.getString(mCursor.getColumnIndexOrThrow("Flag"));
                String addressid = mCursor.getString(mCursor.getColumnIndexOrThrow("Address"));
                String dataTime = mCursor.getString(mCursor.getColumnIndexOrThrow("DataTime"));
                String readingTime = mCursor.getString(mCursor.getColumnIndexOrThrow("ReadingTime"));
                String RateNumber = mCursor.getString(mCursor.getColumnIndexOrThrow("RateNumber"));
                String PowerIndication = mCursor.getString(mCursor.getColumnIndexOrThrow("PowerIndication"));
                String Rate = mCursor.getString(mCursor.getColumnIndexOrThrow("Rate"));
                temp = temp + flagtepm + "\t " + addressid + "\t " + dataTime + "\t " + readingTime + "\t " + RateNumber + "\t " + PowerIndication + "\t " + Rate + "\t " + "\n";
            } while (mCursor.moveToNext());
        }
        if (mCursor != null && !mCursor.isClosed()) {
            mCursor.close();
        }
        close();
        DBHelper.close();
        return temp;
    }
}

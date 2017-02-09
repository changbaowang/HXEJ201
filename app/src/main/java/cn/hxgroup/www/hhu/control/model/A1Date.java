package cn.hxgroup.www.hhu.control.model;

import java.util.Calendar;

/**
 * Created by chenxiaojun1 on 2016/6/6.
 * A1格式解析出来的日期参数
 */
public class A1Date
{

    private int second;
    private int minute;
    private int hour;
    private int week;
    private int day;
    private int month;
    private int year;

    public A1Date(byte[] data)
    {
        if(data == null || data.length != 6)
        {
            return;
        }
        second = ((data[0] & 0xFF) >> 4) * 10 + data[0] & 0x0F;
        minute = ((data[1] & 0xFF) >> 4) * 10 + data[1] & 0x0F;
        hour = ((data[2] & 0xFF) >> 4) * 10 + data[2] & 0x0F;
        day = ((data[3] &0xFF) >> 4) * 10 + data[3] & 0x0F;
        week = (data[4] & 0xFF) >> 5;
        month = ((data[4] & 0x10) >> 4) * 10 + data[4] & 0x0F;
        year = ((data[5] & 0xFF) >> 4) * 10 + data[5] & 0x0F;
    }

    public A1Date(Calendar calendar)
    {

    }

    public byte[] toByteArray()
    {
        return null;
    }

    @Override
    public String toString()
    {
        return super.toString();
    }
}

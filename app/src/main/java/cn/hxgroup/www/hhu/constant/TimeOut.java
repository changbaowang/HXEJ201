package cn.hxgroup.www.hhu.constant;

/**
 * Created by chenxiaojun1 on 2016/6/8.
 */
public enum  TimeOut
{
    TIME_OUT_1(3),
    TIME_OUT_2(5),
    TIME_OUT_3(8),
    TIME_OUT_4(10),
    TIME_OUT_5(15);

    int mValue;

    TimeOut(int value)
    {
        mValue = value;
    }

    public int getValue()
    {
        return mValue;
    }
}

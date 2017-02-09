package cn.hxgroup.www.hhu.constant;

/**
 * Created by CXJ on 2016/6/4.
 */
public enum ResetType
{
    RESET_TYPE_DEVICE(0),//F1
    RESET_TYPE_DATA(1),//F2
    RESET_TYPE_ALL(2);//F4

    int mValue;

    ResetType(int value)
    {
        this.mValue = value;
    }
}

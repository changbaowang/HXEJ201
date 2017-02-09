package cn.hxgroup.www.hhu.control.model;

import android.net.wifi.ScanResult;

import cn.hxgroup.www.hhu.ui.interfaces.IUIScanResult;

/**
 * Created by CXJ on 2016/5/14.
 */
public class UIScanResult implements IUIScanResult
{
    private String SSID;
    private int level;

    public UIScanResult(ScanResult result)
    {
        this.SSID = result.SSID;
        this.level = result.level;
    }

    @Override
    public String getSSID()
    {
        return SSID;
    }

    @Override
    public int getLevel()
    {
        return level;
    }
}

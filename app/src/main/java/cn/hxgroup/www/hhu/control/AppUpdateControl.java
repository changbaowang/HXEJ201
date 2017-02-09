package cn.hxgroup.www.hhu.control;

import cn.hxgroup.www.hhu.business.AppConfig;
import cn.hxgroup.www.hhu.constant.DefaultConstant;
import cn.hxgroup.www.hhu.control.base.BaseControl;
import cn.hxgroup.www.hhu.ui.settings.AppUpdateActivity;
import cn.hxgroup.www.hhu.utils.CommonUtils;

/**
 * Created by CXJ on 2016/5/25.
 */
public class AppUpdateControl extends BaseControl
{
    private AppUpdateActivity mActivity;

    public AppUpdateControl(AppUpdateActivity activity)
    {
        mActivity = activity;
    }


    public void saveServerAddress(String address)
    {

    }

    public void checkUpdate()
    {

    }


    public String getServer()
    {
        String server = AppConfig.getInstance().getUpdateServer();
        if (CommonUtils.isEmpty(server))
        {
            server = DefaultConstant.DEFAULT_UPDATE_SERVER;
        }
        return server;
    }
}

package cn.hxgroup.www.hhu.control;

import cn.hxgroup.www.hhu.business.AppConfig;
import cn.hxgroup.www.hhu.constant.DefaultConstant;
import cn.hxgroup.www.hhu.control.base.BaseControl;
import cn.hxgroup.www.hhu.ui.settings.ServerEditActivity;
import cn.hxgroup.www.hhu.utils.CommonUtils;

/**
 * Created by CXJ on 2016/5/27.
 */
public class ServerEditControl extends BaseControl
{
    private ServerEditActivity mActivity;

    public ServerEditControl(ServerEditActivity activity)
    {
        mActivity = activity;
    }


    public void saveServer(String server)
    {
        if(CommonUtils.isEmpty(server))
        {
            //默认的服务器地址
            server = DefaultConstant.DEFAULT_UPDATE_SERVER;
        }
        AppConfig.getInstance().saveUpdateServer(server);
    }

    public String getServer()
    {
        String server = AppConfig.getInstance().getUpdateServer();
        if (CommonUtils.isEmpty(server))
        {
            //默认的服务器地址
            server = DefaultConstant.DEFAULT_UPDATE_SERVER;
        }
        return server;
    }

    public String getDefaultServer()
    {
        return DefaultConstant.DEFAULT_UPDATE_SERVER;
    }
}

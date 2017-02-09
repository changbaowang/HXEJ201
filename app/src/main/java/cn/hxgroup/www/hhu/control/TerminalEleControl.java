package cn.hxgroup.www.hhu.control;

import cn.hxgroup.www.hhu.control.base.BaseControl;
import cn.hxgroup.www.hhu.ui.feature.TerminalEleActivity;
import hexing.tools.GetTerminalInfoByParam;

/**
 * Created by hex170 on 2016/8/24.
 *
 */
public class TerminalEleControl extends BaseControl {
    private TerminalEleActivity mActivity;
    private GetTerminalInfoByParam getTerminalInfoByParam;

    public TerminalEleControl(TerminalEleActivity activity) {
        this.mActivity = activity;
        getTerminalInfoByParam = new GetTerminalInfoByParam(mActivity);
//        requestData();
    }
}

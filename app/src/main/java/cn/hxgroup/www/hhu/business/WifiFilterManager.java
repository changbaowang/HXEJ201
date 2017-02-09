package cn.hxgroup.www.hhu.business;

import java.util.ArrayList;
import java.util.List;

import cn.hxgroup.www.hhu.utils.CommonUtils;

/**
 * Created by CXJ on 2016/5/14.
 */
public class WifiFilterManager
{
    private static final String[] DEFAULT_FILTER_RULE = {"", ""};

    /**
     * 获取过滤规则（默认+自定义）
     * @return
     */
    public static List<String> getFilterRules()
    {
        List<String> list = new ArrayList<>();
        if(CommonUtils.isEmpty(list))
        {
            //返回默认的规则
            return defaultRules();
        }
        return list;
    }

    private static List<String> defaultRules()
    {
        List<String> list = new ArrayList<>();
        for (String s : DEFAULT_FILTER_RULE)
        {
            list.add(s);
        }
        return list;
    }
}

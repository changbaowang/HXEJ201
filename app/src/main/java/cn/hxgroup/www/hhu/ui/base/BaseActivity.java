package cn.hxgroup.www.hhu.ui.base;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by CXJ on 2016/5/8.
 * 所有界面Activity都继承于该类，用于统一初始化Activity流程
 */
public abstract class BaseActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResourceId());
        initView();
        initData(savedInstanceState);
        initListener();
    }

    /**
     * 获取布局文件ID，必须返回一个存在的布局的ID
     * @return
     */
    abstract protected int getResourceId();

    /**
     * 一般用于初始化控件对象
     */
    abstract protected void initView();

    /**
     * 一般用于将数据绑定到UI控件对象上
     * @param savedInstanceState
     */
    abstract  protected void initData(Bundle savedInstanceState);

    /**
     * 对UI控件设置手势、点击登监听事件
     */
    abstract protected void initListener();

    protected void showLoadingDialog()
    {}

    protected void dismissLoadingDialog()
    {}

    public void showToast(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void showToast(int res)
    {
        try
        {
            Toast.makeText(this, res, Toast.LENGTH_SHORT).show();
        } catch (Resources.NotFoundException e)
        {
            e.printStackTrace();
        }
    }
}

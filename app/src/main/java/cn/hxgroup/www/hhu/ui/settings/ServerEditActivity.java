package cn.hxgroup.www.hhu.ui.settings;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.control.ServerEditControl;
import cn.hxgroup.www.hhu.ui.base.BaseActivity;

/**
 * Created by CXJ on 2016/5/27.
 */
public class ServerEditActivity extends BaseActivity implements View.OnClickListener
{
    private View mBackBtn;
    private View mResetBtn;
    private EditText mServerEt;
    private ServerEditControl mControl;

    @Override
    protected int getResourceId()
    {
        return R.layout.activity_server_edit;
    }

    @Override
    protected void initView()
    {
        mBackBtn = findViewById(R.id.backBtn);
        mResetBtn = findViewById(R.id.resetTv);
        mServerEt = (EditText) findViewById(R.id.serverEt);
    }

    @Override
    protected void initData(Bundle saveedInstanceState)
    {
        mControl = new ServerEditControl(this);
        mServerEt.setText(mControl.getServer());
    }

    @Override
    protected void initListener()
    {
        mBackBtn.setOnClickListener(this);
        mResetBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.backBtn:
                mControl.saveServer(mServerEt.getText().toString());
                finish();
                break;
            case R.id.resetTv:
                mServerEt.setText(mControl.getDefaultServer());
                mControl.saveServer(mServerEt.getText().toString());
                break;
        }
    }
}

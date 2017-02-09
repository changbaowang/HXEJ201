package cn.hxgroup.www.hhu.ui.feature;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.control.TransformerMonitorControl;
import cn.hxgroup.www.hhu.ui.base.BaseActivity;

public class TransformerMonitoringActivity extends BaseActivity implements View.OnClickListener {
    private TextView mFlagTv, mValueTv;
    private ImageView mBackIv;
    private TransformerMonitorControl mControl;

    @Override
    protected int getResourceId() {
        return R.layout.activity_transformer_monitoring;
    }

    @Override
    protected void initView() {
        mFlagTv = (TextView) findViewById(R.id.transformer_monitoring_flag_tv);
        mValueTv = (TextView) findViewById(R.id.transformer_monitoring_value_tv);
        mBackIv = (ImageView) findViewById(R.id.point_tranformer_back_iv);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mControl = new TransformerMonitorControl(this);
    }

    @Override
    protected void initListener() {
        mBackIv.setOnClickListener(this);
    }

    public void upDate(List<String> data) {
        mFlagTv.setText(data.get(0));
        mValueTv.setText(data.get(1));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.point_tranformer_back_iv:
                finish();
                break;
        }
    }
}

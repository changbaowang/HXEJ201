package cn.hxgroup.www.hhu.ui.feature;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.control.TerminalStateInfoControl;
import cn.hxgroup.www.hhu.ui.base.BaseActivity;

/**
 * Created by HEX170 on 2016/8/2.
 * 终端集中抄表状态信息界面
 */
public class TerminalStateInfoActivity extends BaseActivity {
    private ImageView mBackIv;
    private TerminalStateInfoControl mControl;
    private TextView totalRSTv;
    private TextView succeedRSTv;
    private TextView totalPLCTv;
    private TextView succeedPLCTv;

    @Override
    protected int getResourceId() {
        return R.layout.activity_terminal_state_info;
    }

    @Override
    protected void initView() {
        mBackIv = (ImageView) findViewById(R.id.terminal_state_info_back_iv);
        totalRSTv = (TextView) findViewById(R.id.meter_state_info_rs_total_tv);
        succeedRSTv = (TextView) findViewById(R.id.meter_state_info_rs_succeeful_tv);
        totalPLCTv = (TextView) findViewById(R.id.meter_state_info_plc_total_tv);
        succeedPLCTv = (TextView) findViewById(R.id.meter_state_info_plc_succeeful_tv);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mControl = new TerminalStateInfoControl(this);
    }

    public void update(List<String> realdata) {
        if (realdata.size() > 3) {
            totalRSTv.setText(realdata.get(0));
            succeedRSTv.setText(realdata.get(1));
            totalPLCTv.setText(realdata.get(2));
            succeedPLCTv.setText(realdata.get(3));
        }
    }

    @Override
    protected void initListener() {
        mBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

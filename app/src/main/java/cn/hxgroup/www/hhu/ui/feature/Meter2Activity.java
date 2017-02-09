package cn.hxgroup.www.hhu.ui.feature;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.adapter.Meter2Adapter;
import cn.hxgroup.www.hhu.control.Meter2Control;
import cn.hxgroup.www.hhu.ui.base.BaseActivity;

/**
 * Created by HEX170 on 2016/8/2.
 * 选择剩余测量点号界面
 */
public class Meter2Activity extends BaseActivity implements View.OnClickListener {
    private GridView gridView;
    private ImageView backIv;
    private TextView numTv;
    private Button okBtn;
    public Meter2Adapter adapter;
    public List<Integer> data = new ArrayList<>();
    private Meter2Control control;
    private int id = 0;

    @Override
    protected int getResourceId() {
        return R.layout.activity_meter2;
    }

    @Override
    protected void initView() {
        gridView = (GridView) findViewById(R.id.gridview);
        backIv = (ImageView) findViewById(R.id.terminal_add_meter2_backiv);
        numTv = (TextView) findViewById(R.id.meter2_pn_tv);
        okBtn = (Button) findViewById(R.id.meter2_pn_btn);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        control = new Meter2Control(this);
        adapter = new Meter2Adapter(data, this);
        gridView.setAdapter(adapter);
    }

    @Override
    protected void initListener() {
        backIv.setOnClickListener(this);
        okBtn.setOnClickListener(this);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                id = data.get(i);
                numTv.setText(getResources().getString(R.string.kMeterNum2) + id);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.terminal_add_meter2_backiv:
                finish();
                break;
            case R.id.meter2_pn_btn:
                if (id == 0) {
                    showToast(getResources().getString(R.string.kMeterchoiNum));
                } else {
                    Intent intent = new Intent(Meter2Activity.this, AddMeterActivity.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                    finish();
                }
                break;
        }
    }
}

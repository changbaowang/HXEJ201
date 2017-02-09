package cn.hxgroup.www.hhu.ui.feature;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.adapter.InstructionsAdapter;

/**
 * Created by HEX170 on 2016/8/22.
 * 引导界面
 */
public class InstructionsActivity extends Activity {
    private ListView mListView;
    private ImageView mBackIv;
    private List<String> data;
    private InstructionsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
        mListView = (ListView) findViewById(R.id.instructions_listview);
        mBackIv = (ImageView) findViewById(R.id.backIv);
        data = new ArrayList<>();
        String[] function = getResources().getStringArray(R.array.function);
        for (int i = 0; i < function.length; i++) {
            data.add(function[i]);
        }
        mAdapter = new InstructionsAdapter(this, data);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.putExtra("type", i);
                intent.setClass(InstructionsActivity.this, Instructions2Activity.class);
                startActivity(intent);
            }
        });
        mBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

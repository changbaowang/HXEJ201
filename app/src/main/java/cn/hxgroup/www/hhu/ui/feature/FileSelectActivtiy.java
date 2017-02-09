package cn.hxgroup.www.hhu.ui.feature;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.ui.base.BaseActivity;
import cn.hxgroup.www.hhu.utils.CommonUtils;

/**
 * Created by CXJ on 2016/6/11.
 */
public class FileSelectActivtiy extends BaseActivity implements View.OnClickListener {
    private static final String EXP = "";//后缀名
    private ListView mListView;
    private File mRootFile;
    private View mNoFileView;
    private View mBackBtn;
    private EditText mSearchEt;
    private FileAdapter mAdapter;

    @Override
    protected int getResourceId() {
        return R.layout.activity_file_select;
    }

    @Override
    protected void initView() {
        mListView = (ListView) findViewById(R.id.listview);
        mNoFileView = findViewById(R.id.nofileLayout);
        mBackBtn = findViewById(R.id.backBtn);
    }

    private List<File> data = new ArrayList<>();

    @Override
    protected void initData(Bundle savedInstanceState) {
        mRootFile = new File(Environment.getExternalStorageDirectory() + "/HHU/LocalUpdate");
//        mRootFile = new File(getFilesDir() + "/HHU/LocalUpdate");
        mAdapter = new FileAdapter(this, data);
//        addFile();
        if (!mRootFile.exists() || CommonUtils.isEmpty(mRootFile.listFiles())) {
            showNoFile();
            return;
        } else {
            File[] files = mRootFile.listFiles();
            for (File f : files) {
                data.add(f);
            }
            mAdapter.notifyDataSetChanged();
        }
    }

    private void addFile() {
//        mRootFile = new File(getFilesDir() + "HHU");
        for (int i = 0; i < 10; i++) {
            File file = new File(getFilesDir() + "/");
            try {
                file.createNewFile();
                showToast("creat" + i);
            } catch (IOException e) {
                e.printStackTrace();
                showToast("IOException" + i);
            }
        }
    }

    @Override
    protected void initListener() {
        mBackBtn.setOnClickListener(this);
    }

    private void showNoFile() {
        mListView.setVisibility(View.GONE);
        mNoFileView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backBtn:
                finish();
                break;
        }
    }

    private List<File> filterFile(String key) {
        List<File> list = new ArrayList<>();

        return list;
    }
}

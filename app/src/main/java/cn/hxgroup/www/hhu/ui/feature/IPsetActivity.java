package cn.hxgroup.www.hhu.ui.feature;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.control.model.IPsetControl;
import cn.hxgroup.www.hhu.ui.base.BaseActivity;

/**
 * Created by HEX170 on 2016/8/2.
 * IP界面
 */
public class IPsetActivity extends BaseActivity implements View.OnClickListener {
    private ImageView backiv;
    private EditText mMainipEt, mMainipEt2, mMainipEt3;
    private EditText mMainipEt4, mMainipEt5;
    //    private EditText mTerminalipEt, mTerminalipEt1, mTerminalipEt2, mTerminalipEt4;
//    private EditText mTerminalipEt6, mTerminalipEt7, mTerminalipEt8;
//    private Spinner mTerminalipSp3, mTerminalipSp5;
//    private Button mSetMainbtn, mSetTerminalBtn;
    private Button mSetMainbtn;
    private IPsetControl mControl;
    //用于固定输入数据的格式
    private SimpleDateFormat mSimpleDateFormat4;
    private SimpleDateFormat mSimpleDateFormat3;
    private Date date = null;

    @Override
    protected int getResourceId() {
        return R.layout.activity_ipset;
    }

    @Override
    protected void initView() {
        backiv = (ImageView) findViewById(R.id.ipset_backiv);
        mMainipEt = (EditText) findViewById(R.id.ipset_mainip_et);
        mMainipEt2 = (EditText) findViewById(R.id.ipset_mainip_port_et);
        mMainipEt3 = (EditText) findViewById(R.id.ipset_mainip2_et);
        mMainipEt4 = (EditText) findViewById(R.id.ipset_mainip2_port_et);
        mMainipEt5 = (EditText) findViewById(R.id.ipset_mainip3_et);
//        mTerminalipEt = (EditText) findViewById(R.id.ipset_terminalip_et);
//        mTerminalipEt1 = (EditText) findViewById(R.id.ipset_terminalip_et1);
//        mTerminalipEt2 = (EditText) findViewById(R.id.ipset_terminalip_et2);
//        mTerminalipSp3 = (Spinner) findViewById(R.id.ipset_terminalip_et3);
//        mTerminalipEt4 = (EditText) findViewById(R.id.ipset_terminalip_et4);
//        mTerminalipSp5 = (Spinner) findViewById(R.id.ipset_terminalip_et5);
//        mTerminalipEt6 = (EditText) findViewById(R.id.ipset_terminalip_et6);
//        mTerminalipEt7 = (EditText) findViewById(R.id.ipset_terminalip_et7);
//        mTerminalipEt8 = (EditText) findViewById(R.id.ipset_terminalip_et8);
        mSetMainbtn = (Button) findViewById(R.id.setwifi_setmainip_btn);
//        mSetTerminalBtn = (Button) findViewById(R.id.setwifi_setterminalip_btn);
        mSimpleDateFormat3 = new SimpleDateFormat("yyyy.MM.dd.HH");
        mSimpleDateFormat4 = new SimpleDateFormat("yyyy.MM.dd.HH.mm");
    }

//    private SpinnerAdapter adapter1;
//    private SpinnerAdapter adapter2;
//    List<String> data1 = new ArrayList<>();
//    List<String> data2 = new ArrayList<>();

    @Override
    protected void initData(Bundle savedInstanceState) {
        mControl = new IPsetControl(this);
        mControl.getMainIp();
//        Resources res = getResources();
//        String[] stringArray = res.getStringArray(R.array.IpsetSpinner1);
//        data1.clear();
//        data2.clear();
//        for (int i = 0; i < stringArray.length; i++) {
//            data1.add(stringArray[i]);
//        }
//        Resources res1 = getResources();
//        String[] stringArray1 = res1.getStringArray(R.array.IpsetSpinner2);
//        for (int i = 0; i < stringArray1.length; i++) {
//            data2.add(stringArray1[i]);
//
//        }
//        adapter1 = new SpinnerAdapter(data1, this);
//        adapter2 = new SpinnerAdapter(data2, this);
//        mTerminalipSp3.setAdapter(adapter1);
//        mTerminalipSp5.setAdapter(adapter2);
    }

    public void setUpMainData(List<String> realdata) {
        if (realdata.size() > 2) {
            mMainipEt.setText(realdata.get(0));
            mMainipEt2.setText(realdata.get(1));
            mMainipEt3.setText(realdata.get(2));
            mMainipEt4.setText(realdata.get(3));
            mMainipEt5.setText(realdata.get(4));
        }
    }

//    public void setUpTerminalData(List<String> realdata) {
//        if (realdata.size() > 7) {
//            mTerminalipEt.setText(realdata.get(0));
//            mTerminalipEt1.setText(realdata.get(1));
//            mTerminalipEt2.setText(realdata.get(2));
//            mTerminalipSp3.setSelection(Integer.parseInt(realdata.get(3)));
//            mTerminalipEt4.setText(realdata.get(4));
//            mTerminalipSp5.setSelection(Integer.parseInt(realdata.get(5)));
//            mTerminalipEt6.setText(realdata.get(6));
//            mTerminalipEt7.setText(realdata.get(7));
//            mTerminalipEt8.setText(realdata.get(8));
//        }
//    }

    //得到界面的终端IP信息,并作数据格式判断
//    public List<String> getUpTerminalData() {
//        List<String> realdata = new ArrayList<>();
//        //固定格式
//        String mTerminalipParam = mTerminalipEt.getText().toString();
//        try {
//            date = mSimpleDateFormat3.parse(mTerminalipParam);
//            realdata.add(mTerminalipParam);
//        } catch (ParseException e) {
//            //提示用户输入的数据格式不正确
//            showToast(R.string.kSetIpInvalid);
//            return null;
//        }
//        String mTerminalipParam1 = mTerminalipEt1.getText().toString();
//        try {
//            date = mSimpleDateFormat3.parse(mTerminalipParam1);
//            realdata.add(mTerminalipParam1);
//        } catch (ParseException e) {
//            //提示用户输入的数据格式不正确
//            showToast(R.string.kSetIpInvalid);
//            return null;
//        }
//        String mTerminalipParam2 = mTerminalipEt2.getText().toString();
//        try {
//            date = mSimpleDateFormat3.parse(mTerminalipParam2);
//            realdata.add(mTerminalipParam2);
//        } catch (ParseException e) {
//            //提示用户输入的数据格式不正确
//            showToast(R.string.kSetIpInvalid);
//            return null;
//        }
//        realdata.add(mSpinner3Data + "");
//        String mTerminalipParam3 = mTerminalipEt4.getText().toString();
//        try {
//            date = mSimpleDateFormat4.parse(mTerminalipParam3);
//            realdata.add(mTerminalipParam3);
//        } catch (ParseException e) {
//            //提示用户输入的数据格式不正确
//            showToast(R.string.kSetIpInvalid);
//            return null;
//        }
//        realdata.add(mSpinner5Data + "");
//        realdata.add(mTerminalipEt6.getText().toString());
//        realdata.add(mTerminalipEt7.getText().toString());
//        realdata.add(mTerminalipEt8.getText().toString());
//        return realdata;
//    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ipset_backiv:
                finish();
                break;
            case R.id.setwifi_setmainip_btn:
                mControl.setMianIp();
                break;
//            case R.id.setwifi_setterminalip_btn:
//                mControl.setTerminalIp();
//                break;
        }
    }

    //得到界面的主站IP信息,并作数据格式判断
    public List<String> getMainIpData() {
        List<String> data = new ArrayList<>();
        String temp1 = mMainipEt.getText().toString();
        try {
            date = mSimpleDateFormat3.parse(temp1);
            boolean b = mControl.checkData(temp1, 255);
            if (!b) {
                return null;
            }
            data.add(temp1);
        } catch (ParseException e) {
            //提示用户输入的数据格式不正确
            showToast(R.string.kSetIpInvalid);
            return null;
        }
        data.add(mMainipEt2.getText().toString());
        String temp2 = mMainipEt3.getText().toString();
        try {
            date = mSimpleDateFormat3.parse(temp2);
            boolean b = mControl.checkData(temp2, 255);
            if (!b) {
                return null;
            }
            data.add(temp2);
        } catch (ParseException e) {
            //提示用户输入的数据格式不正确
            showToast(R.string.kSetIpInvalid);
            return null;
        }
        data.add(mMainipEt4.getText().toString());
        data.add(mMainipEt5.getText().toString());
        return data;
    }

//    private int mSpinner3Data = 0;
//    private int mSpinner5Data = 0;

    @Override
    protected void initListener() {
        backiv.setOnClickListener(this);
        mSetMainbtn.setOnClickListener(this);
//        mSetTerminalBtn.setOnClickListener(this);
//        mTerminalipSp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                mSpinner3Data = i;
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//        mTerminalipSp5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                mSpinner5Data = i;
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
    }
}

package cn.hxgroup.www.hhu.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import cn.hxgroup.www.hhu.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wifiBtn://wifi通道
//                goWifiActivity();
                break;
            case R.id.channelBtn://channel通道
//                goChannelActivity();
                break;
        }
    }

//    private void goWifiActivity()
//    {
//        Intent intent = new Intent();
//        intent.setClass(this, WifiActivity.class);
//        startActivity(intent);
//        finish();
//    }
//
//    private void goChannelActivity()
//    {
//        Intent intent = new Intent();
//        intent.setClass(this, WifiActivity.class);
//        startActivity(intent);
//        finish();
//    }
}

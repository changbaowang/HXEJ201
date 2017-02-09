package cn.hxgroup.www.hhu.control;

import android.os.Handler;
import android.os.Message;

import java.util.Random;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.control.base.BaseControl;
import cn.hxgroup.www.hhu.tools.SendMessageUtil;
import cn.hxgroup.www.hhu.ui.feature.SIMActivity;

/**
 * Created by hex170 on 2016/8/29.
 * SIM卡认证
 */
public class SimControl extends BaseControl {
    private SIMActivity mActivity;
    private String verification = "";
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mActivity.update(msg.arg1);
        }
    };

    public SimControl(SIMActivity mActivity) {
        this.mActivity = mActivity;
    }

    //发短信进行验证
    public void dellPhone(String phone) {
        if ("".equals(phone)) {
            mActivity.showToast(mActivity.getResources().getString(R.string.kSIMinputPhone));
        } else {
            verification = getVerification();
            String msg = mActivity.getResources().getString(R.string.kSIMSendMsg) + verification;
            SendMessageUtil.sendMessage(mActivity, msg, phone);
            start();
        }
    }

    //验证码60s后才能再发
    private void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int t = 60;
                while (t > 0) {
                    try {
                        Thread.sleep(1000);
                        Message msg = new Message();
                        t--;
                        msg.arg1 = t;
                        handler.sendMessage(msg);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    //判断是否验证成功
    public boolean pullVerification(String str) {
        if ("".equals(verification) || "".equals(str)) {
            mActivity.showToast(mActivity.getResources().getString(R.string.kSIMVerification));
            return false;
        } else {
            if (verification.equals(str)) {
                mActivity.showToast(mActivity.getResources().getString(R.string.kSIMSucceed));
                return true;
            } else {
                mActivity.showToast(mActivity.getResources().getString(R.string.kSIMFail));
                return false;
            }
        }
    }

    //随机生成6位验证码
    private String getVerification() {
        String str = "";
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int i1 = random.nextInt(10);
            str = str + i1;
        }
        return str;
    }
}

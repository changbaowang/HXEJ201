package cn.hxgroup.www.hhu.ui.feature;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.adapter.Instructions2Adapter;

/**
 * Created by HEX170 on 2016/8/22.
 * APP功能介绍
 */
public class Instructions2Activity extends Activity {
    private ViewPager mViewPage;
    private List<Integer> mImageView;
    private ImageView mBackIv;
    private List<String> mData;
    // 底部小点的图片
    private LinearLayout llPoint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions2);
        int i = getIntent().getIntExtra("type", 0);
        initview();
        initdata(i);
        initoper();
        addPoint();
        initListener();
    }

    private void initListener() {
        mBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void initdata(int k) {
        mData = new ArrayList<>();
        mImageView = new ArrayList<>();
        String language = getResources().getConfiguration().locale.getLanguage();
        if ("en".equals(language)) {
            languageEn(k);
        } else {
            languageZh(k);
        }
        mViewPage.setAdapter(new Instructions2Adapter(this, mData, mImageView));
    }

    //加载国语APP功能介绍图片
    private void languageZh(int k) {
        switch (k) {
            case 0:
                mImageView.add(R.drawable.b201);
                mImageView.add(R.drawable.b202);
                mImageView.add(R.drawable.b203);
                mImageView.add(R.drawable.b204);
                mImageView.add(R.drawable.b205);
                mImageView.add(R.drawable.b206);
                mImageView.add(R.drawable.b207);
                mImageView.add(R.drawable.b208);
                mImageView.add(R.drawable.b204);
                break;
            case 1:
                mImageView.add(R.drawable.b301);
                mImageView.add(R.drawable.b302);
                mImageView.add(R.drawable.b303);
                break;
            case 2:
                mImageView.add(R.drawable.b401);
                mImageView.add(R.drawable.b402);
                mImageView.add(R.drawable.b403);
                mImageView.add(R.drawable.b404);
                mImageView.add(R.drawable.b405);
                mImageView.add(R.drawable.b406);
                mImageView.add(R.drawable.b407);
                mImageView.add(R.drawable.b408);
                mImageView.add(R.drawable.b409);
                mImageView.add(R.drawable.b410);
                mImageView.add(R.drawable.b411);
                mImageView.add(R.drawable.b412);
                mImageView.add(R.drawable.b413);
                mImageView.add(R.drawable.b414);
                break;
            case 3:
                mImageView.add(R.drawable.b601);
                mImageView.add(R.drawable.b602);
                mImageView.add(R.drawable.b603);
                mImageView.add(R.drawable.b604);
                break;
            case 4:
                mImageView.add(R.drawable.c11);
                mImageView.add(R.drawable.c12);
                mImageView.add(R.drawable.c13);
                mImageView.add(R.drawable.c14);
                mImageView.add(R.drawable.c15);
                break;
            case 5:
                mImageView.add(R.drawable.c41);
                mImageView.add(R.drawable.c42);
                mImageView.add(R.drawable.c43);
                mImageView.add(R.drawable.c44);
                break;
            case 6:
                mImageView.add(R.drawable.c51);
                mImageView.add(R.drawable.c52);
                mImageView.add(R.drawable.c53);
                mImageView.add(R.drawable.c54);
                mImageView.add(R.drawable.c53);
                break;
            case 7:
                mImageView.add(R.drawable.c61);
                mImageView.add(R.drawable.c62);
                mImageView.add(R.drawable.c63);
                mImageView.add(R.drawable.c64);
                mImageView.add(R.drawable.c65);
                mImageView.add(R.drawable.c66);
                break;
        }
    }

    //加载英语APP功能介绍图片
    private void languageEn(int k) {
        switch (k) {
            case 0:
                mImageView.add(R.drawable.eb201);
                mImageView.add(R.drawable.eb202);
                mImageView.add(R.drawable.eb203);
                mImageView.add(R.drawable.eb204);
                mImageView.add(R.drawable.eb205);
                mImageView.add(R.drawable.eb206);
                mImageView.add(R.drawable.eb207);
                mImageView.add(R.drawable.eb208);
                mImageView.add(R.drawable.eb204);
                break;
            case 1:
                mImageView.add(R.drawable.eb301);
                mImageView.add(R.drawable.eb302);
                mImageView.add(R.drawable.eb303);
                break;
            case 2:
                mImageView.add(R.drawable.eb401);
                mImageView.add(R.drawable.eb402);
                mImageView.add(R.drawable.eb403);
                mImageView.add(R.drawable.eb404);
                mImageView.add(R.drawable.eb405);
                mImageView.add(R.drawable.eb406);
                mImageView.add(R.drawable.eb407);
                mImageView.add(R.drawable.eb408);
                mImageView.add(R.drawable.eb409);
                mImageView.add(R.drawable.eb410);
                mImageView.add(R.drawable.eb411);
                mImageView.add(R.drawable.eb412);
                mImageView.add(R.drawable.eb413);
                mImageView.add(R.drawable.eb414);
                break;
            case 3:
                mImageView.add(R.drawable.eb601);
                mImageView.add(R.drawable.eb602);
                mImageView.add(R.drawable.eb603);
                mImageView.add(R.drawable.eb604);
                break;
            case 4:
                mImageView.add(R.drawable.ec11);
                mImageView.add(R.drawable.ec12);
                mImageView.add(R.drawable.ec13);
                mImageView.add(R.drawable.ec14);
                mImageView.add(R.drawable.ec15);
                break;
            case 5:
                mImageView.add(R.drawable.ec41);
                mImageView.add(R.drawable.ec42);
                mImageView.add(R.drawable.ec43);
                mImageView.add(R.drawable.ec44);
                break;
            case 6:
                mImageView.add(R.drawable.ec51);
                mImageView.add(R.drawable.ec52);
                mImageView.add(R.drawable.ec53);
                mImageView.add(R.drawable.ec54);
                mImageView.add(R.drawable.ec53);
                break;
            case 7:
                mImageView.add(R.drawable.ec61);
                mImageView.add(R.drawable.ec62);
                mImageView.add(R.drawable.ec63);
                mImageView.add(R.drawable.ec64);
                mImageView.add(R.drawable.ec65);
                mImageView.add(R.drawable.ec66);
                break;
        }
    }

    private void initoper() {

        mViewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                monitorPoint(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });

    }

    private void initview() {
        mViewPage = (ViewPager) findViewById(R.id.viewpage);
        llPoint = (LinearLayout) findViewById(R.id.llPoint);
        mBackIv = (ImageView) findViewById(R.id.backIv);
    }

    //添加小圆点
    private void addPoint() {
        for (int i = 0; i < mImageView.size(); i++) {
            LinearLayout.LayoutParams pointParams = new LinearLayout.LayoutParams(
                    ViewPager.LayoutParams.WRAP_CONTENT, ViewPager.LayoutParams.WRAP_CONTENT);
            if (i < 1) {
                pointParams.setMargins(0, 0, 0, 0);
            } else {
                pointParams.setMargins(10, 0, 0, 0);
            }
            final int temp = i;
            ImageView iv = new ImageView(this);
            iv.setLayoutParams(pointParams);
            iv.setBackgroundResource(R.drawable.ic_dot_default);
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mViewPage.setCurrentItem(temp);
                }
            });
            llPoint.addView(iv);
        }
        llPoint.getChildAt(0).setBackgroundResource(R.drawable.ic_dot_selected);
    }

    /**
     * 判断小圆点
     *
     * @param position
     */
    private void monitorPoint(int position) {
        for (int i = 0; i < mImageView.size(); i++) {
            if (i == position) {
                llPoint.getChildAt(position).setBackgroundResource(R.drawable.ic_dot_selected);
            } else {
                llPoint.getChildAt(i).setBackgroundResource(R.drawable.ic_dot_default);
            }
        }
    }
}

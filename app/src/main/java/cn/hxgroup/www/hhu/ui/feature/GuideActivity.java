package cn.hxgroup.www.hhu.ui.feature;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.hxgroup.www.hhu.R;
import cn.hxgroup.www.hhu.adapter.GuideViewAdapter;
import cn.hxgroup.www.hhu.tools.SharedPreferencesUtils;
import cn.hxgroup.www.hhu.ui.base.BaseActivity;
import cn.hxgroup.www.hhu.ui.mainview.LoginActivity;

/**
 * Created by hex170 on 2016/9/18.
 * 引导界面
 */
public class GuideActivity extends BaseActivity {
    private ViewPager viewPage;
    // 图片
    private int[] imageView = {R.drawable.instr1, R.drawable.instr2,
            R.drawable.instr3, R.drawable.instr4};
    private List<View> list;
    // 底部小点的图片
    private LinearLayout llPoint;
    //立即进入按钮
    private TextView textView;

    @Override
    protected int getResourceId() {
        return R.layout.activity_guide;
    }

    @Override
    protected void initView() {
        boolean firstLogin = SharedPreferencesUtils.getBolleanState(this, "FirstLogin");
        if (!firstLogin) {
            SharedPreferencesUtils.setBolleanState(this, "FirstLogin", true);
        } else {
            startActivity(new Intent(GuideActivity.this, LoginActivity.class));
//            startActivity(new Intent(GuideActivity.this, MainViewActivity.class));
            finish();
        }
        viewPage = (ViewPager) findViewById(R.id.viewpage);
        llPoint = (LinearLayout) findViewById(R.id.llPoint);
        textView = (TextView) findViewById(R.id.guideTv);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        addView();
        addPoint();
    }

    @Override
    protected void initListener() {
        // 进入按钮
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                startActivity(new Intent(GuideActivity.this, LoginActivity.class));
                finish();
            }
        });
        // 2.监听当前显示的页面，将对应的小圆点设置为选中状态，其它设置为未选中状态
        viewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

    /**
     * 添加图片到view
     */
    private void addView() {
        list = new ArrayList<View>();
        // 将imageview添加到view
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.MATCH_PARENT);
        for (int i = 0; i < imageView.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setLayoutParams(params);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            iv.setImageResource(imageView[i]);
            list.add(iv);
        }
        // 加入适配器
        viewPage.setAdapter(new GuideViewAdapter(list));

    }

    /**
     * 添加小圆点
     */
    private void addPoint() {
        // 1.根据图片多少，添加多少小圆点
        for (int i = 0; i < imageView.length; i++) {
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
                    viewPage.setCurrentItem(temp);
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
        for (int i = 0; i < imageView.length; i++) {
            if (i == position) {
                llPoint.getChildAt(position).setBackgroundResource(R.drawable.ic_dot_selected);
            } else {
                llPoint.getChildAt(i).setBackgroundResource(R.drawable.ic_dot_default);
            }
        }
        // 3.当滑动到最后一个添加按钮点击进入，
        if (position == imageView.length - 1) {
            textView.setVisibility(View.VISIBLE);
        } else {
            textView.setVisibility(View.GONE);
        }
    }
}

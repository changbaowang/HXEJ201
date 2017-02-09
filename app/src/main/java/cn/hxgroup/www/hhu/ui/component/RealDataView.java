package cn.hxgroup.www.hhu.ui.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import java.util.List;

import cn.hxgroup.www.hhu.R;

/**
 * Created by CXJ on 2016/6/3.
 */
public class RealDataView extends FrameLayout {
    private View mUabUa;
    private View mUb;
    private View mUcbUb;
    private View mIa;
    private View mIb;
    private View mIc;

    public RealDataView(Context context) {
        super(context);
    }

    public RealDataView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RealDataView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void update(List<Float> realdata) {
        if (realdata.size() < 6) {
            return;
        }
        //realdata.get(0)
        mUabUa.setRotation(realdata.get(0));
        mUb.setRotation(realdata.get(1));
        mUcbUb.setRotation(realdata.get(2));
        mIa.setRotation(realdata.get(3));
        mIb.setRotation(realdata.get(4));
        mIc.setRotation(realdata.get(5));
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mUabUa = findViewById(R.id.UabUall);
        mUb = findViewById(R.id.Ubll);
        mUcbUb = findViewById(R.id.UcbUbll);
        mIa = findViewById(R.id.Iall);
        mIb = findViewById(R.id.Ibll);
        mIc = findViewById(R.id.Icll);
    }
}

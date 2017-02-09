package cn.hxgroup.www.hhu.ui.selfview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by hex170 on 2016/8/12.
 */
public class RDListview extends ListView {
    public RDListview(Context context) {
        this(context, null);
    }

    public RDListview(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RDListview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}

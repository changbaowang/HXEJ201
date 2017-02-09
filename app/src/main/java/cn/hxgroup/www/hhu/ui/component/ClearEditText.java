package cn.hxgroup.www.hhu.ui.component;/**
 * Created by chenxiaojun1 on 2015/5/8.
 * ?user chenxiaojun1
 * ?date 2015/5/8
 */

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;

import cn.hxgroup.www.hhu.R;


/**
 * 带清除按钮的编辑框
 * 仿IOS输入框：enable：false时，删除按钮不可见 ; enable：true时，输入框有字符，则删除按钮可见
 * Created by chenxiaojun1 on 2015/5/8.
 * ?user chenxiaojun1
 * ?date 2015/5/8
 */
public class ClearEditText extends EditText implements View.OnFocusChangeListener, TextWatcher
{
    private EditTextImeBackListener mOnImeBack;
    // 删除按钮的引用
    private Drawable mClearDrawable;

    private String mCacheText = "";

    public ClearEditText(Context context)
    {
        this(context, null);
    }

    public ClearEditText(Context context, AttributeSet attrs)
    {
//        this(context, attrs, android.R.attr.editTextStyle);
        super(context, attrs);
        init();
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        init();
    }

    private void init()
    {
        //删除按钮图片paddingRight为28dp
        int paddingRightPx =
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
        setPadding(getPaddingLeft(), getPaddingTop(), paddingRightPx, getPaddingBottom());
        // 获取EditText的DrawableRight,假如没有设置我们就使用默认的图片
        mClearDrawable = getCompoundDrawables()[2];
        if (mClearDrawable == null)
        {
            mClearDrawable = getResources().getDrawable(R.drawable.emotionstore_progresscancelbtn);
        }
        mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(), mClearDrawable.getIntrinsicHeight());
        setOnFocusChangeListener(this);
        addTextChangedListener(this);
    }

    public void setOnEditTextImeBackListener(EditTextImeBackListener listener)
    {
        mOnImeBack = listener;
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event)
    {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP)
        {
            if (mOnImeBack != null)
                mOnImeBack.onImeBack(this, this.getText().toString());
        }
        return super.dispatchKeyEvent(event);
    }

    /**
     * 因为我们不能直接给EditText设置点击事件，所以我们用记住我们按下的位置来模拟点击事件 当我们按下的位置 在 EditText的宽度 - 图标到控件右边的间距 - 图标的宽度 和 EditText的宽度 -
     * 图标到控件右边的间距之间我们就算点击了图标，竖直方向没有考虑
     */
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {

        if (event.getAction() == MotionEvent.ACTION_UP)
        {
            boolean touchable =
                    event.getX() > (getWidth() - getPaddingRight() - mClearDrawable.getIntrinsicWidth())
                            && (event.getX() < ((getWidth() - getPaddingRight())));
            if (touchable)
            {
                if (getCompoundDrawables()[2] != null) {
                    this.setText("");
                }
            }
            else
            {

//                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);


            }
        }

        return super.onTouchEvent(event);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus)
    {
        hasFocus = hasFocus && (mCacheText.length() > 0);
        setClearIconVisible(hasFocus);
    }

    /**
     * 设置清除图标的可见性
     *
     * @param visible
     */
    public void setClearIconVisible(boolean visible)
    {
        Drawable right = visible ? mClearDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
    }

    /**
     * 监听编辑框内容变化
     */
    @Override
    public void onTextChanged(CharSequence s, int start, int count, int after)
    {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after)
    {

    }

    @Override
    public void afterTextChanged(Editable s)
    {
        mCacheText = s.toString();
        if (isEnabled())
        {
            setClearIconVisible(s.length() > 0);
        }
    }

    /**
     * 设置晃动动画
     */
    public void setShakeAnimation()
    {
        this.setAnimation(shakeAnimation(5));
    }

    /**
     * 晃动动画
     *
     * @param counts 1秒晃动几下
     * @return
     */
    public static Animation shakeAnimation(int counts)
    {
        Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
        translateAnimation.setInterpolator(new CycleInterpolator(counts));
        translateAnimation.setDuration(1000);
        return translateAnimation;
    }

    /**
     * enable：false时，删除按钮不可见 enable：true时，输入框有字符，则删除按钮可见
     */
    @Override
    public void setEnabled(boolean enabled)
    {
        if (enabled)
        {
            setClearIconVisible(getText().length() > 0);
        }
        else
        {
            setClearIconVisible(false);
        }
        super.setEnabled(enabled);
    }

    public interface EditTextImeBackListener
    {

        public abstract void onImeBack(ClearEditText ctrl, String text);
    }
}

package cn.hxgroup.www.hhu.ui.component;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import cn.hxgroup.www.hhu.R;


/**
 * Created by chenxiaojun1 on 2015/6/8.
 * ©user chenxiaojun1
 * ©date 2015/6/8
 */
public class MessageDialog extends Dialog
{

    private boolean mSingleBtn;
    private Button mLeftBtn;
    private Button mRightBtn;
    private TextView mTitleTxt;
    private TextView mMessageTxt;
    private String mLeftBtnText;
    private String mRightBtnText;
    private String mTitleText;
    private String mMessageText;
    private Callback mLeftListener;
    private Callback mRightListener;

    private boolean mIsTitleEnable;

    public interface Callback
    {
        void callback();
    }

    public MessageDialog(Context context)
    {
        super(context);
    }

    public MessageDialog(Context context, int theme)
    {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_message_dialog);
        initView();
    }

    private void initView()
    {
        mLeftBtn = (Button) findViewById(R.id.message_dialog_left_Btn);
        mRightBtn = (Button) findViewById(R.id.message_dialog_right_Btn);
        mTitleTxt = (TextView) findViewById(R.id.message_dialog_title_Txt);
        mMessageTxt = (TextView) findViewById(R.id.message_dialog_msg_Txt);

        if (!TextUtils.isEmpty(mLeftBtnText))
        {
            mLeftBtn.setText(mLeftBtnText);
        }

        if (!TextUtils.isEmpty(mRightBtnText))
        {
            mRightBtn.setText(mRightBtnText);
        }

        if (!TextUtils.isEmpty(mTitleText))
        {
            mTitleTxt.setText(mTitleText);
        }

        if (!TextUtils.isEmpty(mMessageText))
        {
            mMessageTxt.setText(mMessageText);
        }

        //只显示一个按钮
        if (mSingleBtn)
        {
            mRightBtn.setVisibility(View.GONE);
            mLeftBtn.setBackgroundResource(R.drawable.dialog_bottom_bg_selector);
        }

        mLeftBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dismiss();
                if (mLeftListener != null)
                {
                    mLeftListener.callback();
                }
            }
        });

        mRightBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dismiss();
                if (mRightListener != null)
                {
                    mRightListener.callback();
                }
            }
        });
    }

    public boolean isIsTitleEnable()
    {
        return mIsTitleEnable;
    }

    public void setTitleGravity(int gravity)
    {
        if (mTitleTxt != null)
        {
            mTitleTxt.setGravity(gravity);
        }
    }

    public void setMessageGravity(int gravity)
    {
        if (mMessageTxt != null)
        {
            mMessageTxt.setGravity(gravity);
        }
    }

    public void setIsTitleEnable()
    {
        if (mTitleTxt != null)
        {
            mTitleTxt.setVisibility(View.VISIBLE);
        }
        this.mIsTitleEnable = mIsTitleEnable;
    }

    /**
     * 设置标题
     *
     * @param title
     */
    @Override
    public void setTitle(CharSequence title)
    {
        if (title != null)
        {
            this.mTitleText = title.toString();
        }
    }

    public void setTitle(int textID)
    {
        this.mTitleText = getContext().getText(textID).toString();
    }

    /**
     * 设置信息
     *
     * @param msg
     */
    public void setMessage(CharSequence msg)
    {
        if (msg != null)
        {
            this.mMessageText = msg.toString();
        }
    }

    public void setMessage(int res)
    {
        if (res > 0)
        {
            this.mMessageText = getContext().getResources().getString(res);
        }
    }

    /**
     * 左边按钮监听
     *
     * @param textID
     * @param listener
     */
    public void setOnLeftBtnClickListener(int textID, Callback listener)
    {
        if (!TextUtils.isEmpty(getContext().getText(textID)))
        {
            this.mLeftBtnText = getContext().getText(textID).toString();
        }
        //        mLeftBtn.setBackgroundResource();
        this.mLeftListener = listener;
    }

    /**
     * 左边按钮监听
     *
     * @param charSequence
     * @param listener
     */
    public void setOnLeftBtnClickListener(CharSequence charSequence, Callback listener)
    {
        if (!TextUtils.isEmpty(charSequence))
        {
            this.mLeftBtnText = charSequence.toString();
        }
        //        mLeftBtn.setBackgroundResource();
        this.mLeftListener = listener;
    }

    /**
     * 右边按钮监听
     *
     * @param textID
     * @param listener
     */
    public void setOnRightBtnClickListener(int textID, Callback listener)
    {
        if (!TextUtils.isEmpty(getContext().getText(textID)))
        {
            mRightBtnText = getContext().getText(textID).toString();
        }
        this.mRightListener = listener;
    }

    /**
     * 右边按钮监听
     *
     * @param charSequence
     * @param listener
     */
    public void setOnRightBtnClickListener(CharSequence charSequence, Callback listener)
    {
        if (!TextUtils.isEmpty(charSequence))
        {
            mRightBtnText = charSequence.toString();
        }
        this.mRightListener = listener;
    }

    /**
     * 只有一个按钮
     *
     * @param textID
     * @param listener
     */
    public void setOnSingleBtnClickListener(int textID, Callback listener)
    {
        if (!TextUtils.isEmpty(getContext().getText(textID)))
        {
            mLeftBtnText = getContext().getText(textID).toString();
        }
        this.mLeftListener = listener;
        mSingleBtn = true;
    }

    /**
     * 只有一个按钮
     *
     * @param charSequence
     * @param listener
     */
    public void setOnSingleBtnClickListener(CharSequence charSequence, Callback listener)
    {
        if (!TextUtils.isEmpty(charSequence))
        {
            mLeftBtnText = charSequence.toString();
        }
        this.mLeftListener = listener;
        mSingleBtn = true;
    }

    @Override
    public void show()
    {
        if (isShowing())
        {
            return;
        }
        super.show();
        WindowManager m = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        int minWidth = Math.min(d.getWidth(), d.getHeight());
        p.width = (int) (minWidth * 0.78);
        p.height = (int) (WindowManager.LayoutParams.WRAP_CONTENT);
        getWindow().setAttributes(p);
    }
}

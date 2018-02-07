package com.developer.nikhil.fillbelly.view.button;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.Button;

import com.developer.nikhil.fillbelly.common.Constants;
import com.developer.nikhil.fillbelly.common.Utils;


@SuppressLint("AppCompatCustomView")
public class CustomItalicButtonView extends Button
{
    String typeFace = Constants.textlato_italic;
    public CustomItalicButtonView(Context context)
    {
        super(context);

        if (!isInEditMode() && !TextUtils.isEmpty(typeFace))
        {
            setFont();
        }
    }

    public CustomItalicButtonView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
		/*TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TextViewTypeFace, 0, 0);
       
        a.recycle();*/
        if (!isInEditMode() && !TextUtils.isEmpty(typeFace))
        {
            setFont();
        }
    }

    public CustomItalicButtonView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
		/*TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TextViewTypeFace, 0, 0);
        
        a.recycle();*/
        if (!isInEditMode() && !TextUtils.isEmpty(typeFace))
        {
            setFont();
        }
    }

    private void setFont()
    {
        this.setTypeface(Utils.SetCustomFont(typeFace, getContext()));
    }
}

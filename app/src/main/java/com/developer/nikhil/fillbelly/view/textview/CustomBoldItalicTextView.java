package com.developer.nikhil.fillbelly.view.textview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import com.developer.nikhil.fillbelly.common.Constants;
import com.developer.nikhil.fillbelly.common.Utils;


@SuppressLint("AppCompatCustomView")
public class CustomBoldItalicTextView extends TextView
{
    String typeFace = Constants.textlato_bold_italic;
    public CustomBoldItalicTextView(Context context)
    {
        super(context);

        if (!isInEditMode() && !TextUtils.isEmpty(typeFace))
        {
            setFont();
        }
    }

    @SuppressLint("Recycle")
    public CustomBoldItalicTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
       /* TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TextViewTypeFace, 0, 0);
        a.recycle();*/
        if (!isInEditMode() && !TextUtils.isEmpty(typeFace))
        {
            setFont();
        }
    }
    public CustomBoldItalicTextView(Context context, AttributeSet attrs, int defStyle) {
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

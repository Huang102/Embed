package com.kisen.embed;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.kisen.edview.interfaces.ILoadAnim;

/**
 * Created by huangwy on 2017/10/9.
 * email: kisenhuang@163.com.
 */

public class CustomLoadAnim extends FrameLayout implements ILoadAnim {

    public CustomLoadAnim(@NonNull Context context) {
        this(context, null);
    }

    public CustomLoadAnim(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomLoadAnim(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        addView(new ProgressBar(context));
    }

    @Override
    public void show() {
        setVisibility(VISIBLE);
    }

    @Override
    public void dismiss() {
        setVisibility(GONE);
    }

    @Override
    public void destroy() {
        dismiss();
        removeAllViews();
    }
}

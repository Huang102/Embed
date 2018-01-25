package com.kisen.edview;

import android.app.Activity;

import com.kisen.edview.interfaces.IContextBuilder;
import com.kisen.edview.interfaces.ILoadAnim;

import java.lang.ref.WeakReference;

/**
 * Created by huangwy on 2017/10/9.
 * email: kisenhuang@163.com.
 */

class EdActivityBuilder implements IContextBuilder {

    private WeakReference<Activity> weakReference;

    EdActivityBuilder(Activity activity) {
        weakReference = new WeakReference<>(activity);
    }

    @Override
    public IContextBuilder loadAnimation(Class<? extends ILoadAnim> loadAnim) {
        EdView.getActivityMap().putLoadActivity(weakReference.get(), loadAnim);
        return this;
    }
}

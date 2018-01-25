package com.kisen.edview;

import android.app.Activity;

import com.kisen.edview.interfaces.IContextBuilder;
import com.kisen.edview.interfaces.ILoadAnim;
import com.kisen.edview.lifecycle.LoadAnimLifecycleCallback;

/**
 * Created by huangwy on 2017/10/9.
 * email: kisenhuang@163.com.
 */

class EdApplicationBuilder implements IContextBuilder {

    private LoadAnimLifecycleCallback loadAnimLifecycleCallback;

    @Override
    public IContextBuilder loadAnimation(Class<? extends ILoadAnim> loadAnim) {
        loadAnimLifecycleCallback = new LoadAnimLifecycleCallback(loadAnim);
        EdView.addLifecycle(loadAnimLifecycleCallback);
        return this;
    }

    void stopLoad(Activity activity) {
        loadAnimLifecycleCallback.stopAnim(activity);
    }

    public void showLoad(Activity activity) {
        loadAnimLifecycleCallback.showAnim(activity);
    }
}

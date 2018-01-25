package com.kisen.edview.lifecycle;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.kisen.edview.EdUtil;
import com.kisen.edview.EdView;
import com.kisen.edview.interfaces.IActivityLifecycle;
import com.kisen.edview.interfaces.ILoadAnim;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by huangwy on 2017/10/9.
 * email: kisenhuang@163.com.
 */

public class LoadAnimLifecycleCallback implements IActivityLifecycle {

    private final Class<? extends ILoadAnim> mLoadAnim;
    private Map<String, ILoadAnim> runningAnim = new HashMap<>();

    public LoadAnimLifecycleCallback(Class<? extends ILoadAnim> loadAnim) {
        mLoadAnim = loadAnim;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override
    public void onActivityStarted(Activity activity) {
        View loadAnimView = generateLoadAnimView(activity);
        if (loadAnimView == null)
            throw new NullPointerException("ILoadAnim创建失败，可能原因是ILoadAnim没有继承View");
        embedView(loadAnimView, activity);
    }

    private void embedView(View loadAnimView, Activity activity) {
        FrameLayout contentView = activity.findViewById(android.R.id.content);
        contentView.addView(loadAnimView);
        runningAnim.put(EdUtil.votKey(activity), (ILoadAnim) loadAnimView);
    }

    private View generateLoadAnimView(Activity activity) {
        try {
            Class<? extends ILoadAnim> loadAnim = EdView.getActivityMap().mapLoad(activity);
            if (loadAnim == null)
                loadAnim = mLoadAnim;
            Constructor constructor = loadAnim.getConstructor(Context.class);
            ILoadAnim iLoadAnim = (ILoadAnim) constructor.newInstance(activity);
            if (iLoadAnim instanceof View)
                return (View) iLoadAnim;
        } catch (InstantiationException
                | IllegalAccessException
                | NoSuchMethodException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onActivityResumed(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivityStopped(Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        String key = EdUtil.votKey(activity);
        if (runningAnim.containsKey(key)) {
            ILoadAnim iLoadAnim = runningAnim.get(key);
            iLoadAnim.destroy();
            runningAnim.remove(key);
            EdView.getActivityMap().delLoadAnim(activity);
        }
    }

    public void showAnim(Activity activity) {
        String key = EdUtil.votKey(activity);
        if (runningAnim.containsKey(key)) {
            ILoadAnim iLoadAnim = runningAnim.get(key);
            iLoadAnim.show();
        }
    }

    public void stopAnim(Activity activity) {
        String key = EdUtil.votKey(activity);
        if (runningAnim.containsKey(key)) {
            ILoadAnim iLoadAnim = runningAnim.get(key);
            iLoadAnim.dismiss();
        }
    }
}

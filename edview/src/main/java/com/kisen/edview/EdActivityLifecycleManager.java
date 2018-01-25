package com.kisen.edview;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.kisen.edview.interfaces.IActivityLifecycle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangwy on 2017/10/9.
 * email: kisenhuang@163.com.
 */

class EdActivityLifecycleManager implements Application.ActivityLifecycleCallbacks {

    private List<IActivityLifecycle> activityLifecycle = new ArrayList<>();

    void addCallback(IActivityLifecycle callback) {
        if (callback != null)
            activityLifecycle.add(callback);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        for (IActivityLifecycle life : activityLifecycle) {
            life.onActivityCreated(activity, bundle);
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {
        for (IActivityLifecycle life : activityLifecycle) {
            life.onActivityStarted(activity);
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {
        for (IActivityLifecycle life : activityLifecycle) {
            life.onActivityResumed(activity);
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {
        for (IActivityLifecycle life : activityLifecycle) {
            life.onActivityPaused(activity);
        }
    }

    @Override
    public void onActivityStopped(Activity activity) {
        for (IActivityLifecycle life : activityLifecycle) {
            life.onActivityStopped(activity);
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        for (IActivityLifecycle life : activityLifecycle) {
            life.onActivitySaveInstanceState(activity, bundle);
        }
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        for (IActivityLifecycle life : activityLifecycle) {
            life.onActivityDestroyed(activity);
        }
    }
}

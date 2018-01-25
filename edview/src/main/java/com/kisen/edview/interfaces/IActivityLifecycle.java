package com.kisen.edview.interfaces;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by huangwy on 2017/10/9.
 * email: kisenhuang@163.com.
 */

public interface IActivityLifecycle {
    void onActivityCreated(Activity var1, Bundle var2);

    void onActivityStarted(Activity var1);

    void onActivityResumed(Activity var1);

    void onActivityPaused(Activity var1);

    void onActivityStopped(Activity var1);

    void onActivitySaveInstanceState(Activity var1, Bundle var2);

    void onActivityDestroyed(Activity var1);
}

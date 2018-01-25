package com.kisen.edview;

import android.app.Activity;
import android.app.Application;
import android.support.annotation.IntDef;

import com.kisen.edview.interfaces.IActivityLifecycle;
import com.kisen.edview.interfaces.IContextBuilder;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by huangwy on 2017/10/9.
 * email: kisenhuang@163.com.
 */

public class EdView {

    public static final int LOAD_ANIMATION = 0x01;
    public static final int SWIPE_EXIT = LOAD_ANIMATION << 1;

    private static EdView mEdView;
    private static Application mApplication;

    @IntDef({LOAD_ANIMATION, SWIPE_EXIT})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface EdMode {
    }

    private static ActivityMap mActivityMap;
    private static EdActivityLifecycleManager mLifecycleManager;

    private EdApplicationBuilder mApplicationManager;

    /**
     * 全局配置调用，在Application中被调用
     *
     * @param application application对象
     */
    public static EdView build(Application application) {
        if (mEdView != null)
            return mEdView;
        mEdView = new EdView();
        registerApplication(application);
        return mEdView;
    }

    public static EdView get() {
        if (mEdView == null) {
            throw new NullPointerException("You have to use the method 'get(Application)' " +
                    "to initiate EdView in Application!");
        }
        return mEdView;
    }

    private static void registerApplication(Application application) {
        if (application == null)
            throw new NullPointerException("Application cannot be null!");
        mApplication = application;
        application.registerActivityLifecycleCallbacks(mLifecycleManager);
    }

    private EdView() {
        mActivityMap = new ActivityMap();
        mLifecycleManager = new EdActivityLifecycleManager();
    }

    /**
     * 全局添加配置,需要维持Manager
     * 只需要在application中添加
     *
     * @return contextManager对象
     */
    public IContextBuilder application() {
        if (mApplication == null)
            throw new NullPointerException("You have to use the method 'get(Application)' " +
                    "to initiate EdView!");
        if (mApplicationManager == null)
            mApplicationManager = new EdApplicationBuilder();
        return mApplicationManager;
    }

    /**
     * 局部添加配置,不需要维持Manager
     * 在需要添加的Activity中手动添加
     *
     * @param activity activity对象
     * @return contextManager对象
     */
    public IContextBuilder activity(Activity activity) {
        return new EdActivityBuilder(activity);
    }

    public static ActivityMap getActivityMap() {
        throwActivityMap();
        return mActivityMap;
    }

    static void addLifecycle(IActivityLifecycle callback) {
        mLifecycleManager.addCallback(callback);
    }

    private static void throwActivityMap() {
        if (mActivityMap == null)
            throw new NullPointerException("You have to use the Method 'get' " +
                    "to initiate EdView!");
    }

    public static void stopLoad(Activity activity) {
        get().mApplicationManager.stopLoad(activity);
    }

    public static void showLoad(Activity activity) {
        get().mApplicationManager.showLoad(activity);
    }

}

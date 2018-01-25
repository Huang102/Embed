package com.kisen.embed;

import android.app.Application;

import com.kisen.edview.EdView;

/**
 * Created by huangwy on 2017/10/9.
 * email: kisenhuang@163.com.
 */

public class EApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        registerEdView();
    }

    private void registerEdView() {
        EdView.build(this)
                .application()
                .loadAnimation(CustomLoadAnim.class);
    }
}

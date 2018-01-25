package com.kisen.edview;

import android.app.Activity;

/**
 * Created by huangwy on 2017/10/9.
 * email: kisenhuang@163.com.
 */

public class EdUtil {

    public static String votKey(Activity activity) {
        if (activity == null)
            return "";
        return activity.getClass().getSimpleName();
    }

}

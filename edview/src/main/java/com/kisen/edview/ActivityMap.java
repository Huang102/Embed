package com.kisen.edview;

import android.app.Activity;
import android.text.TextUtils;

import com.kisen.edview.interfaces.ILoadAnim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huangwy on 2017/10/9.
 * email: kisenhuang@163.com.
 */

public class ActivityMap {

    private Map<String, Class<? extends ILoadAnim>> loadMap = new HashMap<>();

    public void putLoadActivity(Activity activity, Class<? extends ILoadAnim> loadAnim) {
        loadMap.put(EdUtil.votKey(activity), loadAnim);
    }

    public synchronized Class<? extends ILoadAnim> mapLoad(Activity activity) {
        if (loadMap.size() == 0)
            return null;
        for (Map.Entry<String, Class<? extends ILoadAnim>> entry : loadMap.entrySet()) {
            if (TextUtils.equals(entry.getKey(), EdUtil.votKey(activity))) {
                return entry.getValue();
            }
        }
        return null;
    }

    public synchronized void delLoadAnim(Activity activity) {
        if (mapLoad(activity) != null)
            loadMap.remove(EdUtil.votKey(activity));
    }

}

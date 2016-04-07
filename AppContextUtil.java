package com.xiefei.musicplayer.util;

import android.content.Context;

/**
 * Created by xiefei on 2016/4/7.
 * 默认用来提供Application Context
 */
public class AppContextUtil {
    private AppContextUtil(){}
    private static Context context;
    /**
     * @param context1 这里是Application的context 用来提供给其他Util
     */
    public static void init(Context context1){
        context = context1;
    }

    /**
     * @return context
     */
    public static Context getContext() {
        return context;
    }
}

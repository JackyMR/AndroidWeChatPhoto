package com.yuan.androidwechatphoto.utils;

import android.view.View;

/**
 * Created by Yuan on 15/12/20.
 */
public class ImmersiveUtil {

    private static final int SYSTEM_UI_IMMERSIVE = View.SYSTEM_UI_FLAG_IMMERSIVE
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_FULLSCREEN;

    public static void enter(View view) {
        view.setSystemUiVisibility(view.getSystemUiVisibility() | SYSTEM_UI_IMMERSIVE);
    }

    public static void exit(View view) {
        view.setSystemUiVisibility(view.getSystemUiVisibility() & (~SYSTEM_UI_IMMERSIVE));
    }


}

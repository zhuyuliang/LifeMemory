package com.zyl.melife.utils;

import android.content.Context;

public class DistanceUtil {

    public static int getCameraPhotoWidth(Context context) {
        return ViewUtil.getScreenWidth(context) / 4 - ViewUtil.dpToPx(2);
    }

}

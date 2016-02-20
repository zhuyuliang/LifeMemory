package com.zyl.melife.camera.compare;


import com.zyl.melife.camera.param.Size;

import java.util.Comparator;

/**
 * Created by yuyidong on 15/8/20.
 */
public class SizeComparator implements Comparator<Size> {

    @Override
    public int compare(Size lhs, Size rhs) {
        return -(rhs.getWidth() * rhs.getHeight() - lhs.getWidth() * lhs.getHeight());
    }
}

package com.zyl.melife.camera.model;

/**
 * Created by yuyidong on 15-4-9.
 */
public interface ICameraFocus {
    public static final int FOCUS_STATE_IDEL = 0;
    public static final int FOCUS_STATE_FOCUSING = 1;
    public static final int FOCUS_STATE_FOCUSED_GOOD = 2;
    public static final int FOCUS_STATE_FOCUSED_BAD = 3;

    public int getFocusState(); //获取对焦状态

    public void triggerFocus(int x, int y); //激活对焦

    public void cancelFocus();  //关闭对焦
}

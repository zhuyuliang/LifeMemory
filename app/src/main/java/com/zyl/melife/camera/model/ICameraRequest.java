package com.zyl.melife.camera.model;

import com.zyl.melife.camera.param.Size;

/**
 * Created by yuyidong on 15-4-13.
 */
public interface ICameraRequest extends ICameraParams {


    public void setFlash(int flashState);

    public int getFlash();

    public void setPreviewSize(int width, int height);

    public void setPictureSize(int width, int height);

    public Size getPictureSize();

    public void setZoom(int value);

    public int getZoom();

    public void setExposureCompensation(int value); //曝光补偿

    public void setWhiteBalance(int value);

    public int getWhiteBalance();

    public void setDisplayOrientation(int degree);
}

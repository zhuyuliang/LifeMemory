package com.zyl.melife.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.zyl.melife.camera.model.ICameraParams;
import com.zyl.melife.camera.param.Size;
import com.zyl.melife.global.Const;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuyuliang on 16-01-14.
 */
public class SharePreferencesUtils {

    private static final String SETTING_NAME = "Setting";

    /** 主题 */
    private static final String THEME_NAME = "theme_name";//主题
    private static final int THEME_DEFAULT = 0;//主题默认
    /** Camera */
    private static final String CAMERA_BACK_ROTATION = "camera_back_rotation";//后置摄像头方向
    private static final int CAMERA_BACK_ROTATION_DEFAULT = 90;//默认90

    private static final String CAMERA_FRONT_ROTATION = "camera_front_rotation";//前置摄像头方向
    private static final int CAMERA_FRONT_ROTATION_DEFAULT = 90;//默认90

    private static final String PICTURE_SIZES_0 = "picture_sizes_0";//Camera中所有拍照图片大小 后置
    private static final String PICTURE_SIZES_1 = "picture_sizes_1";//Camera中所有拍照图片大小 前置
    private static final String PICTURE_SIZE_0 = "picture_size_1";//设置的拍照的图片大小 后置
    private static final String PICTURE_SIZE_1 = "picture_size_2";//设置的拍照的图片大小 前置
    private static final String PICTURE_DEFAULT = "";//默认拍照图片大小 后置

    private static final String CAMERA_NUMBER = "camera_number";//相机ID数量
    private static final int CAMERA_NUMBER_DEFAULT = 1;//相机ID数量默认1个

    private static final String CAMERA_SYSTEM = "camera_system";//是否是用系统的相机
    private static final boolean CAMERA_SYSTEM_DEFAULT = false;//默认是不使用

    private static final String CAMERA_SAVE_SETTING = "camera_save_setting";//退出时保存相机参数
    private static final boolean CAMERA_SAVE_SETTING_DEFAULT = false;//退出时保存相机参数的默认值，不保存

    /**
     * 相机相关配置
     */
    private static final String CAMERA_SAVE_CAMERA_ID = "camera_save_camera_id";//相机ID
    private static final String CAMERA_SAVE_CAMERA_ID_DEFAULT = Const.CAMERA_BACK;//相机ID默认值

    private static final String CAMERA_WHITE_BALANCE = "camera_white_balance";//白平衡
    private static final int CAMERA_WHITE_BALANCE_DEFAULT = ICameraParams.WHITE_BALANCE_AUTO;//白平衡默认值为auto

    private static final String CAMERA_SAVE_TIMER = "camera_save_time";//相机倒计时拍照时间
    private static final int CAMERA_SAVE_TIMER_DEFAULT = Const.LAYOUT_PERSONAL_TIMER_0;//相机倒计时拍照时间默认值

    private static final String CAMERA_SAVE_FLASH = "camera_save_flash";//相机闪光灯状态
    private static final int CAMERA_SAVE_FLASH_DEFAULT = ICameraParams.FLASH_OFF;//相机闪光灯状态默认值

    private static final String CAMERA_EXPOSURECOMPENSATION = "camera_exposureCompensation";//相机曝光
    private static final int CAMERA_EXPOSURECOMPENSATION_DEFAULT = 0;//相机曝光的默认值为0

    private static final String CAMERA_SOUND_OPEN = "camera_sound_open";//相机声音
    private static final boolean CAMERA_SOUND_OPEN_DEFAULT = false;//默认味关闭，0

    private static final String CAMERA_PREVIEW_RATIO = "camera_preview_ratio";//相机预览的比例
    private static final int CAMERA_PREVIEW_RATIO_DEFAULT = Const.LAYOUT_PERSONAL_RATIO_4_3;

    private static final String CAMERA_LOCATION = "camera_location";//拍照地点
    private static final boolean CAMERA_LOCATION_DEFAULT = false;//默认关闭

    private static final String CAMERA_GRID_OPEN = "camera_grid_open";//grid是否打开
    private static final boolean CAMERA_GRID_OPEN_DEFAULT = false;//grid打开状态默认值是关闭

    private static final String CAMERA_MIRROR_OPEN = "camera_mirror_open";//是否开启镜像
    private static final boolean CAMERA_MIRROR_OPEN_DEFAULT = false;//默认镜像是关闭


    private static SharedPreferences mSharedPreferences;

    public SharePreferencesUtils(Context context) {
        mSharedPreferences = context.getSharedPreferences(SETTING_NAME, Context.MODE_PRIVATE);
        sInstance = this;
    }

    private static SharePreferencesUtils sInstance;

    public static SharePreferencesUtils getInstance(Context context) {
        if (sInstance == null) {
            synchronized (SharePreferencesUtils.class) {
                if (sInstance == null) {
                    sInstance = new SharePreferencesUtils(context);
                }
            }
        }
        return sInstance;
    }

    /**
     * 保存主题索引
     *
     * @param i
     */
    public void setThemeColor(int i) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(THEME_NAME, i);
        editor.commit();
    }

    /**
     * 获得主题索引
     *
     * @return
     */
    public int getThemeColor() {
        int colorIndex = mSharedPreferences.getInt(THEME_NAME, THEME_DEFAULT);
        if (colorIndex > 15) {
            colorIndex = 15;
        }
        return colorIndex;
    }

    /** Camera */
    /**
     * 保存后置摄像头方向
     *
     * @param degree
     */
    public void setCameraBackRotation(int degree) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(CAMERA_BACK_ROTATION, degree);
        editor.commit();
    }

    /**
     * 获取后置摄像头方向
     *
     * @return
     */
    public int getCameraBackRotation() {
        return mSharedPreferences.getInt(CAMERA_BACK_ROTATION, CAMERA_BACK_ROTATION_DEFAULT);
    }

    /**
     * 保存前置摄像头方向
     *
     * @param degree
     */
    public void setCameraFrontRotation(int degree) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(CAMERA_FRONT_ROTATION, degree);
        editor.commit();
    }

    /**
     * 获取前置摄像头方向
     *
     * @return
     */
    public int getCameraFrontRotation() {
        return mSharedPreferences.getInt(CAMERA_FRONT_ROTATION, CAMERA_FRONT_ROTATION_DEFAULT);
    }

    /**
     * 设置picture的所有size
     *
     * @param cameraId
     * @param sizeList
     * @throws JSONException
     */
    public void setPictureSizes(String cameraId, List<Size> sizeList) throws JSONException {
        checkNotNull(cameraId);
        checkNotNull(sizeList);
        JSONArray jsonArray = new JSONArray();
        for (Size size : sizeList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(Const.CAMERA_SIZE_WIDTH, size.getWidth());
            jsonObject.put(Const.CAMERA_SIZE_HEIGHT, size.getHeight());
            jsonArray.put(jsonObject);
        }
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        if (cameraId.equals(Const.CAMERA_BACK)) {
            editor.putString(PICTURE_SIZES_0, jsonArray.toString());
        } else {
            editor.putString(PICTURE_SIZES_1, jsonArray.toString());
        }
        editor.commit();
    }

    /**
     * 获取picture的所有size
     *
     * @param cameraId
     * @return
     * @throws JSONException
     */
    public List<Size> getPictureSizes(@NonNull String cameraId) throws JSONException {
        checkNotNull(cameraId);
        String jsonArrayString;
        if (cameraId.equals(Const.CAMERA_BACK)) {
            jsonArrayString = mSharedPreferences.getString(PICTURE_SIZES_0, PICTURE_DEFAULT);
        } else {
            jsonArrayString = mSharedPreferences.getString(PICTURE_SIZES_1, PICTURE_DEFAULT);
        }
        if (jsonArrayString.equals(PICTURE_DEFAULT)) {
            return null;
        }
        List<Size> sizeList = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(jsonArrayString);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            int width = (int) jsonObject.get(Const.CAMERA_SIZE_WIDTH);
            int height = (int) jsonObject.get(Const.CAMERA_SIZE_HEIGHT);
            sizeList.add(new Size(width, height));
        }
        return sizeList;
    }

    /**
     * 设置picture的size
     *
     * @param cameraId
     * @param size
     * @throws JSONException
     */
    public void setPictureSize(String cameraId, Size size) throws JSONException {
        checkNotNull(cameraId);
        checkNotNull(size);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Const.CAMERA_SIZE_WIDTH, size.getWidth());
        jsonObject.put(Const.CAMERA_SIZE_HEIGHT, size.getHeight());
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        if (cameraId.equals(Const.CAMERA_BACK)) {
            editor.putString(PICTURE_SIZE_0, jsonObject.toString());
        } else {
            editor.putString(PICTURE_SIZE_1, jsonObject.toString());
        }
        editor.commit();
    }

    /**
     * 获取picture的size
     *
     * @param cameraId
     * @return
     * @throws JSONException
     */
    public Size getPictureSize(String cameraId) throws JSONException {
        checkNotNull(cameraId);
        String jsonString;
        if (cameraId.equals(Const.CAMERA_BACK)) {
            jsonString = mSharedPreferences.getString(PICTURE_SIZE_0, PICTURE_DEFAULT);
        } else {
            jsonString = mSharedPreferences.getString(PICTURE_SIZE_1, PICTURE_DEFAULT);
        }
        if (jsonString.equals(PICTURE_DEFAULT)) {
            return null;
        }
        JSONObject jsonObject = new JSONObject(jsonString);
        int width = (int) jsonObject.get(Const.CAMERA_SIZE_WIDTH);
        int height = (int) jsonObject.get(Const.CAMERA_SIZE_HEIGHT);
        return new Size(width, height);
    }

    /**
     * 设置相机数量
     *
     * @param number
     */
    public void setCameraNumber(int number) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(CAMERA_NUMBER, number);
        editor.commit();
    }

    /**
     * 获取是否是使用系统相机
     *
     * @return
     */
    public int getCameraNumber() {
        return mSharedPreferences.getInt(CAMERA_NUMBER, CAMERA_NUMBER_DEFAULT);
    }


    /**
     * 设置是否是用系统相机
     *
     * @param use true--->用
     */
    public void setCameraSystem(boolean use) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(CAMERA_SYSTEM, use);
        editor.commit();
    }

    /**
     * 获取是否是使用系统相机
     *
     * @return true--->用
     */
    public boolean getCameraSystem() {
        return mSharedPreferences.getBoolean(CAMERA_SYSTEM, CAMERA_SYSTEM_DEFAULT);
    }

    /**
     * 设置是否是退出时保持相机参数
     *
     * @param save save--->存
     */
    public void setCameraSaveSetting(boolean save) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(CAMERA_SAVE_SETTING, save);
        editor.commit();
    }

    /**
     * 获取是否是退出时保持相机参数
     *
     * @return true--->存
     */
    public boolean getCameraSaveSetting() {
        return mSharedPreferences.getBoolean(CAMERA_SAVE_SETTING, CAMERA_SAVE_SETTING_DEFAULT);
    }

    /**
     * 保存相机ID
     *
     * @param cameraId
     */
    public void setCameraSaveCameraId(String cameraId) {
        checkNotNull(cameraId);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(CAMERA_SAVE_CAMERA_ID, cameraId);
        editor.commit();
    }

    /**
     * 获取相机ID
     *
     * @return
     */
    public String getCameraSaveCameraId() {
        return mSharedPreferences.getString(CAMERA_SAVE_CAMERA_ID, CAMERA_SAVE_CAMERA_ID_DEFAULT);
    }

    /**
     * 设置相机闪光灯
     *
     * @param flash
     */
    public void setCameraSaveFlash(int flash) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(CAMERA_SAVE_FLASH, flash);
        editor.commit();
    }

    /**
     * 获取相机闪光灯
     *
     * @return
     */
    public int getCameraSaveFlash() {
        return mSharedPreferences.getInt(CAMERA_SAVE_FLASH, CAMERA_SAVE_FLASH_DEFAULT);
    }

    /**
     * 保存相机倒计时
     *
     * @param time
     */
    public void setCameraSaveTimer(int time) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(CAMERA_SAVE_TIMER, time);
        editor.commit();
    }

    /**
     * 获取相机倒计时
     *
     * @return
     */
    public int getCameraSaveTimer() {
        return mSharedPreferences.getInt(CAMERA_SAVE_TIMER, CAMERA_SAVE_TIMER_DEFAULT);
    }

    /**
     * 保存Camera是否开启声音
     *
     * @param bool
     */
    public void setCameraSoundOpen(boolean bool) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(CAMERA_SOUND_OPEN, bool);
        editor.commit();
    }

    /**
     * 获得Camera是否开启声音
     *
     * @return
     */
    public boolean getCameraSoundOpen() {
        return mSharedPreferences.getBoolean(CAMERA_SOUND_OPEN, CAMERA_SOUND_OPEN_DEFAULT);
    }

    /**
     * 保存Camera预览的比例
     *
     * @param i
     */
    public void setCameraPreviewRatio(int i) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(CAMERA_PREVIEW_RATIO, i);
        editor.commit();
    }

    /**
     * 获得Camera预览的比例
     *
     * @return
     */
    public int getCameraPreviewRatioDefault() {
        return mSharedPreferences.getInt(CAMERA_PREVIEW_RATIO, CAMERA_PREVIEW_RATIO_DEFAULT);
    }

    /**
     * 设置相机的曝光
     *
     * @param exposureCompensation
     */
    public void setCameraExposureCompensation(int exposureCompensation) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(CAMERA_EXPOSURECOMPENSATION, exposureCompensation);
        editor.commit();
    }

    /**
     * 获得相机的曝光
     *
     * @return
     */
    public int getCameraExposureCompensation() {
        return mSharedPreferences.getInt(CAMERA_EXPOSURECOMPENSATION, CAMERA_EXPOSURECOMPENSATION_DEFAULT);
    }

    /**
     * 设置相机的location
     *
     * @param open
     */
    public void setCameraLocation(boolean open) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(CAMERA_LOCATION, open);
        editor.commit();
    }

    /**
     * 获得相机的location
     *
     * @return
     */
    public boolean getCameraLocation() {
        return mSharedPreferences.getBoolean(CAMERA_LOCATION, CAMERA_LOCATION_DEFAULT);
    }

    /**
     * 保存白平衡
     *
     * @param wb
     */
    public void setCameraWhiteBalance(int wb) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(CAMERA_WHITE_BALANCE, wb);
        editor.commit();
    }

    /**
     * 获得相机的b白平衡
     *
     * @return
     */
    public int getCameraWhiteBalance() {
        return mSharedPreferences.getInt(CAMERA_WHITE_BALANCE, CAMERA_WHITE_BALANCE_DEFAULT);
    }
    /**
     * 设置相机的Grid是否打开
     *
     * @param open
     */
    public void setCameraGridOpen(boolean open) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(CAMERA_GRID_OPEN, open);
        editor.commit();
    }

    /**
     * 获得相机的grid是否打开
     *
     * @return
     */
    public boolean getCameraGridOpen() {
        return mSharedPreferences.getBoolean(CAMERA_GRID_OPEN, CAMERA_GRID_OPEN_DEFAULT);
    }

    /**
     * 设置相机前置摄像头是否镜像
     *
     * @param open
     */
    public void setCameraMirrorOpen(boolean open) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(CAMERA_MIRROR_OPEN, open);
        editor.commit();
    }

    /**
     * 获得相机前置摄像头拍照是否镜像
     *
     * @return
     */
    public boolean getCameraMirrorOpen() {
//        return mSharedPreferences.getBoolean(CAMERA_MIRROR_OPEN, CAMERA_MIRROR_OPEN_DEFAULT);
        return false;
    }


    /** Common */

    /**
     * 判断null
     *
     * @param object
     */
    private void checkNotNull(Object object) {
        if (null == object) {
            throw new NullPointerException("不能是null");
        }
    }

}

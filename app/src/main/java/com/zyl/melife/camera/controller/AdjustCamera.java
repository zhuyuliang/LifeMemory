package com.zyl.melife.camera.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.View;

import com.zyl.melife.R;
import com.zyl.melife.camera.compare.SizeComparator;
import com.zyl.melife.camera.model.AbsCameraModel;
import com.zyl.melife.camera.model.camera.CameraModel;
import com.zyl.melife.camera.param.Size;
import com.zyl.melife.camera.view.AutoFitSurfaceView;
import com.zyl.melife.global.Const;
import com.zyl.melife.utils.AppCompat;
import com.zyl.melife.utils.SharePreferencesUtils;

import org.json.JSONException;

import java.util.Collections;
import java.util.List;

/**
 * Created by yuyidong on 15/9/7.
 */
public class AdjustCamera extends AppCompatActivity implements SurfaceHolder.Callback, View.OnClickListener {
    /* Preview */
    private AutoFitSurfaceView mAutoFitSurfaceView;
    /* Screen */
    private int mScreenWidth = -1;
    private int mScreenHeight = -1;
    /* Model */
    private AbsCameraModel mCameraModel;
    /* CameraId */
    private String mCameraId = Const.CAMERA_BACK;

    private int mCameraNumber = 1;

    //    private int mCurrentOrientationDegree = LocalStorageUtils.getInstance().getCameraBackRotation();
    private int mCurrentOrientationDegree = 0;
    private int mDeltaOrientationDegree = 90;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompat.setFullWindow(getWindow());
        setContentView(R.layout.activity_adjust_camera);

        mScreenWidth = getResources().getDisplayMetrics().widthPixels;
        mScreenHeight = getResources().getDisplayMetrics().heightPixels;
        mAutoFitSurfaceView = (AutoFitSurfaceView) findViewById(R.id.sv_camera);
        mAutoFitSurfaceView.getHolder().addCallback(this);
        findViewById(R.id.fab_rotate).setOnClickListener(this);
        findViewById(R.id.fab_check).setOnClickListener(this);
        findViewById(R.id.fab_cameraid).setOnClickListener(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mCameraModel = new CameraModel(getApplicationContext(), holder, -1);
//        mCameraModel = new CameraModel(NoteApplication.getContext(), holder, null);
        mCameraModel.onCreate(AdjustCamera.this);
        mCameraModel.openCamera(mCameraId, mCurrentOrientationDegree);
        mCameraNumber = mCameraModel.getSettingModel().getNumberOfCameras();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Size previewSize = getSuitablePreviewSize(mCameraModel.getSettingModel().getPreviewSizes());
        setPreviewSize(previewSize);
    }

    private Size getSuitablePreviewSize(List<Size> previewList) {
        Size previewSize = null;
        Collections.sort(previewList, new SizeComparator());
        float screenScale = mScreenHeight / (float) mScreenWidth;
        for (Size preSize : previewList) {
            if (preSize.getWidth() * preSize.getHeight() > 1200000) {
                continue;
            }
            float preScale = preSize.getWidth() / (float) preSize.getHeight();
            //full ratio 如果全屏也是4：3的话，就先这样吧
            if (Math.abs(preScale - screenScale) < 0.03) {
                previewSize = preSize;
            }
        }
        if (previewSize == null) {
            previewSize = previewList.get(0);
        }
        return previewSize;
    }

    private void setPreviewSize(Size previewSize) {
        mAutoFitSurfaceView.setAspectRatio(previewSize.getHeight(), previewSize.getWidth());
        mCameraModel.getSettingModel().setPreviewSize(previewSize.getWidth(), previewSize.getHeight());
        mCameraModel.startPreview();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mCameraModel != null) {
            mCameraModel.startPreview();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCameraModel.stopPreview();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCameraModel.closeCamera();
        mCameraModel.onDestroy(AdjustCamera.this);
    }

    private void reOpenCamera() {
        mCameraModel.reopenCamera(mCameraId, mCurrentOrientationDegree);
        Size previewSize = getSuitablePreviewSize(mCameraModel.getSettingModel().getPreviewSizes());
        setPreviewSize(previewSize);
        Size size = null;
        try {
            size = SharePreferencesUtils.getInstance(this).getPictureSize(mCameraId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mCameraModel.getSettingModel().setPictureSize(size.getWidth(), size.getHeight());
        mCameraModel.startPreview();
    }

    @Override
    public void onClick(View v) {
        if (mCameraModel == null) {
            return;
        }
        switch (v.getId()) {
            case R.id.fab_rotate:
                mCurrentOrientationDegree += mDeltaOrientationDegree;
                if (mCurrentOrientationDegree >= 360) {
                    mCurrentOrientationDegree = 0;
                }
                mCameraModel.getSettingModel().setDisplayOrientation(mCurrentOrientationDegree);
                break;
            case R.id.fab_check:
                switch (mCameraId) {
                    case Const.CAMERA_FRONT:
                        SharePreferencesUtils.getInstance(this).setCameraFrontRotation(mCurrentOrientationDegree);
                        break;
                    case Const.CAMERA_BACK:
                        SharePreferencesUtils.getInstance(this).setCameraBackRotation(mCurrentOrientationDegree);
                        break;
                }
                break;
            case R.id.fab_cameraid:
                if (mCameraNumber != 2) {
                    return;
                }
                if (mCameraId == Const.CAMERA_BACK) {
                    mCameraId = Const.CAMERA_FRONT;
                    mCurrentOrientationDegree = SharePreferencesUtils.getInstance(this).getCameraFrontRotation();
                } else {
                    mCameraId = Const.CAMERA_BACK;
                    mCurrentOrientationDegree = SharePreferencesUtils.getInstance(this).getCameraBackRotation();
                }
                reOpenCamera();
                break;
        }
    }
}

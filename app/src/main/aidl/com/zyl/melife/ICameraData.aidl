// ICameraData.aidl
package com.zyl.melife;

// Declare any non-default types here with import statements

//todo 后期加上
interface ICameraData {

    void add(String fileName,int size, String cameraId, long time,
            int categoryId, boolean isMirror, int ratio,
            int orientation,String latitude,String lontitude,int whiteBalance,int flash,
            int imageLength,int imageWidth,String make,String model);
}

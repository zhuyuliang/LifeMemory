package com.zyl.melife.ui;

import android.support.annotation.NonNull;

/**
 * Created by zhuyuliang on 16/1/14.
 */
public interface IPresenter {

    /**
     * 注入View，使之能够与View相互响应
     *
     * @param iView
     */
    void attachView(@NonNull IView iView);

    /**
     * 释放资源
     */
    void detachView();
}

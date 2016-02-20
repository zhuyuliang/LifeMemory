package com.zyl.melife.ui.presenter.main.impl;

import android.support.annotation.NonNull;

import com.zyl.melife.ui.IView;
import com.zyl.melife.ui.presenter.main.IAppStartPresenter;
import com.zyl.melife.ui.view.main.ISplashView;

/**
 * Created by zhuyuliang on 16/01/15.
 */
public class AppStartPresenterImpl implements IAppStartPresenter {

    ISplashView mISplashView;

    public AppStartPresenterImpl(){}

    @Override
    public void attachView(@NonNull IView iView) {
        mISplashView = (ISplashView) iView;
    }

    @Override
    public void detachView() {

    }
}

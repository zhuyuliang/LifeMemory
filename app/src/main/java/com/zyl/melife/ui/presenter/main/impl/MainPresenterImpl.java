package com.zyl.melife.ui.presenter.main.impl;

import android.support.annotation.NonNull;

import com.zyl.melife.ui.IView;
import com.zyl.melife.ui.presenter.main.IMainPresenter;
import com.zyl.melife.ui.view.main.IMainView;

/**
 * Created by zhuyuliang on 16/01/15.
 */
public class MainPresenterImpl implements IMainPresenter {

    IMainView mIMainView;

    public MainPresenterImpl(){}

    @Override
    public void attachView(@NonNull IView iView) {
        mIMainView = (IMainView) iView;
    }

    @Override
    public void detachView() {

    }
}

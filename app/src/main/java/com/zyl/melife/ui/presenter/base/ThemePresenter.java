package com.zyl.melife.ui.presenter.base;

import android.content.Context;

import com.zyl.melife.ui.view.base.IThemeView;
import com.zyl.melife.utils.SharePreferencesUtils;


/**
 * Created by zhuyuliang on 16/01/15.
 */
public class ThemePresenter {
    private IThemeView mThemeView;

    private SharePreferencesUtils mSharePreferencesUtils;

    public ThemePresenter(Context context) {
        mSharePreferencesUtils = SharePreferencesUtils.getInstance(context);
    }

    public void attachView(IThemeView iView) {
        mThemeView = iView;
    }

    public void setTheme() {
        mThemeView.setActivityTheme(mSharePreferencesUtils.getThemeColor());
    }

    public void setStatusColor(){
        mThemeView.setStatusBarColor(mSharePreferencesUtils.getThemeColor());
    }

}

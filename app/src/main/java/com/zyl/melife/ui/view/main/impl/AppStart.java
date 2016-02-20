package com.zyl.melife.ui.view.main.impl;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zyl.melife.logic.UIHelper;
import com.zyl.melife.ui.presenter.main.impl.AppStartPresenterImpl;
import com.zyl.melife.ui.view.main.ISplashView;

public class AppStart extends AppCompatActivity implements ISplashView {

    AppStartPresenterImpl mAppStartPresenterImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 作用是将xml定义的一个布局找出来并且隐藏起来，并没有显示出来
        final View view = new View(this);//R.layout.start_activity
        setContentView(view);
        mAppStartPresenterImpl = new AppStartPresenterImpl();
        //直接跳转
        redirectMain();
    }

    @Override
    public void redirectMain(){
        UIHelper.RedirectToActivity(MainActivity.class,AppStart.this);
        finish();
    }

    @Override
    public void redirectIntroduce() {

    }

    @Override
    public Context getContext() {
        return null;
    }
}

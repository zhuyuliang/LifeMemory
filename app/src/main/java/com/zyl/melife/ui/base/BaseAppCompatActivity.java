package com.zyl.melife.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zyl.melife.utils.AppManager;

import butterknife.ButterKnife;

public abstract class BaseAppCompatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutView());
        ButterKnife.inject(this);
        // 添加Activity到堆栈
        AppManager.getInstance().addActivity(this);
    }

    //设置layout
    protected abstract int getLayoutView();

}

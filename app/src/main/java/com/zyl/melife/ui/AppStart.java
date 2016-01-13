package com.zyl.melife.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zyl.melife.logic.UIHelper;
import com.zyl.melife.ui.activity.MainActivity;

public class AppStart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 作用是将xml定义的一个布局找出来并且隐藏起来，并没有显示出来
        final View view = new View(this);//R.layout.start_activity
        setContentView(view);
        //直接跳转
        redirectTo();
        return;
    }

    /**
     * 跳转到...
     */
    private void redirectTo(){
        UIHelper.RedirectToActivity(MainActivity.class,AppStart.this);
        finish();
    }

}

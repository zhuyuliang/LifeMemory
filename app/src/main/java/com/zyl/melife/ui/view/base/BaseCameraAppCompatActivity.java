package com.zyl.melife.ui.view.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zyl.melife.ui.IPresenter;
import com.zyl.melife.utils.AppCompat;
import com.zyl.melife.utils.AppManager;
import com.zyl.melife.utils.ToastUtil;

import butterknife.ButterKnife;

public abstract class BaseCameraAppCompatActivity extends AppCompatActivity {

    //不能为null
    protected IPresenter mIPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompat.setFullWindow(getWindow());
        setContentView(getLayoutView());
        ButterKnife.inject(this);
        // 添加Activity到堆栈
        AppManager.getInstance().addActivity(this);
    }

    /**
     * 设置layout
     */
    protected abstract int getLayoutView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().finishActivity(this);
        if (mIPresenter != null) {
            mIPresenter.detachView();
        }
    }

    public void showToast(String str){
        ToastUtil.showToast(this,str);
    }

}

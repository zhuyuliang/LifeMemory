package com.zyl.melife.ui.view.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.zyl.melife.ui.IPresenter;
import com.zyl.melife.ui.presenter.base.ThemePresenter;
import com.zyl.melife.utils.AppCompat;
import com.zyl.melife.utils.AppManager;
import com.zyl.melife.utils.LogUtils;
import com.zyl.melife.utils.ThemeHelper;
import com.zyl.melife.utils.ToastUtil;

import butterknife.ButterKnife;

public abstract class BaseAppCompatActivity extends AppCompatActivity implements IThemeView,IBaseActivityView {

    public ThemePresenter mThemePresenter;
    //不能为null
    protected IPresenter mIPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
        setContentView(getLayoutView());
        ButterKnife.inject(this);
        // 添加Activity到堆栈
        AppManager.getInstance().addActivity(this);
    }

    /**
     * 初始化presenter
     */
    private void initPresenter(){
        //设置主题
        mThemePresenter = new ThemePresenter(getApplicationContext());
        mThemePresenter.attachView(this);
        mThemePresenter.setTheme();
    }

    /**
     * 设置layout
     */
    protected abstract int getLayoutView();

    @Override
    public void setActivityTheme(int index) {
        super.setTheme(ThemeHelper.THEME.get(index).getStyle());
        if (AppCompat.AFTER_LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(ThemeHelper.THEME.get(index).getColorPrimary()));
        }
    }

    /**
     * 设置Status Color
     * @param index
     */
    @Override
    public void setStatusBarColor(int index){
        if (AppCompat.AFTER_LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(ThemeHelper.THEME.get(index).getStatusColor()));
        }
    }

    public void showToast(String str){
        ToastUtil.showToast(this, str);
    }

    /**
     * 获取当前view的LayoutInflater实例
     * @return
     */
    protected LayoutInflater getLayouInflater() {
        LayoutInflater _LayoutInflater = LayoutInflater.from(this);
        return _LayoutInflater;
    }

    /**
     * 弹出toast 显示时长short
     * @param pMsg
     */
    protected void showToastMsgShort(String pMsg) {
        Toast.makeText(this, pMsg, Toast.LENGTH_SHORT).show();
    }
    /**
     * 弹出toase 显示时长long
     * @param pMsg
     */
    protected void showToastMsgLong(String pMsg) {
        Toast.makeText(this, pMsg, Toast.LENGTH_LONG).show();
    }
    /**
     * 根据传入的类(class)打开指定的activity
     * @param pClass
     */
    protected void openActivity(Class<?> pClass) {
        Intent _Intent = new Intent();
        _Intent.setClass(this, pClass);
        startActivity(_Intent);
    }

    protected void openActivityByIntent(Intent pIntent){
        startActivity(pIntent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.d("BaseActivity onStart Invoke...");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtils.d("BaseActivity onRestart Invoke...");
    }
    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.d("BaseActivity onResume Invoke...");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.d("BaseActivity onPause Invoke...");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.d("BaseActivity onStop Invoke...");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.d("BaseActivity onDestroy Invoke...");
        AppManager.getInstance().finishActivity(this);
        if (mIPresenter != null) {
            mIPresenter.detachView();
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        LogUtils.d("BaseActivity onLowMemory Invoke...");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        LogUtils.d("BaseActivity onBackPressed Invoke...");
    }


}

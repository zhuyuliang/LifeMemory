package com.zyl.melife.ui.view.base;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.zyl.melife.R;

import butterknife.InjectView;

/**
 * Created by zhuyuliang on 2016/01/15.
 */
public abstract class ToolbarActivity extends BaseAppCompatActivity {
    @InjectView(R.id.toolbar)
    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mThemePresenter.setStatusColor();
        initToolbar(getToolbarTitle());
    }

    private void initToolbar(String toolBarTitle) {
        toolbar.setTitle(toolBarTitle);//设置title标题
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);//给左上角图标的左边加上一个返回的图标
        }
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));//设置title颜色
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected abstract String getToolbarTitle();


}

package com.zyl.melife.ui.view.main;

import com.zyl.melife.ui.IView;

/**
 * Created by zhuyuliang on 16/01/15.
 */
public interface IMainView extends IView {
    /**
     * 一般初始化
     */
    void init();
    /**
     *初始化ToolBar
     */
    void initToolBar();
    /**
     * 初始化侧滑菜单
     */
    void initDrawListView();
    /**
     * 侧滑菜单的内容处理
     */
    void initNavigationView();

    /**
     * 初始化模块
     */
    void initMainFragment();

}

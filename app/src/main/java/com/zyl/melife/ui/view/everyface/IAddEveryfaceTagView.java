package com.zyl.melife.ui.view.everyface;

import com.zyl.melife.ui.IView;

/**
 * Created by zhuyuliang on 16/01/15.
 */
public interface IAddEveryfaceTagView extends IView {
    /**
     * 初始化
     */
    void init();
    /**
     * 显示是否退出编辑
     */
    void showBackDialog();
    /**
     * 完成编辑
     */
    void doneEdit();

}

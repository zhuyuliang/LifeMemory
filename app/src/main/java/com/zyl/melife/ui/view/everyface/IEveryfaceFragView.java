package com.zyl.melife.ui.view.everyface;

import com.zyl.melife.ui.IView;

/**
 * Created by zhuyuliang on 16/01/15.
 */
public interface IEveryfaceFragView extends IView {
    /**
     * 初始化Recycle
     */
    void initRecycleView();
    /**
     * 刷新列表
     */
    void refreshList();
    /**
     * 显示删除对话框
     */
    void showDialogDeleteEveryface(int position);

}

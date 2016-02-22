package com.zyl.melife.global;

import com.zyl.melife.utils.LogUtils;
import com.zyl.melife.utils.thread.ThreadPool;

import org.litepal.LitePalApplication;
import org.litepal.tablemanager.Connector;

/**
 * 全局配置基础类
 * Author: zhuyuliang email:zhuyuliang0@126.com
 * Created:2015-2-22
 */
public abstract class BaseApplication extends LitePalApplication {

    private static BaseApplication instance;
    public static BaseApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initLogger(); // 初始化日志工具
        initThreadPool(); // 初始化线程池
        initNetWork(); // 初始化AINetWork中SSL Scheme
        initImageLoader(); // 初始化图片加载器
        initCrashHandler(); // 初始化程序崩溃捕捉处理
        initPrefs(); // 初始化SharedPreference
        initData();  // 初始化数据库
        // 友盟统计配置
        // 友盟推送
        // 缓存初始化
        // 字体初始化
    }

    /**
     * 初始化数据库
     */
    private void initData(){
        Connector.getDatabase();
    }

    /**
     * 初始化日志
     */
    private void initLogger() {
        LogUtils.initLog();
    }

    /**
     * 初始化线程池
     */
    private void initThreadPool() {
        ThreadPool.initThreadPool(-1);
    }

    /**
     * 初始化NetWork中S
     */
    private void initNetWork() {
    }

    /**
     * 初始化图片加载器（子类需要重写）
     */
    private void initImageLoader() {}

    /**
     * 初始化程序崩溃捕捉处理
     */
    private void initCrashHandler() {}

    /**
     * 初始化SharedPreference
     */
    private void initPrefs() {}

    /**
     * 获取应用的data/data/....File目录
     */
    public String getFilesDirPath() {
        return getFilesDir().getAbsolutePath();
    }

    /**
     * 获取应用的data/data/....Cache目录
     */
    public String getCacheDirPath() {
        return getCacheDir().getAbsolutePath();
    }

}

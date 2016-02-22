package com.zyl.melife.global;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * 全局配置类
 * 全局应用程序类：用于保存和调用全局应用配置
 * @author zhuyuliang
 * @version 1.0
 * @created 2015-09-02
 *
 */
public class MeApplication extends BaseApplication
{
	private final static String TAG = MeApplication.class.toString();

	private static MeApplication instance = null;
	public static MeApplication getIntance(){
		if (null == instance) {
			instance = new MeApplication();
		}
		return instance;
	}
	
	@Override
	public void onCreate()
	{
		super.onCreate();
		instance = this;
	}

	/**
	 * 初始化ImageLoader
	 */
	private void initImageLoader(){
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.diskCacheFileNameGenerator(new Md5FileNameGenerator())
				.diskCacheSize(50 * 1024 * 1024)
						// 50 Mb
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.writeDebugLogs() // Remove for release app
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}

}

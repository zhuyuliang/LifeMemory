package com.zyl.melife.logic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
/**
 * Activity跳转管理 （包括跳转封装方法，跳转参数的封装）
 * @author  Michael zhuyuliang (zhuyuliang0@126.com)
 * @version 1.0
 * @created 2013-7-25
 */
public class UIHelper
{
	
	/**
	 * 跳转Activity封装
	 * @param toclass 跳转的Activity
	 * @param context 当前activity
	 */
	public static void RedirectToActivity(Class toclass,Context context){
		Intent intent = new Intent(context,toclass);
		context.startActivity(intent);
	}
	public static void RedirectToActivityFinish(Class toclass,Context context){
		Intent intent = new Intent(context,toclass);
		context.startActivity(intent);
		((Activity)context).finish();
	}



}

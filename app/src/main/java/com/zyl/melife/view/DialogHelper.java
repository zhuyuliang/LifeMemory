package com.zyl.melife.view;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import com.zyl.melife.R;
import com.zyl.melife.global.MeApplication;
/**
 * @author 育梁 
 * Dialog公共工具类
 */
public class DialogHelper{

	/**
	 * 复制到剪切板
	 * @param string
	 */
	@SuppressWarnings("deprecation")
	public static void copyTextToBoard(String string) {
		if (TextUtils.isEmpty(string))
			return;
		ClipboardManager clip = (ClipboardManager) MeApplication.getIntance()
				.getSystemService(Context.CLIPBOARD_SERVICE);
		clip.setText(string);
	}

	/**
	 * 显示对话框
	 * @param msg 消息
	 */
	public static void showDialogCustom(Context context ,String msg) {
		AlertDialog.Builder ad = new AlertDialog.Builder(context);
		ad.setTitle(R.string.prompt);
		ad.setMessage(msg);
		ad.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		if(((Activity)context).isFinishing())return;
		ad.show();
	}

}
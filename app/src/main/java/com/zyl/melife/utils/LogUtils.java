/*
 * Copyright (c) 2013. wyouflf (wyouflf@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zyl.melife.utils;

import android.text.TextUtils;
import android.util.Log;

import com.zyl.melife.global.Const;
import com.zyl.melife.utils.thread.Runtask;
import com.zyl.melife.utils.thread.ThreadPool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Logger;

/**
 * Log工具. 1.根据需求可以简化LOG标签。2.设置本地日志文件保存。3.设置总开关--在配置文件中，也可以对单独的日志级别做开关
 * tag自动产生，格式: customTagPrefix:className.methodName(L:lineNumber),
 * customTagPrefix为空时只输出：className.methodName(L:lineNumber)。
 * Author: zhuyuliang email:zhuyuliang0@126.com
 * Date: 15-2-22
 */
public class LogUtils {

    public static void initLog(){
        //不能做复杂耗时的操作
        //设置总开关--在配置文件中
    }

    public static String customTagPrefix = "";

    public static boolean allowD = true;
    public static boolean allowE = true;
    public static boolean allowI = true;
    public static boolean allowV = true;
    public static boolean allowW = true;
    public static boolean allowWtf = true;

    /**
     * 是否在客户端记录用户操作
     */
    public static boolean logFile = false;

    private static String logFilePath  = Const.DEFAULT_SAVE_PATH + "log" + File.separator;

    /**
     * 获取TAG标签内容
     * @param caller
     * @return
     */
    private static String generateTag(StackTraceElement caller) {
        String tag = "%s.%s(L:%d)";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber());
        tag = TextUtils.isEmpty(customTagPrefix) ? tag : customTagPrefix + ":" + tag;
        return tag;
    }

    /**
     * DEBUG
     */
    public static void d(String content) {
        if ((!allowD)&&(!logFile))return;
        StackTraceElement caller = CommonUtils.getCallerStackTraceElement();
        String tag = generateTag(caller);
        if (allowD) {
            Log.d(tag, content);
        }
        if(logFile){
            writeLog(tag, content, null, "DEBUG");
        }
    }
    public static void d(String tag,String content) {
        if (allowD) {
            Log.d(tag, content);
        }
        if(logFile){
            writeLog(tag, content, null, "DEBUG");
        }
    }
    public static void d(String content, Throwable tr) {
        if ((!allowD)&&(!logFile))return;
        StackTraceElement caller = CommonUtils.getCallerStackTraceElement();
        String tag = generateTag(caller);
        if (allowD) {
            Log.d(tag, content, tr);
        }
        if(logFile){
            writeLog(tag, content, tr, "DEBUG");
        }
    }
    public static void d(String tag,String content, Throwable tr) {
        if (allowD) {
            Log.d(tag, content, tr);
        }
        if(logFile){
            writeLog(tag, content, tr, "DEBUG");
        }
    }

    /**
     * ERROR
     */
    public static void e(String content) {
        if ((!allowE)&&(!logFile))return;
        StackTraceElement caller = CommonUtils.getCallerStackTraceElement();
        String tag = generateTag(caller);
        if (allowE) {
            Log.e(tag, content);
        }
        if(logFile){
            writeLog(tag, content, null, "ERROR");
        }
    }
    public static void e(String tag,String content) {
        if (allowE) {
            Log.e(tag, content);
        }
        if(logFile){
            writeLog(tag, content, null, "ERROR");
        }
    }
    public static void e(String content, Throwable tr) {
        if ((!allowE)&&(!logFile))return;
        StackTraceElement caller = CommonUtils.getCallerStackTraceElement();
        String tag = generateTag(caller);
        if (allowE) {
            Log.e(tag, content, tr);
        }
        if(logFile){
            writeLog(tag, content, tr, "ERROR");
        }
    }
    public static void e(String tag,String content, Throwable tr) {
        if (allowE) {
            Log.e(tag, content, tr);
        }
        if(logFile){
            writeLog(tag, content, tr, "ERROR");
        }
    }

    /**
     * INFO
     */
    public static void i(String content) {
        if ((!allowI)&&(!logFile))return;
        StackTraceElement caller = CommonUtils.getCallerStackTraceElement();
        String tag = generateTag(caller);
        if (allowI) {
            Log.i(tag, content);
        }
        if(logFile){
            writeLog(tag, content, null, "INFO");
        }
    }
    public static void i(String tag,String content) {
        if (allowI) {
            Log.i(tag, content);
        }
        if(logFile){
            writeLog(tag, content, null, "INFO");
        }
    }
    public static void i(String content, Throwable tr) {
        if ((!allowI)&&(!logFile))return;
        StackTraceElement caller = CommonUtils.getCallerStackTraceElement();
        String tag = generateTag(caller);
        if (allowI) {
            Log.i(tag, content, tr);
        }
        if(logFile){
            writeLog(tag, content, tr, "INFO");
        }
    }
    public static void i(String tag,String content, Throwable tr) {
        if (allowI) {
            Log.i(tag, content, tr);
        }
        if(logFile){
            writeLog(tag, content, tr, "INFO");
        }
    }

    /**
     * VERBOSE
     */
    public static void v(String content) {
        if ((!allowV)&&(!logFile))return;
        StackTraceElement caller = CommonUtils.getCallerStackTraceElement();
        String tag = generateTag(caller);
        if (allowV) {
            Log.v(tag, content);
        }
        if(logFile){
            writeLog(tag, content, null, "INFO");
        }
    }
    public static void v(String tag,String content) {
        if (allowV) {
            Log.v(tag, content);
        }
        if(logFile){
            writeLog(tag, content, null, "INFO");
        }
    }
    public static void v(String content, Throwable tr) {
        if ((!allowV)&&(!logFile))return;
        StackTraceElement caller = CommonUtils.getCallerStackTraceElement();
        String tag = generateTag(caller);
        if (allowV) {
            Log.v(tag, content, tr);
        }
        if(logFile){
            writeLog(tag, content, tr, "INFO");
        }
    }
    public static void v(String tag,String content, Throwable tr) {
        if (allowV) {
            Log.v(tag, content, tr);
        }
        if(logFile){
            writeLog(tag, content, tr, "INFO");
        }
    }

    /**
     * WARN
     */
    public static void w(String content) {
        if ((!allowW)&&(!logFile))return;
        StackTraceElement caller = CommonUtils.getCallerStackTraceElement();
        String tag = generateTag(caller);
        if (allowW) {
            Log.w(tag, content);
        }
        if(logFile){
            writeLog(tag, content, null, "WARN");
        }
    }
    public static void w(String tag,String content) {
        if (allowW) {
            Log.w(tag, content);
        }
        if(logFile){
            writeLog(tag, content, null, "WARN");
        }
    }
    public static void w(String tag,String content, Throwable tr) {
        if (allowW) {
            Log.w(tag, content, tr);
        }
        if(logFile){
            writeLog(tag, content, tr, "WARN");
        }
    }
    public static void w(Throwable tr) {
        if ((!allowW)&&(!logFile))return;
        StackTraceElement caller = CommonUtils.getCallerStackTraceElement();
        String tag = generateTag(caller);
        if (allowW) {
            Log.w(tag, tr);
        }
        if(logFile){
            writeLog(tag, "", tr, "WARN");
        }
    }
    public static void w(String tag,Throwable tr) {
        if (allowW) {
            Log.w(tag, tr);
        }
        if(logFile){
            writeLog(tag, "", tr, "WARN");
        }
    }

    /**
     * android.util.Log.wtf()
     */
    public static void wtf(String content) {
        if ((!allowWtf)&&(!logFile))return;
        StackTraceElement caller = CommonUtils.getCallerStackTraceElement();
        String tag = generateTag(caller);
        if (allowWtf) {
            Log.w(tag, content);
        }
        if(logFile){
            writeLog(tag, content, null, "WTF");
        }
    }
    public static void wtf(String tag,String content) {
        if (allowWtf) {
            Log.w(tag, content);
        }
        if(logFile){
            writeLog(tag, content, null, "WTF");
        }
    }
    public static void wtf(String tag,String content, Throwable tr) {
        if (allowWtf) {
            Log.w(tag, content,tr);
        }
        if(logFile){
            writeLog(tag, content, tr, "WTF");
        }
    }
    public static void wtf(Throwable tr) {
        if ((!allowWtf)&&(!logFile))return;
        StackTraceElement caller = CommonUtils.getCallerStackTraceElement();
        String tag = generateTag(caller);
        if (allowWtf) {
            Log.w(tag,tr);
        }
        if(logFile){
            writeLog(tag, "", tr, "WTF");
        }
    }
    public static void wtf(String tag,Throwable tr) {
        if (allowWtf) {
            Log.w(tag,tr);
        }
        if(logFile){
            writeLog(tag, "", tr, "WTF");
        }
    }

    /**
     * 记录日志线程
     * @param tag
     * @param msg
     * @param tr
     * @param priority
     */
    private static void writeLog(String tag, String msg, Throwable tr, String priority){
        ThreadPool.go(new Runtask<Void, Void>(tag, msg, tr, priority) {
            @Override
            public Void runInBackground() {
                synchronized (Logger.class) {
                    String tag = (String) objs[0];
                    String msg = (String) objs[1];
                    Throwable tr = (Throwable) objs[2];
                    String priority = (String) objs[3];

                    if (!logFilePath.endsWith(File.separator)) {
                        logFilePath = logFilePath + File.separator;
                    }

                    String filename = logFilePath
                            + TimeUtils.getTime(System.currentTimeMillis(), TimeUtils.DATE_FORMAT_DATE)
                            + ".log";
                    File logFile = new File(filename);

                    OutputStream os = null;
                    try {
                        if (!logFile.exists()) {
                            logFile.createNewFile();
                        }

                        os = new FileOutputStream(logFile, true);

                        String formatMsg = TimeUtils.getTime(System.currentTimeMillis()) + "\r\n[" + priority + "][" + tag + "]: \r\n"
                                + "User Message: " + msg + "\r\n"
                                + (null == tr ? "" :

                                "Throwable Message: " + tr.getMessage() + "\r\n"
                                        + "Throwable StackTrace: " + transformStackTrace(tr.getStackTrace())
                        )
                                + "\r\n";

                        os.write(formatMsg.getBytes("utf-8"));
                        os.flush();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    } finally {
                        if (null != os) {
                            try {
                                os.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }


                }
                return null;
            }
        });

    }
    public static StringBuilder transformStackTrace(StackTraceElement[] elements){
        StringBuilder sb = new StringBuilder();
        for(StackTraceElement element : elements){
            sb.append(element.toString()).append("\r\n");
        }
        return sb;
    }

}

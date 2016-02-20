/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zyl.melife.global;

import android.os.Environment;

import java.io.File;

/**
 * 全局常量
 * @author 育梁
 * @created 2015-09-02
 */
public class Const {

	/**
	 * 文件保存路径
	 */
	// 默认存放图片的路径
    public final static String DEFAULT_SAVE_IMAGE_PATH = Environment.getExternalStorageDirectory()
            + File.separator
            + "IMe"
            + File.separator + "img" + File.separator;


    /* intent */
    public final static String PHOTO_POSITION = "photoPosition";
    public final static String CATEGORY_ID_4_PHOTNOTES = "categoryId4PhotoNotes";
    public final static String COMPARATOR_FACTORY = "camparator";
    public final static String USER_DETAIL_TYPE = "user_detail_type";

    public final static String WEBVIEW_URL = "webview_url";
    public final static String WEBVIEW_TITLE = "webview_title";
    /* 动画 */
    public final static int DURATION = 1000;
    public final static int DURATION_ACTIVITY = 2000;
    public final static int RADIUS = 10;
    /* 缩略图 */
    public final static int SMALL_PHOTO_WIDTH = 500;
    /* 广播 */
    public final static String BROADCAST_PHOTONOTE_UPDATE = "com.yydcdut.note.model.photonotedbmodel";
    public final static String TARGET_BROADCAST_PROCESS = "target_broadcast_process";//bool
    public final static String TARGET_BROADCAST_PHOTO = "target_broadcast_target";
    public final static String TARGET_BROADCAST_SERVICE = "target_broadcast_target";

    /* Camera */
    public final static String CAMERA_SIZE_WIDTH = "width";
    public final static String CAMERA_SIZE_HEIGHT = "height";
    public final static String CAMERA_BACK = "0";
    public final static String CAMERA_FRONT = "1";
    public final static int CAMERA_SANDBOX_PHOTO_RATIO_4_3 = 0;
    public final static int CAMERA_SANDBOX_PHOTO_RATIO_1_1 = 1;
    public final static int CAMERA_SANDBOX_PHOTO_RATIO_FULL = 2;

    public final static int LAYOUT_MAIN_CAPTURE = 101;

    public final static int LAYOUT_PERSONAL_RATIO_1_1 = 4200;
    public final static int LAYOUT_PERSONAL_RATIO_4_3 = 4201;
    public final static int LAYOUT_PERSONAL_RATIO_FULL = 4202;
    public final static int LAYOUT_PERSONAL_TIMER_0 = 4203;
    public final static int LAYOUT_PERSONAL_TIMER_3 = 4204;
    public final static int LAYOUT_PERSONAL_TIMER_5 = 4205;
    public final static int LAYOUT_PERSONAL_TIMER_10 = 4206;
    public final static int LAYOUT_PERSONAL_TIMER_15 = 4207;

    public final static int CAMERA_ID_REAR = 1000;
    public final static int CAMERA_ID_FRONT = 1001;

    public final static int CAMERA_PARAMS_GRID_OFF = 4210;
    public final static int CAMERA_PARAMS_GRID_ON = 4211;

    public final static int CAMERA_PARAMS_SOUND_OFF = 4220;
    public final static int CAMERA_PARAMS_SOUND_ON = 4221;
	
}

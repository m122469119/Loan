/*
 * Copyright (C) 2012 www.amsoft.cn
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dumai.xianjindai.global;

// TODO: Auto-generated Javadoc

import android.os.Environment;

import java.io.File;

/**
 * 名称：Constant.java
 * 描述：常量.
 *
 * @author haoruigang
 * @version v1.0
 * @date：2017-11-19 11:50:14
 */
public class Constant {

    public static String APPLICATION_DIR = Environment.getExternalStorageDirectory() + File.separator + "xianjindai";//项目根目录
    public static String LOG_PATH = APPLICATION_DIR + File.separator + "logs" + File.separator;//项目日志目录
    public static String LOG_FILE_NAME = "xianjindaiLog.txt";//项目日志文件目录
    public static String CAMERA_PATH = APPLICATION_DIR + File.separator + "img" + File.separator;//拍照路径

}

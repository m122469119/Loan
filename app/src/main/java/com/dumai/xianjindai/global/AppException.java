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


import com.dumai.xianjindai.util.StrUtil;

import org.apache.http.conn.ConnectTimeoutException;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;


// TODO: Auto-generated Javadoc

/**
 * 名称：AppException.java
 * 描述：公共异常类.
 *
 * @author haoruigang
 * @version v1.0
 * @date：2017-11-19 11:50:40
 */
public class AppException extends Exception {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1;


    /**
     * 异常消息.
     */
    private String msg = null;

    /**
     * 构造异常类.
     *
     * @param e 异常
     */
    public AppException(Exception e) {
        super();

        try {
            if (e instanceof ConnectException) {
                msg = AppConfig.CONNECT_EXCEPTION;
            } else if (e instanceof ConnectTimeoutException) {
                msg = AppConfig.CONNECT_EXCEPTION;
            } else if (e instanceof UnknownHostException) {
                msg = AppConfig.UNKNOWN_HOST_EXCEPTION;
            } else if (e instanceof SocketException) {
                msg = AppConfig.SOCKET_EXCEPTION;
            } else if (e instanceof SocketTimeoutException) {
                msg = AppConfig.SOCKET_TIMEOUT_EXCEPTION;
            } else if (e instanceof NullPointerException) {
                msg = AppConfig.NULL_POINTER_EXCEPTION;
            } else {
                if (e == null || StrUtil.isEmpty(e.getMessage())) {
                    msg = AppConfig.NULL_MESSAGE_EXCEPTION;
                } else {
                    msg = e.getMessage();
                }
            }
        } catch (Exception e1) {
        }

    }

    /**
     * 用一个消息构造异常类.
     *
     * @param message 异常的消息
     */
    public AppException(String message) {
        super(message);
        msg = message;
    }

    /**
     * 描述：获取异常信息.
     *
     * @return the message
     */
    @Override
    public String getMessage() {
        return msg;
    }

}

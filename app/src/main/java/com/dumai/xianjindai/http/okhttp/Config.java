package com.dumai.xianjindai.http.okhttp;

/**
 * @author haoruigang
 * @Package com.haoruigang.okhttpdome
 * @project OkHttpDome
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017/11/26 15:50
 */
public class Config {

    public static class API {

        // 1.服务器IP
        public static final String WAPI_IP = "192.168.0.10";
        //        1.1 本地
//        public static final String WAPI_IP = "192.168.0.133";
        public static final String WAPI_HTTP_BASE = "http://" + WAPI_IP;
        // 2.服务器端口
        public static final String WAPI_HTTP_PORT = "8082";
        public static final String WAPI_HTTP_URL = WAPI_HTTP_BASE + ":"
                + WAPI_HTTP_PORT + "/riskmm";
        // 3.项目名称
        public static String PROJECTNAME = "app";
        // 4.基本请求地址
        public static final String WAPI_URL_S = WAPI_HTTP_URL + "/" + PROJECTNAME
                + "/";

        // 登录地址 http://192.168.0.10:8082/riskmm/app/login.do
        public static final String LOGIN_URL = WAPI_URL_S
                + "login.do";
        // 注册地址 http://192.168.0.133:8082/riskmm/app/register.do
        public static final String REGISTER_URL = WAPI_URL_S
                + "register.do";


    }
}

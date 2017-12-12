package com.dumai.xianjindai.http.http;

import com.dumai.xianjindai.http.okhttp.Config;
import com.dumai.xianjindai.http.okhttp.OkHttpUtils;
import com.dumai.xianjindai.jparser.JsonLoginObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者： haoruigang on 2017-11-28 11:14:10.
 * 类描述：网络帮助类(主要用来管理参数)
 */
public class HttpManager {
    //    public VolleyUtils httpUtils;
    public OkHttpUtils okhttpUtils;

    private HttpManager() {
//        httpUtils = VolleyUtils.getInstance();
        okhttpUtils = OkHttpUtils.Instance();
    }

    private static class SingletonHolder {
        static HttpManager INSTANCE = new HttpManager();
    }

    public static HttpManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 作者： haoruigang on 2017/11/28 11:16
     * 描述: 登录
     *
     * @param tag
     * @param username
     * @param pwd
     * @param callBack
     */
    public void doLoginRequest(String tag, String username, String pwd, HttpCallBack callBack) {
        final Map<String, String> map = new HashMap<>();
        map.put("loginName", username);
        map.put("password", pwd);
        String url = Config.API.LOGIN_URL;
        okhttpUtils.postJsonOkhttpRequest(tag, url, map, callBack);
    }

    /**
     * 作者： haoruigang on 2017-12-1 14:25:06
     * 描述: 注册
     *
     * @param tag
     * @param phoneNum
     * @param pwd
     * @param callBack
     */
    public void doReginster(String tag, String phoneNum, String pwd, HttpCallBack callBack) {
        final Map<String, String> map = new HashMap<>();
        map.put("mobile", phoneNum);
        map.put("password", pwd);
        String url = Config.API.REGISTER_URL;
        okhttpUtils.postJsonOkhttpRequest(tag, url, map, callBack);
    }

    /**
     * 作者： haoruigang on 2017-12-4 09:36:02
     * 描述: 身份信息提交
     *
     * @param tag
     * @param identityName
     * @param identityCode
     * @param province
     * @param city
     * @param county
     * @param detailedArs
     * @param education
     * @param maritalStatus
     * @param callBack
     */
    public void doIdentityRequest(String tag, String identityName, String identityCode, String province, String city, String county, String detailedArs, String education, String maritalStatus, HttpCallBack<JsonLoginObject> callBack) {
        final Map<String, String> map = new HashMap<>();
        map.put("identityName", identityName);
        map.put("identityCode", identityCode);
        map.put("province", province);
        map.put("city", city);
        map.put("county", county);
        map.put("detailedArs", detailedArs);
        map.put("education", education);
        map.put("maritalStatus", maritalStatus);
        String url = Config.API.REGISTER_URL;
        okhttpUtils.postJsonOkhttpRequest(tag, url, map, callBack);
    }
}



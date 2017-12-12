package com.dumai.xianjindai.http.volley;

import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dumai.xianjindai.MineApplication;
import com.dumai.xianjindai.http.http.HttpCallBack;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者： haoruigang on 2017-11-28 10:54:18.
 * 类描述：Volley网络封装类
 */
public class VolleyUtils {
    private Logger logger;
    private final RequestQueue queue;

    private VolleyUtils() {
        MyVolley.init(MineApplication.getInstance());
        queue = MyVolley.getRequestQueue();
        logger = Logger.getLogger(getClass());
    }

    private static class SingletonHolder {
        static VolleyUtils INSTANCE = new VolleyUtils();
    }

    public static VolleyUtils getInstance() {
        return SingletonHolder.INSTANCE;
    }

    // get请求  haoruigang  2017-11-28 10:55:09
    public void httpGet(String tag, String url, final Map<String, String> map, final HttpCallBack callback) {
        setHttp(tag, Request.Method.GET, url, map, callback);
    }

    // post请求  haoruigang  2017-11-28 10:55:14
    public void httpPost(String tag, String url, final Map<String, String> map, final HttpCallBack callback) {
        setHttp(tag, Request.Method.POST, url, map, callback);
    }

    public void setHttp(final String tag, int method, final String url, final Map<String, String> map, final HttpCallBack callback) {
        String params = "";
        if (null != map)
            for (String s : map.keySet()) {
                params += (s + ":" + map.get(s) + "&");
            }
        logger.info("请求:" + url + "    参数：" + params);
//        map.put("time", System.currentTimeMillis() + "");
        StringRequest req = new StringRequest(method, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                int segmentSize = 3000;
                long length = response.length();
                if (length <= segmentSize) {
                    // 长度小于等于限制直接打印
                    logger.info("请求:" + url + "   请求结果:\n" + response);
                } else {
                    logger.info("请求:" + url + "   请求结果:");
                    while (length > segmentSize) {
//                        循环分段打印日志
                        String logContent = response.substring((segmentSize - 3000), segmentSize);
                        logger.info(logContent);
                        segmentSize += 3000;
                    }
                    logger.info(response.substring((segmentSize - 3000), response.length()));// 打印剩余日志
                }
                if (null != callback)
                    callback.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                logger.info("请求:" + url + "    请求失败", error);
                if (null != callback)
                    callback.error(error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                if (null == map) {
                    return new HashMap<>();
                }
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }

        };
        if (!TextUtils.isEmpty(tag))
            req.setTag(tag);
        req.setShouldCache(false);
        queue.add(req);
        if (null != callback)
            callback.setRequest(req);
    }
}

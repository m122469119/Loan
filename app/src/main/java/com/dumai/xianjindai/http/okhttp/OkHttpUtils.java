package com.dumai.xianjindai.http.okhttp;

import android.os.Environment;
import android.util.Log;

import com.dumai.xianjindai.http.http.HttpCallBack;
import com.dumai.xianjindai.http.okhttp.progress.ProgressListener;
import com.dumai.xianjindai.http.okhttp.progress.ProgressResponseBody;
import com.dumai.xianjindai.util.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.logging.Logger;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author haoruigang
 * @Package com.haoruigang.okhttpdome
 * @project OkHttpDome
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017/11/26 14:05
 */
public class OkHttpUtils {


    public static OkHttpClient client;

    public OkHttpUtils() {
        super();
        client = new OkHttpClient();
    }

    public static OkHttpUtils Instance() {
        OkHttpUtils httpUtils = null;
        if (httpUtils == null) {
            httpUtils = new OkHttpUtils();
        }
        return httpUtils;
    }

    /**
     * Get请求（获取信息）
     */
    public void getOkhttpRequest(final String tag, String url, Map<String, String> map, final HttpCallBack callBack) {
        Log.d("VolleyUtils", "geturl------>" + url);
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("VolleyUtils", "get失败------>" + e.getLocalizedMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String result = response.body().toString();
                    Log.d("VolleyUtils", "get成功：" + result);
                    if (response != null) {
                        response.body().close();
                    }
                }
            }
        });
    }

    /**
     * Post请求（Form表单形式）
     */
    public void postFormOkhttpRequest(final String tag, String url, Map<String, String> map, final HttpCallBack callBack) {
        Log.d("VolleyUtils", "posturl------>" + url);

        RequestBody body = new FormBody.Builder().add("username", "hometest").add("pad", "hometest").build();

        Request request = new Request.Builder()
                .post(body)
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("VolleyUtils", "post失败------>" + e.getLocalizedMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String result = response.body().toString();
                    Log.d("VolleyUtils", "post成功：" + result);
                    if (response != null) {
                        response.body().close();
                    }
                }
            }
        });
    }

    /**
     * Post请求（JSON参数形式）
     *
     * @param tag
     * @param url
     * @param map
     * @param callBack
     */
    public void postJsonOkhttpRequest(final String tag, String url, Map<String, String> map, final HttpCallBack callBack) {

        JSONObject jsonObj = new JSONObject(map);
        String jsonParams = jsonObj.toString();
        Log.d(tag, "请求:" + url + "    参数：" + jsonParams);

        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonParams);
        final Request request = new Request.Builder()
                .post(body)
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtil.e(tag, "请求失败------>" + e.getLocalizedMessage());
                if (null != callBack)
                    callBack.error(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String result = response.body().string();
                    Log.d(tag, "请求成功结果:\n" + result);
                    if (null != callBack) {
                        callBack.onResponse(result);
                    }
                    if (response != null) {
                        response.body().close();
                    }
                }
            }
        });
    }


    /**
     * 文件下载
     *
     * @param progressBarCall
     */
    public void downloadOkhttpRequest(final ProgressListener progressBarCall) {
        /**
         * 拦截器方式
         */
        OkHttpClient clientinterceptro = new OkHttpClient.Builder().addNetworkInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Response response = chain.proceed(chain.request());

                return response.newBuilder().body(new ProgressResponseBody(response.body(), progressBarCall)).build();
            }
        }).build();
        /**
         * 简单方式
         */
        String url = "http://101.201.111.160:8080/apk/yyshed402.apk";
        Log.d("VolleyUtils", "downloadurl------>" + url);
        Request request = new Request.Builder()
                .url(url)
                .build();

        clientinterceptro.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("VolleyUtils", "下载失败------>" + e.getLocalizedMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                writeFile(response);
            }
        });
    }


    private void writeFile(Response response) throws IOException {

        InputStream is = null;
        FileOutputStream fos = null;

        is = response.body().byteStream();

        String path = Environment.getExternalStorageDirectory().getAbsolutePath();

        String fileName = "gang.apk";
        File file = new File(path, fileName);
        Log.d("VolleyUtils", "下载file------>" + file);
        try {
            fos = new FileOutputStream(file);

            byte[] bytes = new byte[1024];
            int len = 0;

//            long totalSize = response.body().contentLength();
//            long sum = 0;

            while ((len = is.read(bytes)) != -1) {

                fos.write(bytes);

//                sum += len;
//                int progress = (int) ((sum * 1.0f / totalSize) * 100);
//                progressBarCall.progressBar(progress);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                is.close();
            }
            if (fos != null) {
                fos.close();
            }
        }
    }
}

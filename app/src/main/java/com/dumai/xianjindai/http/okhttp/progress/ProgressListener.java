package com.dumai.xianjindai.http.okhttp.progress;

/**
 * @author haoruigang
 * @Package com.haoruigang.okhttpdome
 * @project OkHttpDome
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017/11/26 16:57
 */
public interface ProgressListener {

    void progressBar(int progress);

    void onDone(long totalsize);
}

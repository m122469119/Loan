package com.dumai.xianjindai.http.okhttp.progress;

import android.support.annotation.Nullable;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * @author haoruigang
 * @Package com.haoruigang.okhttpdome.okhttp
 * @project OkHttpDome
 * @Description: 拦截器  TODO(这里用一句话描述这个类的作用)
 * @date 2017/11/26 20:31
 */
public class ProgressResponseBody extends ResponseBody {

    private ResponseBody mResponseBody;
    private ProgressListener mProgressListener;
    private BufferedSource bufferedSource;

    public ProgressResponseBody(ResponseBody mResponseBody, ProgressListener mProgressListener) {
        super();
        this.mResponseBody = mResponseBody;
        this.mProgressListener = mProgressListener;
    }

    @Nullable
    @Override
    public MediaType contentType() {
        return mResponseBody.contentType();
    }

    @Override
    public long contentLength() {
        return mResponseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(getSource(mResponseBody.source()));
        }
        return bufferedSource;
    }


    private Source getSource(Source source) {
        return new ForwardingSource(source) {

            long totalSize = 0l;
            long sum = 0l;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {

                if (totalSize == 0) {
                    totalSize = contentLength();
                }
                long len = super.read(sink, byteCount);
                sum += (len == -1 ? 0 : len);
                int progress = (int) ((sum * 1.0f / totalSize) * 100);
                if (len == -1) {
                    mProgressListener.onDone(totalSize);
                } else {
                    mProgressListener.progressBar(progress);
                }

                return len;
            }
        };
    }

}

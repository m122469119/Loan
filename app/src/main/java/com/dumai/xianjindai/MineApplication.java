package com.dumai.xianjindai;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.dumai.xianjindai.global.Constant;
import com.dumai.xianjindai.util.CrashHandler;
import com.dumai.xianjindai.util.Log4jUtil;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;

import org.apache.log4j.Level;
import org.litepal.LitePal;
import org.litepal.LitePalApplication;
import org.litepal.tablemanager.Connector;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @author haoruigang
 * @Package com.dumai.xianjindai
 * @project xianjindai
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017/11/19 08:53
 */
public class MineApplication extends LitePalApplication {

    private static Context mContext;
    private static MineApplication mApplication;
    //线程池
    public static ExecutorService executorService = null;


    public static Context getContext() {
        return mContext;
    }

    public static MineApplication getInstance() {
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // 一些相关的设置
        mApplication = this;
        mContext = this;
        init();
    }

    private void init() {
        // 注册全局异常捕获
        CrashHandler.getInstance().init(getApplicationContext());
        //性能检测
        LeakCanary.install(this);
        initExecut();
        //普通日志打印初始化
        Logger.addLogAdapter(new AndroidLogAdapter());
        //初始化Sqlite
        LitePal.initialize(this);
    }

    private void initExecut() {
        setupLogger();
        if (executorService == null) {
            //创建线程池，可以容纳10个线程任务的线程池
            executorService = Executors.newFixedThreadPool(10);
        }
    }

    /**
     * 配置日志log4j
     */
    private void setupLogger() {
        String fileName = Constant.LOG_PATH + Constant.LOG_FILE_NAME;
        Log4jUtil.exportLogToFile(fileName, Level.DEBUG, (long) (1024 * 1024 * 5), 200, true);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        executorService.shutdown();
        super.onTerminate();
    }
}

package com.dumai.xianjindai.util;

import org.apache.log4j.Level;

import de.mindpipe.android.logging.log4j.LogConfigurator;

/**
 * 作者： haoruigang on 2017-11-19 12:28:28
 * 类描述：文本log日志打印
 */
public class Log4jUtil {
    /**
     * @param filename 文件名(含路径)
     * @param level    日志级别
     * @param maxsize  最大文件大小
     * @param number   文件数量
     * @param isopen   是否输出到文件
     */
    public static void exportLogToFile(String filename, Level level, long maxsize, int number, boolean isopen) {
        LogConfigurator logConfigurator = new LogConfigurator();
        logConfigurator.setFileName(filename);
        logConfigurator.setUseFileAppender(isopen);
        logConfigurator.setRootLevel(Level.ALL);
        logConfigurator.setLevel("org.apache", level);
        logConfigurator.setFilePattern("%d %-5p [%c{2}]-[%L] %m%n");
        logConfigurator.setMaxFileSize(maxsize);
        logConfigurator.setImmediateFlush(true);
        logConfigurator.configure();
    }

}

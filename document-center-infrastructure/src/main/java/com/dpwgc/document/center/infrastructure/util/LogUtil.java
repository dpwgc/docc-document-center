package com.dpwgc.document.center.infrastructure.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志记录
 */
public class LogUtil {

    /**
     * 日志记录器对象
     */
    private static final Logger LOGGER= LoggerFactory.getLogger(LogUtil.class);


    public static void info(String title,String content,String filter) {
        LOGGER.info(String.format("<title> { %s } <content> { %s } <filter> { %s }",title,content,filter));
    }

    public static void error(String title,String content,String filter) {
        LOGGER.error(String.format("<title> { %s } <content> { %s } <filter> { %s }",title,content,filter));
    }

    public static void warn(String title,String content,String filter) {
        LOGGER.warn(String.format("<title> { %s } <content> { %s } <filter> { %s }",title,content,filter));
    }

    public static void debug(String title,String content,String filter) {
        LOGGER.debug(String.format("<title> { %s } <content> { %s } <filter> { %s }",title,content,filter));
    }
}

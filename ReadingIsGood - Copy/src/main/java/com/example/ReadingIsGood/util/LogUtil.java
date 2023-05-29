package com.example.ReadingIsGood.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LogUtil {
    private static final Logger logger = LoggerFactory.getLogger(LogUtil.class);

    public static void info(String message) {
        logger.info(message);
    }

    public static void info(String message, Object arg) {
        logger.info(message, arg);
    }
    public static void info(String message, Object arg, Object arg2) {
        logger.info(message, arg,arg2);
    }


    public static void error(String message, String email) {
        logger.info(message, email);
    }
}

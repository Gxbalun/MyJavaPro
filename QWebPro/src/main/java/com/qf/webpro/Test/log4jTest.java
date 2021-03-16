package com.qf.webpro.Test;

import org.apache.log4j.Logger;

public class log4jTest {
    static Logger logger = Logger.getLogger(log4jTest.class);
    public static void main(String[] args) {
        logger.debug("debug级别的日志信息");
        logger.info("info级别的日志信息");
        logger.warn("warn级别的日志信息");
        logger.error("error级别的日志信息");
    }
}

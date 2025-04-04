package com.thesis.serverfurnitureecommerce.logs;

import org.slf4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.util.Properties;

public class AppVersionLogger {
    private final Logger LOGGER;
    private String logPattern = "[%s] %s";
    private String appVersion;

    public AppVersionLogger(Logger logger) {
        this.LOGGER = logger;
        try {
            Resource resource = new ClassPathResource("environments/environment.properties");
            Properties props = PropertiesLoaderUtils.loadProperties(resource);
            this.appVersion = props.getProperty("environment.version");
        } catch (Exception ex) {
            this.appVersion = "";
        }
    }

    public void debug(String s) {
        this.LOGGER.debug(String.format(logPattern, this.appVersion, s));
    }

    public void debug(String s, Object o) {
        this.LOGGER.debug(String.format(logPattern, this.appVersion, s), o);
    }

    public void debug(String s, Object o, Object o1) {
        this.LOGGER.debug(String.format(logPattern, this.appVersion, s), o, o1);
    }

    public void debug(String s, Object o, Object o1, Object o2) {
        this.LOGGER.debug(String.format(logPattern, this.appVersion, s), o, o1, o2);
    }

    public void debug(String s, Object o, Object o1, Object o2, Object o3) {
        this.LOGGER.debug(String.format(logPattern, this.appVersion, s), o, o1, o2, o3);
    }

    public void debug(String s, Object o, Object o1, Object o2, Object o3, Object o4) {
        this.LOGGER.debug(String.format(logPattern, this.appVersion, s), o, o1, o2, o3, o4);
    }

    public void info(String s) {
        this.LOGGER.info(String.format(logPattern, this.appVersion, s));
    }

    public void info(String s, Object o) {
        this.LOGGER.info(String.format(logPattern, this.appVersion, s), o);
    }

    public void info(String s, Object o, Object o1) {
        this.LOGGER.info(String.format(logPattern, this.appVersion, s), o, o1);
    }

    public void info(String s, Object o, Object o1, Object o2) {
        this.LOGGER.info(String.format(logPattern, this.appVersion, s), o, o1, o2);
    }

    public void info(String s, Object o, Object o1, Object o2, Object o3) {
        this.LOGGER.info(String.format(logPattern, this.appVersion, s), o, o1, o2, o3);
    }

    public void info(String s, Object o, Object o1, Object o2, Object o3, Object o4) {
        this.LOGGER.info(String.format(logPattern, this.appVersion, s), o, o1, o2, o3, o4);
    }

    public void info(String s, Object o, Object o1, Object o2, Object o3, Object o4, Object o5) {
        this.LOGGER.info(String.format(logPattern, this.appVersion, s), o, o1, o2, o3, o4, o5);
    }

    public void warn(String s) {
        this.LOGGER.warn(String.format(logPattern, this.appVersion, s));
    }

    public void warn(String s, Object o) {
        this.LOGGER.warn(String.format(logPattern, this.appVersion, s), o);
    }

    public void warn(String s, Object o, Object o1) {
        this.LOGGER.warn(String.format(logPattern, this.appVersion, s), o, o1);
    }
}

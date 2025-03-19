package org.example;

import org.example.appender.FileAppender;

public class LoggingFrameworkDemo {
    public static void main(String[] args) {
        Logger logger=Logger.getInstance();
        logger.info("This is an information message");
        logger.warning("This is a warning message");
        logger.error("This is an error message");

        LoggerConfig config = new LoggerConfig(LogLevel.DEBUG, new FileAppender("app.log"));
        logger.setConfig(config);

        logger.debug("This is a debug message");
        logger.info("This is an information message");

    }
}

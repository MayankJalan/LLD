package org.example;

import org.example.appender.ConsoleAppender;

public class Logger {
    private static final Logger logger = new Logger();
    private LoggerConfig config;

    public Logger() {
        config = new LoggerConfig(LogLevel.INFO, new ConsoleAppender());
    }

    public static Logger getInstance() {
        return logger;
    }

    public void setConfig(LoggerConfig config) {
        this.config = config;
    }

    public void log(LogLevel level, String message) {
        if (level.ordinal() >= config.getLogLevel().ordinal()) {
            LogMessage logMessage = new LogMessage(level, message);
            config.getLogAppender().append(logMessage);
        }
    }
    public void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    public void warning(String message) {
        log(LogLevel.WARNING, message);
    }

    public void error(String message) {
        log(LogLevel.ERROR, message);
    }

    public void fatal(String message) {
        log(LogLevel.FATAL, message);
    }
}

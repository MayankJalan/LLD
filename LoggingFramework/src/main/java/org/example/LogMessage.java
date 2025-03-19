package org.example;

import java.util.Date;

public class LogMessage {
    private final LogLevel level;
    private final String message;
    private final long timestamp;

    public LogMessage(LogLevel level, String message) {
        this.level = level;
        this.message = message;
        timestamp= System.currentTimeMillis();
    }
    public LogLevel getLevel() {
        return level;
    }

    public String getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }


    @Override
    public String toString() {
        return "LogMessage{" +
                "level=" + level +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}

package org.example.appender;

import org.example.LogMessage;

public interface LogAppender {
    void append(LogMessage logMessage);
}

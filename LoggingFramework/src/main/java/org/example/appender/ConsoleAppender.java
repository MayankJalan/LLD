package org.example.appender;

import org.example.LogMessage;

public class ConsoleAppender implements LogAppender{
    @Override
    public void append(LogMessage logMessage) {
        System.out.println(logMessage.toString());
    }
}

package org.example.appender;

import org.example.LogMessage;

import java.io.FileWriter;
import java.io.IOException;

public class FileAppender implements LogAppender{
    private final String filePath;

    public FileAppender(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void append(LogMessage logMessage) {
        try(FileWriter writer=new FileWriter(filePath)) {
            writer.write(logMessage.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

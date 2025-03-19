package org.example.appender;

import org.example.LogMessage;

import java.sql.*;

public class DataBaseAppender implements LogAppender{
    private final String jdbcUrl;
    private final String username;
    private final String password;

    public DataBaseAppender(String jdbcUrl, String username, String password) {
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
    }

    @Override
    public void append(LogMessage logMessage) {
        try(Connection connection= DriverManager.getConnection(jdbcUrl,username,password)) {
            String statement="INSERT INTO LOGS (level, message, timestamp) VALUES ?,?,?";
            PreparedStatement preparedStatement=connection.prepareStatement(statement);
            preparedStatement.setString(1,logMessage.getLevel().toString());
            preparedStatement.setString(2,logMessage.getMessage());
            preparedStatement.setLong(3,logMessage.getTimestamp());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

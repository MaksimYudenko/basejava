package ru.javawebinar.basejava.sql;

import ru.javawebinar.basejava.exception.StorageExceptionHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {

    private final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public <T> T executeSql(String sqlRequest, Executor<T> executor) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlRequest)) {
            return executor.execute(ps);
        } catch (SQLException e) {
//            System.out.println("getSQLState - " + e.getSQLState());
            throw new StorageExceptionHandler(e);
        }
    }

}
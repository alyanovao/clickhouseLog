package ru.otus.weatherapp;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class DemoHikari {
    private static final String DB_URL = "jdbc:clickhouse://localhost:8123/default";
    private static final String USER = "default";
    private static final String PASSWORD = null;

    String QUERY = "INSERT INTO log (data) VALUES (?)";

    private DataSource dataSource;

    public DemoHikari() {
        dataSource = new DriverManagerDataSource(DB_URL, USER, PASSWORD);
    }

    public void setLog() {
        String uuid = UUID.randomUUID().toString();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(QUERY)) {
                statement.setString(1, uuid);
                statement.executeUpdate();
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

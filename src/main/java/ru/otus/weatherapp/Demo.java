package ru.otus.weatherapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class Demo implements AutoCloseable {
    private static final String DB_URL = "jdbc:clickhouse://localhost:8123/default";

    String QUERY = "INSERT INTO log (data) VALUES (?)";

    private Connection conn;

    /**
     * Creates new instance
     * @throws SQLException in case of connection issue
     */
    public Demo() throws SQLException {
        conn = DriverManager.getConnection(DB_URL);
    }

    /**
     * Queries db to get most popular flight route for ths given year
     * @param year year to query
     * @throws SQLException in case of query issue
     */
    public void setLog() throws SQLException {
        String uuid = UUID.randomUUID().toString();
        try (PreparedStatement statement = conn.prepareStatement(QUERY)) {
            statement.setString(1, uuid);
            statement.executeUpdate();
        }
    }

    public void close() throws Exception {
        if (conn != null) {
            conn.close();
        }
    }
}

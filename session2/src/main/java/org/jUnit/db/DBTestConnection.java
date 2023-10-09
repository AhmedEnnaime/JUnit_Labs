package org.jUnit.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBTestConnection {
    private static Connection conn;

    public static Connection establishTestConnection() {
        if (conn == null) {
            String jdbcUrl = "jdbc:postgresql://localhost:5432/junit";
            String username = "postgres";
            String password = "3ea14367A4";

            try {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection(jdbcUrl, username, password);

                if (conn != null) {
                    System.out.println("Connection to testing PostgreSQL database established.");
                    createEmployeesTable(conn);
                    createClientsTable(conn);
                }

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    public static void createEmployeesTable(Connection conn) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS employees ("
                + "matricule SERIAL PRIMARY KEY,"
                + "firstName VARCHAR(255),"
                + "lastName VARCHAR(255),"
                + "recruitmentDate DATE,"
                + "birthDate DATE,"
                + "email VARCHAR(255),"
                + "phone VARCHAR(255),"
                + "address VARCHAR(255)"
                + ");";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(createTableSQL);
        }
    }

    public static void createClientsTable(Connection conn) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS clients ("
                + "code SERIAL PRIMARY KEY,"
                + "firstName VARCHAR(255),"
                + "lastName VARCHAR(255),"
                + "birthDate DATE,"
                + "phone VARCHAR(255),"
                + "address VARCHAR(255),"
                + "employeeMatricule INT"
                + ");";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(createTableSQL);
        }
    }
}

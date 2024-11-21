/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.management.inventory.management;
import java.sql.*;

/**
 *
 * @author gaura
 */
public class EnhancedDataSynchronizer {


    // Method to ensure table exists and fetch schema dynamically
    private static String ensureTableExistsAndGetSchema(Connection conn, String tableName) throws SQLException {
        DatabaseMetaData dbm = conn.getMetaData();
        ResultSet rs = dbm.getTables(null, null, tableName, null);
        if (!rs.next()) {
            throw new SQLException("Table " + tableName + " does not exist in source database.");
        }
        return "(id INT PRIMARY KEY, data VARCHAR(255))"; // Simplified schema
    }

    // Fetch data from a table
    private static ResultSet fetchData(Connection conn, String tableName) throws SQLException {
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        return stmt.executeQuery("SELECT * FROM " + tableName);
    }

    // Insert data into a table if not exists
    private static void insertDataIfNotExists(Connection conn, ResultSet data, String tableName) throws SQLException {
        ResultSetMetaData metaData = data.getMetaData();
        int columnCount = metaData.getColumnCount();
        StringBuilder insertQuery = new StringBuilder("INSERT INTO " + tableName + " VALUES (");
        for (int i = 1; i <= columnCount; i++) {
            insertQuery.append("?");
            if (i < columnCount) insertQuery.append(", ");
        }
        insertQuery.append(") ON CONFLICT (id) DO NOTHING");

        try (PreparedStatement pstmt = conn.prepareStatement(insertQuery.toString())) {
            while (data.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    pstmt.setObject(i, data.getObject(i));
                }
                pstmt.executeUpdate();
            }
        }
    }
private static void ensureTableExists(Connection conn, String tableName, String tableSchema) throws SQLException {
    DatabaseMetaData dbm = conn.getMetaData();
    try (ResultSet tables = dbm.getTables(null, null, tableName, null)) {
        if (!tables.next()) {
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("CREATE TABLE " + tableName + " " + tableSchema);
            }
        }
    }
}

    // Synchronize all tables
    public static void synchronizeAllTables(String sourceUrl, String targetUrl, String user, String password) {
        try (Connection sourceConn = DriverManager.getConnection(sourceUrl, user, password);
             Connection targetConn = DriverManager.getConnection(targetUrl, user, password)) {

            DatabaseMetaData dbm = sourceConn.getMetaData();
            try (ResultSet tables = dbm.getTables(null, null, "%", new String[]{"TABLE"})) {
                while (tables.next()) {
                    String tableName = tables.getString("TABLE_NAME");
                    String tableSchema = ensureTableExistsAndGetSchema(sourceConn, tableName);
                    ensureTableExists(targetConn, tableName, tableSchema);

                    try (ResultSet data = fetchData(sourceConn, tableName)) {
                        insertDataIfNotExists(targetConn, data, tableName);
                    } // Closing try for data fetching
                } // Closing while loop
            } // Closing try for table fetching
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // Closing method synchronizeAllTables
} // Closing class EnhancedDataSynchronizer



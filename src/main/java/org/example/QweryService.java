package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QweryService {

    public ResultSet executeQuery(String query){
        try {
            return getStatement().executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public PreparedStatement getPreparedStatement(String query) throws SQLException {
        Connection connection = Database.getInstance().getConnection();
        return connection.prepareStatement(query);
    }

    private Statement getStatement() throws SQLException {
        Connection conn = Database.getInstance().getConnection();
         return conn.createStatement();
    }
}

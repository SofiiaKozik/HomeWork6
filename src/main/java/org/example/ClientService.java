package org.example;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class ClientService {

    QweryService qweryService = new QweryService();

    public long create(String name) throws IllegalAccessException, SQLException {
        nameCheck(name);
        Long id = getLastClientID() + 1;
        String query = "INSERT INTO client\n" +
                "(ID, NAME)\n" +
                "VALUES\n" +
                "(?,?);";
        PreparedStatement preparedStatement = qweryService.getPreparedStatement(query);
        preparedStatement.setLong(1, id);
        preparedStatement.setString(2, name);
        preparedStatement.executeUpdate();
        return id;
    }

    public String getById(long id) throws SQLException {
        return findClientById(id).toString();
    }

    public void setName(long id, String name) throws SQLException, IllegalAccessException {
        nameCheck(name);
        String query = "UPDATE client SET name = ? WHERE id = ?";
        PreparedStatement preparedStatement = qweryService.getPreparedStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setLong(2, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void deleteById(long id) throws SQLException {
        String query = "DELETE FROM client WHERE ID = ?";
        PreparedStatement preparedStatement = qweryService.getPreparedStatement(query);
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
    }

    public List<Client> listAll() throws SQLException {
        List<Client> clientList = new LinkedList<>();
        String query = "SELECT * FROM client";
        ResultSet resultSets = qweryService.executeQuery(query);
        while (resultSets.next()) {
            long id = resultSets.getLong("id");
            String name = resultSets.getString("name");

            clientList.add(new Client(name, id));
        }
        return clientList;
    }
    private Client findClientById(float id) throws SQLException {
        Client client = null;
        String query = "SELECT * FROM client WHERE ID = ?";
        PreparedStatement preparedStatement = qweryService.getPreparedStatement(query);
        preparedStatement.setInt(1, (int)id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            String name = resultSet.getString("name");
            client = new Client(name, (int)id);
        }

        if (client == null) {
            throw new IllegalArgumentException("Client with ID " + id + " not found.");
        }
        return client;
    }

    private long getLastClientID () throws SQLException {
        Long maxID = 0L;
        String query = "SELECT id FROM client WHERE id = (SELECT MAX(id) FROM client)";
        ResultSet rs = qweryService.executeQuery(query);
        while (rs.next()) {
             Long Id = rs.getLong("id");
             maxID = Id;
        }
        return maxID;
    }


    private void nameCheck(String name) throws IllegalAccessException {
        if (name.length() < 2 || name.length() >1000) {
            throw new IllegalAccessException("Client name length must be from 2 to 1000");
        }
    }


}

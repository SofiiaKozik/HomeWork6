package org.example;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, SQLException {
        ClientService clientService = new ClientService();
        System.out.println(clientService.listAll());

    }
}
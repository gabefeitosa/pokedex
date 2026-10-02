package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {
    private static Connection connection;

    public static Connection getConnection(){
        try {

        } catch (SQLException e){
            System.out.println("Erro de SQL: " + e.getMessage());
        } catch (ClassNotFoundException e){
            System.out.println("Classe não encontrada\nErro: ");
        }
    }
}

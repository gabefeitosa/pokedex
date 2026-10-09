package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static Connection connection;

    public static Connection getConnection(){
        try {
            if(connection != null && !connection.isClosed())
                return connection;
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
            final String USER = "******";
            final String PASS = "******";
            connection = DriverManager.getConnection(url, USER, PASS);
        } catch (SQLException e){
            System.out.println("Erro de SQL: " + e.getMessage());
        } catch (ClassNotFoundException e){
            System.out.println("Classe não encontrada\nErro: ");
        }
        return connection;
    }

    public static void closeConnection(){
        try {
            if (!connection.isClosed())
                connection.close();
        } catch (SQLException e) {
            System.out.println("Erro de SQL\n" + e.getMessage());
        }

    }
}

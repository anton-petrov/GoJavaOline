package edu.petrov.gojavaonline.module999;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class DB {

    public static void Run() throws Exception {

        Properties properties = new Properties();

        try (final FileInputStream propertyFile = new FileInputStream("res/testdb.properties")) {
            properties.load(propertyFile);
        } catch (Exception e) {
            System.out.println("File not found!");
        }

        // -------------------------------------------------------------------------------------------------------------

        String connectionString = properties.getProperty("jdbc.url");
        String userName = properties.getProperty("jdbc.username");
        String userPassword = properties.getProperty("jdbc.password");
        String dbName = properties.getProperty("jdbc.dbname");

        Class.forName(properties.getProperty("jdbc.driver"));

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(connectionString, userName, userPassword);
            System.out.println("Connected successfully!");

            printGirlsWithToys(connection);
            updateToyName(connection);
            printToys(connection);

        } catch (SQLException e) {
            System.out.println(e.getSQLState());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    private static void updateToyName(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            connection.setAutoCommit(false);

            final String updateQuery = "UPDATE toy SET name = ? WHERE name = ?";
            preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, "train");
            preparedStatement.setString(2, "parovozik");
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    System.out.println(ex.getSQLState());
                }
            }
            throw new SQLException(e);

        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.setAutoCommit(true);
            }
        }
    }

    private static void printToys(Connection connection) throws SQLException {
        final String selectQuery = "SELECT * FROM toy ORDER BY id";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(selectQuery);
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.format("%d\t%s\n", resultSet.getInt(1), resultSet.getString(2));
            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    private static void printGirlsWithToys(Connection connection) {
        final String SELECT_QUERY = "SELECT g.name, t.name FROM girl AS g LEFT OUTER JOIN toy AS t ON g.toy_id = t.id";
        try (final Statement statement = connection.createStatement();
             final ResultSet resultSet = statement.executeQuery(SELECT_QUERY)) {

            while (resultSet.next()) {
                System.out.format("user %s has a toy %s\n", resultSet.getString(1), resultSet.getString(2));
            }

        } catch (SQLException e) {
        }
    }
}

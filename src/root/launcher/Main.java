package root.launcher;


import root.connection_pool.ConnectionPool;
import root.connection_pool.exception.ConnectionPoolException;

import java.sql.*;
import java.util.Map;
import java.util.Set;

import static root.launcher.ResourceManager.QUERIES;

public class Main {

    private static final String KEY_FROM_BUNDLE = "Key : ";
    private static final String QUERY = "Query: ";
    private static final String RESULT = "Result: ";
    private static final String DELIMITER = "_________________________________";

    public static void main(String[] args) {

        Connection connection = null;
        PreparedStatement statement = null;
        Map<String, PreparedStatement> statementsMap = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            Set<String> set3 = QUERIES.keySet();
            for (String key : set3) {
                if (key.contains(".byId.")) {
                    int testedUserId = 1;

                    String statementString = QUERIES.getString(key);
                    ConsoleHelper.writeMessage(QUERY);
                    ConsoleHelper.writeMessage(statementString);
                    statement = connection.prepareStatement(statementString);
                    statement.setInt(1, testedUserId);
                    resultSet = statement.executeQuery();
                    ConsoleHelper.writeMessage(RESULT);
                    ConsoleHelper.writeMessage(resultSet);
                    ConsoleHelper.writeMessage(DELIMITER);
                }
            }
        } catch (ConnectionPoolException e) {
            ConsoleHelper.writeMessage("Unexpected ConnectionPoolException : " + e);
        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Unexpected SQLException : " + e);

        } finally {
            closeResultSet(resultSet);
            closeStatesmentQuetly(statement);
            closeStatesmentsQuetly(statementsMap);
            closeConnectionQuetly(connection);
        }
    }

    private static void closeConnectionQuetly(Connection connection){
        if (connection != null){
            try{
                connection.close();;
            } catch (SQLException e) {
                ConsoleHelper.writeMessage("Connection close error " + e);
            }
        }
    }

    private static void closeStatesmentsQuetly(Map<String, PreparedStatement> statementMap){
        if (statementMap != null){
            for (PreparedStatement statement : statementMap.values()){
                closeStatesmentQuetly(statement);
            }
        }
    }

    private static void closeStatesmentQuetly(Statement statement){
        if (statement != null){
            try{
                statement.close();;
            } catch (SQLException e) {
                ConsoleHelper.writeMessage("Statement close error " + e);
            }
        }
    }

    private static void closeResultSet(ResultSet resultSet)  {
        if (resultSet != null){
            try{
                resultSet.close();;
            } catch (SQLException e) {
                ConsoleHelper.writeMessage("ResultSet close error " + e);
            }
        }
    }
}




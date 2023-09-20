package com.wipro.raemisclient.dao;

import com.wipro.raemisclient.model.Throughput;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class SubscriberFlowStatsDAO implements DAOInterface<Throughput> {

    private static final String DELETE_SUBSCRIBER_THROUGHPUT_QUERY = "DELETE FROM subscriber_5g_throughput";
    private static final String INSERT_SUBSCRIBER_THROUGHPUT_QUERY = "INSERT INTO subscriber_5g_throughput VALUES";
    private static final String VIEW_SUBSCRIBER_THROUGHPUT_QUERY = "SELECT * FROM subscriber_5g_throughput";
    private static Connection connection = null;

    public SubscriberFlowStatsDAO() {
        connection = DAOConnection.create_connection();
    }

    public void showAllRecords() throws SQLException {
        ResultSet resultSet;
        try (Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery(VIEW_SUBSCRIBER_THROUGHPUT_QUERY);
            while (resultSet.next()) {
                //System.out.println(resultSet.getString(2) + " " + resultSet.getString(3));
            }
        } catch (SQLException e) {
            connection.close();
            System.out.println("Connection Closed while fetching subscriber flow records");
        }
    }

    public void insertRecords(List<Throughput> listOfData) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            for (Throughput data : listOfData) {
                String queryParam = "('" + data.getNmsId() + "', '" + data.getParentId() + "', '" + data.getDatetime() + "'," +
                        " '" + data.getUplink() + "', '" + data.getDownlink() + "')";
                int res = statement.executeUpdate(INSERT_SUBSCRIBER_THROUGHPUT_QUERY + queryParam);
                if (res != 0) {
                    //System.out.println("subscriber id ----:" + data.getNmsId() + " successfully polled.!");
                }
            }
        } catch (SQLException e) {
            connection.close();
            e.printStackTrace();
        }

    }

    public void deleteRecords() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(DELETE_SUBSCRIBER_THROUGHPUT_QUERY);
        } catch (SQLException e) {
            connection.close();
            System.out.println("Connection Closed while deleting subscriber flow records");
        }
    }

    public void pollRecords(List<Throughput> listOfData) throws SQLException, InterruptedException {
        insertRecords(listOfData);
        System.out.println("Subscriber Throughput records are polling..");
    }
}

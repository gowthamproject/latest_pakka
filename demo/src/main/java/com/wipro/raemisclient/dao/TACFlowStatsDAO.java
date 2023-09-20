package com.wipro.raemisclient.dao;

import com.wipro.raemisclient.model.Throughput;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class TACFlowStatsDAO implements DAOInterface<Throughput> {

    private static final String DELETE_TAC_THROUGHPUT_QUERY = "DELETE FROM tac_5g_throughput";
    private static final String INSERT_TAC_THROUGHPUT_QUERY = "INSERT INTO tac_5g_throughput VALUES";
    private static final String VIEW_TAC_THROUGHPUT_QUERY = "SELECT * FROM tac_5g_throughput";
    private static Connection connection = null;

    public TACFlowStatsDAO() {
        connection = DAOConnection.create_connection();
    }

    public void showAllRecords() throws SQLException {
        ResultSet resultSet;
        try (Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery(VIEW_TAC_THROUGHPUT_QUERY);
            while (resultSet.next()) {
                // System.out.println(resultSet.getString(2) + " " + resultSet.getString(3));
            }
        } catch (SQLException e) {
            connection.close();
            System.out.println("Connection Closed while fetching tac flow records");
        }
    }

    public void insertRecords(List<Throughput> listOfData) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            for (Throughput data : listOfData) {
                String queryParam = "('" + data.getNmsId() + "', '" + data.getParentId() + "', '" + data.getDatetime() + "'," +
                        " '" + data.getUplink() + "', '" + data.getDownlink() + "')";
                int res = statement.executeUpdate(INSERT_TAC_THROUGHPUT_QUERY + queryParam);
                if (res != 0) {
                    //System.out.println("tac id ----:" + data.getNmsId() + " successfully polled.!");
                }
            }
        } catch (SQLException e) {
            connection.close();
            System.out.println("Connection Closed while inserting tac flow records");
        }
    }

    public void deleteRecords() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(DELETE_TAC_THROUGHPUT_QUERY);
        } catch (SQLException e) {
            connection.close();
            System.out.println("Connection Closed while deleting tac flow records");
        }
    }

    public void pollRecords(List<Throughput> listOfData) throws SQLException, InterruptedException {
        insertRecords(listOfData);
    }
}

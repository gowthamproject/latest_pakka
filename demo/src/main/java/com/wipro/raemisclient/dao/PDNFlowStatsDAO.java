package com.wipro.raemisclient.dao;

import com.wipro.raemisclient.model.Throughput;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class PDNFlowStatsDAO implements DAOInterface<Throughput> {

    private static final String DELETE_PDN_THROUGHPUT_QUERY = "DELETE FROM pdn_5g_throughput";
    private static final String INSERT_PDN_THROUGHPUT_QUERY = "INSERT INTO pdn_5g_throughput VALUES";
    private static final String VIEW_PDN_THROUGHPUT_QUERY = "SELECT * FROM pdn_5g_throughput";
    private static Connection connection = null;

    public PDNFlowStatsDAO() {
        connection = DAOConnection.create_connection();
    }

    public void showAllRecords() throws SQLException {
        ResultSet resultSet;
        try (Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery(VIEW_PDN_THROUGHPUT_QUERY);
            while (resultSet.next()) {
                //System.out.println(resultSet.getString(2) + " " + resultSet.getString(3));
            }
        } catch (SQLException e) {
            connection.close();
            System.out.println("Connection Closed while fetching pdn flow records");
        }
    }

    public void insertRecords(List<Throughput> listOfData) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            for (Throughput data : listOfData) {
                String queryParam = "('" + data.getNmsId() + "', '" + data.getParentId() + "', '" + data.getDatetime() + "'," +
                        " '" + data.getUplink() + "', '" + data.getDownlink() + "')";
                int res = statement.executeUpdate(INSERT_PDN_THROUGHPUT_QUERY + queryParam);
                if (res != 0) {
                    //System.out.println("pdn id ----:" + data.getNmsId() + " successfully polled.!");
                }
            }
        } catch (SQLException e) {
            connection.close();
            System.out.println("Connection Closed while inserting pdn flow records");
        }
    }

    public void deleteRecords() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(DELETE_PDN_THROUGHPUT_QUERY);
        } catch (SQLException e) {
            connection.close();
            System.out.println("Connection Closed while deleting pdn flow records");
        }
    }

    public void pollRecords(List<Throughput> listOfData) throws SQLException, InterruptedException {
        insertRecords(listOfData);
    }
}

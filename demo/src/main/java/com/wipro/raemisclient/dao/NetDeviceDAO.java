package com.wipro.raemisclient.dao;

import com.wipro.raemisclient.common.Core5GDetails;
import com.wipro.raemisclient.model.NetDevice;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class NetDeviceDAO implements DAOInterface<NetDevice> {

    private static final String DELETE_ALL_NETDEVICE_QUERY = "DELETE FROM netdevice";
    private static final String INSERT_NETDEVICE_QUERY = "INSERT INTO netdevice VALUES";
    private static final String VIEW_NETDEVICE_QUERY = "SELECT * FROM netdevice";
    private static Connection connection = null;

    public NetDeviceDAO() {
        connection = DAOConnection.create_connection();
    }

    public void showAllRecords() throws SQLException {
        ResultSet resultSet;
        try (Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery(VIEW_NETDEVICE_QUERY);
            while (resultSet.next()) {
                // System.out.println(resultSet.getString(2) + " " + resultSet.getString(3));
            }
        } catch (SQLException e) {
            connection.close();
            System.out.println("Connection Closed while fetching netdevice records");
        }
    }

    public void insertRecords(List<NetDevice> listOfData) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            for (NetDevice data : listOfData) {
                String queryParam = "(" + data.getId() + ", '" + data.getMac() +
                        "', '" + data.getDevice() + "', '" + data.getParent_device() + "'," +
                        " " + data.getVlan_id() + ", '" + data.getIp() + "', '" + data.getNetmask() + "'," +
                        " '" + data.getIpv6() + "', " + data.getNat_enabled() + "," +
                        " '" + data.getOwner() + "', " + data.getDevice_type() + ", '" + Core5GDetails._5G_CORE_ID + "')";

                int res = statement.executeUpdate(INSERT_NETDEVICE_QUERY + queryParam);
                if (res != 0) {
                    //System.out.println("netdevice ----:" + data.getId() + " successfully polled.!");
                }
            }
        } catch (SQLException e) {
            connection.close();
            System.out.println("Connection Closed while inserting gnb records");
        }
    }

    public void deleteRecords() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(DELETE_ALL_NETDEVICE_QUERY);
        } catch (SQLException e) {
            connection.close();
            System.out.println("Connection Closed while deleting gnb records");
        }
    }

    public void pollRecords(List<NetDevice> listOfData) throws SQLException, InterruptedException {
        insertRecords(listOfData);
    }
}

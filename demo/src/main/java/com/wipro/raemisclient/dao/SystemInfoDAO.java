package com.wipro.raemisclient.dao;

import com.wipro.raemisclient.common.Constants;
import com.wipro.raemisclient.common.Core5GDetails;
import com.wipro.raemisclient.model.NetDevice;
import com.wipro.raemisclient.model.SystemInfo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class SystemInfoDAO implements DAOInterface<SystemInfo> {

    private static final String INSERT_SYSTEMINFO_QUERY = "INSERT INTO systeminfo VALUES";
    private static final String VIEW_SYSTEMINFO_QUERY = "SELECT * FROM systeminfo";
    private static final String SELECT_NETDEVICE_BY_CORE_ID = "SELECT * FROM netdevice where core_id=1045173342497102197";
    private static Connection connection = null;
    public SystemInfoDAO() {
        connection = DAOConnection.create_connection();
    }

    @Override
    public void showAllRecords() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(VIEW_SYSTEMINFO_QUERY);
            while (resultSet.next()) {
                // System.out.println(resultSet.getString(2) + " " + resultSet.getString(3));
            }
        } catch (SQLException e) {
            connection.close();
            System.out.println("Connection Closed while fetching systeminfo records");
        }
    }

    public NetDevice getNetDeviceRecordByCoreId() throws SQLException {
        ResultSet resultSet;
        NetDevice netDevice = null;
        try (Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery(SELECT_NETDEVICE_BY_CORE_ID);
            while (resultSet.next()) {
                String mac = resultSet.getString(2);
                String ip = resultSet.getString(6);
                if (mac != null && !mac.isEmpty() && ip != null && !ip.isEmpty()) {
                    netDevice = new NetDevice();
                    netDevice.setMac(resultSet.getString(2));
                    netDevice.setDevice(resultSet.getString(3));
                    netDevice.setIp(resultSet.getString(6));
                    netDevice.setNetmask(resultSet.getString(7));
                }
            }
        } catch (SQLException e) {
            connection.close();
            System.out.println("Connection Closed while fetching netdevice records");
        }
        return netDevice;
    }

    @Override
    public void insertRecords(List<SystemInfo> listOfData) throws SQLException {
        int res;
        try (Statement statement = connection.createStatement()) {
            for (SystemInfo data : listOfData) {
                setSystemType(data);
                String queryParam = "('" + data.getNodename() + "', '" + data.getInstance() + "', '" + data.getType() +
                        "', '" + data.getPolling_date_time() +
                        "', '" + data.getMemory_usage_in_percent() +
                        "', '" + data.getCpu_utilization_in_percent() + "', '" + data.getDisk_usage_in_percent() + "'," +
                        " '" + data.getAvailable_memory_in_gb() + "','" + data.getTotal_memory_in_gb() + "','" + data.getAvailable_diskspace_in_gb() + "','" + data.getTotal_diskspace_in_gb() + "','" + Core5GDetails._5G_CORE_ID + "')";

                res = statement.executeUpdate(INSERT_SYSTEMINFO_QUERY + queryParam);
                if (res != 0) {
                    //System.out.println("systeminfo id ----:" + data.getNodename() + " successfully polled.!");
                }
            }
        } catch (SQLException e) {
            connection.close();
            System.out.println("Connection Closed while inserting systeminfo records");
        }
    }

    private void setSystemType(SystemInfo data) throws SQLException {
        NetDevice netDevice = getNetDeviceRecordByCoreId();
        String netDeviceIp = netDevice.getIp();
        String systemIp = data.getInstance().split(":")[0];
        if (netDeviceIp.equals(systemIp))
            data.setType(Constants._5G_CORE);
        else
            data.setType(Constants.GNB);
    }

    @Override
    public void deleteRecords() throws SQLException {

    }

    @Override
    public void pollRecords(List<SystemInfo> listOfData) throws SQLException, InterruptedException {
        insertRecords(listOfData);
        System.out.println("SystemInfo records are polling..");
    }
}

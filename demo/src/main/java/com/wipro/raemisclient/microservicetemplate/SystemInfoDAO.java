package com.wipro.raemisclient.microservicetemplate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import com.wipro.raemisclient.common.Constants;
import com.wipro.raemisclient.common.Core5GDetails;
import com.wipro.raemisclient.model.response.NetDeviceResponse;
import com.wipro.raemisclient.model.response.SystemInfoResponse;

public class SystemInfoDAO implements DAOInterface<SystemInfoResponse> {

	private static final String INSERT_SYSTEMINFO_QUERY = "INSERT INTO systeminfo VALUES";
	private static final String SELECT_NETDEVICE_BY_CORE_ID = "SELECT * FROM netdevice where core_id=1045173342497102197";
	private static Connection connection = null;

	public SystemInfoDAO() {
		connection = DAOConnection.create_connection();
	}

	private NetDeviceResponse getNetDeviceRecordByCoreId() throws SQLException {
		ResultSet resultSet;
		NetDeviceResponse netDevice = null;
		try (Statement statement = connection.createStatement()) {
			resultSet = statement.executeQuery(SELECT_NETDEVICE_BY_CORE_ID);
			while (resultSet.next()) {
				String mac = resultSet.getString(2);
				String ip = resultSet.getString(6);
				if (mac != null && !mac.isEmpty() && ip != null && !ip.isEmpty()) {
					netDevice = new NetDeviceResponse();
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
	public void insertRecords(List<SystemInfoResponse> listOfData) throws SQLException {
		for (SystemInfoResponse data : listOfData) {
			insertRecord(data);
		}
	}

	@Override
	public void insertRecord(SystemInfoResponse data) throws SQLException {
		try (Statement statement = connection.createStatement()) {
			setSystemType(data);
			String queryParam = "('" + data.getNodename() + "', '" + data.getInstance() + "', '" + data.getType()
					+ "', '" + data.getPolling_date_time() + "', '" + data.getMemory_usage_in_percent() + "', '"
					+ data.getCpu_utilization_in_percent() + "', '" + data.getDisk_usage_in_percent() + "'," + " '"
					+ data.getAvailable_memory_in_gb() + "','" + data.getTotal_memory_in_gb() + "','"
					+ data.getAvailable_diskspace_in_gb() + "','" + data.getTotal_diskspace_in_gb() + "','"
					+ Core5GDetails._5G_CORE_ID + "')";

			int res = statement.executeUpdate(INSERT_SYSTEMINFO_QUERY + queryParam);
			if (res != 0) {
				// System.out.println("systeminfo id ----:" + data.getNodename() + "
				// successfully polled.!");
			}
		} catch (SQLException e) {
			connection.close();
			System.out.println("Connection Closed while inserting systeminfo records");
		}
	}

	private void setSystemType(SystemInfoResponse data) throws SQLException {
		NetDeviceResponse netDevice = getNetDeviceRecordByCoreId();
		String netDeviceIp = netDevice.getIp();
		String systemIp = data.getInstance().split(":")[0];
		if (netDeviceIp.equals(systemIp))
			data.setType(Constants._5G_CORE);
		else
			data.setType(Constants.GNB);
	}

	@Override
	public void updateOrInsertRecords(List<SystemInfoResponse> listOfData) throws SQLException {
		insertRecords(listOfData);
	}

	@Override
	public void pollRecords(List<SystemInfoResponse> listOfData) throws SQLException, InterruptedException {
		updateOrInsertRecords(listOfData);
		System.out.println("SystemInfo records are polling..");
	}

	@Override
	public void updateRecord(SystemInfoResponse data) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<SystemInfoResponse> getRecordByParam(Map<String, Object> paramMap) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRecords(List<String> params) throws SQLException {
		// TODO Auto-generated method stub

	}
}

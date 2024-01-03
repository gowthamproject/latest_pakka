package com.wipro.raemisclient.microservicetemplate;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import com.wipro.raemisclient.common.Core5GDetails;
import com.wipro.raemisclient.model.response.NetDeviceResponse;

public class NetDeviceDAO implements DAOInterface<NetDeviceResponse> {

	private static final String INSERT_NETDEVICE_QUERY = "INSERT INTO netdevice VALUES";
	private static Connection connection = null;

	public NetDeviceDAO() {
		connection = DAOConnection.create_connection();
	}

	@Override
	public void insertRecords(List<NetDeviceResponse> listOfData) throws SQLException {
		for (NetDeviceResponse data : listOfData) {
			insertRecord(data);
		}
	}

	@Override
	public void insertRecord(NetDeviceResponse data) throws SQLException {
		try (Statement statement = connection.createStatement()) {

			String queryParam = "(" + data.getId() + ", '" + data.getMac() + "', '" + data.getDevice() + "', '"
					+ data.getParent_device() + "'," + " " + data.getVlan_id() + ", '" + data.getIp() + "', '"
					+ data.getNetmask() + "'," + " '" + data.getIpv6() + "', " + data.getNat_enabled() + "," + " '"
					+ data.getOwner() + "', " + data.getDevice_type() + ", '" + Core5GDetails._5G_CORE_ID + "')";

			int res = statement.executeUpdate(INSERT_NETDEVICE_QUERY + queryParam);
			if (res != 0) {
				// System.out.println("netdevice ----:" + data.getId() + " successfully
				// polled.!");
			}

		} catch (SQLException e) {
			connection.close();
			System.out.println("Connection Closed while inserting gnb records");
		}
	}

	@Override
	public void updateRecord(NetDeviceResponse data) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<NetDeviceResponse> getRecordByParam(Map<String, Object> paramMap) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRecords(List<String> params) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateOrInsertRecords(List<NetDeviceResponse> listOfData) throws SQLException {
		insertRecords(listOfData);
	}

	@Override
	public void pollRecords(List<NetDeviceResponse> listOfData) throws SQLException, InterruptedException {
		updateOrInsertRecords(listOfData);
	}
}

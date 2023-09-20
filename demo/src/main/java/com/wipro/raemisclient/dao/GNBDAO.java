package com.wipro.raemisclient.dao;

import com.wipro.raemisclient.common.Core5GDetails;
import com.wipro.raemisclient.model.GNB;
import com.wipro.raemisclient.utils.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GNBDAO implements DAOInterface<GNB> {
	private static final String INSERT_GNB_QUERY = "INSERT INTO gnb VALUES";
	private static final String VIEW_GNB_QUERY = "SELECT * FROM gnb";
	private static final String UPDATE_GNB_QUERY = "UPDATE gnb SET plmn_id=?, gnb_id=?, tac=?, status=? where name=?";
	private static final String DELETE_GNB_QUERY = "DELETE from gnb where core_id = '" + Core5GDetails._5G_CORE_ID
			+ "' and name=";

	private static Connection connection = null;

	public GNBDAO() {
		connection = DAOConnection.create_connection();
	}

	@Override
	public List<GNB> getRecordByParam(Map<String, Object> paramMap) throws SQLException {
		List<GNB> gnbs = new ArrayList<GNB>();
		try (Statement statement = connection.createStatement()) {
			String param = Util.parseAndGetSqlParam(paramMap);
			ResultSet resultSet = statement.executeQuery(VIEW_GNB_QUERY + param);

			while (resultSet.next()) {
				GNB gnb = new GNB();
				gnb.setName(resultSet.getString(1));
				gnb.setGnb_id(resultSet.getInt(3));
				gnb.setOper_state(resultSet.getString(6));
				gnbs.add(gnb);
			}
		} catch (SQLException e) {
			connection.close();
			System.out.println("Connection Closed while fetching gnb records");
		}
		return gnbs;
	}

	@Override
	public void insertRecords(List<GNB> listOfData) throws SQLException {
		for (GNB data : listOfData) {
			insertRecord(data);
		}
	}

	@Override
	public void insertRecord(GNB data) throws SQLException {
		try (Statement statement = connection.createStatement()) {
			String status = data.getOper_state().equals("1") ? "Disconnected" : "Connected";
			String queryParam = "('" + data.getName() + "', '" + data.getPlmn_id() + "', " + data.getGnb_id() + ", "
					+ data.getTac() + "," + " '" + data.getSctp_address() + "', '" + status + "','"
					+ Core5GDetails._5G_CORE_ID + "')";

			int res = statement.executeUpdate(INSERT_GNB_QUERY + queryParam);
			if (res != 0) {
				// System.out.println("gnb id ----:" + data.getGnb_id() + " successfully
				// polled.!");
			}
		} catch (SQLException e) {
			connection.close();
			System.out.println("Connection Closed while inserting gnb records");
		}

	}

	@Override
	public void updateRecord(GNB data) throws SQLException {
		{
			try {
				String status = data.getOper_state().equals("1") ? "Disconnected" : "Connected";
				PreparedStatement preparedStmt = connection.prepareStatement(UPDATE_GNB_QUERY);
				preparedStmt.setString(1, data.getPlmn_id());
				preparedStmt.setInt(2, data.getGnb_id());
				preparedStmt.setInt(3, data.getTac());
				preparedStmt.setString(4, status);
				preparedStmt.setString(5, data.getName());
				int res = preparedStmt.executeUpdate();
				if (res != 0) {
					// System.out.println("gnb ----:" + data.getName() + " successfully updated.!");
				}
			} catch (SQLException e) {
				connection.close();
				System.out.println("Connection Closed while updating gnb records");
			}
		}
	}

	@Override
	public void deleteRecords(List<String> params) throws SQLException {
		try (Statement statement = connection.createStatement()) {
			for (String param : params) {
				statement.executeUpdate(DELETE_GNB_QUERY + "'" + param + "'");
			}
		} catch (SQLException e) {
			connection.close();
		}
	}

	@Override
	public void updateOrInsertRecords(List<GNB> listOfData) throws SQLException {
		if (listOfData == null || listOfData.isEmpty())
			return;

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("core_id", Core5GDetails._5G_CORE_ID);
		List<GNB> existingGNBs = getRecordByParam(paramMap);

		if (existingGNBs.isEmpty()) {
			insertRecords(listOfData);
		} else {

			List<String> existingGNBNames = existingGNBs.stream().map(gnb -> gnb.getName())
					.collect(Collectors.toList());
			List<String> currentGNBNames = listOfData.stream().map(gnb -> gnb.getName()).collect(Collectors.toList());

			List<String> deleteGNBs = existingGNBNames.stream().filter(i -> !currentGNBNames.contains(i))
					.collect(Collectors.toList());
			deleteRecords(deleteGNBs);
			List<GNB> insertGNBs = listOfData.stream().filter(i -> !existingGNBNames.contains(i.getName()))
					.collect(Collectors.toList());
			insertRecords(insertGNBs);

			for (GNB curr_gnb : listOfData) {
				for (GNB ext_gnb : existingGNBs) {
					if (curr_gnb.getName().equals(ext_gnb.getName())) {
						String status = curr_gnb.getOper_state().equals("1") ? "Disconnected" : "Connected";
						if (!status.equals(ext_gnb.getOper_state()))
							updateRecord(curr_gnb);
					}
				}
			}
		}
	}

	@Override
	public void pollRecords(List<GNB> listOfData) throws SQLException, InterruptedException {
		updateOrInsertRecords(listOfData);
		System.out.println("gNB records are polling..");
	}

	public static void main(String[] args) throws SQLException, InterruptedException {
		new GNBDAO().pollRecords(null);
	}
}

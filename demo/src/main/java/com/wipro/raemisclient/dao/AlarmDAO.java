package com.wipro.raemisclient.dao;

import com.wipro.raemisclient.common.Constants;
import com.wipro.raemisclient.common.Core5GDetails;
import com.wipro.raemisclient.model.Alarm;
import com.wipro.raemisclient.utils.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;	

public class AlarmDAO implements DAOInterface<Alarm> {

	private static final String INSERT_ALARMS_QUERY = "INSERT INTO alarmdetails VALUES";
	private static final String GET_ALARM_QUERY = "SELECT * FROM alarmdetails";
	private static final String UPDATE_ALARM_QUERY = " UPDATE alarmdetails SET alarm_status=? where id=? and node_id=?";
	private static final String UPDATE_CLOSED_ALARM_QUERY = " UPDATE alarmdetails SET alarm_status=?, start_time=?, severity=?, objclass=?, objid=?, "
			+ "eventtype=?, probablecause=?, specificproblem=?, addtext=?, internalid=?, acknowledged=? where id=? and node_id=?";

	private static Connection connection = null;

	public AlarmDAO() {
		connection = DAOConnection.create_connection();
	}

	@Override
	public void insertRecords(List<Alarm> listOfData) throws SQLException {
		try {
			for (Alarm data : listOfData) {
				insertRecord(data);
			}
		} catch (SQLException e) {
			connection.close();
			System.out.println("Connection Closed while inserting alarm records");
		}
	}

	@Override
	public void insertRecord(Alarm data) throws SQLException {
		Statement statement = connection.createStatement();
		String queryParam = "(" + data.getId() + ", '" + data.getNode_type() + "', '" + data.getNode_name() + "', '"
				+ data.getStart_time() + "', '" + data.getSeverity() + "', '" + data.getObj_class() + "'," + " '"
				+ data.getObj_id() + "', '" + data.getAlarm_identifier() + "', '" + data.getEvent_type() + "'," + " '"
				+ data.getProbable_cause() + "', '" + data.getSpecific_problem() + "'," + " '" + data.getAdd_text()
				+ "'," + data.getInternal_id() + "," + data.getAcknowledge() + ", '" + data.getAlarmStatus() + "','"
				+ Core5GDetails._5G_CORE_ID + "')";
		int res = statement.executeUpdate(INSERT_ALARMS_QUERY + queryParam);
		if (res != 0) {
			// System.out.println("alarm id ----:" + data.getId() + " successfully
			// polled.!");
		}
	}

	@Override
	public void updateRecord(Alarm data) throws SQLException {
		{
			try {
				PreparedStatement preparedStmt = connection.prepareStatement(UPDATE_ALARM_QUERY);
				preparedStmt.setString(1, data.getAlarmStatus());
				preparedStmt.setInt(2, data.getId());
				preparedStmt.setString(3, Core5GDetails._5G_CORE_ID);

				int res = preparedStmt.executeUpdate();
				if (res != 0) {
					// System.out.println("Alarm ----:" + data.getId() + " successfully updated.!");
				}
			} catch (SQLException e) {
				connection.close();
				System.out.println("Connection Closed while updating alarm records");
			}
		}
	}
	
	public void updateClosedRecord(Alarm data) throws SQLException {
		{
			final String UPDATE_CLOSED_ALARM_QUERYY = " UPDATE alarmdetails SET alarm_status=?, start_time=?, severity=?, objclass=?, objid=?, "
					+ "eventtype=?, probablecause=?, specificproblem=?, addtext=?, internalid=?, acknowledged=? where id=? and node_id=?";

			try {
				PreparedStatement preparedStmt = connection.prepareStatement(UPDATE_CLOSED_ALARM_QUERY);
				preparedStmt.setString(1, data.getAlarmStatus());
				preparedStmt.setString(2, data.getStart_time());
				preparedStmt.setString(3, data.getSeverity());
				preparedStmt.setString(4, data.getObj_class());
				preparedStmt.setString(5, data.getObj_id());
				preparedStmt.setString(6, data.getEvent_type());
				preparedStmt.setString(7, data.getProbable_cause());
				preparedStmt.setString(8, data.getSpecific_problem());
				preparedStmt.setString(9, data.getAdd_text());
				preparedStmt.setInt(10, data.getInternal_id());
				preparedStmt.setInt(11, data.getAcknowledge());
				preparedStmt.setInt(12, data.getId());
				preparedStmt.setString(13, Core5GDetails._5G_CORE_ID);

				int res = preparedStmt.executeUpdate();
				if (res != 0) {
					// System.out.println("Alarm ----:" + data.getId() + " successfully updated.!");
				}
			} catch (SQLException e) {
				connection.close();
				System.out.println("Connection Closed while updating alarm records");
			}
		}
	}

	@Override
	public List<Alarm> getRecordByParam(Map<String, Object> paramMap) throws SQLException {

		List<Alarm> alarmList = new ArrayList<>();
		try (Statement statement = connection.createStatement()) {
			String param = Util.parseAndGetSqlParam(paramMap);
			ResultSet resultSet = statement.executeQuery(GET_ALARM_QUERY + param);
			Alarm alarm = null;
			while (resultSet.next()) {
				alarm = new Alarm();
				alarm.setId(resultSet.getInt(1));
				alarm.setAlarmStatus(resultSet.getString(15));
				alarmList.add(alarm);
			}
		} catch (SQLException e) {
			connection.close();
		}
		return alarmList;
	}

	@Override
	public void deleteRecords(List<String> params) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateOrInsertRecords(List<Alarm> listOfData) throws SQLException {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("node_id", Core5GDetails._5G_CORE_ID);
		List<Alarm> existingAlarmList = getRecordByParam(paramMap);
		if (existingAlarmList.isEmpty()) {
			insertRecords(listOfData);
		} else {
			for (Alarm pollingAlarm : listOfData) {
				List<Alarm> matchedAlarm = existingAlarmList.stream().filter(al -> al.getId() == pollingAlarm.getId())
						.collect(Collectors.toList());
				if (matchedAlarm == null || matchedAlarm.isEmpty()) {
					insertRecord(pollingAlarm);
				}
			}
			for (Alarm alarm : existingAlarmList) {
				long count = listOfData.stream().filter(al -> al.getId() == alarm.getId()).count();
				List<Alarm> updateClosedAlarm = listOfData.stream().filter(al -> al.getId() == alarm.getId()).collect(Collectors.toList());

				if (updateClosedAlarm == null || updateClosedAlarm.isEmpty()) {
					if (alarm.getAlarmStatus().equals(Constants.ALARM_STATUS[0]))
						continue;
					Alarm alarm1 = new Alarm();
					alarm1.setId(alarm.getId());
					alarm1.setAlarmStatus(Constants.ALARM_STATUS[0]);
					updateRecord(alarm1);
				} else {
					updateClosedAlarm.get(0).setAlarmStatus(Constants.ALARM_STATUS[1]);
					updateRecord(updateClosedAlarm.get(0));
				}
			}
		}
	}

	@Override
	public void pollRecords(List<Alarm> listOfData) throws SQLException, InterruptedException {
		updateOrInsertRecords(listOfData);
		System.out.println("Alarm records are polling..");
	}
}
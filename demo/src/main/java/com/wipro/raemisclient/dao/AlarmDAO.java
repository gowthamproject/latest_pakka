package com.wipro.raemisclient.dao;

import com.wipro.raemisclient.common.Constants;
import com.wipro.raemisclient.common.Core5GDetails;
import com.wipro.raemisclient.model.Alarm;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AlarmDAO implements DAOInterface<Alarm> {

	private static final String DELETE_ALL_ALARMS_QUERY = "DELETE FROM alarmdetails";
	private static final String INSERT_ALARMS_QUERY = "INSERT INTO alarmdetails VALUES";
	private static final String GET_ALARM_BY_COREID_QUERY = "SELECT * FROM alarmdetails where node_id=1045173342497102197";
	private static final String UPDATE_ALARM_QUERY = " UPDATE alarmdetails SET alarm_status=? where id=? and node_id=?";
	private static Connection connection = null;

	public AlarmDAO() {
		connection = DAOConnection.create_connection();
	}

	public void showAllRecords() throws SQLException {
		ResultSet resultSet;
		try (Statement statement = connection.createStatement()) {
			resultSet = statement.executeQuery(GET_ALARM_BY_COREID_QUERY);
			while (resultSet.next()) {
				// System.out.println(resultSet.getString(2) + " " + resultSet.getString(3));
			}
		} catch (SQLException e) {
			connection.close();
			System.out.println("Connection Closed while fetching alarm records");
		}
	}

	public List<Alarm> getAlarmRecordsByNodeID() throws SQLException {
		List<Alarm> alarmList = new ArrayList<>();
		try (Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery(GET_ALARM_BY_COREID_QUERY);
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

	public void deleteRecords() throws SQLException {
		try (Statement statement = connection.createStatement()) {
			statement.executeUpdate(DELETE_ALL_ALARMS_QUERY);
		} catch (SQLException e) {
			connection.close();
			e.printStackTrace();
		}
	}

	public void pollRecords(List<Alarm> listOfData) throws SQLException, InterruptedException {
		List<Alarm> existingAlarmList = getAlarmRecordsByNodeID();
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
				if (count == 0) {
					if (alarm.getAlarmStatus().equals(Constants.ALARM_STATUS[0]))
						continue;
					Alarm alarm1 = new Alarm();
					alarm1.setId(alarm.getId());
					alarm1.setAlarmStatus(Constants.ALARM_STATUS[0]);
					updateRecord(alarm1);
				}
			}
		}
		System.out.println("Alarm records are polling..");
	}

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
}
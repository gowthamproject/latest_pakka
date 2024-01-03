package com.wipro.raemisclient.microservicetemplate;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import com.wipro.raemisclient.apiservice.NotifyMessage;
import com.wipro.raemisclient.model.response.ThroughputResponse;

public class SubscriberFlowStatsDAO implements DAOInterface<ThroughputResponse> {

	private static final String INSERT_SUBSCRIBER_THROUGHPUT_QUERY = "INSERT INTO subscriber_5g_throughput VALUES";
	private static Connection connection = null;

	public SubscriberFlowStatsDAO() {
		connection = DAOConnection.create_connection();
	}

	public void insertRecords(List<ThroughputResponse> listOfData) throws SQLException, IOException {
		for (ThroughputResponse data : listOfData) {
			insertRecord(data);
		}
		NotifyMessage.sendMessage_Throughput();
	}

	@Override
	public void insertRecord(ThroughputResponse data) throws SQLException {
		try (Statement statement = connection.createStatement()) {
			String queryParam = "('" + data.getNmsId() + "', '" + data.getParentId() + "', '" + data.getDatetime()
					+ "'," + " '" + data.getUplink() + "', '" + data.getDownlink() + "')";
			int res = statement.executeUpdate(INSERT_SUBSCRIBER_THROUGHPUT_QUERY + queryParam);
			if (res != 0) {
				// System.out.println("subscriber id ----:" + data.getNmsId() + " successfully
				// polled.!");
			}

		} catch (SQLException e) {
			connection.close();
			e.printStackTrace();
		}

	}

	@Override
	public void updateOrInsertRecords(List<ThroughputResponse> listOfData) throws SQLException, IOException {
		insertRecords(listOfData);
	}

	public void pollRecords(List<ThroughputResponse> listOfData) throws SQLException, InterruptedException, IOException {
		updateOrInsertRecords(listOfData);
		System.out.println("Subscriber Throughput records are polling..");
	}

	@Override
	public void updateRecord(ThroughputResponse data) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ThroughputResponse> getRecordByParam(Map<String, Object> paramMap) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRecords(List<String> params) throws SQLException {
		// TODO Auto-generated method stub

	}
}

package com.wipro.raemisclient.microservicetemplate;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import com.wipro.raemisclient.model.response.ThroughputResponse;

public class PDNFlowStatsDAO implements DAOInterface<ThroughputResponse> {

	private static final String INSERT_PDN_THROUGHPUT_QUERY = "INSERT INTO pdn_5g_throughput VALUES";
	private static Connection connection = null;

	public PDNFlowStatsDAO() {
		connection = DAOConnection.create_connection();
	}

	@Override
	public void insertRecords(List<ThroughputResponse> listOfData) throws SQLException {
		for (ThroughputResponse data : listOfData) {
			insertRecord(data);
		}
	}

	@Override
	public void insertRecord(ThroughputResponse data) throws SQLException {
		try (Statement statement = connection.createStatement()) {
			String queryParam = "('" + data.getNmsId() + "', '" + data.getParentId() + "', '" + data.getDatetime()
					+ "'," + " '" + data.getUplink() + "', '" + data.getDownlink() + "')";
			int res = statement.executeUpdate(INSERT_PDN_THROUGHPUT_QUERY + queryParam);
			if (res != 0) {
				// System.out.println("pdn id ----:" + data.getNmsId() + " successfully
				// polled.!");
			}
		} catch (SQLException e) {
			connection.close();
			System.out.println("Connection Closed while inserting pdn flow records");
		}
	}

	@Override
	public void updateOrInsertRecords(List<ThroughputResponse> listOfData) throws SQLException {
		insertRecords(listOfData);

	}

	@Override
	public void pollRecords(List<ThroughputResponse> listOfData) throws SQLException, InterruptedException {
		updateOrInsertRecords(listOfData);
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

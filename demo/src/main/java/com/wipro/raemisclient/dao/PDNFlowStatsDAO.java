package com.wipro.raemisclient.dao;

import com.wipro.raemisclient.model.Throughput;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public class PDNFlowStatsDAO implements DAOInterface<Throughput> {

	private static final String INSERT_PDN_THROUGHPUT_QUERY = "INSERT INTO pdn_5g_throughput VALUES";
	private static Connection connection = null;

	public PDNFlowStatsDAO() {
		connection = DAOConnection.create_connection();
	}

	@Override
	public void insertRecords(List<Throughput> listOfData) throws SQLException {
		for (Throughput data : listOfData) {
			insertRecord(data);
		}
	}

	@Override
	public void insertRecord(Throughput data) throws SQLException {
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
	public void updateOrInsertRecords(List<Throughput> listOfData) throws SQLException {
		insertRecords(listOfData);

	}

	@Override
	public void pollRecords(List<Throughput> listOfData) throws SQLException, InterruptedException {
		updateOrInsertRecords(listOfData);
	}

	@Override
	public void updateRecord(Throughput data) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Throughput> getRecordByParam(Map<String, Object> paramMap) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRecords(List<String> params) throws SQLException {
		// TODO Auto-generated method stub

	}
}

package com.wipro.raemisclient.dao;

import com.wipro.raemisclient.model.Throughput;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public class TACFlowStatsDAO implements DAOInterface<Throughput> {

	private static final String DELETE_TAC_THROUGHPUT_QUERY = "DELETE FROM tac_5g_throughput";
	private static final String INSERT_TAC_THROUGHPUT_QUERY = "INSERT INTO tac_5g_throughput VALUES";
	private static Connection connection = null;

	public TACFlowStatsDAO() {
		connection = DAOConnection.create_connection();
	}

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
			int res = statement.executeUpdate(INSERT_TAC_THROUGHPUT_QUERY + queryParam);
			if (res != 0) {
				// System.out.println("tac id ----:" + data.getNmsId() + " successfully
				// polled.!");
			}

		} catch (SQLException e) {
			connection.close();
			System.out.println("Connection Closed while inserting tac flow records");
		}
	}

	public void deleteRecords() throws SQLException {
		try (Statement statement = connection.createStatement()) {
			statement.executeUpdate(DELETE_TAC_THROUGHPUT_QUERY);
		} catch (SQLException e) {
			connection.close();
			System.out.println("Connection Closed while deleting tac flow records");
		}
	}

	@Override
	public void updateOrInsertRecords(List<Throughput> listOfData) throws SQLException {
		insertRecords(listOfData);

	}

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

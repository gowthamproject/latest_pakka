package com.wipro.raemisclient.dao;

import com.wipro.raemisclient.common.Core5GDetails;
import com.wipro.raemisclient.model.PDUSession;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class PDUSessionDAO implements DAOInterface<PDUSession> {

	private static final String INSERT_PDU_SESSION_QUERY = "INSERT INTO pdu_session VALUES";

	private static Connection connection = null;

	public PDUSessionDAO() {
		connection = DAOConnection.create_connection();
	}

	@Override
	public void insertRecords(List<PDUSession> listOfData) throws SQLException {
		final Date dateTime = new Timestamp(new Date().getTime());
		for (PDUSession data : listOfData) {
			data.setDateTime(dateTime.toString());
			insertRecord(data);
		}
	}

	@Override
	public void insertRecord(PDUSession data) throws SQLException {
		try (Statement statement = connection.createStatement()) {
			String queryParam = "(" + data.getId() + ", '" + data.getGnb_id() + "', " + data.getRan_ue_ngap_id() + ", "
					+ data.getSupi() + "," + " '" + data.getDateTime() + "','" + Core5GDetails._5G_CORE_ID + "')";

			int res = statement.executeUpdate(INSERT_PDU_SESSION_QUERY + queryParam);
			if (res != 0) {
				// System.out.println("pdu session id ----:" + data.getGnb_id() + " successfully
				// polled.!");
			}
		} catch (SQLException e) {
			connection.close();
			System.out.println("Connection Closed while inserting pdu session records");
		}
	}

	@Override
	public void updateOrInsertRecords(List<PDUSession> listOfData) throws SQLException {
		insertRecords(listOfData);

	}

	@Override
	public void pollRecords(List<PDUSession> listOfData) throws SQLException, InterruptedException {
		updateOrInsertRecords(listOfData);
		System.out.println("PDU session records are polling..");
	}

	@Override
	public void updateRecord(PDUSession data) throws SQLException {
		// TODO Auto-generated method stub
	}

	@Override
	public List<PDUSession> getRecordByParam(Map<String, Object> paramMap) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRecords(List<String> params) throws SQLException {
		// TODO Auto-generated method stub
	}
}

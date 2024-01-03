package com.wipro.raemisclient.microservicetemplate;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wipro.raemisclient.apiservice.NotifyMessage;
import com.wipro.raemisclient.common.Core5GDetails;
import com.wipro.raemisclient.model.response.PDUSessionResponse;

public class PDUSessionDAO implements DAOInterface<PDUSessionResponse> {

	private static final String INSERT_PDU_SESSION_QUERY = "INSERT INTO pdu_session VALUES";

	private static Connection connection = null;

	public PDUSessionDAO() {
		connection = DAOConnection.create_connection();
	}

	@Override
	public void insertRecords(List<PDUSessionResponse> listOfData) throws SQLException, IOException {
		final Date dateTime = new Timestamp(new Date().getTime());
		for (PDUSessionResponse data : listOfData) {
			data.setDateTime(dateTime.toString());
			insertRecord(data);
		}
		NotifyMessage.sendMessage_PDUSession();
	}

	@Override
	public void insertRecord(PDUSessionResponse data) throws SQLException {
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
	public void updateOrInsertRecords(List<PDUSessionResponse> listOfData) throws SQLException, IOException {
		insertRecords(listOfData);

	}

	@Override
	public void pollRecords(List<PDUSessionResponse> listOfData) throws SQLException, InterruptedException, IOException {
		updateOrInsertRecords(listOfData);
		System.out.println("PDU session records are polling..");
	}

	@Override
	public void updateRecord(PDUSessionResponse data) throws SQLException {
		// TODO Auto-generated method stub
	}

	@Override
	public List<PDUSessionResponse> getRecordByParam(Map<String, Object> paramMap) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRecords(List<String> params) throws SQLException {
		// TODO Auto-generated method stub
	}
}

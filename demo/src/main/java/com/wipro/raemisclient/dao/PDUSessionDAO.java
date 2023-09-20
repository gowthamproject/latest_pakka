package com.wipro.raemisclient.dao;

import com.wipro.raemisclient.common.Core5GDetails;
import com.wipro.raemisclient.model.PDUSession;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class PDUSessionDAO implements DAOInterface<PDUSession> {

    private static final String INSERT_PDU_SESSION_QUERY = "INSERT INTO pdu_session VALUES";
    private static final String SHOW_PDU_SESSION_QUERY = "SELECT * FROM pdu_session where node_id=1045173342497102197";

    private static Connection connection = null;

    public PDUSessionDAO() {
        connection = DAOConnection.create_connection();
    }

    @Override
    public void showAllRecords() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SHOW_PDU_SESSION_QUERY);
            while (resultSet.next()) {
                //System.out.println(resultSet.getString(2) + " " + resultSet.getString(3));
            }
        } catch (SQLException e) {
            connection.close();
            System.out.println("Connection Closed while fetching pdu session records");
        }

    }

    @Override
    public void insertRecords(List<PDUSession> listOfData) throws SQLException {
        try {
        	final Date dateTime = new Timestamp(new Date().getTime());
            for (PDUSession data : listOfData) {
            	data.setDateTime(dateTime.toString());
                insertRecord(data);
            }
        } catch (SQLException e) {
            connection.close();
            System.out.println("Connection Closed while inserting pdu session records");
        }
    }

    public void insertRecord(PDUSession data) throws SQLException {
        Statement statement = connection.createStatement();
        String queryParam = "(" + data.getId() + ", '" + data.getGnb_id() +
                "', " + data.getRan_ue_ngap_id() + ", " + data.getSupi() + "," +
                " '" + data.getDateTime() + "','" + Core5GDetails._5G_CORE_ID + "')";

        int res = statement.executeUpdate(INSERT_PDU_SESSION_QUERY + queryParam);
        if (res != 0) {
            //System.out.println("pdu session id ----:" + data.getGnb_id() + " successfully polled.!");
        }
    }

    @Override
    public void deleteRecords() throws SQLException {

    }

    @Override
    public void pollRecords(List<PDUSession> listOfData) throws SQLException, InterruptedException {
        insertRecords(listOfData);
        System.out.println("PDU session records are polling..");
    }
}

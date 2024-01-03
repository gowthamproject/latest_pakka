package com.wipro.raemisclient.microservicetemplate;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface DAOInterface<T> {


	public void insertRecords(List<T> listOfData) throws SQLException, IOException;

	public void insertRecord(T data) throws SQLException;

	public void updateRecord(T data) throws SQLException;

	public List<T> getRecordByParam(Map<String, Object> paramMap) throws SQLException;
	
	public void deleteRecords(List<String> params) throws SQLException;

	public void updateOrInsertRecords(List<T> listOfData) throws SQLException, IOException;
	
	public void pollRecords(List<T> listOfData) throws SQLException, InterruptedException, IOException;

}

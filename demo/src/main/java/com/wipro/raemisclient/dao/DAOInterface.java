package com.wipro.raemisclient.dao;

import java.sql.SQLException;
import java.util.List;

public interface DAOInterface<T> {

    public void showAllRecords() throws SQLException;

    public void insertRecords(List<T> listOfData) throws SQLException;

    public void deleteRecords() throws SQLException;

    public void pollRecords(List<T> listOfData) throws SQLException, InterruptedException;
}

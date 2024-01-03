package com.wipro.raemisclient.microservicetemplate;

import java.io.IOException;
import java.util.List;

public interface RestTemplateInterface<T> {

	public void insertGNodeBRecords(String paramName, String coreId, List<T> listOfData);

	public void deleteRecord(String paramName, List<Long> ids);

	public void updateRecords(String paramName, String core_id, T data);

	public List<T> getGNodeBRecord(String coreId);

	public void updateOrInsertRecords(List<T> listOfData) throws IOException;

}

package com.wipro.raemisclient.microservicetemplate;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.wipro.raemisclient.apiservice.NotifyMessage;
import com.wipro.raemisclient.common.Core5GDetails;
import com.wipro.raemisclient.datamodel.GNodeB;
import com.wipro.raemisclient.mapper.GNBMapper;
import com.wipro.raemisclient.model.response.GNodeBResponse;
import com.wipro.raemisclient.utils.Util;

public class GNodeBTeamplate extends RestTemplateService implements RestTemplateInterface<GNodeB> {
	@Autowired
	public List<GNodeB> getGNodeBRecord(String coreId) {
		String endPoint = "/gNB/nodes/";
		ResponseEntity<?> responseEntity = get(coreId, GNodeB[].class, endPoint);
		GNodeB[] gNodeBs = (GNodeB[]) responseEntity.getBody();
		return Arrays.asList(gNodeBs);
	}

	@Autowired
	public void insertGNodeBRecords(String paramName, String core_id, List<GNodeB> gNodeBs) {
		post(paramName, core_id, gNodeBs, String.class, "/gNB/saveGNodeBs");
	}

	@Autowired
	public void deleteRecord(String paramName, List<Long> gNodeBs) {
		if (!gNodeBs.isEmpty())
			delete(paramName, Util.convertLongListToString(gNodeBs), "/gNB/deleteGNodeBs");
	}

	@Autowired
	public void updateRecords(String paramName, String core_id, GNodeB gNodeB) {
		update(paramName, core_id, gNodeB, String.class, "/gNB/udpateGNodeB");
	}

	@Autowired
	public void updateOrInsertRecords(List<GNodeB> listOfData) throws IOException {
		if (listOfData == null || listOfData.isEmpty())
			return;

		boolean isDataUpdated = false;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("core_id", Core5GDetails._5G_CORE_ID);
		List<GNodeB> existingGNBs = getGNodeBRecord(Core5GDetails._5G_CORE_ID);

		if (existingGNBs.isEmpty()) {
			insertGNodeBRecords("core_id", Core5GDetails._5G_CORE_ID, listOfData);
		} else {

			List<Long> existingGNBIds = existingGNBs.stream().map(gnb -> gnb.getGnbId()).collect(Collectors.toList());
			List<Long> currentGNBIds = listOfData.stream().map(gnb -> gnb.getGnbId()).collect(Collectors.toList());
			List<Long> deleteGNBs = existingGNBIds.stream().filter(i -> !currentGNBIds.contains(i))
					.collect(Collectors.toList());

			if (deleteGNBs != null && !deleteGNBs.isEmpty()) {
				deleteRecord("gNB_ids", deleteGNBs);
				isDataUpdated = true;
			}
			List<GNodeB> insertGNBs = listOfData.stream().filter(i -> !existingGNBIds.contains(i.getGnbId()))
					.collect(Collectors.toList());

			if (insertGNBs != null && !insertGNBs.isEmpty()) {
				insertGNodeBRecords("core_id", Core5GDetails._5G_CORE_ID, insertGNBs);
				isDataUpdated = true;
			}

			for (GNodeB curr_gnb : listOfData) {
				for (GNodeB ext_gnb : existingGNBs) {
					if (curr_gnb.getGnbId() == ext_gnb.getGnbId()) {
						if (!curr_gnb.getStatus().equals(ext_gnb.getStatus())) {
							updateRecords("core_id", Core5GDetails._5G_CORE_ID, curr_gnb);
							isDataUpdated = true;
						}
					}
				}
			}
		}
		if (isDataUpdated) {
			NotifyMessage.sendMessage_GNodeB();
		}
	}

	public void pollRecords(List<GNodeBResponse> parseJsonStrToObject) throws SQLException, IOException {
		List<GNodeB> gNodeBs = GNBMapper.mappingModelToDAODataModelList(parseJsonStrToObject);
		updateOrInsertRecords(gNodeBs);
	}
}

package com.wipro.raemisclient.mapper;

import java.util.ArrayList;
import java.util.List;

import com.wipro.raemisclient.datamodel.GNodeB;
import com.wipro.raemisclient.model.response.GNodeBResponse;

public class GNBMapper {
	
	public static GNodeB mappingModelToDAODataModel(GNodeBResponse gnb) {
		GNodeB gNodeB = new GNodeB();
		gNodeB.setGnbId(gnb.getGnb_id());
		gNodeB.setGnbName(gnb.getName());
		gNodeB.setIpAddress(gnb.getSctp_address());
		gNodeB.setPlmnId(gnb.getPlmn_id());
		gNodeB.setStatus(getGnbStatus(gnb.getOper_state()));
		return gNodeB;
	}
	
	public static List<GNodeB> mappingModelToDAODataModelList(List<GNodeBResponse> gnbList) {
		List<GNodeB> gNodeBList = new ArrayList<GNodeB>();
		for(GNodeBResponse gnb : gnbList) {
			gNodeBList.add(mappingModelToDAODataModel(gnb));
		}
		return gNodeBList;
	}

	private static String getGnbStatus(String operState) {
		return operState.equals("1") ? "Disconnected" : "Connected";
	}
}

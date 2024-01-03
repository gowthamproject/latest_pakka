package com.wipro.raemisclient.services;

import com.wipro.raemisclient.certificate.Certificate;
import com.wipro.raemisclient.common.Constants;
import com.wipro.raemisclient.microservicetemplate.GNodeBTeamplate;
import com.wipro.raemisclient.model.response.GNodeBResponse;
import com.wipro.raemisclient.utils.Util;

import java.util.List;

public class GNBService extends RaemisService {

	@SuppressWarnings("unchecked")
	public void pull_GNBDetailsFromRaemisAPI() throws Exception {

		Certificate.doTrustToCertificates();
		String responseJson = super.pullData(Constants.GNB_URL);
		// System.out.println("GNB RESPONSE ----: " + responseJson);
		if (responseJson != null && !responseJson.isEmpty())
			new GNodeBTeamplate().pollRecords((List<GNodeBResponse>) Util.parseJsonStrToObject(responseJson, Constants.GNB));
	}
}

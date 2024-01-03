package com.wipro.raemisclient.services;

import com.wipro.raemisclient.certificate.Certificate;
import com.wipro.raemisclient.common.Constants;
import com.wipro.raemisclient.microservicetemplate.PDUSessionDAO;
import com.wipro.raemisclient.model.response.PDUSessionResponse;
import com.wipro.raemisclient.utils.Util;

import java.util.List;

public class PDUSessionService extends RaemisService {

	@SuppressWarnings("unchecked")
	public void pull_PDUSessionDetailsFromRaemisAPI() throws Exception {

		Certificate.doTrustToCertificates();
		String responseJson = super.pullData(Constants.PDU_SESSON_URL);
		// System.out.println("PDU SESSION RESPONSE ----: " + responseJson);
		if (responseJson != null && !responseJson.isEmpty())
			new PDUSessionDAO().pollRecords((List<PDUSessionResponse>) Util.parseJsonStrToObject(responseJson, Constants.PDU_SESSION));
	}
}

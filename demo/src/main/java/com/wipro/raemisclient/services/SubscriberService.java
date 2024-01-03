package com.wipro.raemisclient.services;

import com.wipro.raemisclient.certificate.Certificate;
import com.wipro.raemisclient.common.Constants;
import com.wipro.raemisclient.microservicetemplate.SubscriberDAO;
import com.wipro.raemisclient.model.response.SubscriberResponse;
import com.wipro.raemisclient.utils.Util;

import java.util.List;

public class SubscriberService extends RaemisService {

	@SuppressWarnings("unchecked")
	public void pull_SubsriberDetailsFromRaemisAPI() throws Exception {
		Certificate.doTrustToCertificates();
		String responseJson = super.pullData(Constants.SUBSCRIBER_URL);
		// System.out.println("SUBSCRIBER RESPONSE ----: " + responseJson);
		if (responseJson != null && !responseJson.isEmpty())
			new SubscriberDAO().pollRecords((List<SubscriberResponse>) Util.parseJsonStrToObject(responseJson, Constants.SUBSCRIBER));
	}
}

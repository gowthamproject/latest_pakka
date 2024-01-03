package com.wipro.raemisclient.services;

import com.wipro.raemisclient.certificate.Certificate;
import com.wipro.raemisclient.common.Constants;
import com.wipro.raemisclient.microservicetemplate.AlarmDAO;
import com.wipro.raemisclient.model.response.AlarmResponse;
import com.wipro.raemisclient.utils.Util;

import java.util.List;

public class AlarmService extends RaemisService {
	@SuppressWarnings("unchecked")
	public void pull_AlarmDetailsFromRaemisAPI() throws Exception {

		Certificate.doTrustToCertificates();
		String responseJson = super.pullData(Constants.ALARM_URL);
		if (responseJson != null && !responseJson.isEmpty())
			new AlarmDAO().pollRecords((List<AlarmResponse>) Util.parseJsonStrToObject(responseJson, Constants.ALARM));
	}
}
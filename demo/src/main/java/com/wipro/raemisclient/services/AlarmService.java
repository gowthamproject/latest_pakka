package com.wipro.raemisclient.services;

import com.wipro.raemisclient.certificate.Certificate;
import com.wipro.raemisclient.common.Constants;
import com.wipro.raemisclient.dao.AlarmDAO;
import com.wipro.raemisclient.model.Alarm;
import com.wipro.raemisclient.utils.Util;

import java.util.List;

public class AlarmService extends RaemisService {
	@SuppressWarnings("unchecked")
	public void pull_AlarmDetailsFromRaemisAPI() throws Exception {

		Certificate.doTrustToCertificates();
		String responseJson = super.pullData(Constants.ALARM_URL);
		if (responseJson != null && !responseJson.isEmpty())
			new AlarmDAO().pollRecords((List<Alarm>) Util.parseJsonStrToObject(responseJson, Constants.ALARM));
	}
}
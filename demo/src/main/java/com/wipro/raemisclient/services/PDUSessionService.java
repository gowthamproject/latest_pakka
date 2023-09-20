package com.wipro.raemisclient.services;

import com.wipro.raemisclient.certificate.Certificate;
import com.wipro.raemisclient.common.Constants;
import com.wipro.raemisclient.dao.AlarmDAO;
import com.wipro.raemisclient.dao.PDUSessionDAO;
import com.wipro.raemisclient.model.Alarm;
import com.wipro.raemisclient.model.PDUSession;
import com.wipro.raemisclient.utils.Util;

import java.util.List;

public class PDUSessionService extends RaemisService {

    public void pull_PDUSessionDetailsFromRaemisAPI() throws Exception {

        Certificate.doTrustToCertificates();
        String responseJson = super.pullData(Constants.PDU_SESSON_URL);
        //System.out.println("PDU SESSION RESPONSE ----: " + responseJson);
        
        new PDUSessionDAO().pollRecords((List<PDUSession>) Util.parseJsonStrToObject(responseJson, Constants.PDU_SESSION));
    }
}

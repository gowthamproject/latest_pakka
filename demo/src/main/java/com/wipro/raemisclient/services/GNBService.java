package com.wipro.raemisclient.services;

import com.wipro.raemisclient.certificate.Certificate;
import com.wipro.raemisclient.common.Constants;
import com.wipro.raemisclient.dao.GNBDAO;
import com.wipro.raemisclient.model.GNB;
import com.wipro.raemisclient.utils.Util;

import java.util.List;

public class GNBService extends RaemisService {

    public void pull_GNBDetailsFromRaemisAPI() throws Exception {

        Certificate.doTrustToCertificates();
        String responseJson = super.pullData(Constants.GNB_URL);
        //System.out.println("GNB RESPONSE ----: " + responseJson);
        new GNBDAO().pollRecords((List<GNB>) Util.parseJsonStrToObject(responseJson, Constants.GNB));
    }
}

package com.wipro.raemisclient.services;

import com.wipro.raemisclient.certificate.Certificate;
import com.wipro.raemisclient.common.Constants;
import com.wipro.raemisclient.dao.NetDeviceDAO;
import com.wipro.raemisclient.model.NetDevice;
import com.wipro.raemisclient.utils.Util;

import java.util.List;

public class NetDeviceService extends RaemisService {

    private NetDeviceDAO netDeviceDAO = new NetDeviceDAO();

    public void pull_NetDeviceDetailsFromRaemisAPI() throws Exception {

        Certificate.doTrustToCertificates();
        String responseJson = super.pullData(Constants.NETDEVICE_URL);
        //System.out.println("NETDEVICE RESPONSE ----: " + responseJson);
        new NetDeviceDAO().pollRecords((List<NetDevice>) Util.parseJsonStrToObject(responseJson, Constants.NETDEVICE));
    }
}

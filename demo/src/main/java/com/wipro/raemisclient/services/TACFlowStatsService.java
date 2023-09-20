package com.wipro.raemisclient.services;

import com.wipro.raemisclient.certificate.Certificate;
import com.wipro.raemisclient.common.Constants;
import com.wipro.raemisclient.dao.TACFlowStatsDAO;
import com.wipro.raemisclient.model.MGWControlFlowStats;
import com.wipro.raemisclient.model.Throughput;
import com.wipro.raemisclient.utils.Util;

import java.io.IOException;
import java.util.List;

public class TACFlowStatsService extends RaemisService implements ThroughputIntf {

    @SuppressWarnings("unchecked")
	public void pull_ThroughputFromRaemisAPI() throws Exception {

        Certificate.doTrustToCertificates();
        String throughput_5g_Json = get5GCtrlFlowStats(Constants.MGW_CTRL_FLOW_STATS_URL, Constants._5G, "");
        if (throughput_5g_Json != null && !throughput_5g_Json.isEmpty()) {
            List<MGWControlFlowStats> listOf5GCtrlFlow = (List<MGWControlFlowStats>) Util.parseJsonStrToObject(throughput_5g_Json, Constants.THROUGHPUT);
            //System.out.println("THROUGHPUT RESPONSE ----: " + listOf5GCtrlFlow);
            new TACFlowStatsDAO().pollRecords(calculateThroughput(listOf5GCtrlFlow));
        }
    }

    @Override
    public String get5GCtrlFlowStats(String throughput_url, String type, String dir) throws IOException {
        return super.pullData(throughput_url + "/" + type);
    }

    @Override
    public List<Throughput> calculateThroughput(List<MGWControlFlowStats> ctrlFlowStatList) {
        return null;
    }
}

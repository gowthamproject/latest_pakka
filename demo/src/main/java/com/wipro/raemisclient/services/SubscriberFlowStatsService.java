package com.wipro.raemisclient.services;

import com.wipro.raemisclient.certificate.Certificate;
import com.wipro.raemisclient.common.Constants;
import com.wipro.raemisclient.common.Core5GDetails;
import com.wipro.raemisclient.dao.SubscriberFlowStatsDAO;
import com.wipro.raemisclient.model.MGWControlFlowStats;
import com.wipro.raemisclient.model.Throughput;
import com.wipro.raemisclient.utils.Util;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SubscriberFlowStatsService extends RaemisService {


    @SuppressWarnings("unchecked")
	public void pull_ThroughputFromRaemisAPI() throws Exception {

        Certificate.doTrustToCertificates();
        String throughput_5g_Json;
        List<Throughput> throughputs = new ArrayList<>();
        Throughput throughputObj = new Throughput();
        for (String dir : Constants.DIRECTION) {
            throughput_5g_Json = get5GCtrlFlowStats(Constants.MGW_CTRL_FLOW_STATS_URL, Constants._5G, dir);
            if (throughput_5g_Json != null && !throughput_5g_Json.isEmpty()) {
            	 List<MGWControlFlowStats> listOf5GCtrlFlow = (List<MGWControlFlowStats>) Util.parseJsonStrToObject(throughput_5g_Json, Constants.THROUGHPUT);
                 /*listOf5GCtrlFlow.forEach(p -> {
                     System.out.println("THROUGHPUT RESPONSE SUBSCRIBER----: " + p.toString());
                 });*/
                 int throughput = Util.calculateThroughput(listOf5GCtrlFlow);

                 if (dir.equals(Constants.UP_LINK))
                     throughputObj.setUplink(throughput);
                 else
                     throughputObj.setDownlink(throughput);
            }
        }
        throughputObj.setNmsId(Core5GDetails._5G_CORE_ID);
        throughputObj.setParentId("");
        throughputObj.setDatetime(new Timestamp(new Date().getTime()));
        throughputs.add(throughputObj);
        new SubscriberFlowStatsDAO().pollRecords(throughputs);
    }

    public String get5GCtrlFlowStats(String throughput_url, String type, String dir) throws IOException {
        /*Map<String, Object> params = new HashMap<>();
        params.put("aggr_value", type + ":1:" + dir);*/
        String param = "";
        if (dir.equals(Constants.UP_LINK))
            param = "?aggr_value=SMF:2:0:*";
        else
            param = "?aggr_value=SMF:2:1:*";
        return super.pullData(throughput_url + param);
    }
}

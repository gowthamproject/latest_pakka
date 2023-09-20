package com.wipro.raemisclient.services;

import com.wipro.raemisclient.certificate.Certificate;
import com.wipro.raemisclient.common.Constants;
import com.wipro.raemisclient.dao.SubscriberFlowStatsDAO;
import com.wipro.raemisclient.model.MGWControlFlowStats;
import com.wipro.raemisclient.model.Throughput;
import com.wipro.raemisclient.utils.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PDNFlowStatsService extends RaemisService {

    public void pull_ThroughputFromRaemisAPI() throws Exception {

        Certificate.doTrustToCertificates();
        String throughput_5g_Json;
        List<Throughput> throughputs = new ArrayList<>();
        Throughput throughputObj = new Throughput();
        for (String dir : Constants.DIRECTION) {
            throughput_5g_Json = get5GCtrlFlowStats(Constants.MGW_CTRL_FLOW_STATS_URL, Constants._5G, Constants.UP_LINK);
            List<MGWControlFlowStats> listOf5GCtrlFlow = (List<MGWControlFlowStats>) Util.parseJsonStrToObject(throughput_5g_Json, Constants.THROUGHPUT);
            //System.out.println("THROUGHPUT RESPONSE ----: " + listOf5GCtrlFlow);
            int throughput = Util.calculateThroughput(listOf5GCtrlFlow);

            if (dir.equals(Constants.UP_LINK))
                throughputObj.setUplink(throughput);
            else
                throughputObj.setDownlink(throughput);
        }

        throughputObj.setNmsId(Constants.NMS_ID);
        throughputObj.setParentId("");
        throughputObj.setDatetime(new Date());
        throughputs.add(throughputObj);
        new SubscriberFlowStatsDAO().pollRecords(throughputs);
    }

    public String get5GCtrlFlowStats(String throughput_url, String type, String dir) throws IOException {
        /*Map<String, Object> params = new HashMap<>();
        params.put("aggr_value", type + ":2:" + dir);*/
        String param = "";
        if (dir.equals(Constants.UP_LINK))
            param = "?like_aggr_value=SMF:2:0:%";
        else
            param = "?like_aggr_value=SMF:2:1:%";
        return super.pullData(throughput_url + param);
    }
}

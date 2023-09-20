package com.wipro.raemisclient.services;

import com.wipro.raemisclient.model.MGWControlFlowStats;
import com.wipro.raemisclient.model.Throughput;

import java.io.IOException;
import java.util.List;

public interface ThroughputIntf {

    public String get5GCtrlFlowStats(String throughput_url, String type, String dir) throws IOException;
    public List<Throughput> calculateThroughput(List<MGWControlFlowStats> ctrlFlowStatList);
}

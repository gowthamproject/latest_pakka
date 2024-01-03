package com.wipro.raemisclient.services;

import java.io.IOException;
import java.util.List;

import com.wipro.raemisclient.model.response.MGWControlFlowStatsResponse;
import com.wipro.raemisclient.model.response.ThroughputResponse;

public interface ThroughputIntf {

	public String get5GCtrlFlowStats(String throughput_url, String type, String dir) throws IOException;

	public List<ThroughputResponse> calculateThroughput(List<MGWControlFlowStatsResponse> ctrlFlowStatList);
}

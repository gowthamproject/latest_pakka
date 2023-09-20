package com.wipro.raemisclient.poll;


import java.util.TimerTask;

import com.wipro.raemisclient.services.SubscriberFlowStatsService;

public class SubscriberThroughputPolling extends TimerTask {

	@Override
	public void run() {
		try {
			startPolling();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void startPolling() throws Exception {
		new SubscriberFlowStatsService().pull_ThroughputFromRaemisAPI();
		
	}
}

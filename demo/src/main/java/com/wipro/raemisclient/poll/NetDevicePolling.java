package com.wipro.raemisclient.poll;

import com.wipro.raemisclient.services.NetDeviceService;

import java.util.TimerTask;

public class NetDevicePolling extends TimerTask {

	@Override
	public void run() {
		try {
			startPolling();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void startPolling() throws Exception {

		new NetDeviceService().pull_NetDeviceDetailsFromRaemisAPI();

	}
}

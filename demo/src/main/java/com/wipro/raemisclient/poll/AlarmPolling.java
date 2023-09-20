package com.wipro.raemisclient.poll;

import com.wipro.raemisclient.services.AlarmService;

import java.util.TimerTask;

public class AlarmPolling extends TimerTask {

	@Override
	public void run() {
		try {
			startPolling();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void startPolling() throws Exception {

		new AlarmService().pull_AlarmDetailsFromRaemisAPI();
	}
}

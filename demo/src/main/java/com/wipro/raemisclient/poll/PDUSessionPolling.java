package com.wipro.raemisclient.poll;

import com.wipro.raemisclient.services.PDUSessionService;

import java.util.TimerTask;

public class PDUSessionPolling extends TimerTask {

	@Override
	public void run() {
		try {
			startPolling();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void startPolling() throws Exception {

		new PDUSessionService().pull_PDUSessionDetailsFromRaemisAPI();
	}
}

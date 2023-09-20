package com.wipro.raemisclient;

import com.wipro.raemisclient.common.Constants;
import com.wipro.raemisclient.poll.AlarmPolling;
import com.wipro.raemisclient.poll.GNBPolling;
import com.wipro.raemisclient.poll.PDUSessionPolling;
import com.wipro.raemisclient.poll.SubscriberPolling;
import com.wipro.raemisclient.poll.SubscriberThroughputPolling;
import com.wipro.raemisclient.poll.SystemInfoPolling;
import com.wipro.raemisclient.session.HTTPSessionManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Timer;

@SpringBootApplication
public class StartApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(StartApplication.class, args);
		System.out.println("Raemis Client Application Starting...");
		HTTPSessionManager.createSession();

		new Timer().schedule(new AlarmPolling(), 0, Constants.POLL_INTERVAL_10_SEC);
		System.out.println("Alarm Polling Activated...");

		new Timer().schedule(new GNBPolling(), 0, Constants.POLL_INTERVAL_10_SEC);
		System.out.println("GNB Polling Polling Activated...");

		new Timer().schedule(new SubscriberPolling(), 0, Constants.POLL_INTERVAL_10_SEC);
		System.out.println("Subscriber Polling Polling Activated...");

		new Timer().schedule(new PDUSessionPolling(), 0, Constants.POLL_INTERVAL_10_SEC);
		System.out.println("PDUSession Polling Polling Activated...");

		new Timer().schedule(new SystemInfoPolling(), 0, Constants.POLL_INTERVAL_10_SEC);
		System.out.println("SystemInfo Polling Polling Activated...");

		new Timer().schedule(new SubscriberThroughputPolling(), 0, Constants.POLL_INTERVAL_10_SEC);
		System.out.println("Subscriber Throughput Polling Activated...");
	}
}

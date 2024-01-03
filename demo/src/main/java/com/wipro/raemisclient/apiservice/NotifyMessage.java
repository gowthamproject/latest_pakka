package com.wipro.raemisclient.apiservice;

import java.io.IOException;

import com.wipro.raemisclient.common.Core5GDetails;

public class NotifyMessage extends HTTPService {

	public static void sendMessage_GNodeB() throws IOException {
		POST(Constants.MICROSERVICE_GNODEB_URL, Core5GDetails._5G_CORE_ID);
	}

	public static void sendMessage_Throughput() throws IOException {
		POST(Constants.MICROSERVICE_THROUHPUT_URL, Core5GDetails._5G_CORE_ID);
	}

	public static void sendMessage_Subscriber() throws IOException {
		POST(Constants.MICROSERVICE_SUBSCRIBER_URL, Core5GDetails._5G_CORE_ID);
	}

	public static void sendMessage_Alarm() throws IOException {
		POST(Constants.MICROSERVICE_ALARM_URL, Core5GDetails._5G_CORE_ID);
	}

	public static void sendMessage_PDUSession() throws IOException {
		POST(Constants.MICROSERVICE_PDUSESSION_URL, Core5GDetails._5G_CORE_ID);
	}

}

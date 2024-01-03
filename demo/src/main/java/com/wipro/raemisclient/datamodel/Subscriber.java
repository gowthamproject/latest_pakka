package com.wipro.raemisclient.datamodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Subscriber {

	private long subscriberId;

	private String name;

	private long imsi;

	private long imei;

	private long msisdn;

	private String ipAddress;

	private String status;

}

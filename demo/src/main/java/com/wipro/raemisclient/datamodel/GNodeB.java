package com.wipro.raemisclient.datamodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GNodeB {

	private long gnbId;

	private String gnbName;

	private String ipAddress;

	private long plmnId;

	private String status;

	private Location location;

}

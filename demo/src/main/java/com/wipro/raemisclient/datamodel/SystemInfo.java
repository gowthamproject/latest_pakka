package com.wipro.raemisclient.datamodel;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SystemInfo {

	private long id;

	private String hostName;

	private String ipAddress;

	private String nodeType;

	private Date updatedTime;

	private String memoryUsagePercentage;

	private String cpuUtilizationPercentage;

	private String diskUsagePercentage;

	private String availableMemoryGB;

	private String totalMemoryGB;

	private String availableDiskGB;

	private String totalDiskGB;

}

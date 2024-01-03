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
public class Alarm {

	private long alarmId;

	private String nodeType;

	private String nodeName;

	private Date updatedTime;

	private String severity;

	private String status;

	private String evnetType;

	private String specificProblem;

	private String additionalMessage;

	private String acknowledged;
	
	private String coreId;
	
}

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
public class PDUSession {

	private long id;

	private int sessionCount;
	
	private int msisdn;

	private Date updatedTime;

}

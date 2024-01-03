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
public class Throughput {

	private long id;

	private long uplink;

	private long downlink;

	private Date updatedTime;

}

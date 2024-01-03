package com.wipro.raemisclient.datamodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Location {

	private int id;
    
	private String type;

	private String street;

	private String regionState;

	private String country;

	private long zipcode;

	private long latitude;

	private long longitude;

}

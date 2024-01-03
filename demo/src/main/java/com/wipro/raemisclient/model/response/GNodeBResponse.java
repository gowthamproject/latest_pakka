package com.wipro.raemisclient.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GNodeBResponse {

    private String name;
    private long plmn_id;
    private int gnb_id;
    private int tac;
    private String sctp_address;
    private String oper_state;
}

package com.wipro.raemisclient.model;

public class GNB {

    private String name;
    private String plmn_id;
    private int gnb_id;
    private int tac;
    private String sctp_address;
    private String oper_state;
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlmn_id() {
		return plmn_id;
	}

	public void setPlmn_id(String plmn_id) {
		this.plmn_id = plmn_id;
	}

	public int getGnb_id() {
		return gnb_id;
	}

	public void setGnb_id(int gnb_id) {
		this.gnb_id = gnb_id;
	}

	public int getTac() {
		return tac;
	}

	public void setTac(int tac) {
		this.tac = tac;
	}

	public String getSctp_address() {
		return sctp_address;
	}

	public void setSctp_address(String sctp_address) {
		this.sctp_address = sctp_address;
	}

	public String getOper_state() {
		return oper_state;
	}

	public void setOper_state(String oper_state) {
		this.oper_state = oper_state;
	}

	public GNB(){}

    public GNB(String name, String plmn_id, int gnb_id, int tac, String sctp_address, String oper_state) {
        this.name = name;
        this.plmn_id = plmn_id;
        this.gnb_id = gnb_id;
        this.tac = tac;
        this.sctp_address = sctp_address;
        this.oper_state = oper_state;
    }
}

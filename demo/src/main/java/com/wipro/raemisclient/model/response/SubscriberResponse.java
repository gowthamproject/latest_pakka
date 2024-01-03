package com.wipro.raemisclient.model.response;

public class SubscriberResponse {

    private int id;
    private String imsi;
    private String tmsi;
    private String ptmsi;
    private String imei;
    private String msisdn;
    private String sip_client_attachment;

    private String mno_attachment;

    private String local_ps_attachment;

    private String mno_ps_attachment;
    private String domain;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getTmsi() {
        return tmsi;
    }

    public void setTmsi(String tmsi) {
        this.tmsi = tmsi;
    }

    public String getPtmsi() {
        return ptmsi;
    }

    public void setPtmsi(String ptmsi) {
        this.ptmsi = ptmsi;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getSip_client_attachment() {
        return sip_client_attachment;
    }

    public void setSip_client_attachment(String sip_client_attachment) {
        this.sip_client_attachment = sip_client_attachment;
    }

    public String getMno_attachment() {
        return mno_attachment;
    }

    public void setMno_attachment(String mno_attachment) {
        this.mno_attachment = mno_attachment;
    }

    public String getLocal_ps_attachment() {
        return local_ps_attachment;
    }

    public void setLocal_ps_attachment(String local_ps_attachment) {
        this.local_ps_attachment = local_ps_attachment;
    }

    public String getMno_ps_attachment() {
        return mno_ps_attachment;
    }

    public void setMno_ps_attachment(String mno_ps_attachment) {
        this.mno_ps_attachment = mno_ps_attachment;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SubscriberResponse(){}

    public SubscriberResponse(int id, String imsi, String tmsi, String ptmsi, String imei, String msisdn, String sip_client_attachment, String mno_attachment, String local_ps_attachment, String mno_ps_attachment, String domain, String name) {
        this.id = id;
        this.imsi = imsi;
        this.tmsi = tmsi;
        this.ptmsi = ptmsi;
        this.imei = imei;
        this.msisdn = msisdn;
        this.sip_client_attachment = sip_client_attachment;
        this.mno_attachment = mno_attachment;
        this.local_ps_attachment = local_ps_attachment;
        this.mno_ps_attachment = mno_ps_attachment;
        this.domain = domain;
        this.name = name;
    }
}

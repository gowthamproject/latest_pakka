package com.wipro.raemisclient.model;

import com.wipro.raemisclient.common.Core5GDetails;

public class PDUSession {

    private int id;
    private String gnb_id;
    private String ran_ue_ngap_id;
    private String supi;
    private String dateTime;
    private String core_id = Core5GDetails._5G_CORE_ID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGnb_id() {
        return gnb_id;
    }

    public void setGnb_id(String gnb_id) {
        this.gnb_id = gnb_id;
    }

    public String getRan_ue_ngap_id() {
        return ran_ue_ngap_id;
    }

    public void setRan_ue_ngap_id(String ran_ue_ngap_id) {
        this.ran_ue_ngap_id = ran_ue_ngap_id;
    }

    public String getSupi() {
        return supi;
    }

    public void setSupi(String supi) {
        this.supi = supi;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getCore_id() {
        return core_id;
    }

    public void setCore_id(String core_id) {
        this.core_id = core_id;
    }

    @Override
    public String toString() {
        return "PDUSession{" +
                "id=" + id +
                ", gnb_id='" + gnb_id + '\'' +
                ", ran_ue_ngap_id='" + ran_ue_ngap_id + '\'' +
                ", supi='" + supi + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", core_id='" + core_id + '\'' +
                '}';
    }
}

package com.wipro.raemisclient.model.response;

import com.wipro.raemisclient.common.Core5GDetails;

import java.util.Date;

public class ThroughputResponse {

    private String nmsId = Core5GDetails._5G_CORE_ID;
    private String parentId = null;
    private Date datetime;
    private int uplink;
    private int downlink;

    public String getNmsId() {
        return nmsId;
    }

    public void setNmsId(String nmsId) {
        this.nmsId = nmsId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public int getUplink() {
        return uplink;
    }

    public void setUplink(int uplink) {
        this.uplink = uplink;
    }

    public int getDownlink() {
        return downlink;
    }

    public void setDownlink(int downlink) {
        this.downlink = downlink;
    }

    public ThroughputResponse() {}

    public ThroughputResponse(String nmsId, String parentId, Date datetime, int uplink, int downlink) {
        this.nmsId = nmsId;
        this.parentId = parentId;
        this.datetime = datetime;
        this.uplink = uplink;
        this.downlink = downlink;
    }
}

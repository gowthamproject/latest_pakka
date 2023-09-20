package com.wipro.raemisclient.model;

import java.util.Date;

public class SystemInfo {

    private String instance;
    private String nodename;

    private String type;
    private Date polling_date_time;
    private String cpu_utilization_in_percent;
    private String memory_usage_in_percent;
    private String available_memory_in_gb;
    private String total_memory_in_gb;

    private String total_diskspace_in_gb;

    private String available_diskspace_in_gb;

    private String disk_usage_in_percent;

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNodename() {
        return nodename;
    }

    public void setNodename(String nodename) {
        this.nodename = nodename;
    }

    public Date getPolling_date_time() {
        return polling_date_time;
    }

    public void setPolling_date_time(Date polling_date_time) {
        this.polling_date_time = polling_date_time;
    }

    public String getCpu_utilization_in_percent() {
        return cpu_utilization_in_percent;
    }

    public void setCpu_utilization_in_percent(String cpu_utilization_in_percent) {
        this.cpu_utilization_in_percent = cpu_utilization_in_percent;
    }

    public String getMemory_usage_in_percent() {
        return memory_usage_in_percent;
    }

    public void setMemory_usage_in_percent(String memory_usage_in_percent) {
        this.memory_usage_in_percent = memory_usage_in_percent;
    }

    public String getAvailable_memory_in_gb() {
        return available_memory_in_gb;
    }

    public void setAvailable_memory_in_gb(String available_memory_in_gb) {
        this.available_memory_in_gb = available_memory_in_gb;
    }

    public String getTotal_memory_in_gb() {
        return total_memory_in_gb;
    }

    public void setTotal_memory_in_gb(String total_memory_in_gb) {
        this.total_memory_in_gb = total_memory_in_gb;
    }

    public String getTotal_diskspace_in_gb() {
        return total_diskspace_in_gb;
    }

    public void setTotal_diskspace_in_gb(String total_diskspace_in_gb) {
        this.total_diskspace_in_gb = total_diskspace_in_gb;
    }

    public String getAvailable_diskspace_in_gb() {
        return available_diskspace_in_gb;
    }

    public void setAvailable_diskspace_in_gb(String available_diskspace_in_gb) {
        this.available_diskspace_in_gb = available_diskspace_in_gb;
    }

    public String getDisk_usage_in_percent() {
        return disk_usage_in_percent;
    }

    public void setDisk_usage_in_percent(String disk_usage_in_percent) {
        this.disk_usage_in_percent = disk_usage_in_percent;
    }

    @Override
    public String toString() {
        return "SystemInfo{" +
                "instance='" + instance + '\'' +
                ", nodename='" + nodename + '\'' +
                ", type='" + type + '\'' +
                ", polling_date_time=" + polling_date_time +
                ", cpu_utilization_in_percent='" + cpu_utilization_in_percent + '\'' +
                ", memory_usage_in_percent='" + memory_usage_in_percent + '\'' +
                ", available_memory_in_gb='" + available_memory_in_gb + '\'' +
                ", total_memory_in_gb='" + total_memory_in_gb + '\'' +
                ", total_diskspace_in_gb='" + total_diskspace_in_gb + '\'' +
                ", available_diskspace_in_gb='" + available_diskspace_in_gb + '\'' +
                ", disk_usage_in_percent='" + disk_usage_in_percent + '\'' +
                '}';
    }
}

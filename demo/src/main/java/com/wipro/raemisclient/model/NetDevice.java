package com.wipro.raemisclient.model;

public class NetDevice {

    private int id;
    private String mac;
    private String device;
    private String parent_device;
    private int vlan_id;
    private String ip;
    private String netmask;
    private String ipv6;
    private int nat_enabled;
    private String owner;
    private int device_type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getParent_device() {
        return parent_device;
    }

    public void setParent_device(String parent_device) {
        this.parent_device = parent_device;
    }

    public int getVlan_id() {
        return vlan_id;
    }

    public void setVlan_id(int vlan_id) {
        this.vlan_id = vlan_id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNetmask() {
        return netmask;
    }

    public void setNetmask(String netmask) {
        this.netmask = netmask;
    }

    public String getIpv6() {
        return ipv6;
    }

    public void setIpv6(String ipv6) {
        this.ipv6 = ipv6;
    }

    public int getNat_enabled() {
        return nat_enabled;
    }

    public void setNat_enabled(int nat_enabled) {
        this.nat_enabled = nat_enabled;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getDevice_type() {
        return device_type;
    }

    public void setDevice_type(int device_type) {
        this.device_type = device_type;
    }

}

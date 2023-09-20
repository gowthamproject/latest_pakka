package com.wipro.raemisclient.common;

public class Constants {

    public static final String NMS_ID = "1045173342497102197";
    public static final String USERNAME = "raemis";
    public static final String PASSWORD = "Test@1234";

    // ----------------- Endpoint Constants --------------
    public static final String RAEMIS_ENDPOINT = "https://20.163.176.87";
    public static final String SESSION_URL = RAEMIS_ENDPOINT + "/api/session";
    public static final String ALARM_URL = RAEMIS_ENDPOINT + "/api/alarm";
    public static final String SUBSCRIBER_URL = RAEMIS_ENDPOINT + "/api/subscriber";
    public static final String NETDEVICE_URL = RAEMIS_ENDPOINT + "/api/net_device";
    public static final String MGW_CTRL_FLOW_STATS_URL = RAEMIS_ENDPOINT + "/api/mgw_ctrl_flow_stats";
    public static final String GNB_URL = RAEMIS_ENDPOINT + "/api/gnb";

    public static final String PDU_SESSON_URL = RAEMIS_ENDPOINT + "/api/ngap_context";



    // ----------------- Operation Constants --------------
    public static final String ALARM = "Alarm";
    public static final String SUBSCRIBER = "Subscriber";
    public static final String NETDEVICE = "NetDevice";
    public static final String THROUGHPUT = "Throughput";
    public static final String GNB = "gNB";
    public static final String _5G_CORE = "5GCore";

    public static final String PDU_SESSION = "PDU";

    // ----------------- Throughput Constants --------------

    public static final String UP_LINK = "0";
    public static final String DOWN_LINK = "1";
    public static final String _5G = "SMF";
    public static final String DIRECTION[] = {UP_LINK, DOWN_LINK};


    //--------------- Poll Interval Constants ---------------

    public static final int POLL_INTERVAL_5_SEC = 5000;
    public static final int POLL_INTERVAL_10_SEC = 10000;
    public static final int POLL_INTERVAL_15_SEC = 15000;
    public static final String[] SEVIRITY = {"Critical", "Major", "Warning", "Minor"};
    public static final String[] ALARM_STATUS = {"Closed", "Open"};


    //--------------- Azure Prometheus ---------------

    public static final String PROMETHEUS_URL = "http://172.172.202.111:9090/api/v1/query";
    public static final String PARAM_NAME_1 = "query";
    public static final String NODE_NAME_INFO_PARAM = "node_uname_info";
    public static final String MEMORY_USAGE_IN_PERCENT_PARAM = "100 * (1 - ((avg_over_time(node_memory_MemFree_bytes[10m]) + avg_over_time(node_memory_Cached_bytes[10m]) + avg_over_time(node_memory_Buffers_bytes[10m])) / avg_over_time(node_memory_MemTotal_bytes[10m])))";
    public static final String CPU_UTILIZATION_IN_PERCENT_PARAM = "100 - (avg by (instance) (irate(node_cpu_seconds_total{mode=\"idle\"}[10m]) * 100) * on(instance) group_left(nodename) (node_uname_info))";
    public static final String AVAILABLE_MEMORY_IN_GB_PARAM = "node_memory_MemAvailable_bytes * on(instance) group_left(nodename) (node_uname_info)/1024/1024/1024";
    public static final String TOTAL_MEMORY_IN_GB_PARAM = "node_memory_MemTotal_bytes/1024/1024/1024";
    public static final String TOTAL_DISK_SPACE_IN_GB = "node_filesystem_size_bytes{mountpoint=\"/\"} * on(instance) group_left(nodename) (node_uname_info)/1024/1024/1024";
    public static final String AVAILABLE_DISK_SPACE_IN_GB = "node_filesystem_free_bytes{mountpoint=\"/\"} * on(instance) group_left(nodename) (node_uname_info)/1024/1024/1024";


    public static final String NODE_NAME_INFO = "Node_Name_Info";
    public static final String MEMORY_USAGE_IN_PERCENT = "Memory_Usage_Percentage";
    public static final String CPU_UTILIZATION_IN_PERCENT = "Cpu_Utilization_Percentage";
    public static final String AVAILABLE_MEMORY_IN_GB = "Available_Memory_GB";
    public static final String TOTAL_MEMORY_IN_GB = "Total_Memory_GB";
    public static final String TOTAL_DISK_SPACE = "Total_Disk_Space";
    public static final String AVAILABLE_DISK_SPACE = "Available_Disk_Space";

}

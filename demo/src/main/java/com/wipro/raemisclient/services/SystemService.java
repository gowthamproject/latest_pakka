package com.wipro.raemisclient.services;

import com.wipro.raemisclient.certificate.Certificate;
import com.wipro.raemisclient.common.Constants;
import com.wipro.raemisclient.microservicetemplate.SystemInfoDAO;
import com.wipro.raemisclient.model.response.SystemInfoResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

public class SystemService extends PrometheusService {

    public void pullSystemInfoFromInstances() throws NoSuchAlgorithmException, KeyManagementException, IOException, ParseException, SQLException, InterruptedException {
        LinkedHashMap<String, String> jsonResponsrMap = new LinkedHashMap<>();
        jsonResponsrMap.put(Constants.NODE_NAME_INFO, pullDataFromInstance(Constants.NODE_NAME_INFO_PARAM));
        jsonResponsrMap.put(Constants.AVAILABLE_MEMORY_IN_GB, pullDataFromInstance(Constants.AVAILABLE_MEMORY_IN_GB_PARAM));
        jsonResponsrMap.put(Constants.MEMORY_USAGE_IN_PERCENT, pullDataFromInstance(Constants.MEMORY_USAGE_IN_PERCENT_PARAM));
        jsonResponsrMap.put(Constants.CPU_UTILIZATION_IN_PERCENT, pullDataFromInstance(Constants.CPU_UTILIZATION_IN_PERCENT_PARAM));
        jsonResponsrMap.put(Constants.TOTAL_MEMORY_IN_GB, pullDataFromInstance(Constants.TOTAL_MEMORY_IN_GB_PARAM));
        jsonResponsrMap.put(Constants.TOTAL_DISK_SPACE, pullDataFromInstance(Constants.TOTAL_DISK_SPACE_IN_GB));
        jsonResponsrMap.put(Constants.AVAILABLE_DISK_SPACE, pullDataFromInstance(Constants.AVAILABLE_DISK_SPACE_IN_GB));


        List<SystemInfoResponse> systemInfos = parseJson(jsonResponsrMap);
        /*systemInfos.forEach(p -> {
            System.out.println(p.toString());
        });*/
        new SystemInfoDAO().pollRecords(systemInfos);
    }


    private String pullDataFromInstance(String paramValue) throws NoSuchAlgorithmException, KeyManagementException, IOException {
        Certificate.doTrustToCertificates();
        Map<String, Object> queryParam = new HashMap<>();
        queryParam.put(Constants.PARAM_NAME_1, paramValue);
        return super.pullData(Constants.PROMETHEUS_URL, queryParam);
    }

    private List<SystemInfoResponse> parseJson(LinkedHashMap<String, String> jsonResponsrMap) throws ParseException {
        List<SystemInfoResponse> systemInfos = new ArrayList<>();
        SystemInfoResponse systemInfo = null;
        for (String key : jsonResponsrMap.keySet()) {
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(jsonResponsrMap.get(key));
            JSONObject data = (JSONObject) json.get("data");
            JSONArray result = (JSONArray) data.get("result");
            for (int i = 0; i < result.size(); i++) {
                JSONObject metrics = (JSONObject) result.get(i);
                JSONObject metricObj = (JSONObject) metrics.get("metric");
                JSONArray valueArr = (JSONArray) metrics.get("value");
                String value = "";
                String instance = (String) metricObj.get("instance");
                String nodeName = (String) metricObj.get("nodename");
                if (!key.equals(Constants.NODE_NAME_INFO))
                    value = (String) valueArr.get(1);

                if (key.equals(Constants.NODE_NAME_INFO)) {
                    systemInfo = new SystemInfoResponse();
                    systemInfo.setInstance(instance);
                    systemInfo.setNodename(nodeName);
                    systemInfo.setPolling_date_time(new Timestamp(new Date().getTime()));
                    systemInfos.add(systemInfo);
                } else {
                    for (int k = 0; k < systemInfos.size(); k++) {
                        SystemInfoResponse sysInfo = systemInfos.get(k);
                        if (instance.equals(sysInfo.getInstance())) {
                            SystemInfoResponse sysInfo_new = sysInfo;
                            systemInfos.remove(k);
                            if (key.equals(Constants.AVAILABLE_MEMORY_IN_GB))
                                sysInfo_new.setAvailable_memory_in_gb(value);
                            else if (key.equals(Constants.MEMORY_USAGE_IN_PERCENT))
                                sysInfo_new.setMemory_usage_in_percent(value);
                            else if (key.equals(Constants.CPU_UTILIZATION_IN_PERCENT))
                                sysInfo_new.setCpu_utilization_in_percent(value);
                            else if (key.equals(Constants.TOTAL_MEMORY_IN_GB))
                                sysInfo_new.setTotal_memory_in_gb(value);
                            else if (key.equals(Constants.AVAILABLE_DISK_SPACE))
                                sysInfo_new.setAvailable_diskspace_in_gb(value);
                            else if (key.equals(Constants.TOTAL_DISK_SPACE))
                                sysInfo_new.setTotal_diskspace_in_gb(value);
                            systemInfos.add(sysInfo_new);
                        }
                    }
                }
            }
        }
        return calculateUsedDiskPercentage(systemInfos);
    }

    private List<SystemInfoResponse> calculateUsedDiskPercentage(List<SystemInfoResponse> systemInfos){
        for(SystemInfoResponse sysInfo : systemInfos){
           double availabel_disk_space = Double.parseDouble(sysInfo.getAvailable_diskspace_in_gb());
           double total_disk_space = Double.parseDouble(sysInfo.getTotal_diskspace_in_gb());
           double used_disck_space = total_disk_space - availabel_disk_space;
           double disk_usage_percentage = (used_disck_space / total_disk_space) * 100;
           sysInfo.setDisk_usage_in_percent(disk_usage_percentage+"");
        }
        return systemInfos;
    }

    public static void main(String[] args) throws Exception {
         new SystemService().pullSystemInfoFromInstances();
    }
}

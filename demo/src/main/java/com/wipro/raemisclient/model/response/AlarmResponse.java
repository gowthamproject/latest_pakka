package com.wipro.raemisclient.model.response;

import com.wipro.raemisclient.common.Constants;
import com.wipro.raemisclient.common.Core5GDetails;

public class AlarmResponse {
    private int id;
    private String node_type = Core5GDetails._5G_CORE;
    private String node_name = Core5GDetails.SYSTEM_NAME;
    private String start_time;
    private String severity;
    private String obj_class;
    private String obj_id;
    private String alarm_identifier;
    private String event_type;
    private String probable_cause;
    private String specific_problem;
    private String add_text;
    private int internal_id;
    private int acknowledge;
    private String alarmStatus  = Constants.ALARM_STATUS[1];

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNode_type() {
        return node_type;
    }

    public void setNode_type(String node_type) {
        this.node_type = node_type;
    }

    public String getNode_name() {
        return node_name;
    }

    public void setNode_name(String node_name) {
        this.node_name = node_name;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getObj_class() {
        return obj_class;
    }

    public void setObj_class(String obj_class) {
        this.obj_class = obj_class;
    }

    public String getObj_id() {
        return obj_id;
    }

    public void setObj_id(String obj_id) {
        this.obj_id = obj_id;
    }

    public String getAlarm_identifier() {
        return alarm_identifier;
    }

    public void setAlarm_identifier(String alarm_identifier) {
        this.alarm_identifier = alarm_identifier;
    }

    public String getEvent_type() {
        return event_type;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }

    public String getProbable_cause() {
        return probable_cause;
    }

    public void setProbable_cause(String probable_cause) {
        this.probable_cause = probable_cause;
    }

    public String getSpecific_problem() {
        return specific_problem;
    }

    public void setSpecific_problem(String specific_problem) {
        this.specific_problem = specific_problem;
    }

    public String getAdd_text() {
        return add_text;
    }

    public void setAdd_text(String add_text) {
        this.add_text = add_text;
    }

    public int getInternal_id() {
        return internal_id;
    }

    public void setInternal_id(int internal_id) {
        this.internal_id = internal_id;
    }

    public int getAcknowledge() {
        return acknowledge;
    }

    public void setAcknowledge(int acknowledge) {
        this.acknowledge = acknowledge;
    }

    public String getAlarmStatus() {
        return alarmStatus;
    }

    public void setAlarmStatus(String alarmStatus) {
        this.alarmStatus = alarmStatus;
    }

    public AlarmResponse() {}

    public AlarmResponse(int id, String node_type, String node_name, String start_time, String severity, String obj_class, String obj_id, String alarm_identifier, String event_type, String probable_cause, String specific_problem, String add_text, int internal_id, int acknowledge, String alarmStatus) {
        this.id = id;
        this.node_type = node_type;
        this.node_name = node_name;
        this.start_time = start_time;
        this.severity = severity;
        this.obj_class = obj_class;
        this.obj_id = obj_id;
        this.alarm_identifier = alarm_identifier;
        this.event_type = event_type;
        this.probable_cause = probable_cause;
        this.specific_problem = specific_problem;
        this.add_text = add_text;
        this.internal_id = internal_id;
        this.acknowledge = acknowledge;
        this.alarmStatus = alarmStatus;
    }
}

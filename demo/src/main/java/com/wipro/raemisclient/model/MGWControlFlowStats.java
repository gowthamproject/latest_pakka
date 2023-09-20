package com.wipro.raemisclient.model;

public class MGWControlFlowStats {

    private String aggr_value;
    private int measurement_period;
    private int egress_octet_count;

    public String getAggr_value() {
        return aggr_value;
    }

    public int getMeasurement_period() {
        return measurement_period;
    }

    public int getEgress_octet_count() {
        return egress_octet_count;
    }

    @Override
    public String toString() {
        return "MGWControlFlowStats{" +
                "aggr_value='" + aggr_value + '\'' +
                ", measurement_period=" + measurement_period +
                ", egress_octet_count=" + egress_octet_count +
                '}';
    }
}

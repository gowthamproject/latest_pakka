package com.wipro.raemisclient.dao;

import com.wipro.raemisclient.common.Constants;
import com.wipro.raemisclient.model.Alarm;
import com.wipro.raemisclient.model.GNB;
import com.wipro.raemisclient.model.Throughput;
import com.wipro.raemisclient.utils.Util;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class ManualPolling {

    private static final long aDay = TimeUnit.DAYS.toMillis(1);
    private static final long now = new Date().getTime();
    private static final long DaysAgo = now - aDay * 2;

    private static LocalDate yourDate = LocalDate.of(2023, Month.SEPTEMBER, 1);
    private static LocalDateTime dateTime = yourDate.atTime(LocalTime.of(1, 00));

    private static LocalDateTime ldt = LocalDateTime.ofInstant(new Date(DaysAgo).toInstant(),
            ZoneId.systemDefault());
    private static LocalDateTime ldt2 = LocalDateTime.ofInstant(new Date().toInstant(),
            ZoneId.systemDefault());

    private static List<Throughput> getRandomDataForThroughputPolling() {

        List<Throughput> tl = new ArrayList<>();
        Iterator<LocalDateTime> ldtl = Util.datesBetween(dateTime, ldt2, 30);
        for (Iterator<LocalDateTime> it = ldtl; it.hasNext(); ) {
            LocalDateTime ld = it.next();
            Timestamp timestamp = Timestamp.valueOf(ld);
           // System.out.println(timestamp.toString());
            tl.add(new Throughput("730451733424971667", null, timestamp, new Random().nextInt(500), (new Random().nextInt(500))));
        }

        /*for (int i = 0; i <= 100; i++) {
            tl.add(new Throughput("230451733424971555", null, Util.between(new Date(DaysAgo), new Date()), new Random().nextInt(500), (new Random().nextInt(500))));
        }*/
        return tl;
    }

    private static List<Alarm> getRandomDataForAlarmPolling() {
        List<Alarm> al = new ArrayList<>();

        Iterator<LocalDateTime> ldtl = Util.datesBetween(dateTime, ldt2, 30);
        int i = 2;
        for (Iterator<LocalDateTime> it = ldtl; it.hasNext(); ) {
            LocalDateTime ld = it.next();
            Timestamp timestamp = Timestamp.valueOf(ld);
            // System.out.println(timestamp.toString());
            al.add(new Alarm(new Random().nextInt(1000), null, null, timestamp.toString(), Constants.SEVIRITY[new Random().nextInt(4)], "gnb", "" + new Random().nextInt(1000), null, "disconnected", "gNB disconnected", null, "The SCTP connection between the gNB and the N2 server has been disconnected.", 18, i % 2 == 0 ? 0 : 1, null));
            i++;
        }

        /*for (int i = 1; i <= 100; i++) {
            al.add(new Alarm(i + 5, Util.between(new Date(DaysAgo), new Date()).toString(), Constants.SEVIRITY[new Random().nextInt(4)], "gnb", "" + i, null, "disconnected", "gNB disconnected", null, "The SCTP connection between the gNB and the N2 server has been disconnected. [00101 1] UERANSIM-gnb-1-1-1", 18, i % 2 == 0 ? 0 : 1));
            System.out.println(new Random().nextInt(4));
        }*/
        return al;
    }

    private static List<GNB> getRandomDataForGNodeBPolling() {
       List<GNB> gnbl = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            gnbl.add(new GNB("RAN-"+Util.randomNumber()+"-gnb", "556677", Util.randomNumber(), 1, Util.getRandomIpAddr(), "Connected"));
        }
        return gnbl;
    }

    public static void main(String[] args) throws SQLException, InterruptedException {

        /*new SubscriberFlowStatsDAO().pollRecords(getRandomDataForThroughputPolling());
        new TACFlowStatsDAO().pollRecords(getRandomDataForThroughputPolling());
        new PDNFlowStatsDAO().pollRecords(getRandomDataForThroughputPolling());*/
        //new AlarmDAO().pollRecords(getRandomDataForAlarmPolling());
        //new AlarmDAO().insertRecord(getRandomDataForGNodeBPolling());
      //  new GNodeBDAO().pollRecords(getRandomDataForGNodeBPolling());
    }
}

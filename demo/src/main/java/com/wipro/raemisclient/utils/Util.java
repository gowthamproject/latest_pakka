package com.wipro.raemisclient.utils;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.wipro.raemisclient.common.Constants;
import com.wipro.raemisclient.model.*;

import java.io.StringReader;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Util {

	public static Object parseJsonStrToObject(String jsonStr, String oper) {

		jsonStr = jsonStr.replace("\"", "'");
		JsonReader reader = new JsonReader(new StringReader(jsonStr.trim()));
		reader.setLenient(true);

		switch (oper) {
		case Constants.ALARM:
			return Arrays.asList(new Gson().fromJson(reader, Alarm[].class));
		case Constants.SUBSCRIBER:
			return Arrays.asList(new Gson().fromJson(reader, Subscriber[].class));
		case Constants.NETDEVICE:
			return Arrays.asList(new Gson().fromJson(reader, NetDevice[].class));
		case Constants.THROUGHPUT:
			return Arrays.asList(new Gson().fromJson(reader, MGWControlFlowStats[].class));
		case Constants.GNB:
			return Arrays.asList(new Gson().fromJson(reader, GNB[].class));
		case Constants.PDU_SESSION:
			return Arrays.asList(new Gson().fromJson(reader, PDUSession[].class));
		}
		return null;
	}

	public static int calculateThroughput(List<MGWControlFlowStats> ctrlFlowStatList) {
		int totat_Throughput = 0;
		for (MGWControlFlowStats flowStats : ctrlFlowStatList) {
			if (flowStats.getMeasurement_period() < 1000)
				continue;
			
			int bit = 8;
			int d= flowStats.getEgress_octet_count();
			int flowOctVal = Math.abs(d *  bit);
			double mes = flowStats.getMeasurement_period() / 1000;
			totat_Throughput += ( flowOctVal / mes)/ 1000;
		}
		return totat_Throughput;
	}

	public static Date between(Date startInclusive, Date endExclusive) {
		long startMillis = startInclusive.getTime();
		long endMillis = endExclusive.getTime();
		long randomMillisSinceEpoch = ThreadLocalRandom.current().nextLong(startMillis, endMillis);
		return new Timestamp(new Date(randomMillisSinceEpoch).getTime());
	}

	public static String getRandomIpAddr() {
		return randomNumber() + "." + randomNumber() + "." + randomNumber() + "." + randomNumber();
	}

	public static int randomNumber() {
		return new Random().nextInt((255 - 1) + 1) + 1;
	}

	public static Iterator<LocalDateTime> datesBetween(LocalDateTime start, LocalDateTime end, int periodInMinutes) {
		return new DatesBetweenIterator(start, end, periodInMinutes);
	}

	private static class DatesBetweenIterator implements Iterator<LocalDateTime> {

		private LocalDateTime nextDate;
		private final LocalDateTime end;
		private final int periodInMinutes;

		private DatesBetweenIterator(LocalDateTime start, LocalDateTime end, int periodInMinutes) {
			this.nextDate = start;
			this.end = end;
			this.periodInMinutes = periodInMinutes;
		}

		@Override
		public boolean hasNext() {
			return nextDate.isBefore(end);
		}

		@Override
		public LocalDateTime next() {
			LocalDateTime toReturn = nextDate;
			nextDate = nextDate.plusMinutes(periodInMinutes);
			return toReturn;
		}
	}

	public static String parseAndGetSqlParam(Map<String, Object> paramMap) {
		if (paramMap.isEmpty())
			return "";
		StringBuffer sb = new StringBuffer(" where ");
		int mapSize = paramMap.size();
		int i = 1;
		for (String param : paramMap.keySet()) {
			Object obj = paramMap.get(param);
			if (obj instanceof String)
				sb.append(param).append("=").append("'" + paramMap.get(param) + "'");
			else if (obj instanceof Integer)
				sb.append(param).append("=").append(paramMap.get(param));
			if (i < mapSize) {
				sb.append(" and ");
			}
			i++;
		}
		return sb.toString();
	}

}

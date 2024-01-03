package com.wipro.raemisclient.utils;

import java.io.StringReader;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.wipro.raemisclient.common.Constants;
import com.wipro.raemisclient.model.response.AlarmResponse;
import com.wipro.raemisclient.model.response.GNodeBResponse;
import com.wipro.raemisclient.model.response.MGWControlFlowStatsResponse;
import com.wipro.raemisclient.model.response.NetDeviceResponse;
import com.wipro.raemisclient.model.response.PDUSessionResponse;
import com.wipro.raemisclient.model.response.SubscriberResponse;

public class Util {

	public static Object parseJsonStrToObject(String jsonStr, String oper) {

		jsonStr = jsonStr.replace("\"", "'");
		JsonReader reader = new JsonReader(new StringReader(jsonStr.trim()));
		reader.setLenient(true);

		switch (oper) {
		case Constants.ALARM:
			return Arrays.asList(new Gson().fromJson(reader, AlarmResponse[].class));
		case Constants.SUBSCRIBER:
			return Arrays.asList(new Gson().fromJson(reader, SubscriberResponse[].class));
		case Constants.NETDEVICE:
			return Arrays.asList(new Gson().fromJson(reader, NetDeviceResponse[].class));
		case Constants.THROUGHPUT:
			return Arrays.asList(new Gson().fromJson(reader, MGWControlFlowStatsResponse[].class));
		case Constants.GNB:
			return Arrays.asList(new Gson().fromJson(reader, GNodeBResponse[].class));
		case Constants.PDU_SESSION:
			return Arrays.asList(new Gson().fromJson(reader, PDUSessionResponse[].class));
		}
		return null;
	}

	public static int calculateThroughput(List<MGWControlFlowStatsResponse> ctrlFlowStatList) {
		int totat_Throughput = 0;
		for (MGWControlFlowStatsResponse flowStats : ctrlFlowStatList) {
			if (flowStats.getMeasurement_period() < 1000)
				continue;

			int bit = 8;
			int d = flowStats.getEgress_octet_count();
			int flowOctVal = Math.abs(d * bit);
			double mes = flowStats.getMeasurement_period() / 1000;
			totat_Throughput += (flowOctVal / mes) / 1000;
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

	public static String convertLongListToString(List<Long> longList) {
		StringBuilder resultBuilder = new StringBuilder();
		for (long value : longList) {
			resultBuilder.append(value).append(",");
		}
		if (resultBuilder.length() > 0) {
			resultBuilder.setLength(resultBuilder.length() - 1);
		}
		return resultBuilder.toString();
	}

}

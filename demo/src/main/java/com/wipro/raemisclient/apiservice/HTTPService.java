package com.wipro.raemisclient.apiservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class HTTPService {

	protected static void POST(final String microserviceUrl, String data) throws IOException {

		try {
			String urlString = microserviceUrl + "?node_id=" + data;
			URL url = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			int responseCode = connection.getResponseCode();
			System.out.println("Response Code: " + responseCode);
			try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
				String inputLine;
				StringBuilder response = new StringBuilder();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				System.out.println("Response: " + response.toString());
			}
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

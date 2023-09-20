package com.wipro.raemisclient.services;

import com.wipro.raemisclient.session.HTTPSessionManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public abstract class RaemisService {

    protected String pullData(final String url) throws IOException {
        URL weburl = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) weburl.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("Cookie", HTTPSessionManager.COOKIE);
        //System.out.println("Output is: " + conn.getResponseCode());
        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
        StringBuilder sb = new StringBuilder();
        String output;
        while ((output = br.readLine()) != null) {
            sb.append(output);
        }
        return sb.toString();
    }

    protected String pullData(final String url, Map<String, Object> params) throws IOException {
        URL weburl = new URL(url);
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), String.valueOf(StandardCharsets.UTF_8)));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), String.valueOf(StandardCharsets.UTF_8)));
        }
        byte[] postDataBytes = postData.toString().getBytes(StandardCharsets.UTF_8);

        HttpURLConnection conn = (HttpURLConnection) weburl.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("Cookie", HTTPSessionManager.COOKIE);
        conn.getOutputStream().write(postDataBytes);
        //System.out.println("Output is: " + conn.getResponseCode());
        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
        StringBuilder sb = new StringBuilder();
        String output;
        while ((output = br.readLine()) != null) {
            sb.append(output);
        }
        return sb.toString();
    }
}

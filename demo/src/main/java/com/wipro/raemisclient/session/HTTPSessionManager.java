package com.wipro.raemisclient.session;

import com.microsoft.sqlserver.jdbc.StringUtils;
import com.wipro.raemisclient.certificate.Certificate;
import com.wipro.raemisclient.common.Constants;

import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.*;


public class HTTPSessionManager {

    public static String COOKIE = StringUtils.EMPTY;

    public static void createSession() {
        try {
            Certificate.doTrustToCertificates();
            URL url = new URL(Constants.SESSION_URL);
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("username", Constants.USERNAME);
            params.put("password", Constants.PASSWORD);

            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String, Object> param : params.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), StandardCharsets.UTF_8));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), StandardCharsets.UTF_8));
            }
            byte[] postDataBytes = postData.toString().getBytes(StandardCharsets.UTF_8);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);

            Map<String, List<String>> headerFields = conn.getHeaderFields();
            Set<String> headerFieldsSet = headerFields.keySet();
            Iterator<String> hearerFieldsIter = headerFieldsSet.iterator();
            while (hearerFieldsIter.hasNext()) {
                String headerFieldKey = hearerFieldsIter.next();
                if ("Set-Cookie".equalsIgnoreCase(headerFieldKey)) {
                    List<String> headerFieldValue = headerFields.get(headerFieldKey);
                    for (String headerValue : headerFieldValue) {
                        //System.out.println("Cookie Found...");
                        String[] fields = headerValue.split(";\\s*");
                        COOKIE = fields[0];
                        System.out.println("cookieValue:" + COOKIE);
                        System.out.println("Raemis Http session created successfully");
                    }
                }
            }
        } catch (NoSuchAlgorithmException e) {
            System.exit(0);
            System.out.println("Exception occur in Certificate:" + e);
        } catch (ConnectException e) {
            System.exit(0);
            System.out.println("Exception occur in HTTPSessionManager.. \n Server(" + Constants.RAEMIS_ENDPOINT + ") is down\n" + e);
        } catch (Exception e) {
            System.exit(0);
            System.out.println("Exception occur while creating the HTTPSession:" + e);
        }
    }
}

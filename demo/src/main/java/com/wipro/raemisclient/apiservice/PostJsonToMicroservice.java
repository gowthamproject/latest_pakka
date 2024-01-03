package com.wipro.raemisclient.apiservice;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class PostJsonToMicroservice {

    public static void main(String[] args) {
        // Specify the URL of the microservice endpoint
        String microserviceUrl = "http://localhost:8080/api/ws/throughput";

        // Specify the JSON data you want to post
       // String jsonData = "{\"key1\":\"value1\",\"key2\":\"value2\"}";
        
        String jsonData = "[\n"
        		+ "  {\n"
        		+ "    \"nodeId\": \"4324242\",\n"
        		+ "    \"updated_time\": \"432423432\",\n"
        		+ "    \"uplink\": 1000,\n"
        		+ "    \"downlink\": 2000\n"
        		+ "  }\n"
        		+ "]";

        try {
            // Create an HttpClient
            HttpClient httpClient = HttpClient.newHttpClient();

            // Create an HttpRequest with POST method and JSON payload
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(microserviceUrl))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonData, StandardCharsets.UTF_8))
                    .build();

            // Send the request and get the response
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Print the response code and body
            System.out.println("Response Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
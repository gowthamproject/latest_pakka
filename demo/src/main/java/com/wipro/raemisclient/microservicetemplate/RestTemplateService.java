package com.wipro.raemisclient.microservicetemplate;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public abstract class RestTemplateService {

	private String microserviceUrl = "http://localhost:8080/api/v1";

	private static final RestTemplate restTemplate = new RestTemplate();

	/**
	 * Post call for invoke from database microservice endpoint
	 * 
	 * @param <T>        RequestBody Class
	 * @param parmName   query paramName
	 * @param qureyValue query value
	 * @param t          RequestBody object
	 * @param cls        Type of return Type .class
	 * @param endPoint   microservice endpoint
	 * @return RequestEntity
	 */
	protected <T> ResponseEntity<?> post(String parmName, String qureyValue, T t, Class<?> cls, String endPoint) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(microserviceUrl + endPoint)
				.queryParam(parmName, qureyValue);
		// Set request headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		// Create HttpEntity with headers and body
		HttpEntity<T> requestEntity = new HttpEntity<>(t, headers);
		// Make the POST request with parameters and body
		ResponseEntity<?> responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, requestEntity,
				cls);
		return responseEntity;
	}

	/**
	 * Delete record from database microservice endpoint
	 * 
	 * @param parmName
	 * @param qureyValue
	 * @param endPoint
	 * @return
	 */
	protected ResponseEntity<?> delete(String parmName, String qureyValue, String endPoint) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(microserviceUrl + endPoint)
				.queryParam(parmName, qureyValue);
		// Set request headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		// Make the POST request with parameters and body
		ResponseEntity<String> responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.DELETE, null,
				String.class);
		return responseEntity;
	}

	/**
	 * 
	 * @param <T>
	 * @param pathVariableValue
	 * @param cls
	 * @param endPoint
	 * @return
	 * @throws JsonProcessingException
	 * @throws JsonMappingException
	 */
	protected ResponseEntity<?> get(String pathVariableValue, Class<?> cls, String endPoint) {
		// Get the data with path variable
		ResponseEntity<?> responseEntityList = restTemplate.getForEntity(microserviceUrl + endPoint + pathVariableValue,
				cls);
		return responseEntityList;
	}

	/**
	 * Update data to database microservice endpoint
	 * 
	 * @param <T>        RequestBody Class
	 * @param parmName   query paramName
	 * @param qureyValue query value
	 * @param t          RequestBody object
	 * @param cls        Type of return Type .class
	 * @param endPoint   microservice endpoint
	 * @return RequestEntity
	 */
	protected <T> ResponseEntity<?> update(String parmName, String qureyValue, T t, Class<?> cls, String endPoint) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(microserviceUrl + endPoint)
				.queryParam(parmName, qureyValue);
		// Set request headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		// Create HttpEntity with headers and body
		HttpEntity<T> requestEntity = new HttpEntity<>(t, headers);
		// Make the POST request with parameters and body
		ResponseEntity<?> responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.PUT, requestEntity,
				cls);
		return responseEntity;
	}

}

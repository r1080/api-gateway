package edu.tlabs.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.tlabs.api.model.Response;

@Service
public class JwtService {

	private static final Logger LOGGER = LoggerFactory.getLogger(JwtService.class);

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private Environment env;

	@Value("${oauth.token.url}")
	private String tokenUrl;

	public String authenticateAndGetJWTtoken() {

		HttpHeaders headers = createRequestHeaders();
		MultiValueMap<String, String> body = createRequestBody();
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(body,headers);

		ResponseEntity<String> responseEntity = restTemplate.postForEntity(tokenUrl, request, String.class);
		String jwt = getJWT(responseEntity.getBody());

		return jwt;
	}

	private HttpHeaders createRequestHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setBasicAuth(env.getProperty("service.username"), env.getProperty("service.password"));
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		return headers;
	}

	private MultiValueMap<String, String> createRequestBody() {
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("username", env.getProperty("authentication.username"));
		body.add("password", env.getProperty("authentication.password"));
		body.add("grant_type", env.getProperty("token.granttype"));
		body.add("scopes", env.getProperty("token.scopes"));
		return body;
	}

	private String getJWT(String body) {
		Response response = null;
		try {
			response = mapper.readValue(body, Response.class);
		} catch (JsonProcessingException e) {
			LOGGER.error("Error parsing response {}", e.getMessage());
		}
		return response.getAccessToken();
	}

}

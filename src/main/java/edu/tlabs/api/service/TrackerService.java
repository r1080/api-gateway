package edu.tlabs.api.service;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import edu.tlabs.api.model.Tasks;

@Service
public class TrackerService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TrackerService.class);
	
	@Value("${tracker.service}")
	private String trackerServiceUrl;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@PostConstruct
	public void init(){
		LOGGER.info("Construction of Tracker Service Completed");
	}
	
	@HystrixCommand(fallbackMethod = "getAllTasksFallback",commandProperties = {
			@HystrixProperty(name ="execution.isolation.thread.timeoutInMilliseconds", value = "5000")
	})
	public void getAllTasks() {
		
		String url = trackerServiceUrl.concat("tasks");
		
		LOGGER.info("Calling Tracker Service Url -> " + url);
		
		ResponseEntity<Tasks> responseEntity = restTemplate.getForEntity(url, Tasks.class);
		if(responseEntity.getStatusCode().equals(HttpStatus.OK) ){
			LOGGER.info("Successful Hit With Status Code 200");
			Tasks tasks = responseEntity.getBody();
			System.out.println("Tasks ::::::: =====>>>>" + tasks);
		}
		
	}
	
	public void getAllTasksFallback(){
		LOGGER.error(":::::::::> Fallback Task Service Get All Tasks Called!!! <:::::::::");
		//Cache to return previous values
	}
}

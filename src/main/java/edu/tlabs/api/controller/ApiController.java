package edu.tlabs.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.tlabs.api.model.User;
import edu.tlabs.api.service.JwtService;
import edu.tlabs.api.service.TrackerService;

@RestController
public class ApiController {
	
	@Autowired
	private TrackerService trackerService;
	
	@Autowired
	private JwtService jwtService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ApiController.class);
			
	@PostMapping("/tasks/search")
	public String getAllTasks(@RequestBody User user){
				
		LOGGER.info("Retrieve all tasks after Auth with User " + user.getUserName());
		
		String jwt = jwtService.getJwt(user);
		
		System.out.println("JWT " + jwt);
		
		return "HIT";
	}
	
	

}

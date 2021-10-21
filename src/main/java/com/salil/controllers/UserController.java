package com.salil.controllers;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.salil.service.AggregateService;
import com.salil.service.UserService;

@RestController
public class UserController {

	@Resource
	UserService userService;

	@Resource
	AggregateService aggregateService;

	@RequestMapping(value = "/userDetails", method = RequestMethod.GET)
	public List getAllUserDetails() {

		return userService.getAllUserDetails();
	}

	@RequestMapping(value = "/userDetails/{userId}", method = RequestMethod.GET)
	public List getUserDetails(@PathVariable("userId") String userId) {

		return userService.getUserDetails(userId);
	}

	@RequestMapping(value = "/userDetailsBankDetails/{userId}", method = RequestMethod.GET)
	public Map getUserDetailsBankDetails(@PathVariable("userId") String userId) {

		return aggregateService.getAggregateDetails(userId);
	}
	
	@RequestMapping(value = "/getUserBankAndEmploymentDetails/{userId}", method = RequestMethod.GET)
	public Map getUserBankAndEmploymentDetails(@PathVariable("userId") String userId) {

		return aggregateService.getUserBankAndEmploymentDetails(userId);
	}

}
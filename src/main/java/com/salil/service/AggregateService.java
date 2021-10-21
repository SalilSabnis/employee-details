package com.salil.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AggregateService {

	@Autowired
	@Qualifier("snowflakeJdbctemplate")
	protected NamedParameterJdbcTemplate namedJdbcTemplateSnowflake;

	@Autowired
	RestTemplate restTemplate;


	public Map getUserBankAndEmploymentDetails(String userId) {

		String query = "select * from user_details where user_pan_no = '" + userId + "'";
		MapSqlParameterSource params = null;

		List<Map<String, Object>> userResults = namedJdbcTemplateSnowflake.queryForList(query, params);

		List<Map<String, Object>> bankResults = getBankResultsForUser(userId);
		List<Map<String, Object>> employerResults = getEmployerResultsForUser(userId);

		Map<String, Object> aggregatedData = new HashMap<>();
		aggregatedData.putAll(userResults.get(0));
		aggregatedData.putAll(bankResults.get(0));
		for(int i=0;i<employerResults.size();i++)
		aggregatedData.putAll(employerResults.get(i));

		System.out.println("aggregatedData Results" + bankResults);
		return aggregatedData;

	}
	
	private List<Map<String, Object>> getBankResultsForUser(String userId) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		return restTemplate
				.exchange("http://localhost:8090/userBankDetails/" + userId, HttpMethod.GET, entity, List.class)
				.getBody();
	}
	
	private List<Map<String, Object>> getEmployerResultsForUser(String userId) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		return restTemplate
				.exchange("http://localhost:8098/employeeEmployerDetails/" + userId, HttpMethod.GET, entity, List.class)
				.getBody();
	}

	
	
	
	
	
	public Map getAggregateDetails(String userId) {

		String query = "select * from user_details where user_pan_no = '" + userId + "'";
		MapSqlParameterSource params = null;

		List<Map<String, Object>> userResults = namedJdbcTemplateSnowflake.queryForList(query, params);

		List<Map<String, Object>> bankResults = getBankResultsForUser(userId);

		Map<String, Object> aggregatedData = new HashMap<>();
		aggregatedData.putAll(userResults.get(0));
		aggregatedData.putAll(bankResults.get(0));

		System.out.println("aggregatedData Results" + bankResults);
		return aggregatedData;
	}
}

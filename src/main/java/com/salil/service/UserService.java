package com.salil.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.salil.model.User;

@Component
public class UserService {
	 
	@Autowired
	@Qualifier("snowflakeJdbctemplate")
	protected NamedParameterJdbcTemplate namedJdbcTemplateSnowflake;

	public List getAllUserDetails() {
		
		String query = "select * from user_details";
		MapSqlParameterSource params = null;
		
		List<Map<String, Object>> results = namedJdbcTemplateSnowflake.queryForList(query, params);
		
		return results;
	}
	
public List getUserDetails(String userId) {
		
		String query = "select * from user_details where user_pan_no = '"+userId+"'";
		MapSqlParameterSource params = null;
		
		List<Map<String, Object>> results = namedJdbcTemplateSnowflake.queryForList(query, params);
		
		return results;
	}
	
	
}

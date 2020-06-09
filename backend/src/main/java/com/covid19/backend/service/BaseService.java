package com.covid19.backend.service;


import com.covid19.backend.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaseService {

	@Autowired
	public UserMapper userMapper;

}

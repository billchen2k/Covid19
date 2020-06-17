package com.covid19.backend.dao;

import com.covid19.backend.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

// MyBatis
@Mapper
@Component
public interface UserMapper {

	@Select("SELECT * from user where user_id=#{user_id}")
	public User selectUserByID(@Param("user_id") long user_id);

	@Select("SELECT * from user where username=#{username}")
	public User selectUserByUsername(@Param("username") String username);

	@Select("SELECT * from user where email=#{email}")
	public User selectUserByEmail(String email);

	@Insert("insert into user(" +
			"username, " +
			"email, " +
			"password, " +
			"registration_ip, " +
			"registration_date, " +
			"password_change_date, " +
			"role, " +
			"gender)" +
			" values (" +
			"#{username}, " +
			"#{email}, " +
			"#{password}, " +
			"#{registration_ip}, " +
			"CURRENT_TIMESTAMP(), " +
			"CURRENT_TIMESTAMP(), " +
			"#{role}, " +
			"#{gender})")
	@SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "user_id", before = false, resultType = long.class)
	long insertUser(User user);

	@Update("Update user set " +
			"username=#{username}, " +
			"email=#{email}, " +
			"password=#{password}, " +
			"password_change_date=#{password_change_date, jdbcType = VARCHAR}, " +
			"role=#{role}, " +
			"gender=#{gender}" +
			" where user_id=#{user_id}")
	void updateUserInfo(User user);
}

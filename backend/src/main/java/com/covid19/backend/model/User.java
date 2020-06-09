package com.covid19.backend.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;


//swagger
@ApiModel("User 用户实体")
@Data

@NoArgsConstructor

@AllArgsConstructor


@Setter
@Getter
@Accessors(chain = true)
@Entity(name = "user")
public class User {
	@ApiModelProperty("用户 ID")
	@Id
	private long user_id;

	@ApiModelProperty("用户名")
	private String username;

	@ApiModelProperty("邮箱")
	private String email;

	@ApiModelProperty("密码")
	private String password;

	@ApiModelProperty("注册时间")
	private String registration_ip;

	@ApiModelProperty("注册时间")
	private String registration_date;

	@ApiModelProperty("密码修改日期")
	private String password_change_date;

	@ApiModelProperty("用户角色")
	private String role;

	@ApiModelProperty("性别")
	private String gender;
}

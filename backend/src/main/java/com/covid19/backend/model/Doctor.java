package com.covid19.backend.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

//swagger
@ApiModel("Doctor 医生实体")
@Data

@NoArgsConstructor

@AllArgsConstructor


@Setter
@Getter
@Accessors(chain = true)
@Entity(name = "doctor")
public class Doctor {
    @ApiModelProperty("医生 ID")
    @Id
    private long doctor_id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("性别")
    private String gender;

    @ApiModelProperty("生日")
    private String birthday;

    @ApiModelProperty("部门")
    private String department;

    @ApiModelProperty("所在医院")
    private String hospital_id;
}
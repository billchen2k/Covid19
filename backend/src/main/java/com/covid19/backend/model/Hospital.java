package com.covid19.backend.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

//swagger
@ApiModel("Hospital 医院实体")
@Data

@NoArgsConstructor

@AllArgsConstructor


@Setter
@Getter
@Accessors(chain = true)
@Entity(name = "hospital")
public class Hospital {
    @ApiModelProperty("医院 ID")
    @Id
    private long hospital_id;

    @ApiModelProperty("医院名称")
    private String name;

    @ApiModelProperty("医院地址")
    private String address;
}
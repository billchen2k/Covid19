package com.covid19.backend.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

//swagger
@ApiModel("Patient 病人实体")
@Data

@NoArgsConstructor

@AllArgsConstructor


@Setter
@Getter
@Accessors(chain = true)
@Entity(name = "patient")
@Table(name = "patient")
public class Patient implements Serializable {
    @ApiModelProperty("患者 ID")
    @Id
    private long patient_id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("性别")
    private String gender;

    @ApiModelProperty("生日")
    private String birthday;

    @ApiModelProperty("发病地点")
    private String onset_place;

    @ApiModelProperty("发病时间")
    private String onset_date;

    @ApiModelProperty("确诊时间")
    private String confirm_date;

    @ApiModelProperty("住院状态")
    private String status;

    @ApiModelProperty("主治大夫")
    private String doctor_id;

    @ApiModelProperty("治疗医院 ID")
    private String hospital_id;
//    @ManyToOne(fetch = FetchType.LAZY, optional = true)
//    @JoinColumn(name = "hospital_id", nullable = true)
//    private Hospital hospital_id;

    @ApiModelProperty("是否为医生")
    private String is_doctor;

}
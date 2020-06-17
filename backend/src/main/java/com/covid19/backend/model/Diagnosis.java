package com.covid19.backend.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

//swagger
@ApiModel("Diagnosis 诊断实体")
@Data

@NoArgsConstructor

@AllArgsConstructor


@Setter
@Getter
@Accessors(chain = true)
@Entity(name = "diagnosis")
public class Diagnosis {
    @ApiModelProperty("诊断 ID")
    @Id
    private long diagnosis_id;

    @ApiModelProperty("病人 ID")
    private String patient_id;

    @ApiModelProperty("医生 ID")
    private String doctor_id;

    @ApiModelProperty("诊断时间")
    private String time;

    @ApiModelProperty("症状")
    private String symptom;

    @ApiModelProperty("体温")
    private String temperature;

    @ApiModelProperty("核酸检测结果")
    private String nucleic_acid;
}
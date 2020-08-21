package com.covid19.backend.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

//swagger
@ApiModel("Prescription 用药实体")
@Data

@NoArgsConstructor

@AllArgsConstructor


@Setter
@Getter
@Accessors(chain = true)
@Entity(name = "prescription")
public class Prescription {
    @ApiModelProperty("用药 ID")
    @Id
    private long prescription_id;

    @ApiModelProperty("病人 ID")
    private String patient_id;

    @ApiModelProperty("药品 ID")
    private String medicine_id;

    @ApiModelProperty("剂量")
    private String dosage;

    @ApiModelProperty("药物使用方法")
    private String usage;

    @ApiModelProperty("医生")
    private String doctor_id;
}
package com.covid19.backend.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

//swagger
@ApiModel("Image 肺部影像实体")
@Data

@NoArgsConstructor

@AllArgsConstructor


@Setter
@Getter
@Accessors(chain = true)
@Entity(name = "image")
public class Image {
    @ApiModelProperty("肺部影像 ID")
    @Id
    private long image_id;

    @ApiModelProperty("病人 ID")
    private String patient_id;

    @ApiModelProperty("扫描医生 ID")
    private String scanning_doctor_id;

    @ApiModelProperty("肺部影像图片")
    private String image_picture;

    @ApiModelProperty("结论")
    private String conclusion;

    @ApiModelProperty("结论医生")
    private String conclusion_doctor_id;
}
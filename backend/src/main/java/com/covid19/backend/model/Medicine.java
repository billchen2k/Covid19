package com.covid19.backend.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

//swagger
@ApiModel("Medicine 药品实体")
@Data

@NoArgsConstructor

@AllArgsConstructor


@Setter
@Getter
@Accessors(chain = true)
@Entity(name = "medicine")
public class Medicine {
    @ApiModelProperty("药品 ID")
    @Id
    private long medicine_id;

    @ApiModelProperty("名称")
    private String medicine_name;

    @ApiModelProperty("厂商")
    private String manufacturer;

    @ApiModelProperty("药品详细介绍")
    private String introduction;

    @ApiModelProperty("类型")
    private String type;
}
package com.github.ontio.dataset.controller.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class DataVo {
    @ApiModelProperty(name="id",value = "id")
    private String id;
    @ApiModelProperty(name="data",value = "data")
    private List<String> data;
    @ApiModelProperty(name="price",value = "price")
    private String price;
    @ApiModelProperty(name="coin",value = "coin")
    private String coin;
}

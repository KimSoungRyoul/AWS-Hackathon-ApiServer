package org.amathon.ksr.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "starDTO", description = "별점주기 DTO")
public class StarDTO {

  @ApiModelProperty(value = "맛집 고유번호", example = "2907655", required = true)
  private long serialNum;

  @ApiModelProperty(value = "맛 평점", example = "5.0")
  private double tasteRating;

  @ApiModelProperty(value = "인테리어 평점", example = "0.3")
  private double interiorRating;

  @ApiModelProperty(value = "가격 평점", example = "3.4")
  private double priceRating;


}

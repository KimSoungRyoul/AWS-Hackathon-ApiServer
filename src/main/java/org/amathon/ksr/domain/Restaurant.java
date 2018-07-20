package org.amathon.ksr.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import lombok.Data;
import org.amathon.ksr.domain.vo.RestaurantCategory;
import org.amathon.ksr.domain.vo.RestaurantRating;

@Entity
@Data
@ApiModel(value = "restaurant", description = "맛집정보")
public class Restaurant {


  @Id
  @ApiModelProperty(value = "serialNum", example = "543643")
  private long serialNum;

  @ApiModelProperty(value = "맛집 상호명", example = "싱싱청과물")
  private String name = "";

  @ApiModelProperty(value = "latitude", example = "37.4923661")
  private String lat = "";

  @ApiModelProperty(value = "longitude", example = "127.0205431")
  @Column(nullable = true)
  private String lon = "";

  @Enumerated(EnumType.STRING)
  private RestaurantCategory category;

  @Embedded
  @ApiModelProperty(value = "평점 목록")
  private RestaurantRating rating;

  @ApiModelProperty(value = "맛집전화번호", example = "010-1234-1234")
  private String phoneNum = "";

  @ApiModelProperty(value = "주소", example = "서울 서초구서초동 서초대로73길")
  private String address = "";

  @ApiModelProperty(value = "음식점분류법(하)", example = "전문음식점")
  private String lowBizName = "";

  @ApiModelProperty(value = "음식점분류법(상세)", example = "스파게티 전문")
  private String detailBizName = "";

  @ApiModelProperty(value = "맛집 상세 소개", example = "이러쿵 저러쿵 맛있는 곳임 .....")
  @Lob
  private String description = "";


}

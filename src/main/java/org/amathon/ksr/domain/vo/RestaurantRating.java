package org.amathon.ksr.domain.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Data;


@Embeddable
@Data
public class RestaurantRating {

  @Column(nullable = true)
  private double tasteRating = 0;

  @Column(nullable = true)
  private double interiorRating = 0;

  @Column(nullable = true)
  private double priceRating = 0;

  @Column(nullable = true)
  private double totalRating;

  @Column(nullable = true)
  private int evaluaterCnt;

  public RestaurantRating() {
    totalRating = (tasteRating + interiorRating + priceRating) / 3;
    evaluaterCnt = 0;
  }
}

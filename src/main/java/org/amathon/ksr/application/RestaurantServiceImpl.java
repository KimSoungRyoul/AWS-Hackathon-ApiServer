package org.amathon.ksr.application;

import java.util.List;
import lombok.AllArgsConstructor;
import org.amathon.ksr.api.dto.StarDTO;
import org.amathon.ksr.domain.Restaurant;
import org.amathon.ksr.domain.repository.RestaurantRepository;
import org.amathon.ksr.domain.vo.RestaurantRating;
import org.amathon.ksr.infrastructure.remote.PoiRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RestaurantServiceImpl implements RestaurantService {

  private RestaurantRepository restaurantRepository;

  private PoiRestTemplate poiRestTemplate;

  @Override
  @Transactional
  public void evaluateRestaurant(StarDTO starDTO) {

    Restaurant restaurant = restaurantRepository.findOne(starDTO.getSerialNum());

    if (restaurant == null) {
      restaurant = new Restaurant();
      restaurant.setSerialNum(starDTO.getSerialNum());
      RestaurantRating restaurantRating = new RestaurantRating();
      restaurantRating.setEvaluaterCnt(1);
      restaurantRating.setTasteRating(starDTO.getTasteRating());
      restaurantRating.setPriceRating(starDTO.getPriceRating());
      restaurantRating.setInteriorRating(starDTO.getInteriorRating());

      restaurant.setRating(restaurantRating);

    } else {

      RestaurantRating rating = restaurant.getRating();

      double interiorRating =
          (rating.getInteriorRating() * rating.getEvaluaterCnt() + starDTO.getInteriorRating())
              / (rating.getEvaluaterCnt() + 1);
      double priceRating =
          (rating.getPriceRating() * rating.getEvaluaterCnt() + starDTO.getPriceRating())
              / (rating.getEvaluaterCnt() + 1);
      double tasteRating =
          (rating.getTasteRating() * rating.getEvaluaterCnt() + starDTO.getTasteRating())
              / (rating.getEvaluaterCnt() + 1);

      rating.setInteriorRating(interiorRating);
      rating.setPriceRating(priceRating);
      rating.setTasteRating(tasteRating);
      rating.setEvaluaterCnt(rating.getEvaluaterCnt() + 1);

      rating.setTotalRating(
          (rating.getInteriorRating() + rating.getPriceRating() + rating.getTasteRating()) / 3);
      restaurant.setRating(rating);
    }
    restaurantRepository.save(restaurant);
  }


  @Override
  public List<Restaurant> searchRestaurants(String lat, String lon, String searchKeyword,
      String orderby) {

    /*List<Restaurant> restaurants = poiRestTemplate.searchRestaurants(lat, lon, searchKeyword);

    for (Restaurant restaurant : restaurants) {

      if (!restaurantRepository.exists(restaurant.getSerialNum())) {
        restaurantRepository.save(restaurant);
      }

    }*/

    List<Restaurant> restaurantList = restaurantRepository
        .findALLOrderByDistance(lat, lon, searchKeyword);

    return restaurantList;
  }
}

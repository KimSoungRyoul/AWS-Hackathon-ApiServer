package org.amathon.ksr.application;

import java.util.List;
import org.amathon.ksr.domain.Restaurant;
import org.amathon.ksr.domain.repository.RestaurantRepository;
import org.amathon.ksr.domain.vo.RestaurantRating;
import org.amathon.ksr.infrastructure.remote.PoiRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("product")
public class InitService {

  @Autowired
  private PoiRestTemplate poiRestTemplate;

  @Autowired
  private RestaurantRepository restaurantRepository;

  //@PostConstruct
  public void init() {

    String lat = "37.4923661";
    String lon = "127.0205431";
    String searchKeyword = "음식점";

    RestaurantRating restaurantRating = new RestaurantRating();
    restaurantRating.setEvaluaterCnt(0);
    restaurantRating.setInteriorRating(0);
    restaurantRating.setPriceRating(0);
    restaurantRating.setTasteRating(0);
    restaurantRating.setTotalRating(0);

    List<Restaurant> restaurants = poiRestTemplate.searchRestaurants(lat, lon, searchKeyword);

    for (Restaurant restaurant : restaurants) {

      if (!restaurantRepository.exists(restaurant.getSerialNum())) {
        restaurant.setRating(restaurantRating);
        restaurantRepository.save(restaurant);
      }
    }

    searchKeyword = "한식";

    restaurants = poiRestTemplate.searchRestaurants(lat, lon, searchKeyword);

    for (Restaurant restaurant : restaurants) {

      if (!restaurantRepository.exists(restaurant.getSerialNum())) {
        restaurant.setRating(restaurantRating);
        restaurantRepository.save(restaurant);
      }
    }

    searchKeyword = "중식";

    restaurants = poiRestTemplate.searchRestaurants(lat, lon, searchKeyword);

    for (Restaurant restaurant : restaurants) {

      if (!restaurantRepository.exists(restaurant.getSerialNum())) {
        restaurant.setRating(restaurantRating);
        restaurantRepository.save(restaurant);
      }
    }

    searchKeyword = "양식";

    restaurants = poiRestTemplate.searchRestaurants(lat, lon, searchKeyword);

    for (Restaurant restaurant : restaurants) {

      if (!restaurantRepository.exists(restaurant.getSerialNum())) {
        restaurant.setRating(restaurantRating);
        restaurantRepository.save(restaurant);
      }
    }

    searchKeyword = "피자";

    restaurants = poiRestTemplate.searchRestaurants(lat, lon, searchKeyword);

    for (Restaurant restaurant : restaurants) {

      if (!restaurantRepository.exists(restaurant.getSerialNum())) {
        restaurant.setRating(restaurantRating);
        restaurantRepository.save(restaurant);
      }
    }

    searchKeyword = "치킨";

    restaurants = poiRestTemplate.searchRestaurants(lat, lon, searchKeyword);

    for (Restaurant restaurant : restaurants) {

      if (!restaurantRepository.exists(restaurant.getSerialNum())) {
        restaurant.setRating(restaurantRating);
        restaurantRepository.save(restaurant);
      }
    }

    searchKeyword = "디저트";

    restaurants = poiRestTemplate.searchRestaurants(lat, lon, searchKeyword);

    for (Restaurant restaurant : restaurants) {

      if (!restaurantRepository.exists(restaurant.getSerialNum())) {
        restaurant.setRating(restaurantRating);
        restaurantRepository.save(restaurant);
      }
    }

    searchKeyword = "야식";

    restaurants = poiRestTemplate.searchRestaurants(lat, lon, searchKeyword);

    for (Restaurant restaurant : restaurants) {

      if (!restaurantRepository.exists(restaurant.getSerialNum())) {
        restaurant.setRating(restaurantRating);
        restaurantRepository.save(restaurant);
      }
    }

    searchKeyword = "족발";

    restaurants = poiRestTemplate.searchRestaurants(lat, lon, searchKeyword);

    for (Restaurant restaurant : restaurants) {

      if (!restaurantRepository.exists(restaurant.getSerialNum())) {
        restaurant.setRating(restaurantRating);
        restaurantRepository.save(restaurant);
      }
    }

    searchKeyword = "보쌈";

    restaurants = poiRestTemplate.searchRestaurants(lat, lon, searchKeyword);

    for (Restaurant restaurant : restaurants) {

      if (!restaurantRepository.exists(restaurant.getSerialNum())) {
        restaurant.setRating(restaurantRating);
        restaurantRepository.save(restaurant);
      }
    }

    searchKeyword = "패스트푸드";

    restaurants = poiRestTemplate.searchRestaurants(lat, lon, searchKeyword);

    for (Restaurant restaurant : restaurants) {

      if (!restaurantRepository.exists(restaurant.getSerialNum())) {
        restaurant.setRating(restaurantRating);
        restaurantRepository.save(restaurant);
      }
    }
  }
}

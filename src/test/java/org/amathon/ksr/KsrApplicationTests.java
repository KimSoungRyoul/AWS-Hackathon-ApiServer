package org.amathon.ksr;

import lombok.extern.log4j.Log4j;
import org.amathon.ksr.application.RestaurantService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
@Log4j
public class KsrApplicationTests {

  @Autowired
  private RestaurantService restaurantService;

  @Test
  public void contextLoads() {

    String lat = "37.4923661";
    String lon = "127.0205431";
    String searchKeyword = "음식점";

    //List<Restaurant> restaurants = poiRestTemplate.searchRestaurants(lat, lon, searchKeyword);

    restaurantService.searchRestaurants(lat, lon, searchKeyword, "distance");

    //log.info(restaurants.toString());

  }
}

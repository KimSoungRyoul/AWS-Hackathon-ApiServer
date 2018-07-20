package org.amathon.ksr;

import java.util.List;
import lombok.extern.log4j.Log4j;
import org.amathon.ksr.domain.Restaurant;
import org.amathon.ksr.infrastructure.remote.PoiRestTemplate;
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
  private PoiRestTemplate poiRestTemplate;

  @Test
  public void contextLoads() {

    String lat = "37.4923661";
    String lon = "127.0205431";
    String searchKeyword = "일식";

    List<Restaurant> restaurants = poiRestTemplate.searchRestaurants(lat, lon, searchKeyword);

    log.info(restaurants.toString());

  }
}

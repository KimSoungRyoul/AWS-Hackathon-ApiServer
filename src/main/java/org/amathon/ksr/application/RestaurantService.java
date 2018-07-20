package org.amathon.ksr.application;

import java.util.List;
import org.amathon.ksr.api.dto.StarDTO;
import org.amathon.ksr.domain.Restaurant;

public interface RestaurantService {

  void evaluateRestaurant(StarDTO starDTO);

  List<Restaurant> searchRestaurants(String lat, String lon, String searchKeyword, String orderby);

}

package org.amathon.ksr.api;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.AllArgsConstructor;
import org.amathon.ksr.api.dto.StarDTO;
import org.amathon.ksr.application.RestaurantService;
import org.amathon.ksr.domain.Restaurant;
import org.amathon.ksr.infrastructure.remote.PoiRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurants")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RestaurantApiController {

  private PoiRestTemplate poiRestTemplate;

  private RestaurantService restaurantService;

  @ApiOperation(value = "위치기반 주변 음식점 검색", produces = "application/json")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "lat", required = true, example = "37.4923661", value = "좌표정보"),
      @ApiImplicitParam(name = "lon", required = true, example = "127.0205431", value = "좌표정보"),
      @ApiImplicitParam(name = "searchKeyword", required = true, example = "일식", value = "검색카테고리"),
      @ApiImplicitParam(
          name = "orderby",
          required = true,
          allowableValues = "star,distance",
          value = "별점순,거리순",
          defaultValue = "distance"
      )
  })
  @GetMapping
  public ResponseEntity<List<Restaurant>> getRestaurantList(
      @RequestParam("lat") String lat,
      @RequestParam("lon") String lon,
      @RequestParam("searchKeyword") String searchKeyword,
      @RequestParam("orderby") String orderby) {

    ResponseEntity<List<Restaurant>> entity = null;

    List<Restaurant> restaurants = restaurantService
        .searchRestaurants(lat, lon, searchKeyword, orderby);

    System.out.println("--------------------------------" + searchKeyword);
    entity = new ResponseEntity(restaurants, HttpStatus.OK);

    return entity;
  }

  @ApiOperation(value = "별점 주기", produces = "application/json")
  @PostMapping
  public ResponseEntity postStarRating(@RequestBody StarDTO starDTO) {

    ResponseEntity entity = null;

    try {

      restaurantService.evaluateRestaurant(starDTO);

    } catch (Exception e) {
      e.printStackTrace();
    }

    entity = new ResponseEntity(HttpStatus.CREATED);

    return entity;
  }
}

package org.amathon.ksr.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.amathon.ksr.api.dto.StarDTO;
import org.amathon.ksr.application.RestaurantService;
import org.amathon.ksr.domain.Restaurant;
import org.amathon.ksr.domain.repository.RestaurantRepository;
import org.amathon.ksr.infrastructure.remote.PoiRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurants")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "맛집API", description = "맛집 검색및 별점등록...")
public class RestaurantApiController {

  private PoiRestTemplate poiRestTemplate;

  private RestaurantService restaurantService;

  private RestaurantRepository repository;

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
  public ResponseEntity<Map<String, Object>> getRestaurantList(
      @RequestParam("lat") String lat,
      @RequestParam("lon") String lon,
      @RequestParam("searchKeyword") String searchKeyword,
      @RequestParam("orderby") String orderby) {

    ResponseEntity<Map<String, Object>> entity = null;

    try {

      List<Restaurant> restaurants =
          restaurantService.searchRestaurants(lat, lon, searchKeyword, orderby);

      System.out.println("--------------------------------" + searchKeyword);

      Map<String, Object> result = new HashMap();
      result.put("message", "SUCCESS");
      result.put("restaurants", restaurants);

      entity = new ResponseEntity(result, HttpStatus.OK);

    } catch (Exception e) {
      Map<String, Object> result = new HashMap();
      result.put("message", "FAIL");

      entity = new ResponseEntity(result, HttpStatus.INTERNAL_SERVER_ERROR);
      e.printStackTrace();
    }
    return entity;
  }

  @ApiOperation(value = "Model 등록용 사용 X")
  @GetMapping("/unUse")
  public ResponseEntity<Restaurant> asdf() {

    return new ResponseEntity<>(new Restaurant(), HttpStatus.OK);
  }

  @ApiOperation(value = "별점 주기", produces = "application/json")
  @PostMapping
  public ResponseEntity postStarRating(@RequestBody StarDTO starDTO) {

    ResponseEntity entity = null;

    try {

      Map<String, Object> result = new HashMap();
      result.put("message", "SUCCESS");

      restaurantService.evaluateRestaurant(starDTO);

      entity = new ResponseEntity(result, HttpStatus.CREATED);

    } catch (Exception e) {
      e.printStackTrace();
      Map<String, Object> result = new HashMap();
      result.put("message", "FAIL");
      entity = new ResponseEntity(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return entity;
  }

  @ApiOperation("카테고리별 맛집조회")

  @GetMapping("/{category}")
  public ResponseEntity<Map<String, Object>> asdf(
      @PathVariable("category")
      @ApiParam(
          value = "category",
          required = true,

          allowableValues = "한식,양식,중식,족발,보쌈,패스트푸드,야식,디저트,치킨,피자"
      )
          String category) {

    ResponseEntity<Map<String, Object>> entity = null;

    Map<String, Object> map = new HashMap();

    List<Restaurant> restaurants =
        repository.findALLOrderByDistance("37.4923661", "127.0205431", category);

    map.put("restaurants", restaurants);
    map.put("messages", "SUCCESS");

    entity = new ResponseEntity<>(map, HttpStatus.OK);

    return entity;
  }
}

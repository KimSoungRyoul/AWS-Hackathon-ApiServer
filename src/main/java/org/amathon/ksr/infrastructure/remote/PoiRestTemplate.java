package org.amathon.ksr.infrastructure.remote;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.log4j.Log4j;
import org.amathon.ksr.domain.Restaurant;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@Log4j
public class PoiRestTemplate {

  private static final String version = "1";

  private static final String appKey = "3a1a26a7-4b40-4b1d-a611-22b12de3c29f";

  private final String radius = "0";

  private final String pageNum = "1";

  //private String searchKeyword="일식";

  public List<Restaurant> searchRestaurants(String lat, String lon, String searchKeyword) {

    List<Restaurant> restaurantList = new ArrayList();
    try {
      RestTemplate restTemplate = new RestTemplate();

      //    String utf8String = new String(searchKeyword.getBytes("UTF-8"));

      URI uri =
          UriComponentsBuilder.newInstance()
              .scheme("https")
              .host("api2.sktelecom.com")
              .path("/tmap/pois")
              .queryParam("version", version)
              .queryParam("centerLat", lat)
              .queryParam("centerLon", lon)
              .queryParam("radius", radius)
              .queryParam("searchKeyword", searchKeyword)
              .queryParam("page", pageNum)
              .queryParam("count", "20")
              .build()
              .encode("UTF-8")
              .toUri();

      MultiValueMap<String, String> headers = new LinkedMultiValueMap();

      headers.add("content-type", "application/json");
      headers.add("appKey", appKey);

      HttpEntity<Object> requestEntity = new HttpEntity(headers);

      //String url=encodeURIComponent().encode(uri.toString(),"UTF-8");

      log.info(uri.toString() + "------------------------------------------");

      ResponseEntity<String> responseEntity =
          restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);

      JSONObject rootJsonObj = new JSONObject(responseEntity.getBody())
          .getJSONObject("searchPoiInfo");
      log.info(rootJsonObj.toString());

      JSONArray jsonArray = rootJsonObj.getJSONObject("pois").getJSONArray("poi");

      for (int i = 0; i < jsonArray.length(); i++) {
        Restaurant restaurant = new Restaurant();
        JSONObject jObj = jsonArray.getJSONObject(i);
        restaurant.setSerialNum(jObj.getLong("id"));
        restaurant.setName(jObj.getString("name"));
        restaurant.setLat(jObj.getString("frontLat"));
        restaurant.setLon(jObj.getString("frontLon"));

        restaurant.setAddress(
            new StringBuilder()
                .append(jObj.getString("upperAddrName")).append(" ")
                .append(jObj.getString("middleAddrName")).append(" ")
                .append(jObj.getString("lowerAddrName")).append(" ")
                .append(jObj.getString("detailAddrName")).append(" ")
                .append(jObj.getString("roadName"))
                .toString());

        restaurant.setDescription(jObj.getString("desc"));

        restaurant.setLowBizName(jObj.getString("lowerBizName"));
        restaurant.setDetailBizName(jObj.getString("detailBizName"));
        restaurant.setPhoneNum(jObj.getString("telNo"));
        restaurantList.add(restaurant);
      }

    } catch (Exception e) {
      e.printStackTrace();
      log.error("UTF -8 없음 문제 발생 POIRestTemplate.................");
    }

    return restaurantList;
  }

}

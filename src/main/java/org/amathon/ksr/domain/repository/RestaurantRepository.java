package org.amathon.ksr.domain.repository;

import java.util.List;
import org.amathon.ksr.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

  @Query(
      value =
          "SELECT * ,\n"
              + "\t(6371*acos(cos(radians(:nowLat))*cos(radians(lat))*cos(radians(lon)\n"
              + "\t-radians(:nowLon))+sin(radians(:nowLat))*sin(radians(lat))))\n"
              + "\tAS distance\n"
              + "\n"
              + "FROM amathon_db.restaurant  \n"
              + "where detail_biz_name like CONCAT('%',:searchKeyword,'%') or low_biz_name like CONCAT('%',:searchKeyword,'%') or description like CONCAT('%',:searchKeyword,'%')"
              + "HAVING distance <= 0.9\n"
              + "ORDER BY distance\n"
              + "LIMIT 0,20\n",
      nativeQuery = true
  )
  List<Restaurant> findALLOrderByDistance(@Param("nowLat") String nowLat,
      @Param("nowLon") String nowLon, @Param("searchKeyword") String searchKeyword);
}

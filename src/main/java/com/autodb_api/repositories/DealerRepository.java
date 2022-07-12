package com.autodb_api.repositories;

import com.autodb_api.models.Dealer;
import com.autodb_api.models.Defect;
import com.vividsolutions.jts.geom.Point;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("DealerRepository")
public interface DealerRepository extends JpaRepository<Dealer, Integer> {

    Optional<Dealer> findById(Integer id);

    Page<Dealer> findAll (Pageable page);

    Page<Dealer> findByPostcode(String postcode, Pageable page);

    @Query(value = "SELECT longitude, latitude, postcode, name, sp_id, rating, id, city, franchise_dealer, franchise_make FROM (\n" +
            "    SELECT *, (6371 * acos ( cos ( radians(:lat) ) * cos( radians( latitude ) ) * cos( radians( longitude ) - radians(:lng) ) + sin ( radians(:lat) ) * sin( radians( latitude ) ) ) )\n" +
            "    AS distance FROM dealer GROUP BY id ORDER BY distance\n" +
            ") AS dealers WHERE distance < :dist ",
            nativeQuery = true)
    List<Dealer> findNearbyDealers(@Param("lat") double lat, @Param("lng") double lng, @Param("dist") double dist);


    @Query(value = "SELECT * FROM dealer WHERE ST_Distance(ST_SetSRID(ST_MakePoint(:lng, :lat), 4326), location) * 0.000621371 < :dist", nativeQuery = true)
    List<Dealer> findAllSellersByPostcodeAndRadius(@Param("lat") double lat, @Param("lng") double lng, @Param("dist") double dist);


    @Query(value = "SELECT * FROM dealer WHERE ST_Distance(ST_SetSRID(ST_MakePoint(-118.1766294, 33.8640647), 4326), location) * 0.000621371 < 10", nativeQuery = true)
    List<Dealer> findallTest();

}
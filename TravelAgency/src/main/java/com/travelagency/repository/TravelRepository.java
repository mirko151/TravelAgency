package com.travelagency.repository;

import com.travelagency.model.Travel;
import com.travelagency.model.TransportMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface TravelRepository extends JpaRepository<Travel, Integer> {

    @Query("SELECT t FROM Travel t WHERE " +
            "(:transportMode IS NULL OR t.transportMode = :transportMode) AND " +
            "(:minPrice IS NULL OR t.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR t.price <= :maxPrice) AND " +
            "(:nights IS NULL OR t.numberOfNights = :nights)")
    Page<Travel> findTravelsByCriteria(@Param("transportMode") TransportMode transportMode,
                                       @Param("minPrice") BigDecimal minPrice,
                                       @Param("maxPrice") BigDecimal maxPrice,
                                       @Param("nights") Integer nights,
                                       Pageable pageable);

    @Query("SELECT t FROM Travel t WHERE t.discountPrice IS NOT NULL AND t.discountValidUntil >= :currentDate")
    List<Travel> findActiveDiscountTravels(@Param("currentDate") Date currentDate);

    @Query("SELECT t FROM Travel t WHERE t.category.name = :season")
    List<Travel> findSeasonalTravels(@Param("season") String season);
}

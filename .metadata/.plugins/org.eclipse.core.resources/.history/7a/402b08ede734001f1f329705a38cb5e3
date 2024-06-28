package com.travelagency.repository;

import com.travelagency.model.Travel;
import com.travelagency.model.TransportMode;
import com.travelagency.model.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface TravelRepository extends JpaRepository<Travel, Integer> {

    @Query("SELECT t FROM Travel t WHERE t.category.name = :categoryName")
    List<Travel> findByCategoryName(@Param("categoryName") String categoryName);

    List<Travel> findByDestinationName(String destinationName);
    List<Travel> findByTransportMode(TransportMode transportMode);
    List<Travel> findByAccommodation(Accommodation accommodation);
    List<Travel> findByNights(int nights);
    List<Travel> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
}

package com.travelagency.repository;

import com.travelagency.model.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface TravelRepository extends JpaRepository<Travel, Integer>, JpaSpecificationExecutor<Travel> {
    
    // Ova metoda omogućava pretragu putovanja po sezoni sa paginacijom
    List<Travel> findByCategoryName(String season, Pageable pageable);
}

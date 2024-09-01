package com.travelagency.repository;

import com.travelagency.model.TravelCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelCategoryRepository extends JpaRepository<TravelCategory, Integer> {
}

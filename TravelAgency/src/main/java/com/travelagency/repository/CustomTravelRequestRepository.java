package com.travelagency.repository;

import com.travelagency.model.CustomTravelRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomTravelRequestRepository extends JpaRepository<CustomTravelRequest, Long> {
    List<CustomTravelRequest> findByUserId(Long userId);
}

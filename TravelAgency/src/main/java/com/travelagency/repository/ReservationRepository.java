package com.travelagency.repository;

import com.travelagency.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    /**
     * Finds all reservations made by a specific user.
     *
     * @param userId the ID of the user
     * @return a list of reservations made by the user
     */
    List<Reservation> findByUserId(int userId);

    /**
     * Finds all reservations for a specific travel.
     *
     * @param travelId the ID of the travel
     * @return a list of reservations for the travel
     */
    List<Reservation> findByTravelId(Long travelId);
}

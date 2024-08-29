package com.travelagency.repository;

import com.travelagency.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    // Metoda za preuzimanje svih rezervacija za datog korisnika na osnovu userId
    List<Reservation> findByUserId(int userId);
}

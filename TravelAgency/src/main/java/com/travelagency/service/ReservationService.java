package com.travelagency.service;

import com.travelagency.model.Reservation;
import com.travelagency.model.Travel;
import com.travelagency.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private TravelService travelService;

    @Transactional
    public Reservation saveReservation(Reservation reservation) {
        Travel travel = reservation.getTravel();
        
        if (travel == null) {
            throw new IllegalArgumentException("Putovanje ne može biti null.");
        }

        if (travel.getAvailableSeats() < reservation.getPassengers()) {
            throw new IllegalArgumentException("Nema dovoljno slobodnih mesta za ovo putovanje.");
        }

        travel.setAvailableSeats(travel.getAvailableSeats() - reservation.getPassengers());
        travelService.saveTravel(travel);

        return reservationRepository.save(reservation);
    }

    @Transactional
    public void cancelReservation(int id) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);

        if (reservation != null) {
            Travel travel = reservation.getTravel();

            if (travel != null) {
                travel.setAvailableSeats(travel.getAvailableSeats() + reservation.getPassengers());
                travelService.saveTravel(travel);
            }

            reservationRepository.delete(reservation);
        }
    }

    // Nova metoda koja preuzima sve rezervacije za datog korisnika
    public List<Reservation> getReservationsByUser(int userId) {
        return reservationRepository.findByUserId(userId);
    }
}

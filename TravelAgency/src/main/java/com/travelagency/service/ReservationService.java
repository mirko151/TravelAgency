package com.travelagency.service;

import com.travelagency.model.Reservation;
import com.travelagency.model.Travel;
import com.travelagency.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private TravelService travelService;

    public Reservation saveReservation(Reservation reservation) {
        // Ažuriraj broj slobodnih mesta
        Travel travel = reservation.getTravel();
        travel.setAvailableSeats(travel.getAvailableSeats() - reservation.getPassengers());
        travelService.saveTravel(travel);
        return reservationRepository.save(reservation);
    }

    public void cancelReservation(int id) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        if (reservation != null) {
            // Vrati slobodna mesta
            Travel travel = reservation.getTravel();
            travel.setAvailableSeats(travel.getAvailableSeats() + reservation.getPassengers());
            reservationRepository.delete(reservation);
        }
    }
}

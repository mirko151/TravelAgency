package com.travelagency.service;

import com.travelagency.model.Reservation;
import com.travelagency.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getReservationsByUser(int userId) {
        return reservationRepository.findByUserId(userId);
    }

    public void cancelReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        if (reservation != null && reservation.getTravel().getDepartureDate().after(new Date(System.currentTimeMillis() + 48 * 60 * 60 * 1000))) {
            reservation.getTravel().setAvailableSeats(reservation.getTravel().getAvailableSeats() + reservation.getNumberOfTravelers());
            reservationRepository.deleteById(reservationId);
        }
    }

    public List<Reservation> findByTravelId(Long travelId) {
        return reservationRepository.findByTravelId(travelId);
    }

    public Reservation findById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    public void save(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    public void delete(Long id) {
        reservationRepository.deleteById(id);
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }
}

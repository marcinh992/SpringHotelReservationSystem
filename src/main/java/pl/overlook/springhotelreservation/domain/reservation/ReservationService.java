package pl.overlook.springhotelreservation.domain.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.overlook.springhotelreservation.domain.guest.GuestService;
import pl.overlook.springhotelreservation.domain.room.Room;
import pl.overlook.springhotelreservation.domain.room.RoomService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    GuestService guestService;
    @Autowired
    RoomService roomService;
    @Autowired
    ReservationRepository repository;

    public void createNewReservation(Reservation reservation) throws IllegalArgumentException {

        if(reservation.getToDate().before(reservation.getFromDate())){
            throw new IllegalArgumentException();
        }

        repository.save(reservation);
    }

    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        reservations.addAll(repository.findAll());

        return reservations;
    }

    public void deleteReservation(Long id) {
        repository.deleteById(id);
    }

    public Reservation findReservationById(Long id) {
        return repository.getById(id);
    }


}

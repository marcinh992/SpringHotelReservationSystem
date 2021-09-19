package pl.overlook.springhotelreservation.domain.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.overlook.springhotelreservation.domain.guest.Guest;
import pl.overlook.springhotelreservation.domain.guest.GuestService;
import pl.overlook.springhotelreservation.domain.room.Room;
import pl.overlook.springhotelreservation.domain.room.RoomService;

import java.time.LocalDate;
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

        if (reservation.getToDate().isBefore(reservation.getFromDate())) {
            throw new IllegalArgumentException();
        }

        repository.save(reservation);
    }


    public Reservation guestCreatingReservation(LocalDate fromDate, LocalDate toDate, Long room) {

        Room guestRoom = roomService.findRoomById(room);
        Reservation guestReservation = new Reservation(guestRoom, fromDate, toDate);

        return repository.save(guestReservation);
    }


//    UWAGA MANEWRY WYBUCHOWE !!!!

    public void puttingGuestIntoUncompletedReservation(Long idGuest, Long idReservation){

        Guest guest = guestService.findGuestById(idGuest);

        Reservation reservation = findReservationById(idReservation);

        reservation.setGuest(guest);
    }

//    KONIEC MANEWRÓW


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

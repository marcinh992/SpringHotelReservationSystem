package pl.overlook.springhotelreservation.domain.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.overlook.springhotelreservation.domain.reservation.Reservation;
import pl.overlook.springhotelreservation.domain.reservation.ReservationService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RoomService {

    @Autowired
    RoomRepository repository;

    ReservationService reservationService;

    public void createNewRoom(Room room) {
        repository.save(room);
    }

    public void deleteRoom(Long id) {
        repository.deleteById(id);
    }

    public Room findRoomById(Long id) {
        return repository.getById(id);
    }

    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        rooms.addAll(repository.findAll());
        return rooms;
    }

    public List<Room> getAvailableRooms(Date from, Date to) {

        if (from == null || to == null) {
            throw new IllegalArgumentException("Parameters can't be null");
        }

        if (to.before(from)) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }

        List<Room> availableRooms = this.getAllRooms();

        List<Reservation> reservations = this.reservationService.getAllReservations();

        for (Reservation reservation : reservations) {
            if (reservation.getFromDate().equals(from)) {
                availableRooms.remove(reservation.getRoom());
            } else if (reservation.getToDate().equals(to)) {
                availableRooms.remove(reservation.getRoom());
            } else if (reservation.getFromDate().after(from) && reservation.getFromDate().before(to)) {
                availableRooms.remove(reservation.getRoom());
            } else if (reservation.getToDate().after(from) && reservation.getToDate().before(to)) {
                availableRooms.remove(reservation.getRoom());
            } else if (from.after(reservation.getFromDate()) && to.before(reservation.getToDate())) {
                availableRooms.remove(reservation.getRoom());
            }
        }

        return availableRooms;
    }


}

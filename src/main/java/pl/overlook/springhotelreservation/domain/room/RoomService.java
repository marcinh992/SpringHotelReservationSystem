package pl.overlook.springhotelreservation.domain.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.overlook.springhotelreservation.domain.reservation.Reservation;
import pl.overlook.springhotelreservation.domain.reservation.ReservationService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {

    @Autowired
    RoomRepository repository;

    @Autowired
    ReservationService reservationService;


    public void createNewRoom(Room room) {

        deleteAllNoneBedTypeValue(room);

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


    public List<Room> getAvailableRooms(LocalDate from, LocalDate to) {

        if (from == null || to == null) {
            throw new IllegalArgumentException("Parameters cannot be null");
        }

        if (to.isBefore(from)) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }

        List<Room> availableRooms = this.getAllRooms();

        List<Reservation> reservations = this.reservationService.getAllReservations();

        for (Reservation reservation : reservations) {
            if (reservation.getFromDate().equals(from)) {
                availableRooms.remove(reservation.getRoom());
            } else if (reservation.getToDate().equals(to)) {
                availableRooms.remove(reservation.getRoom());
            } else if (reservation.getFromDate().isAfter(from) && reservation.getFromDate().isBefore(to)) {
                availableRooms.remove(reservation.getRoom());
            } else if (reservation.getToDate().isAfter(from) && reservation.getToDate().isBefore(to)) {
                availableRooms.remove(reservation.getRoom());
            } else if (from.isAfter(reservation.getFromDate()) && to.isBefore(reservation.getToDate())) {
                availableRooms.remove(reservation.getRoom());
            }
        }
        return availableRooms;
    }

    public void updateRoom(Long id, Room room) {

        Room existingRoom = this.findRoomById(id);

        if (existingRoom == null) {
            this.createNewRoom(room);
        } else {
            room.setId(existingRoom.getId());
            this.repository.save(room);
        }
    }

    public void deleteAllNoneBedTypeValue(Room room) {

        room.getBeds().removeIf(n -> n.equals(BedType.NONE));

    }

    public Page<Room> findPaginated(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

        return this.repository.findAll(pageable);
    }

}

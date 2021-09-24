package pl.overlook.springhotelreservation.domain.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

        room.setSize(calculateRoomSize(room));

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


    public void updateRoom(Long id, Room room) {

        Room existingRoom = this.findRoomById(id);

        if (existingRoom == null) {
            this.createNewRoom(room);
        } else {
            room.setId(existingRoom.getId());
            this.repository.save(room);
        }
    }

    public Page<Room> findPaginated(int pageNo, int pageSize, String sortField, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        return this.repository.findAll(pageable);
    }

    public void deleteAllNoneBedTypeValue(Room room) {

        room.getBeds().removeIf(n -> n.equals(BedType.NONE));
    }

    public int calculateRoomSize(Room room) {

        int size = 0;

        for (int i = 0; i < room.getBeds().size(); i++) {
            if (room.getBeds().get(i).equals(BedType.DOUBLE) || room.getBeds().get(i).equals(BedType.KING_SIZE)) {
                size = size + 2;
            }
            if (room.getBeds().get(i).equals(BedType.SINGLE)) {
                size = size + 1;
            }
        }
        return size;
    }

    public List<Room> findEnoughSizeRooms(int roomSize) {

        List<Room> fittedRooms = getAllRooms();

        for (int i = 0; i < fittedRooms.size(); i++) {

            if (fittedRooms.get(i).getSize() < roomSize) {
                fittedRooms.remove(i);
            }

        }
        return fittedRooms;
    }

    public void getAvailableRooms(LocalDate from, LocalDate to, List<Room> fittedSizeRooms) {

        if (from == null || to == null) {
            throw new IllegalArgumentException("Parameters cannot be null");
        }
        if (to.isBefore(from)) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }

        List<Reservation> reservations = this.reservationService.getAllReservations();

        for (Reservation reservation : reservations) {
            if (reservation.getFromDate().equals(from)) {
                fittedSizeRooms.remove(reservation.getRoom());
            } else if (reservation.getToDate().equals(to)) {
                fittedSizeRooms.remove(reservation.getRoom());
            } else if (reservation.getFromDate().isAfter(from) && reservation.getFromDate().isBefore(to)) {
                fittedSizeRooms.remove(reservation.getRoom());
            } else if (reservation.getToDate().isAfter(from) && reservation.getToDate().isBefore(to)) {
                fittedSizeRooms.remove(reservation.getRoom());
            } else if (from.isAfter(reservation.getFromDate()) && to.isBefore(reservation.getToDate())) {
                fittedSizeRooms.remove(reservation.getRoom());
            }
        }
    }

    public List<Room> showAvailableAndFittedSizeRooms(int roomSize, LocalDate from, LocalDate to) {

        List<Room> sortedRooms = findEnoughSizeRooms(roomSize);

        getAvailableRooms(from, to, sortedRooms);

        return sortedRooms;
    }


}

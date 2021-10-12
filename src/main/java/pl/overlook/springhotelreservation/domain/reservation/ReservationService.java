package pl.overlook.springhotelreservation.domain.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.overlook.springhotelreservation.domain.guest.GuestService;
import pl.overlook.springhotelreservation.domain.room.Room;
import pl.overlook.springhotelreservation.domain.room.RoomService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
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
        Reservation guestReservation = new Reservation(guestRoom, fromDate, toDate, LocalDateTime.now(), false);

        return repository.save(guestReservation);
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


    public Page<Reservation> findPaginated(int pageNo, int pageSize, String sortField, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        return this.repository.findAll(pageable);
    }


    public void removeUnconfirmedReservations() {

        int timeAfterDeleteUnconfirmedReservation = 15;

        if (this.repository.findAllByConfirmedFalse().size() > 0) {

            for (int i = 0; i < this.repository.findAllByConfirmedFalse().size(); i++) {

                Reservation reservation = this.repository.findAllByConfirmedFalse().get(i);

                long minutesAfterCreatedUnconfirmedReservation =
                        ChronoUnit.MINUTES.between(reservation.getCreatedDate(), LocalDateTime.now());

                if (reservation.getGuest() == null &&
                        minutesAfterCreatedUnconfirmedReservation > timeAfterDeleteUnconfirmedReservation) {

                    deleteReservation(reservation.getId());
                    System.out.println("Usunięto niedokończoną rezerwację o ID: " + reservation.getId() + " o godzinie:"
                            + LocalDateTime.now());
                }
            }
        }
    }


}

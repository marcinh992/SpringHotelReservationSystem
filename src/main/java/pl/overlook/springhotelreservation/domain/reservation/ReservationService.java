package pl.overlook.springhotelreservation.domain.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.overlook.springhotelreservation.Utils;
import pl.overlook.springhotelreservation.domain.guest.GuestService;
import pl.overlook.springhotelreservation.domain.reservation.token.ConfirmationToken;
import pl.overlook.springhotelreservation.domain.reservation.token.ConfirmationTokenService;
import pl.overlook.springhotelreservation.domain.room.Room;
import pl.overlook.springhotelreservation.domain.room.RoomService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    GuestService guestService;

    @Autowired
    RoomService roomService;

    @Autowired
    ConfirmationTokenService confirmationTokenService;

    @Autowired
    ReservationRepository repository;


    public void createNewReservation(Reservation reservation) throws IllegalArgumentException {

        if (reservation.getToDate().isBefore(reservation.getFromDate())) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }
        repository.save(reservation);
    }


    public Reservation guestCreatingReservation(LocalDate fromDate, LocalDate toDate, Long room) {

        Room guestRoom = roomService.findRoomById(room);
        Reservation guestReservation = new Reservation(guestRoom, fromDate, toDate, LocalDateTime.now(), false);

        return repository.save(guestReservation);
    }


    public List<Reservation> getAllReservations() {
        return this.repository.findAll();
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

    //method returns String because somehow this method needed to be tested
    public String removeUnconfirmedReservations() {

        String message = "";

        if (this.repository.findAllByConfirmedFalse().size() > 0) {

            for (int i = 0; i < this.repository.findAllByConfirmedFalse().size(); i++) {

                Reservation reservation = this.repository.findAllByConfirmedFalse().get(i);

                long minutesAfterCreatedUnconfirmedReservation =
                        ChronoUnit.MINUTES.between(reservation.getCreatedDate(), LocalDateTime.now());

                if (!reservation.isConfirmed() &&
                        minutesAfterCreatedUnconfirmedReservation > Utils.MINUTES_AFTER_DELETE_UNCONFIRMED_RESERVATION) {

                    deleteReservation(reservation.getId());
                    message = "deleted unconfirmed reservations";
                } else {
                    message = "no reservations for delete";
                }
            }
        }

        return message;
    }

    @Transactional
    public Boolean confirmToken(String token) {
        boolean isConfirmed = false;

        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("reservation already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        confirmationToken.getReservation().setConfirmed(true);

        if (confirmationToken.getReservation().isConfirmed()) {
            isConfirmed = true;
        }

        return isConfirmed;
    }


}

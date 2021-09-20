package pl.overlook.springhotelreservation.domain.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.overlook.springhotelreservation.domain.room.Room;

@RestController
@RequestMapping("/api")
public class ReservationControllerRest {

    @Autowired
    ReservationService reservationService;

    @PostMapping("/reservation")
    public void createRoom(@RequestBody Reservation reservation) {
        reservationService.createNewReservation(reservation);
    }
}

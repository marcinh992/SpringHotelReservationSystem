package pl.overlook.springhotelreservation.domain.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservation")
public class ReservationControllerRest {

    @Autowired
    ReservationService reservationService;

    @PostMapping("/createreservation")
    public void createRoom(@RequestBody Reservation reservation) {
        reservationService.createNewReservation(reservation);
    }
}

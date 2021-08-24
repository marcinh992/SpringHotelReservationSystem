package pl.overlook.springhotelreservation.domain.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @RequestMapping("/reservations")
    public String getReservations(Model model) {
        List<Reservation> allReservations = reservationService.getAllReservations();
        model.addAttribute("reservations", allReservations);

        return "reservations";
    }

    @RequestMapping("/newreservation")
    public String createReservation(Model model) {
        model.addAttribute("reservation", new Reservation());

        return "reservationform";
    }

    @RequestMapping(value = "/reservations", method = RequestMethod.POST)
    public String saveReservation(@Valid Reservation reservation, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            System.out.println("There were errors");
            bindingResult.getAllErrors().forEach(error -> {
                        System.out.println(error.getObjectName() + " " + error.getDefaultMessage());
                    }
            );
            return "reservationform";
        } else {
            reservationService.createNewReservation(reservation);
            return "redirect:/reservations";
        }
    }

}

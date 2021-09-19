package pl.overlook.springhotelreservation.domain.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.overlook.springhotelreservation.domain.guest.Guest;
import pl.overlook.springhotelreservation.domain.guest.GuestService;
import pl.overlook.springhotelreservation.domain.room.Room;
import pl.overlook.springhotelreservation.domain.room.RoomService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    GuestService guestService;

    @Autowired
    RoomService roomService;

    @RequestMapping("/reservations")
    public String getReservations(Model model) {
        List<Reservation> allReservations = reservationService.getAllReservations();
        model.addAttribute("reservations", allReservations);

        return "reservations";
    }


    @RequestMapping("/newreservation")
    public String createReservation(Model model) {
        model.addAttribute("reservation", new Reservation());

        List<Room> listRooms = roomService.getAllRooms();
        List<Guest> listGuests = guestService.getAllGuests();

        model.addAttribute("listRooms", listRooms);
        model.addAttribute("listGuests", listGuests);

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


    @RequestMapping(value = "/reservation/delete/{id}")
    public String deleteReservation(@PathVariable("id") Long id) {
        reservationService.deleteReservation(id);

        return "redirect:/reservations";
    }


    @GetMapping(value = "/reservationUpdateForm/{id}")
    public String reservationUpdateForm(@PathVariable("id") Long id, Model model) {

        List<Room> listRooms = roomService.getAllRooms();
        List<Guest> listGuests = guestService.getAllGuests();

        model.addAttribute("listRooms", listRooms);
        model.addAttribute("listGuests", listGuests);


        Reservation reservation = reservationService.findReservationById(id);
        model.addAttribute("reservation", reservation);

        return "update_reservation";
    }


    @GetMapping(value = "/guestreservation")
    public String guestCreatingReservation() {

        return "guestreservation";

    }


    @PostMapping(value = "/roomChoosing")
    public String guestChoosingRoom(ReservationDTO reservationDTO, Model model) {

        System.out.println("Dostałem");

        model.addAttribute("fromDate", reservationDTO.getFromDate());
        model.addAttribute("toDate", reservationDTO.getToDate());

//        wyszukaj wolne pokoje w tym termienie
        List<Room> listRooms = roomService.getAvailableRooms(reservationDTO.getFromDate(), reservationDTO.getToDate());
        model.addAttribute("listRooms", listRooms);

        return "roomChoosing";
    }

////    getmapping na reservation zeby obsłużyć parametry
    //dodać potwierdzenie rezerwacja poprzez email
    // dodać logikę taska który co jakiś interwał będzie sprawdzał i usuwał niekompletne rezerwacje

    @GetMapping("/reservation")
    public String guestCreatingNewGuest(
            @RequestParam Long room,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate,
            Model model) {

        Reservation newReservation = reservationService.guestCreatingReservation(fromDate, toDate, room);

        model.addAttribute("idReservation", newReservation.getId());
        model.addAttribute("guest", new Guest());


        return "guestCreatingGuest";
    }

    @PostMapping("/finalStep")
    public String finalizeReservation(Guest guest, @RequestParam Long idReservation){

        guestService.createNewGuest(guest);

        Reservation finalReservation = reservationService.findReservationById(idReservation);

        finalReservation.setGuest(guest);


        return "reservationComplited";

    }






}

package pl.overlook.springhotelreservation.domain.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.overlook.springhotelreservation.domain.guest.Guest;
import pl.overlook.springhotelreservation.domain.guest.GuestService;
import pl.overlook.springhotelreservation.domain.room.Room;
import pl.overlook.springhotelreservation.domain.room.RoomService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    GuestService guestService;

    @Autowired
    RoomService roomService;

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, null,  new CustomDateEditor(dateFormat, true));
    }

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

//    @RequestMapping("/guestreservation")
//    public String guestCreatingReservation(Model model){
//        model.addAttribute("reservation", new Reservation());
//
//        List<Room> listRooms = roomService.getAllRooms();
//
//        model.addAttribute("listRooms", listRooms);
//
//
//        return "guestreservation";
//
//
//    }

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
    public String deleteReservation(@PathVariable("id") Long id){
        reservationService.deleteReservation(id);

        return "redirect:/reservations";
    }

    @GetMapping(value = "/reservationUpdateForm/{id}")
    public String reservationUpdateForm(@PathVariable("id") Long id,Model model){

        List<Room> listRooms = roomService.getAllRooms();
        List<Guest> listGuests = guestService.getAllGuests();

        model.addAttribute("listRooms", listRooms);
        model.addAttribute("listGuests", listGuests);


        Reservation reservation = reservationService.findReservationById(id);
        model.addAttribute("reservation", reservation);

        return "update_reservation";
    }



}

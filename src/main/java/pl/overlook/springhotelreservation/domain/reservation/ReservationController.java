package pl.overlook.springhotelreservation.domain.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    GuestService guestService;

    @Autowired
    RoomService roomService;

    @GetMapping("/reservations")
    public String getReservations(Model model) {

        return findPaginated(1, "id", "desc", model);
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

        model.addAttribute("fromDate", reservationDTO.getFromDate());
        model.addAttribute("toDate", reservationDTO.getToDate());
        model.addAttribute("roomSize", reservationDTO.getRoomSize());

//        List<Room> listRooms = roomService.getAvailableRooms(reservationDTO.getFromDate(), reservationDTO.getToDate());
        List<Room> listRooms = roomService.showAvailableAndFittedSizeRooms(reservationDTO.getRoomSize(),
                reservationDTO.getFromDate(), reservationDTO.getToDate());

        model.addAttribute("listRooms", listRooms);

        return "roomChoosing";
    }


    @GetMapping("/reservation")
    public String guestCreatingNewGuest(
            @RequestParam Long room,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate,
            Model model) {

        Reservation temporaryReservation = reservationService.guestCreatingReservation(fromDate, toDate, room);
        System.out.println("Utworzono tymczasową rezerwację o ID:" + temporaryReservation.getId() + " o godzinie: "
         + LocalDateTime.now());
        reservationService.addReservationIdToUnconfirmedReservationsList(temporaryReservation.getId());

        model.addAttribute("idReservation", temporaryReservation.getId());
        model.addAttribute("guest", new Guest());


        return "guestCreatingGuest";
    }

    @PostMapping("/finalStep")
    public String finalizeReservation(Guest guest, @RequestParam Long idReservation, Model model) {

        guestService.createNewGuest(guest);

        Reservation temporaryReservation = reservationService.findReservationById(idReservation);

        //making new reservation once again is necessary cause reservations.html template don't want to show guest info
        // when adding guest via 'finalReservation.setGuest(guest)'

        Reservation finalReservation = new Reservation(temporaryReservation.getId(), temporaryReservation.getRoom(), guest,
                temporaryReservation.getFromDate(), temporaryReservation.getToDate(), temporaryReservation.getCreatedDate());

        reservationService.removeReservationIdFromUnconfirmedList(finalReservation.getId());

        reservationService.createNewReservation(finalReservation);

        System.out.println(finalReservation);

        model.addAttribute("finalReservation", finalReservation);
        model.addAttribute("idReservation", idReservation);


        return "reservationComplited";
    }

    @GetMapping("/reservationPage/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {

        int pageSize = 10;

        Page<Reservation> page = reservationService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Reservation> listReservations = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listReservations", listReservations);

        return "reservations";
    }


}

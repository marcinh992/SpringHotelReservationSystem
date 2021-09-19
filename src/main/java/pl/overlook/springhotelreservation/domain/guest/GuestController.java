package pl.overlook.springhotelreservation.domain.guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
public class GuestController {

    @Autowired
    GuestService guestService;


    @RequestMapping("/guests")
    public String getGuests(Model model) {
        List<Guest> allGuests = guestService.getAllGuests();
        model.addAttribute("guests", allGuests);

        return "guests";
    }

    @RequestMapping("/newguest")
    public String createGuest(Model model) {
        model.addAttribute("guest", new Guest());

        return "guestform";
    }

    @RequestMapping(value = "/guests", method = RequestMethod.POST)
    public String saveGuest(@Valid Guest guest, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            System.out.println("There were errors");
            bindingResult.getAllErrors().forEach(error -> {
                        System.out.println(error.getObjectName() + " " + error.getDefaultMessage());
                    }
            );
            return "guestform";
        } else {
            guestService.createNewGuest(guest);
            return "redirect:/guests";
        }
    }

    @RequestMapping(value = "/guest/delete/{id}")
    public String deleteGuest(@PathVariable("id") Long id) {
        guestService.deleteGuest(id);

        return "redirect:/guests";
    }

    @GetMapping(value = "/guestUpdateForm/{id}")
    public String guestUpdateForm(@PathVariable("id") Long id, Model model) {
        Guest guest = guestService.findGuestById(id);
        model.addAttribute("guest", guest);

        return "update_guest";
    }
}

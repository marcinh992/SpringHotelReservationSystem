package pl.overlook.springhotelreservation.domain.guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class GuestController {

    @Autowired
    GuestService guestService;


    @GetMapping("/guests")
    public String getGuests(Model model) {

        return findPaginated(1, "firstName", "asc", model);
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

    @GetMapping("/guestPage/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {

        int pageSize = 10;

        Page<Guest> page = guestService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Guest> listGuests = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listGuests", listGuests);

        return "guests";
    }


}

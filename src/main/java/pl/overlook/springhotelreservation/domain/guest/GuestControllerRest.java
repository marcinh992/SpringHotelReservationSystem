package pl.overlook.springhotelreservation.domain.guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/guest")
public class GuestControllerRest {

    @Autowired
    GuestService guestService;

    @PostMapping("/createguest")
    public void createGuest(@RequestBody Guest guest) {
        guestService.createNewGuest(guest);
    }
}

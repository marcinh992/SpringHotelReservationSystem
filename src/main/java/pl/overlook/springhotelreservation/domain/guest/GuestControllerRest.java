package pl.overlook.springhotelreservation.domain.guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.overlook.springhotelreservation.domain.room.Room;

@RestController
@RequestMapping("/api")
public class GuestControllerRest {

    @Autowired
    GuestService guestService;

    @PostMapping("/guest")
    public void createRoom(@RequestBody Guest guest) {
        guestService.createNewGuest(guest);
    }
}

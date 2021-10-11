package pl.overlook.springhotelreservation.domain.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomControllerRest {

    @Autowired
    RoomService roomService;

    @GetMapping("/getallrooms")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/room/{id}")
    public Room getSingleRoom(@PathVariable Long id) {
        return roomService.findRoomById(id);
    }
}

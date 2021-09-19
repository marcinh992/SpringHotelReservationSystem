package pl.overlook.springhotelreservation.domain.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoomControllerRest {

    @Autowired
    RoomService roomService;

    @GetMapping("/rooms")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/rooms/{id}")
    public Room getSingleRoom(@PathVariable Long id) {
        return roomService.findRoomById(id);
    }

    @PostMapping("/rooms")
    public void createRoom(@RequestBody Room room) {
        roomService.createNewRoom(room);
    }

    @DeleteMapping("/rooms/{id}")
    public void deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
    }

    @PutMapping("/rooms/{id}")
    public void updateRoom(@PathVariable Long id, @RequestBody Room room) {
        this.roomService.updateRoom(id, room);
    }

}

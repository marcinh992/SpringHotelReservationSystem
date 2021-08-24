package pl.overlook.springhotelreservation.domain.room;

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
public class RoomController {

    @Autowired
    RoomService roomService;

    @RequestMapping("/rooms")
    public String getRooms(Model model) {
        List<Room> allRooms = roomService.getAllRooms();
        model.addAttribute("rooms", allRooms);

        return "rooms";
    }

    @RequestMapping("/newroom")
    public String createRoom(Model model) {
        model.addAttribute("room", new Room());

        return "roomform";
    }

    @RequestMapping(value = "/rooms", method = RequestMethod.POST)
    public String saveRoom(@Valid Room room, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            System.out.println("There were errors");
            bindingResult.getAllErrors().forEach(error -> {
                        System.out.println(error.getObjectName() + " " + error.getDefaultMessage());
                    }
            );
            return "roomform";
        } else {
            roomService.createNewRoom(room);
            return "redirect:/rooms";
        }
    }

    @RequestMapping(value = "/room/delete/{id}")
    public String deleteRoom(@PathVariable("id") Long id) {
        roomService.deleteRoom(id);

        return "redirect:/rooms";
    }

    @GetMapping(value = "/roomUpdateForm/{id}")
    public String roomUpdateForm(@PathVariable("id") Long id, Model model) {
        Room room = roomService.findRoomById(id);
        model.addAttribute("room", room);

        return "update_room";
    }


}

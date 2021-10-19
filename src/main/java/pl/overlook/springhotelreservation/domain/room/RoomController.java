package pl.overlook.springhotelreservation.domain.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RoomController {

    @Autowired
    RoomService roomService;


    @GetMapping("/rooms")
    public String getRooms(Model model) {

        return findPaginated(1, "number", "asc", model);
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


    @GetMapping("/roomPage/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {

        int pageSize = 10;

        Page<Room> page = roomService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Room> listRooms = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listRooms", listRooms);

        return "rooms";
    }
}

package pl.overlook.springhotelreservation.domain.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {

    @Autowired
    RoomRepository repository;

    public void createNewRoom(Room room) {
        repository.save(room);
    }

    public void deleteRoom(Long id) {
        repository.deleteById(id);
    }

    public Room findRoomById(Long id) {
        return repository.getById(id);
    }

    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        rooms.addAll(repository.findAll());
        return rooms;
    }


}

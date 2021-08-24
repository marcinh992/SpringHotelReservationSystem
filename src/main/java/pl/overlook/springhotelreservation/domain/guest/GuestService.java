package pl.overlook.springhotelreservation.domain.guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GuestService {

    @Autowired
    GuestRepository repository;

    public void createNewGuest(Guest guest){
    repository.save(guest);
    }

    public void deleteGuest (Long id){
        repository.deleteById(id);
    }

    public Guest findGuestById(Long id){
        return repository.getById(id);
    }

    public List<Guest> getAllGuests(){
        List<Guest> guests = new ArrayList<>();
        guests.addAll(repository.findAll());
        return guests;
    }






}

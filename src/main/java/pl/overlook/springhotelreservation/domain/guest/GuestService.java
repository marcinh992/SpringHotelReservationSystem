package pl.overlook.springhotelreservation.domain.guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class GuestService {

    @Autowired
    GuestRepository repository;

    public void createNewGuest(Guest guest) throws IllegalArgumentException {

        if (checkingThatGuestIsAdult(guest)) {
            repository.save(guest);
        } else {
            throw new IllegalArgumentException();
        }

    }

    public void deleteGuest(Long id) {
        repository.deleteById(id);
    }

    public Guest findGuestById(Long id) {
        return repository.getById(id);
    }

    public List<Guest> getAllGuests() {
        List<Guest> guests = new ArrayList<>();
        guests.addAll(repository.findAll());
        return guests;
    }

    public boolean checkingThatGuestIsAdult(Guest guest) {

        Period period = Period.between(guest.getBirthDate(), LocalDate.now());

        if (period.getYears() >= 18) {
            return true;
        } else {
            return false;
        }
    }


}

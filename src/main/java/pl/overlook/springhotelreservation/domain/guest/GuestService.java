package pl.overlook.springhotelreservation.domain.guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.overlook.springhotelreservation.Utils;

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
            throw new IllegalArgumentException("Rezerwujący musi być pełnoletni");
        }

    }

    public void deleteGuest(Long id) {
        repository.deleteById(id);
    }

    public Guest findGuestById(Long id) {
        return repository.getById(id);
    }

    public List<Guest> getAllGuests() {
        return this.repository.findAll();
    }

    public boolean checkingThatGuestIsAdult(Guest guest) {


        Period period = Period.between(guest.getBirthDate(), LocalDate.now());

        return period.getYears() >= Utils.ADULT_AGE;
    }

    public Page<Guest> findPaginated(int pageNo, int pageSize, String sortField, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        return this.repository.findAll(pageable);
    }


}

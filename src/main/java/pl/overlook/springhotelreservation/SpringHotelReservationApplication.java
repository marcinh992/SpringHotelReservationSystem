package pl.overlook.springhotelreservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.overlook.springhotelreservation.domain.guest.Guest;
import pl.overlook.springhotelreservation.domain.guest.GuestService;

import java.util.List;

@SpringBootApplication
public class SpringHotelReservationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringHotelReservationApplication.class, args);



    }

}

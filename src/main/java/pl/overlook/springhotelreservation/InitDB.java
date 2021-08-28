package pl.overlook.springhotelreservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.overlook.springhotelreservation.domain.guest.Guest;
import pl.overlook.springhotelreservation.domain.guest.GuestService;
import pl.overlook.springhotelreservation.domain.reservation.Reservation;
import pl.overlook.springhotelreservation.domain.reservation.ReservationService;
import pl.overlook.springhotelreservation.domain.room.BedType;
import pl.overlook.springhotelreservation.domain.room.Room;
import pl.overlook.springhotelreservation.domain.room.RoomService;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;

@Component
public class InitDB implements CommandLineRunner {

    public static final int HOTEL_NIGHT_START_HOUR = 15;
    public static final int HOTEL_NIGHT_START_MINUTE = 0;
    public static final int HOTEL_NIGHT_END_HOUR = 10;
    public static final int HOTEL_NIGHT_END_MINUTE = 0;


    @Autowired
    GuestService guestService;

    @Autowired
    RoomService roomService;

    @Autowired
    ReservationService reservationService;

    @Override
    public void run(String... args) throws Exception {

//        dummy data

        System.out.println("INIT GUEST AND ROOM DATABASE");

        Guest guest1 = new Guest("Przykładowy1", "Gość1", 51);
        Guest guest2 = new Guest("Przykładowy2", "Gość2", 52);
        Guest guest3 = new Guest("Przykładowy3", "Gość3", 53);

        Room room1 = new Room(51, BedType.SINGLE, 1, true);
        Room room2 = new Room(52, BedType.DOUBLE, 2, false);
        Room room3 = new Room(53, BedType.KING_SIZE, 1, true);



        guestService.createNewGuest(new Guest("Jack", "Torrance", 43));
        guestService.createNewGuest(new Guest("Wendy", "Torrance", 35));
        guestService.createNewGuest(new Guest("Danny", "Torrance", 8));

        guestService.createNewGuest(guest1);
        guestService.createNewGuest(guest2);
        guestService.createNewGuest(guest3);

        roomService.createNewRoom(new Room(99, BedType.DOUBLE, 1, true));
        roomService.createNewRoom(new Room(1, BedType.KING_SIZE, 2, false));
        roomService.createNewRoom(new Room(20, BedType.SINGLE, 2, true));

        roomService.createNewRoom(room1);
        roomService.createNewRoom(room2);
        roomService.createNewRoom(room3);

//        reservationService.createNewReservation(new Reservation(room1, guest1, firstReservationDateStart, firstReservationDateEnd));


        System.out.println("Current guest count: " + guestService.getAllGuests().size());
        System.out.println("Current room count: " + roomService.getAllRooms().size());
        System.out.println("Current reservation count: " + reservationService.getAllReservations().size());
    }
}

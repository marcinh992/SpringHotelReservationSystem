package pl.overlook.springhotelreservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.overlook.springhotelreservation.domain.guest.Guest;
import pl.overlook.springhotelreservation.domain.guest.GuestService;
import pl.overlook.springhotelreservation.domain.reservation.ReservationService;
import pl.overlook.springhotelreservation.domain.room.BedType;
import pl.overlook.springhotelreservation.domain.room.Room;
import pl.overlook.springhotelreservation.domain.room.RoomService;
import pl.overlook.springhotelreservation.domain.room.RoomType;

import java.util.ArrayList;
import java.util.List;

@Component
public class InitDB implements CommandLineRunner {

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

        List<BedType> bedsForRoom1 = new ArrayList<>();
        bedsForRoom1.add(BedType.SINGLE);
        bedsForRoom1.add(BedType.DOUBLE);


        List<BedType> bedsForRoom2 = new ArrayList<>();
        bedsForRoom2.add(BedType.KING_SIZE);
        bedsForRoom2.add(BedType.SINGLE);

        List<BedType> bedsForRoom3 = new ArrayList<>();
        bedsForRoom3.add(BedType.DOUBLE);
        bedsForRoom3.add(BedType.DOUBLE);

//        List<BedType> bedsForRoom4 = new ArrayList<>();
//        bedsForRoom1.add(BedType.SINGLE);
//        bedsForRoom1.add(BedType.DOUBLE);
//
//        List<BedType> bedsForRoom5 = new ArrayList<>();
//        bedsForRoom1.add(BedType.SINGLE);
//        bedsForRoom1.add(BedType.DOUBLE);
//
//        List<BedType> bedsForRoom6 = new ArrayList<>();
//        bedsForRoom1.add(BedType.SINGLE);
//        bedsForRoom1.add(BedType.DOUBLE);
//
//        List<BedType> bedsForRoom7 = new ArrayList<>();
//        bedsForRoom1.add(BedType.SINGLE);
//        bedsForRoom1.add(BedType.DOUBLE);



        Room room1 = new Room(51, bedsForRoom1, RoomType.STANDARD, true);
        Room room2 = new Room(52, bedsForRoom2, RoomType.LUXURY, false);
        Room room3 = new Room(53, bedsForRoom3, RoomType.BUSINESS, true);



        guestService.createNewGuest(new Guest("Jack", "Torrance", false, true));
        guestService.createNewGuest(new Guest("Wendy", "Torrance", false, false));
        guestService.createNewGuest(new Guest("Danny", "Torrance", true, false  ));




        roomService.createNewRoom(room1);
        roomService.createNewRoom(room2);
        roomService.createNewRoom(room3);

//        reservationService.createNewReservation(new Reservation(room1, guest1, firstReservationDateStart, firstReservationDateEnd));


        System.out.println("Current guest count: " + guestService.getAllGuests().size());
        System.out.println("Current room count: " + roomService.getAllRooms().size());
        System.out.println("Current reservation count: " + reservationService.getAllReservations().size());

    }
}

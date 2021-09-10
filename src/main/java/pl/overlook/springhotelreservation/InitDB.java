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
import pl.overlook.springhotelreservation.domain.room.RoomType;

import javax.xml.crypto.Data;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

        Room room1 = new Room(51, bedsForRoom1, RoomType.STANDARD, true);
        Room room2 = new Room(52, bedsForRoom2, RoomType.LUXURY, false);
        Room room3 = new Room(53, bedsForRoom3, RoomType.BUSINESS, true);

        Guest guest1 = new Guest("Jack", "Torrance", false, true);
        Guest guest2 =new Guest("Wendy", "Torrance", false, false);
        Guest guest3 =new Guest("Danny", "Torrance", true, false);


        guestService.createNewGuest(guest1);
        guestService.createNewGuest(guest2);
        guestService.createNewGuest(guest3);


        roomService.createNewRoom(room1);
        roomService.createNewRoom(room2);
        roomService.createNewRoom(room3);

        Date firstReservationStartDate = new Date(2021, Calendar.NOVEMBER,1);
        Date secondReservationStartDate = new Date(2021, Calendar.NOVEMBER,2);
        Date thirdReservationStartDate = new Date(2021, Calendar.NOVEMBER,3);

        Date firstReservationEndDate = new Date(2021, Calendar.NOVEMBER,7);
        Date secondReservationEndDate = new Date(2021, Calendar.NOVEMBER,8);
        Date thirdReservationEndDate = new Date(2021, Calendar.NOVEMBER,9);

        Reservation firstReservation = new Reservation(room1, guest1,firstReservationStartDate, firstReservationEndDate);
        Reservation secondReservation = new Reservation(room2, guest2,secondReservationStartDate, secondReservationEndDate);
        Reservation thirdReservation = new Reservation(room3, guest3,thirdReservationStartDate, thirdReservationEndDate);

        reservationService.createNewReservation(firstReservation);
        reservationService.createNewReservation(secondReservation);
        reservationService.createNewReservation(thirdReservation);


        System.out.println("Current guest count: " + guestService.getAllGuests().size());
        System.out.println("Current room count: " + roomService.getAllRooms().size());
        System.out.println("Current reservation count: " + reservationService.getAllReservations().size());

    }
}

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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
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
        Room room4 = new Room(55, bedsForRoom3, RoomType.BUSINESS, true);
        Room room5 = new Room(58, bedsForRoom3, RoomType.BUSINESS, true);
        Room room6 = new Room(60, bedsForRoom3, RoomType.BUSINESS, true);
        Room room7 = new Room(65, bedsForRoom3, RoomType.BUSINESS, true);
        Room room8 = new Room(65, bedsForRoom3, RoomType.BUSINESS, true);
        Room room9 = new Room(65, bedsForRoom3, RoomType.BUSINESS, true);
        Room room10 = new Room(65, bedsForRoom3, RoomType.BUSINESS, true);
        Room room11 = new Room(65, bedsForRoom3, RoomType.BUSINESS, true);
        Room room12 = new Room(65, bedsForRoom3, RoomType.BUSINESS, true);

        LocalDate firstGuestBirthDay = LocalDate.of(1970, 3, 1);
        LocalDate secondGuestBirthDay = LocalDate.of(1975, 8, 25);


        Guest guest1 = new Guest("Jack", "Torrance", firstGuestBirthDay);
        Guest guest2 = new Guest("Wendy", "Torrance", secondGuestBirthDay);
        Guest guest3 = new Guest("Tony", "Stark", secondGuestBirthDay);
        Guest guest4 = new Guest("Chris", "Jakiśtam", secondGuestBirthDay);
        Guest guest5 = new Guest("Anoty", "Hopkins", secondGuestBirthDay);
        Guest guest6 = new Guest("Marcin", "Pypeć", secondGuestBirthDay);
        Guest guest7 = new Guest("Dariusz", "Szpakowski", secondGuestBirthDay);
        Guest guest8 = new Guest("Roman", "Polański", secondGuestBirthDay);
        Guest guest9 = new Guest("Artur", "Nowak", secondGuestBirthDay);
        Guest guest10 = new Guest("Eliza", "Inna", secondGuestBirthDay);
        Guest guest11 = new Guest("Marta", "Takatam", secondGuestBirthDay);
        Guest guest12 = new Guest("Agata", "Jeszczeinna", secondGuestBirthDay);
        Guest guest13 = new Guest("Agnieszka", "Niewiem", secondGuestBirthDay);
        Guest guest14 = new Guest("Romualda", "Kijwie", secondGuestBirthDay);




        guestService.createNewGuest(guest1);
        guestService.createNewGuest(guest2);
        guestService.createNewGuest(guest3);
        guestService.createNewGuest(guest4);
        guestService.createNewGuest(guest5);
        guestService.createNewGuest(guest6);
        guestService.createNewGuest(guest7);
        guestService.createNewGuest(guest8);
        guestService.createNewGuest(guest9);
        guestService.createNewGuest(guest10);
        guestService.createNewGuest(guest11);
        guestService.createNewGuest(guest12);
        guestService.createNewGuest(guest13);
        guestService.createNewGuest(guest14);

        roomService.createNewRoom(room1);
        roomService.createNewRoom(room2);
        roomService.createNewRoom(room3);
        roomService.createNewRoom(room4);
        roomService.createNewRoom(room5);
        roomService.createNewRoom(room6);
        roomService.createNewRoom(room7);
        roomService.createNewRoom(room8);
        roomService.createNewRoom(room9);
        roomService.createNewRoom(room10);
        roomService.createNewRoom(room11);
        roomService.createNewRoom(room12);

//        LocalDate firstReservationStartDate = LocalDate.of(2021, Calendar.NOVEMBER, 1);
//        LocalDate secondReservationStartDate = LocalDate.of(2021, Calendar.NOVEMBER, 2);
//
//        LocalDate firstReservationEndDate = LocalDate.of(2021, Calendar.NOVEMBER, 7);
//        LocalDate secondReservationEndDate = LocalDate.of(2021, Calendar.NOVEMBER, 8);
//
//        Reservation firstReservation = new Reservation(room1, guest1, firstReservationStartDate, firstReservationEndDate, LocalDateTime.now());
//        Reservation secondReservation = new Reservation(room2, guest2, secondReservationStartDate, secondReservationEndDate, LocalDateTime.now());
//
//        reservationService.createNewReservation(firstReservation);
//        reservationService.createNewReservation(secondReservation);


        System.out.println("Current guest count: " + guestService.getAllGuests().size());
        System.out.println("Current room count: " + roomService.getAllRooms().size());
        System.out.println("Current reservation count: " + reservationService.getAllReservations().size());

    }
}

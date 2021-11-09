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

import java.time.LocalDate;
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

        List<BedType> bedsForOne = new ArrayList<>();
        bedsForOne.add(BedType.SINGLE);

        List<BedType> bedsForTwoV1 = new ArrayList<>();
        bedsForTwoV1.add(BedType.DOUBLE);

        List<BedType> bedsForTwoV2 = new ArrayList<>();
        bedsForTwoV2.add(BedType.SINGLE);
        bedsForTwoV2.add(BedType.SINGLE);

        List<BedType> bedsForThree = new ArrayList<>();
        bedsForThree.add(BedType.SINGLE);
        bedsForThree.add(BedType.DOUBLE);

        List<BedType> bedsForFour = new ArrayList<>();
        bedsForFour.add(BedType.DOUBLE);
        bedsForFour.add(BedType.DOUBLE);

        List<BedType> bedsForFive = new ArrayList<>();
        bedsForFive.add(BedType.DOUBLE);
        bedsForFive.add(BedType.DOUBLE);
        bedsForFive.add(BedType.SINGLE);

        List<BedType> bedsForSix = new ArrayList<>();
        bedsForSix.add(BedType.DOUBLE);
        bedsForSix.add(BedType.DOUBLE);
        bedsForSix.add(BedType.DOUBLE);


        List<BedType> bedsForSingleReservation = new ArrayList<>();
        bedsForSingleReservation.add(BedType.SINGLE);

        List<BedType> bedsForTwo = new ArrayList<>();
        bedsForTwo.add(BedType.SINGLE);
        bedsForTwo.add(BedType.SINGLE);


        List<BedType> bedsForRoom2 = new ArrayList<>();
        bedsForRoom2.add(BedType.KING_SIZE);
        bedsForRoom2.add(BedType.SINGLE);

        List<BedType> bedsForRoom3 = new ArrayList<>();
        bedsForRoom3.add(BedType.DOUBLE);
        bedsForRoom3.add(BedType.DOUBLE);

        for (int i = 1; i <= 8; i++) {
            Room room = new Room(i,bedsForOne, RoomType.STANDARD, false);
            roomService.createNewRoom(room);
        }

        for (int i = 9; i <=14 ; i++) {
            Room room = new Room(i, bedsForTwoV1, RoomType.LUXURY, true);
            roomService.createNewRoom(room);
        }

        for (int i = 15; i <=18 ; i++) {
            Room room = new Room(i, bedsForTwoV2, RoomType.LUXURY, true);
            roomService.createNewRoom(room);
        }

        for (int i = 19; i <= 27 ; i++) {
            Room room = new Room(i,bedsForThree, RoomType.STANDARD, true);
            roomService.createNewRoom(room);
        }

        for (int i = 28; i <= 36; i++) {
            Room room = new Room(i, bedsForFour, RoomType.BUSINESS, false);
            roomService.createNewRoom(room);
        }

        for (int i = 37; i <= 45 ; i++) {
            Room room = new Room(i, bedsForFive, RoomType.STANDARD, true);
            roomService.createNewRoom(room);
        }

        for (int i = 46; i <=54 ; i++) {
            Room room = new Room(i, bedsForSix, RoomType.LUXURY, true);
            roomService.createNewRoom(room);
        }



        LocalDate firstGuestBirthDay = LocalDate.of(1970, 3, 1);
        LocalDate secondGuestBirthDay = LocalDate.of(1975, 8, 25);


        Guest guest1 = new Guest("Jack", "Torrance", firstGuestBirthDay);
        Guest guest2 = new Guest("Wendy", "Torrance", secondGuestBirthDay);
        Guest guest3 = new Guest("Tony", "Torrance", secondGuestBirthDay);
        Guest guest4 = new Guest("Marcin", "Pypeć", secondGuestBirthDay);
        Guest guest5 = new Guest("Norman", "Osborn", secondGuestBirthDay);
        Guest guest6 = new Guest("Peter", "Parker", secondGuestBirthDay);
        Guest guest7 = new Guest("Tony", "Stark", secondGuestBirthDay);
        Guest guest8 = new Guest("Roman", "Polański", secondGuestBirthDay);
        Guest guest9 = new Guest("Bruce", "Banner", secondGuestBirthDay);
        Guest guest10 = new Guest("Eddie", "Brock", secondGuestBirthDay);
        Guest guest11 = new Guest("Frank", "Castle", secondGuestBirthDay);
        Guest guest12 = new Guest("Steve", "Rogers", secondGuestBirthDay);
        Guest guest13 = new Guest("Stephen", "Strange", secondGuestBirthDay);
        Guest guest14 = new Guest("Stan", "Lee", secondGuestBirthDay);

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
    }
}

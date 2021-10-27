package pl.overlook.springhotelreservation.domain.reservation;

import ognl.Token;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import pl.overlook.springhotelreservation.Utils;
import pl.overlook.springhotelreservation.domain.reservation.token.ConfirmationToken;
import pl.overlook.springhotelreservation.domain.reservation.token.ConfirmationTokenRepository;
import pl.overlook.springhotelreservation.domain.reservation.token.ConfirmationTokenService;
import pl.overlook.springhotelreservation.domain.room.BedType;
import pl.overlook.springhotelreservation.domain.room.Room;
import pl.overlook.springhotelreservation.domain.room.RoomType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@SpringBootTest
class ReservationServiceTest {

    @InjectMocks
    ReservationService reservationService = new ReservationService();

    @Mock
    ConfirmationTokenService confirmationTokenService = new ConfirmationTokenService();

    @Mock
    private ReservationRepository mockRepository;

    @Mock
    private ConfirmationTokenRepository tokenRepository;



    @Test
    public void shouldThrowExceptionIfENdDateIsBeforeStartDate() {

        //given
        List<BedType> beds1 = new ArrayList<>();
        beds1.add(BedType.SINGLE);
        Room room1 = new Room(1, beds1, RoomType.STANDARD, false);

        LocalDate fromDate = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
        LocalDate toDate = LocalDate.of(LocalDate.now().getYear()+1, LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());

        Reservation reservation = new Reservation(room1, fromDate, toDate, LocalDateTime.now(), false);

        //when
        //then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    reservationService.createNewReservation(reservation);
                }).withMessageContaining("End date cannot be before start date");
    }

    @Test
    public void shouldDeleteUnconfirmedReservation(){

        //given
        List<BedType> beds1 = new ArrayList<>();
        beds1.add(BedType.SINGLE);
        Room room1 = new Room(1, beds1, RoomType.STANDARD, false);

        LocalDate fromDate = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth() +1);
        LocalDate toDate = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth() + 5);

        Reservation reservation = new Reservation(room1, fromDate, toDate, LocalDateTime.now().minusHours(1), false);
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation);

        when(mockRepository.findAllByConfirmedFalse()).thenReturn(reservations);

        //when
        //then
        assertThat(reservationService.removeUnconfirmedReservations()).isEqualTo("deleted unconfirmed reservations");
    }

    @Test
    public void shouldNotDeleteConfirmedReservation(){

        //given
        List<BedType> beds1 = new ArrayList<>();
        beds1.add(BedType.SINGLE);
        Room room1 = new Room(1, beds1, RoomType.STANDARD, false);

        LocalDate fromDate = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth() +1);
        LocalDate toDate = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth() + 5);

        Reservation reservation = new Reservation(room1, fromDate, toDate, LocalDateTime.now().minusHours(1), true);
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation);
        when(mockRepository.findAllByConfirmedFalse()).thenReturn(reservations);

        //when
        //then
        assertThat(reservationService.removeUnconfirmedReservations()).isEqualTo("no reservations for delete");
    }

    @Test
    public void shouldNotDeleteUnconfirmedReservationWhenTimeAfterDeleteUnconfirmedReservationNotPassed(){

        //given
        List<BedType> beds1 = new ArrayList<>();
        beds1.add(BedType.SINGLE);
        beds1.add(BedType.DOUBLE);
        beds1.add(BedType.KING_SIZE);
        Room room1 = new Room(1, beds1, RoomType.STANDARD, false);

        LocalDate fromDate = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth() +1);
        LocalDate toDate = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth() + 5);

        int minutesAfterCreation = Utils.MINUTES_AFTER_DELETE_UNCONFIRMED_RESERVATION/2;

        Reservation reservation = new Reservation(room1, fromDate, toDate, LocalDateTime.now().minusMinutes(minutesAfterCreation), false);
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation);
        when(mockRepository.findAllByConfirmedFalse()).thenReturn(reservations);

        //when
        //then
        assertThat(reservationService.removeUnconfirmedReservations()).isEqualTo("no reservations for delete");
    }
}
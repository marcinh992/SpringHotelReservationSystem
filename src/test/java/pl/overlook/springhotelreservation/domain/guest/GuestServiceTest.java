package pl.overlook.springhotelreservation.domain.guest;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class GuestServiceTest {

    GuestService guestService = new GuestService();


    @Test
    public void booleanTypeOfCheckingThatGuestIsAdultShouldBeTrue() {

        //given
        Guest guest1 = new Guest("John", "Doe", LocalDate.of(2000, 1, 1));

        //when
        //then
        assertThat(guestService.checkingThatGuestIsAdult(guest1)).isEqualTo(true);
    }

    @Test
    public void booleanTypeOfCheckingThatGuestIsAdultShouldBeFalse() {

        //given
        Guest guest2 = new Guest("Tom", "Smith", LocalDate.of(2017, 1, 1));

        //when
        //then
        assertThat(guestService.checkingThatGuestIsAdult(guest2)).isEqualTo(false);
    }

    @Test
    public void booleanTypeOfCheckingThatGuestIsAdultShouldBeTrueInHisEighteenBirthday() {

        //given
        int bday = LocalDate.now().getYear() - 18;
        LocalDate guestEighteenBirthday = LocalDate.of(bday, LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
        Guest guest = new Guest("John", "Doe", guestEighteenBirthday);

        //when
        //then
        assertThat(guestService.checkingThatGuestIsAdult(guest)).isEqualTo(true);
    }

    @Test
    public void booleanTypeOfCheckingThatGuestIsAdultShouldBeFalseOneDayBeforeHisEighteenBirthday() {

        //given
        int bdayYear = LocalDate.now().getYear() - 18;
        int bdayMonth = LocalDate.now().getMonthValue();
        int bdayDay = LocalDate.now().getDayOfMonth() + 1;
        LocalDate bday = LocalDate.of(bdayYear, bdayMonth, bdayDay);

        Guest guest = new Guest("John", "Doe", bday);

        //when
        //then
        assertThat(guestService.checkingThatGuestIsAdult(guest)).isEqualTo(false);
    }

    @Test
    public void createNewGuestShouldThrowExceptionWhenGuestIsNotAdult(){

        //given
        Guest guest = new Guest("John", "Doe", LocalDate.now());

        //when
        //then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() ->{
                    guestService.createNewGuest(guest);
                }).withMessageContaining("Rezerwujący musi być pełnoletni");
    }

}
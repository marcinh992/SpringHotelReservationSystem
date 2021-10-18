package pl.overlook.springhotelreservation.domain.reservation.token;

import org.junit.jupiter.api.Test;
import pl.overlook.springhotelreservation.domain.reservation.Reservation;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;


class ConfirmationTokenServiceTest {

    ConfirmationTokenService confirmationTokenService = new ConfirmationTokenService();

    @Test
    public void shouldCreateToken(){

        //given
        LocalDate start = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()+2);
        LocalDate end = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()+5);
        Reservation reservation = new Reservation(start, end);

        //when
        ConfirmationToken token = confirmationTokenService.createNewToken(reservation);

        //then
        assertThat(token).isNotNull();
        assertThat(token.getReservation()).isEqualTo(reservation);
    }

    @Test
    public void shouldReservationInTokenBeEqualToReservationInMethodParameter(){

        //given
        LocalDate start = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()+2);
        LocalDate end = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()+5);
        Reservation reservation = new Reservation(start, end);

        //when
        ConfirmationToken token = confirmationTokenService.createNewToken(reservation);

        //then
        assertThat(token.getReservation()).isEqualTo(reservation);
    }

    @Test
    public void allGeneratedTokensLengthShouldBeEqualsTo5(){

        //given
        //when
        String token1 = confirmationTokenService.generateCodeForToken();
        String token2 = confirmationTokenService.generateCodeForToken();
        String token3 = confirmationTokenService.generateCodeForToken();
        String token4 = confirmationTokenService.generateCodeForToken();

        //then
        assertThat(token1).hasSize(5);
        assertThat(token2).hasSize(5);
        assertThat(token3).hasSize(5);
        assertThat(token4).hasSize(5);
    }

    @Test
    public void allCodesForTokensShouldBeDifferent(){

        //given
        //when
        String token1 = confirmationTokenService.generateCodeForToken();
        String token2 = confirmationTokenService.generateCodeForToken();
        String token3 = confirmationTokenService.generateCodeForToken();
        String token4 = confirmationTokenService.generateCodeForToken();

        //then
        assertThat(token1).isNotEqualTo(token2).isNotEqualTo(token3).isNotEqualTo(token4);
    }

    @Test
    public void newlyCreatedTokenShouldNotBeConfirmed(){

        //given
        LocalDate start = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()+2);
        LocalDate end = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()+5);
        Reservation reservation = new Reservation(start, end);

        //when
        ConfirmationToken token = confirmationTokenService.createNewToken(reservation);

        //then
        assertThat(token.getConfirmedAt()).isNull();
    }







}
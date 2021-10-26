package pl.overlook.springhotelreservation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import pl.overlook.springhotelreservation.domain.reservation.ReservationService;

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true)
public class SchedulingConfig {

    final
    ReservationService reservationService;

    public SchedulingConfig(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Scheduled(initialDelayString = "PT15M", fixedDelayString = "PT15M")
    void deleteUnconfirmedReservations() {
        reservationService.removeUnconfirmedReservations();
    }
}

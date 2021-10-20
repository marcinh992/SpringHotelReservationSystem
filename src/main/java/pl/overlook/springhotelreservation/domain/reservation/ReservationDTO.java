package pl.overlook.springhotelreservation.domain.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class ReservationDTO {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fromDate;

    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate toDate;

    @Min(value = 1, message = "Pokoje mieszczą od jednej do sześciu osób")
    @Max(value = 6, message = "Pokoje mieszczą od jednej do sześciu osób")
    private int roomSize;
}

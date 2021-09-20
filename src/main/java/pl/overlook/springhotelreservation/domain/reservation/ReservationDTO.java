package pl.overlook.springhotelreservation.domain.reservation;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import java.time.LocalDate;

public class ReservationDTO {


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fromDate;

    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate toDate;

    public ReservationDTO(LocalDate fromDate, LocalDate toDate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }
}

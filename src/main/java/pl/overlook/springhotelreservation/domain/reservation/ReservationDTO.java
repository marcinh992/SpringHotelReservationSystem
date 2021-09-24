package pl.overlook.springhotelreservation.domain.reservation;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ReservationDTO {


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fromDate;

    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate toDate;

    @Min(value = 1, message = "Pokoje mieszczą od jednej do sześciu osób")
    @Max(value = 6, message = "Pokoje mieszczą od jednej do sześciu osób")
    private int roomSize;

    public ReservationDTO(LocalDate fromDate, LocalDate toDate, int roomSize) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.roomSize = roomSize;
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

    public int getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(int roomSize) {
        this.roomSize = roomSize;
    }
}

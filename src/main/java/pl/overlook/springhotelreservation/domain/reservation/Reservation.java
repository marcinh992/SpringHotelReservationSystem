package pl.overlook.springhotelreservation.domain.reservation;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import pl.overlook.springhotelreservation.domain.guest.Guest;
import pl.overlook.springhotelreservation.domain.room.Room;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Room room;

    @OneToOne
    private Guest guest;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fromDate;

    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate toDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdDate;

    private boolean confirmed;

    public Reservation() {
    }

    public Reservation(Room room, Guest guest, LocalDate fromDate, LocalDate toDate, LocalDateTime createdDate) {
        this.room = room;
        this.guest = guest;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.createdDate = createdDate;
    }

    public Reservation(LocalDate fromDate, LocalDate toDate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Reservation(Room room, LocalDate fromDate, LocalDate toDate, LocalDateTime createdDate, boolean confirmed) {
        this.room = room;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.createdDate = createdDate;
        this.confirmed = confirmed;
    }

    public Reservation(Long id, Room room, Guest guest, LocalDate fromDate, LocalDate toDate, LocalDateTime createdDate, boolean confirmed) {
        this.id = id;
        this.room = room;
        this.guest = guest;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.createdDate = createdDate;
        this.confirmed = confirmed;
    }
}

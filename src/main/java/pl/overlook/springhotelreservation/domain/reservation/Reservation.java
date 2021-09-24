package pl.overlook.springhotelreservation.domain.reservation;

import org.apache.tomcat.jni.Local;
import org.springframework.format.annotation.DateTimeFormat;
import pl.overlook.springhotelreservation.domain.guest.Guest;
import pl.overlook.springhotelreservation.domain.room.Room;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

    public Reservation(Room room, LocalDate fromDate, LocalDate toDate, LocalDateTime createdDate) {
        this.room = room;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.createdDate = createdDate;

    }

    public Reservation(Long id, Room room, Guest guest, LocalDate fromDate, LocalDate toDate, LocalDateTime createdDate) {
        this.id = id;
        this.room = room;
        this.guest = guest;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
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

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}

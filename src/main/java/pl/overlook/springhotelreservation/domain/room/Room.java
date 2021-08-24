package pl.overlook.springhotelreservation.domain.room;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Min(value = 1, message = "Nadaj numer pokoju")
    @Max(value = 100, message = "Numer pokoju przekracza liczbę pokoi w hotelu")
    private int number;


    private BedType beds;


    @Min(value = 1, message = "Minimalna liczba łazienek w pokoju to 1")
    @Max(value = 2, message = "Maksymalna liczba łazienek w pokoju to 2")
    private int bathrooms;

    private boolean view;

    public Room() {
    }

    public Room(int number, BedType beds, int bathrooms, boolean view) {
        this.number = number;
        this.beds = beds;
        this.bathrooms = bathrooms;
        this.view = view;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public BedType getBeds() {
        return beds;
    }

    public void setBeds(BedType beds) {
        this.beds = beds;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public boolean isView() {
        return view;
    }

    public void setView(boolean view) {
        this.view = view;
    }
}

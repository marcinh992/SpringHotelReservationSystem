package pl.overlook.springhotelreservation.domain.room;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Getter
@Setter
@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Min(value = 1, message = "Nadaj numer pokoju")
    @Max(value = 100, message = "Numer pokoju przekracza liczbę pokoi w hotelu")
    private int number;

    @ElementCollection
    private List<BedType> beds;

    private int size;

    @Enumerated
    private RoomType roomType;

    private boolean panoramicView;

    public Room() {
    }

    public Room(int number, List<BedType> beds, RoomType roomType, boolean panoramicView) {
        this.number = number;
        this.beds = beds;
        this.roomType = roomType;
        this.panoramicView = panoramicView;
    }
}

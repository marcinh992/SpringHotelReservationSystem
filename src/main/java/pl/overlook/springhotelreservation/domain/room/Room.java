package pl.overlook.springhotelreservation.domain.room;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

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

    public List<BedType> getBeds() {
        return beds;
    }

    public void setBeds(List<BedType> beds) {
        this.beds = beds;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public boolean isPanoramicView() {
        return panoramicView;
    }

    public void setPanoramicView(boolean panoramicView) {
        this.panoramicView = panoramicView;
    }

}

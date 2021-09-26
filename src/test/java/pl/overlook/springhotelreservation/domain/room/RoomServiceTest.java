package pl.overlook.springhotelreservation.domain.room;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertThrows;


class RoomServiceTest {

    RoomService roomService = new RoomService();

    @Test
    public void shouldDeleteAllNoneBedTypeValue() {

        //given
        List<BedType> beds = new ArrayList<>();
        beds.add(BedType.NONE);
        beds.add(BedType.DOUBLE);
        beds.add(BedType.NONE);
        Room room = new Room(1, beds, RoomType.STANDARD, false);

        //when
        roomService.deleteAllNoneBedTypeValue(room);

        //then
        assertThat(room.getBeds()).doesNotContain(BedType.NONE);
    }

    @Test
    public void calculateRoomSizeTest() {

        //given
        List<BedType> beds1 = new ArrayList<>();
        beds1.add(BedType.SINGLE);
        beds1.add(BedType.DOUBLE);
        beds1.add(BedType.KING_SIZE);
        Room room1 = new Room(1, beds1, RoomType.STANDARD, false);

        List<BedType> beds2 = new ArrayList<>();
        beds2.add(BedType.DOUBLE);
        beds2.add(BedType.DOUBLE);
        Room room2 = new Room(1, beds2, RoomType.STANDARD, false);

        //when
        //then
        assertThat(roomService.calculateRoomSize(room1)).isEqualTo(5);
        assertThat(roomService.calculateRoomSize(room2)).isEqualTo(4);
    }

    @Test
    public void shouldShowFittedSizeRooms(){

        //given
        List<BedType> beds1 = new ArrayList<>();
        beds1.add(BedType.SINGLE);
        beds1.add(BedType.DOUBLE);
        beds1.add(BedType.KING_SIZE);
        Room room1 = new Room(1, beds1, RoomType.STANDARD, false);

        int room1Size = roomService.calculateRoomSize(room1);
        room1.setSize(room1Size);

        List<BedType> beds2 = new ArrayList<>();
        beds2.add(BedType.DOUBLE);
        Room room2 = new Room(1, beds2, RoomType.STANDARD, false);

        int room2Size = roomService.calculateRoomSize(room2);
        room2.setSize(room2Size);

        //when



    }




}
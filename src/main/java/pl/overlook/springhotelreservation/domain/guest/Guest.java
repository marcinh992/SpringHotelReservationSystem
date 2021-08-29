package pl.overlook.springhotelreservation.domain.guest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Imie nie może być puste")
    @Size(min = 2, max = 45, message = "Imię musi mieścić się w przedziale między 2, a 45 znaków")
    private String firstName;

    @NotNull(message = "Nazwisko nie może być puste")
    @Size(min = 2, max = 45, message = "Nazwisko musi mieścić się w przedziale między 2, a 45 znaków")
    private String lastName;

    private boolean underEighteen;

    private boolean reservationPerson;

    public Guest() {
    }

    public Guest(String firstName, String lastName, boolean isUnderEighteen, boolean isReservationPerson) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.underEighteen = underEighteen;
        this.reservationPerson = reservationPerson;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isUnderEighteen() {
        return underEighteen;
    }

    public void setUnderEighteen(boolean underEighteen) {
        this.underEighteen = underEighteen;
    }

    public boolean isReservationPerson() {
        return reservationPerson;
    }

    public void setReservationPerson(boolean reservationPerson) {
        this.reservationPerson = reservationPerson;
    }
}

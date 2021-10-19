package pl.overlook.springhotelreservation.domain.guest;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Imie nie może być puste")
    @Size(min = 2, max = 45, message = "Imię musi mieścić się w przedziale między 2, a 45 znpaków")
    private String firstName;

    @NotNull(message = "Nazwisko nie może być puste")
    @Size(min = 2, max = 45, message = "Nazwisko musi mieścić się w przedziale między 2, a 45 znaków")
    private String lastName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @Email
    private String email;

    public Guest() {
    }

    public Guest(String firstName, String lastName, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", " + firstName + " " + lastName;
    }
}

package pl.overlook.springhotelreservation.domain.guest;

import org.hibernate.validator.constraints.Range;

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

    @NotNull(message = "Pole wiek nie może być puste")
    @Range(min = 1, max = 110, message = "Wiek musi mieścić się w zakresie od 1 do 110")
    private int age;

    public Guest() {
    }

    public Guest(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "ID Gościa: " + id + " Dane: " + firstName + " " + lastName + " " + age;
    }
}

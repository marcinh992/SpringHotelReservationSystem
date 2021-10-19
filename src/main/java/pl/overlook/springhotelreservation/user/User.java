package pl.overlook.springhotelreservation.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Imie nie może być puste")
    @Size(min = 2, max = 45, message = "Imię musi mieścić się w przedziale między 2, a 45 znaków")
    private String firstName;

    @NotNull(message = "Nazwisko nie może być puste")
    @Size(min = 2, max = 45, message = "Nazwisko musi mieścić się w przedziale między 2, a 45 znaków")
    private String lastName;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty(message = "Hasło nie może być puste")
    private String password;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<UserRole> roles = new HashSet<>();
}

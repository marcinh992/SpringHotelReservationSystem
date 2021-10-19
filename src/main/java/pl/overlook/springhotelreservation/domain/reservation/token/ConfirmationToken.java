package pl.overlook.springhotelreservation.domain.reservation.token;

import lombok.Getter;
import lombok.Setter;
import pl.overlook.springhotelreservation.domain.reservation.Reservation;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 5, max = 5, message = "Kod weryfikacyjny musi mieć 5 znaków")
    private String token;

    private LocalDateTime createdAt;

    @Future
    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;

    @ManyToOne
    private Reservation reservation;

    public ConfirmationToken() {
    }

    public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiresAt, Reservation reservation) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.reservation = reservation;
    }
}

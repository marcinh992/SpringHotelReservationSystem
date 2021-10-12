package pl.overlook.springhotelreservation.domain.reservation.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.overlook.springhotelreservation.Utils;
import pl.overlook.springhotelreservation.domain.reservation.Reservation;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class ConfirmationTokenService {

    @Autowired
    private  ConfirmationTokenRepository repository;

    public void saveConfirmationToken(ConfirmationToken token){
        repository.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token){
        return repository.findByToken(token);
    }

    public int setConfirmedAt(String token){
        return repository.updateConfirmedAt(token, LocalDateTime.now());
    }

    public ConfirmationToken createNewToken(Reservation reservation){

        String token = UUID.randomUUID().toString();

        return new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(Utils.MINUTES_AFTER_TOKEN_EXPIRED),
                reservation
        );
    }




}

package pl.overlook.springhotelreservation.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private JavaMailSender mailSender;


    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendConfirmationCode(String guestEmail, String token) throws MailException {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(guestEmail);
        mail.setFrom("overlookSpring@gmail.com");
        mail.setSubject("Hotel Overlook - kod weryfikacyjny");
        mail.setText("Kod do potwierdzenia rezerwacji: " + token + "\n" + " Kod jest ważny 30min, po upływie tego czasu " +
                "niepotwierdzona rezerwacja zostanie usunięta.");

        mailSender.send(mail);
    }

}

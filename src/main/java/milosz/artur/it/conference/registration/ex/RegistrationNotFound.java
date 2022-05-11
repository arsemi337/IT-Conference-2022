package milosz.artur.it.conference.registration.ex;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RegistrationNotFound extends RuntimeException {
    public RegistrationNotFound(UUID uuid) {
        super("Nie znaleziono rezerwacji o id " + uuid);
    }
}

package milosz.artur.it.conference.user.ex;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)

public class UserWithGivenLoginAlreadyExistsException extends RuntimeException {
    public UserWithGivenLoginAlreadyExistsException() {
        super("Podany login jest już zajęty");
    }

}

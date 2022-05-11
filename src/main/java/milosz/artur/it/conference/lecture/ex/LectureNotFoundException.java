package milosz.artur.it.conference.lecture.ex;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.UUID;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class LectureNotFoundException extends RuntimeException {
    public LectureNotFoundException(UUID uuid) {
        super("Nie znaleziono wykładu o uuid " + uuid.toString() + ". ");
    }
}

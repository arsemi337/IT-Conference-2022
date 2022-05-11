package milosz.artur.it.conference.lecture.ex;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class LectureNotFoundByPathException extends RuntimeException {
    public LectureNotFoundByPathException(String path) {
        super("Nie znaleziono wykładów w ścieżce tematycznej " + path + ". ");
    }

}

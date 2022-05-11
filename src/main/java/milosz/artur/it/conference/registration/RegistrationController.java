package milosz.artur.it.conference.registration;

import io.swagger.annotations.Api;
import milosz.artur.it.conference.lecture.domain.Lecture;
import milosz.artur.it.conference.lecture.services.LectureService;
import milosz.artur.it.conference.models.CreateRegistrationRequest;
import milosz.artur.it.conference.registration.domain.Registration;
import milosz.artur.it.conference.registration.services.RegistrationService;
import milosz.artur.it.conference.user.domain.User;
import milosz.artur.it.conference.user.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@RestController
@Api(tags = "Registrations")
public class RegistrationController {
    private final RegistrationService registrationService;
    private final LectureService lectureService;
    private final UserService userService;

    RegistrationController(RegistrationService registrationService, LectureService lectureService, UserService userService) {
        this.registrationService = registrationService;
        this.lectureService = lectureService;
        this.userService = userService;
    }

    @PostMapping("/registrations/create")
    ResponseEntity<String> createRegistration(@RequestBody CreateRegistrationRequest createRegistrationRequest) throws IOException {
        Lecture lecture = lectureService.findById(createRegistrationRequest.getLectureId());
        User user = userService.tryToCreateNewUser(createRegistrationRequest.getUserLogin(), createRegistrationRequest.getUserEmail());

        if (!lectureService.arePlacesAvailable(lecture)) {
            return ResponseEntity.badRequest().body("Nie dokonano rezerwacji. Wszystkie miejsca na tym wykładzie są już zajęte. ");
        }
        if (!lectureService.isUserAvailableAtThisTime(lecture, user)) {
            return ResponseEntity.badRequest().body("Nie dokonano rezerwacji. Użytkownik już zapisał się na wykład o tej godzinie. ");
        }
        lectureService.decreaseAvailablePlacesNumber(lecture);
        registrationService.createRegistration(user, lecture);
        registrationService.sendConfirmationEmail(user);
        return ResponseEntity.status(HttpStatus.OK).body("Dokonano rezerwacji");
    }

    @DeleteMapping("/registrations/delete")
    ResponseEntity<String> deleteRegistration(@RequestParam UUID uuid) {
        Registration registration = registrationService.getRegistrationById(uuid);
        Lecture lecture = lectureService.findById(registration.getLectureId());
        lecture.increaseAvailablePlacesNumber();

        registrationService.deleteRegistration(uuid);
        return ResponseEntity.status(HttpStatus.OK).body("Rezerwacja została usunięta");
    }
}

package milosz.artur.it.conference.registration;

import milosz.artur.it.conference.lecture.Lecture;
import milosz.artur.it.conference.lecture.LectureService;
import milosz.artur.it.conference.models.CreateRegistrationRequest;
import milosz.artur.it.conference.user.User;
import milosz.artur.it.conference.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class RegistrationController {
    private final RegistrationService registrationService;
    private final LectureService lectureService;
    private final UserService userService;

    RegistrationController(RegistrationService registrationService, LectureService lectureService, UserService userService)
    {
        this.registrationService = registrationService;
        this.lectureService = lectureService;
        this.userService = userService;
    }

    @GetMapping("registrations")
    List<Registration> getAll()
    {
        return registrationService.getAll();
    }

    @PostMapping("/registrations/create")
    ResponseEntity<String> createRegistration(@RequestBody CreateRegistrationRequest createRegistrationRequest) throws IOException {
        Lecture lecture = lectureService.findById(createRegistrationRequest.getLectureId());
        User user = userService.tryToCreateNewUser(createRegistrationRequest.getUserLogin(), createRegistrationRequest.getUserEmail());

        if (lectureService.canRegister(lecture))
        {
            lectureService.decreaseAvailablePlacesNumber(lecture);
            registrationService.createRegistration(user, lecture);
            registrationService.sendConfirmationEmail(user);
            return ResponseEntity.status(HttpStatus.OK).body("Dokonano rezerwacji");
        } else {
            return ResponseEntity.badRequest().body("Nie dokonano rezerwacji. Wszystkie miejsca na tym wykładzie są już zajęte. ");
        }
    }
}

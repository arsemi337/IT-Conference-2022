package milosz.artur.it.conference.registration;

import milosz.artur.it.conference.lecture.Lecture;
import milosz.artur.it.conference.lecture.LectureService;
import milosz.artur.it.conference.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class RegistrationController {
    private final RegistrationService registrationService;
    private final LectureService lectureService;

    RegistrationController(RegistrationService registrationService, LectureService lectureService)
    {
        this.registrationService = registrationService;
        this.lectureService = lectureService;
    }

    @GetMapping("registrations")
    List<Registration> getAll()
    {
        return registrationService.getAll();
    }

    @PostMapping("/registrations/create")
    ResponseEntity<String> createRegistration(@RequestParam UUID uuid)
    {
        Lecture lecture = lectureService.findById(uuid);

        if (lectureService.canRegister(lecture))
        {
            registrationService.createRegistration(lecture);
            return ResponseEntity.status(HttpStatus.OK).body("Dokonano rezerwacji");
        } else {
            return ResponseEntity.badRequest().body("Nie dokonano rezerwacji");
        }
    }
}

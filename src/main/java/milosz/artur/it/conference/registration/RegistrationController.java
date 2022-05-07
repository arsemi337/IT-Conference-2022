package milosz.artur.it.conference.registration;

import milosz.artur.it.conference.user.UserService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {
    private final RegistrationService registrationService;

    RegistrationController(RegistrationService registrationService)
    {
        this.registrationService = registrationService;
    }
}

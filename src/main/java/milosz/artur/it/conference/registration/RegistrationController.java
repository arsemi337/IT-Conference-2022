package milosz.artur.it.conference.registration;

import milosz.artur.it.conference.user.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class RegistrationController {
    private final RegistrationService registrationService;

    RegistrationController(RegistrationService registrationService)
    {
        this.registrationService = registrationService;
    }

    @GetMapping("registrations")
    List<Registration> getAll()
    {
        return registrationService.getAll();
    }

    @PostMapping("/registrations/create{uuid}")
    void createRegistration(@PathVariable UUID uuid)
    {
        registrationService.createRegistration(uuid);
    }
}

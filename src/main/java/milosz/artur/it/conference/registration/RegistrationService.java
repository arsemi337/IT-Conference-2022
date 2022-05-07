package milosz.artur.it.conference.registration;

import milosz.artur.it.conference.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    private final RegistrationRepository registrationRepository;

    RegistrationService(RegistrationRepository registrationRepository)
    {
        this.registrationRepository = registrationRepository;
    }
}

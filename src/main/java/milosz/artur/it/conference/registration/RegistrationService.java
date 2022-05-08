package milosz.artur.it.conference.registration;

import milosz.artur.it.conference.lecture.Lecture;
import milosz.artur.it.conference.lecture.LectureRepository;
import milosz.artur.it.conference.user.User;
import milosz.artur.it.conference.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RegistrationService {
    private final RegistrationRepository registrationRepository;
    private final UserRepository userRepository;
    private final LectureRepository lectureRepository;

    RegistrationService(RegistrationRepository registrationRepository, UserRepository userRepository, LectureRepository lectureRepository)
    {
        this.registrationRepository = registrationRepository;
        this.userRepository = userRepository;
        this.lectureRepository = lectureRepository;
    }

    List<Registration> getAll()
    {
        return registrationRepository.findAll();
    }

    List<Registration> getRegistrationsByUserId(UUID id)
    {
        return registrationRepository.getRegistrationsByUserId(id);
    }

    public void createRegistration(UUID lectureId)
    {
        String login = "Crust";
        String email = "crust@email.com";

        User user = userRepository.getUserByLoginAndEmail(login, email);
//        Optional<Lecture> lectureOptional = lectureRepository.findById(lectureId);
//        Lecture lecture;
//        if (lectureOptional.isPresent())
//        {
//            lecture = lectureOptional.get();
//            registrationRepository.save(new Registration(user.getId(), lecture.getId()));
//        }
        registrationRepository.save(new Registration(user.getId(), lectureId));

    }
}

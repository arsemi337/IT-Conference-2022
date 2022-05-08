package milosz.artur.it.conference.lecture;

import milosz.artur.it.conference.registration.Registration;
import milosz.artur.it.conference.registration.RegistrationRepository;
import milosz.artur.it.conference.user.User;
import milosz.artur.it.conference.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LectureService {
    private final LectureRepository lectureRepository;
    private final UserRepository userRepository;
    private final RegistrationRepository registrationRepository;

    LectureService(LectureRepository lectureRepository, UserRepository userRepository, RegistrationRepository registrationRepository)
    {
        this.lectureRepository = lectureRepository;
        this.userRepository = userRepository;
        this.registrationRepository = registrationRepository;
    }

    List<Lecture> getAll()
    {
        return lectureRepository.findAll();
    }

    String conferencePlan()  {
        StringBuilder conferencePlan;
        conferencePlan = new StringBuilder("Date: 01.06.2022\n" +
                "Each lecture lasts 1h 45 min.\n" +
                "The lecture time slots are:\n" +
                "10:00 - 11:45\n" +
                "12:00 - 13:45\n" +
                "14:00 - 15:45\n" +
                "Lectures:\n");
        for (Lecture lecture : this.getAll())
        {
            conferencePlan.append(lecture.toString()).append('\n');
        }
        return conferencePlan.toString();
    }

    List<Lecture> getLecturesOfUserByLogin(String login)
    {
        User user = userRepository.getUserByLogin(login);
        List<Registration> registrations = registrationRepository.getRegistrationsByUserId(user.getId());
        List<Lecture> lectures = new ArrayList<>();
        for (Registration registration : registrations)
        {
            Optional<Lecture> lecture = lectureRepository.findById(registration.getId());
            lecture.ifPresent(lectures::add);
        }
        return lectures;
    }
}

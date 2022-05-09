package milosz.artur.it.conference.lecture;

import milosz.artur.it.conference.lecture.ex.LectureNotFoundException;
import milosz.artur.it.conference.models.ConferenceResponse;
import milosz.artur.it.conference.registration.Registration;
import milosz.artur.it.conference.registration.RegistrationRepository;
import milosz.artur.it.conference.user.User;
import milosz.artur.it.conference.user.UserRepository;
import milosz.artur.it.conference.user.ex.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

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

    public Lecture findById(UUID uuid)
    {
        return lectureRepository.findById(uuid).orElseThrow(() -> new LectureNotFoundException(uuid));
    }

    public List<Lecture> getAll()
    {
        return lectureRepository.findAll();
    }

    public ConferenceResponse conferencePlan()  {
        return new ConferenceResponse(this.getAll());
    }

    public List<Lecture> getLecturesOfUserByLogin(String login)
    {
        User user = userRepository.getUserByLogin(login).orElseThrow(() -> new UserNotFoundException(login));
        List<Registration> registrations = registrationRepository.getRegistrationsByUserId(user.getId());
        List<Lecture> lectures = new ArrayList<>();
        for (Registration registration : registrations)
        {
            Optional<Lecture> lecture = lectureRepository.findById(registration.getId());
            lecture.ifPresent(lectures::add);
        }
        return lectures;
    }

    public boolean canRegister(Lecture lecture)
    {
        return lecture.getAvailablePlaces() > 0;
    }

    public void decreaseAvailablePlacesNumber(Lecture lecture)
    {
        lecture.decreaseAvailablePlacesNumber();
        lectureRepository.save(lecture);
    }
}

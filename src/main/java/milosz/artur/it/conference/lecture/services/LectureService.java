package milosz.artur.it.conference.lecture.services;

import milosz.artur.it.conference.lecture.domain.Lecture;
import milosz.artur.it.conference.lecture.domain.LectureRepository;
import milosz.artur.it.conference.lecture.ex.LectureNotFoundException;
import milosz.artur.it.conference.models.ReadConferenceResponse;
import milosz.artur.it.conference.models.ReadLectureResponse;
import milosz.artur.it.conference.registration.domain.Registration;
import milosz.artur.it.conference.registration.domain.RegistrationRepository;
import milosz.artur.it.conference.registration.ex.RegistrationForUserNotFound;
import milosz.artur.it.conference.user.domain.User;
import milosz.artur.it.conference.user.domain.UserRepository;
import milosz.artur.it.conference.user.ex.UserNotFoundException;
import org.springframework.stereotype.Service;
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

    public ReadConferenceResponse conferencePlan()  {
        return new ReadConferenceResponse(this.getAll());
    }

    public List<ReadLectureResponse> getLecturesOfUserByLogin(String login)
    {
        User user = userRepository.getUserByLogin(login).orElseThrow(() -> new UserNotFoundException(login));
        List<Registration> registrations = registrationRepository.getRegistrationsByUserId(user.getId())
                .orElseThrow(RegistrationForUserNotFound::new);
        List<ReadLectureResponse> lectures = new ArrayList<>();
        for (Registration registration : registrations)
        {
            Optional<Lecture> lecture = lectureRepository.findById(registration.getLectureId());
            if (lecture.isPresent())
            {
                lectures.add(lecture.get().toReadLecturesResponse(lecture.get().getTitle(), lecture.get().getStartTime()));
            }
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

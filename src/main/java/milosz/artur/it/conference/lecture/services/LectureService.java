package milosz.artur.it.conference.lecture.services;

import milosz.artur.it.conference.lecture.domain.Lecture;
import milosz.artur.it.conference.lecture.domain.LectureRepository;
import milosz.artur.it.conference.lecture.ex.LectureNotFoundByPathException;
import milosz.artur.it.conference.lecture.ex.LectureNotFoundException;
import milosz.artur.it.conference.models.ReadConferenceResponse;
import milosz.artur.it.conference.models.ReadLectureResponse;
import milosz.artur.it.conference.models.ReadLecturesByInterestResponse;
import milosz.artur.it.conference.models.ReadPathsByInterestResponse;
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

    LectureService(LectureRepository lectureRepository, UserRepository userRepository, RegistrationRepository registrationRepository) {
        this.lectureRepository = lectureRepository;
        this.userRepository = userRepository;
        this.registrationRepository = registrationRepository;
    }

    public Lecture findById(UUID uuid) {
        return lectureRepository.findById(uuid).orElseThrow(() -> new LectureNotFoundException(uuid));
    }

    public List<Lecture> getAll() {
        return lectureRepository.findAll();
    }

    public ReadConferenceResponse conferencePlan() {
        return new ReadConferenceResponse(this.getAll());
    }

    public List<ReadLectureResponse> getLecturesOfUserByLogin(String login) {
        User user = userRepository.getUserByLogin(login).orElseThrow(() -> new UserNotFoundException(login));
        List<Registration> registrations = registrationRepository.getRegistrationsByUserId(user.getId())
                .orElseThrow(RegistrationForUserNotFound::new);
        List<ReadLectureResponse> lectures = new ArrayList<>();
        for (Registration registration : registrations) {
            Optional<Lecture> lecture = lectureRepository.findById(registration.getLectureId());
            lecture.ifPresent(value -> lectures.add(value.toReadLecturesResponse(value.getTitle(), value.getStartTime())));
        }
        return lectures;
    }

    public boolean arePlacesAvailable(Lecture lecture) {
        return lecture.getAvailablePlaces() > 0;
    }

    public boolean isUserAvailableAtThisTime(Lecture lecture, User user) {
        boolean isUserAvailableAtThisTime = true;
        List<ReadLectureResponse> readLectureResponses = this.getLecturesOfUserByLogin(user.getLogin());
        for (ReadLectureResponse readLectureResponse : readLectureResponses) {
            if (readLectureResponse.getStartTime().equals(lecture.getStartTime())) {
                isUserAvailableAtThisTime = false;
                break;
            }
        }
        return isUserAvailableAtThisTime;
    }

    public void decreaseAvailablePlacesNumber(Lecture lecture) {
        lecture.decreaseAvailablePlacesNumber();
        lectureRepository.save(lecture);
    }

    public List<ReadLecturesByInterestResponse> getLecturesByInterest() {
        List<Lecture> lectures = lectureRepository.findAll();
        List<ReadLecturesByInterestResponse> readLecturesByInterestResponses = new ArrayList<>();
        for (Lecture lecture : lectures) {
            readLecturesByInterestResponses.add(lecture.toReadLecturesByInterestResponse(
                    lecture.getTitle(),
                    lecture.getPath(),
                    lecture.getStartTime(),
                    lecture.getPercentageOfRegistrations()
            ));
        }
        Collections.sort(readLecturesByInterestResponses);
        Collections.reverse(readLecturesByInterestResponses);
        return readLecturesByInterestResponses;
    }

    public List<ReadPathsByInterestResponse> getPathsByInterest() {
        List<Lecture> lecturesFirstPath = lectureRepository.getLecturesByPath("First path")
                .orElseThrow(() -> new LectureNotFoundByPathException("First path"));
        List<Lecture> lecturesSecondPath = lectureRepository.getLecturesByPath("Second path")
                .orElseThrow(() -> new LectureNotFoundByPathException("Second path"));
        List<Lecture> lecturesThirdPath = lectureRepository.getLecturesByPath("Third path")
                .orElseThrow(() -> new LectureNotFoundByPathException("Third path"));

        int firstPathAvailablePlaces = 0, secondPathAvailablePlaces = 0, thirdPathAvailablePlaces = 0;

        for (Lecture lecture : lecturesFirstPath) {
            firstPathAvailablePlaces += lecture.getAvailablePlaces();
        }
        for (Lecture lecture : lecturesSecondPath) {
            secondPathAvailablePlaces += lecture.getAvailablePlaces();
        }
        for (Lecture lecture : lecturesThirdPath) {
            thirdPathAvailablePlaces += lecture.getAvailablePlaces();
        }

        int firstPathRegistrations, secondPathRegistrations, thirdPathRegistrations;

        firstPathRegistrations = 15 - firstPathAvailablePlaces;
        secondPathRegistrations = 15 - secondPathAvailablePlaces;
        thirdPathRegistrations = 15 - thirdPathAvailablePlaces;

        double firstPathRegistrationsPercentage, secondPathRegistrationsPercentage, thirdPathRegistrationsPercentage;

        firstPathRegistrationsPercentage = firstPathRegistrations / 15.0 * 100;
        secondPathRegistrationsPercentage = secondPathRegistrations / 15.0 * 100;
        thirdPathRegistrationsPercentage = thirdPathRegistrations / 15.0 * 100;

        List<ReadPathsByInterestResponse> readPathsByInterestResponses = new ArrayList<>();

        readPathsByInterestResponses.add(new ReadPathsByInterestResponse("First path", firstPathRegistrationsPercentage));
        readPathsByInterestResponses.add(new ReadPathsByInterestResponse("Second path", secondPathRegistrationsPercentage));
        readPathsByInterestResponses.add(new ReadPathsByInterestResponse("Third path", thirdPathRegistrationsPercentage));

        Collections.sort(readPathsByInterestResponses);
        Collections.reverse(readPathsByInterestResponses);

        return readPathsByInterestResponses;
    }
}

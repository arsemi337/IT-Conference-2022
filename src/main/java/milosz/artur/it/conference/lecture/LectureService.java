package milosz.artur.it.conference.lecture;

import milosz.artur.it.conference.user.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LectureService {
    private final LectureRepository lectureRepository;

    LectureService(LectureRepository lectureRepository)
    {
        this.lectureRepository = lectureRepository;
    }

    List<Lecture> getAll()
    {
        return lectureRepository.findAll();
    }

    List<String> conferencePlan()  {
        List<String> conferencePlan = new ArrayList<>();
        conferencePlan.add("Date: 01.06.2022");
        conferencePlan.add("Each lecture lasts 1h 45 min. ");
        conferencePlan.add("The lecture time slots are:");
        conferencePlan.add("10:00 - 11:45");
        conferencePlan.add("12:00 - 13:45");
        conferencePlan.add("14:00 - 15:45");
        conferencePlan.add("Lectures:");
        for (Lecture lecture : this.getAll())
        {
            conferencePlan.add(lecture.toString());
        }
        return conferencePlan;
    }
}

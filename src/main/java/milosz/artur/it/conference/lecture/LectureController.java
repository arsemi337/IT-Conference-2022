package milosz.artur.it.conference.lecture;

import milosz.artur.it.conference.user.User;
import milosz.artur.it.conference.user.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LectureController {
    private final LectureService lectureService;

    LectureController(LectureService lectureService)
    {
        this.lectureService = lectureService;
    }

    @GetMapping("/lectures/conferenceInfo")
    List<String> conferenceInfo()
    {
        return lectureService.conferencePlan();
    }
}

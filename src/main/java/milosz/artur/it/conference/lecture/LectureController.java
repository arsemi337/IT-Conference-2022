package milosz.artur.it.conference.lecture;

import milosz.artur.it.conference.lecture.services.LectureService;
import milosz.artur.it.conference.models.ReadConferenceResponse;
import milosz.artur.it.conference.models.ReadLectureResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    ReadConferenceResponse conferenceInfo()
    {
        return lectureService.conferencePlan();
    }

    @GetMapping("/lectures/allByUserLogin")
    ResponseEntity<List<ReadLectureResponse>> getByUserLogin(@RequestParam String login)
    {
        return ResponseEntity.status(HttpStatus.OK).body(lectureService.getLecturesOfUserByLogin(login));
    }
}

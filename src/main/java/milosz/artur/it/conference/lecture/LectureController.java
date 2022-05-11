package milosz.artur.it.conference.lecture;

import io.swagger.annotations.Api;
import milosz.artur.it.conference.lecture.services.LectureService;
import milosz.artur.it.conference.models.ReadConferenceResponse;
import milosz.artur.it.conference.models.ReadLectureResponse;
import milosz.artur.it.conference.models.ReadLecturesByInterestResponse;
import milosz.artur.it.conference.models.ReadPathsByInterestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "Lectures")
public class LectureController {
    private final LectureService lectureService;

    LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @GetMapping("/lectures/conferenceInfo")
    ResponseEntity<ReadConferenceResponse> conferenceInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(lectureService.conferencePlan());
    }

    @GetMapping("/lectures/allByUserLogin")
    ResponseEntity<List<ReadLectureResponse>> getByUserLogin(@RequestParam String login) {
        return ResponseEntity.status(HttpStatus.OK).body(lectureService.getLecturesOfUserByLogin(login));
    }

    @GetMapping("/lectures/lecturesInterest")
    ResponseEntity<List<ReadLecturesByInterestResponse>> getLecturesByInterest() {
        return ResponseEntity.status(HttpStatus.OK).body(lectureService.getLecturesByInterest());
    }

    @GetMapping("/lectures/pathsInterest")
    ResponseEntity<List<ReadPathsByInterestResponse>> getPathsByInterest() {
        return ResponseEntity.status(HttpStatus.OK).body(lectureService.getPathsByInterest());
    }
}

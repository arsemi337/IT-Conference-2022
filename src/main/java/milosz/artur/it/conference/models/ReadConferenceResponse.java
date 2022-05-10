package milosz.artur.it.conference.models;

import milosz.artur.it.conference.lecture.domain.Lecture;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class ReadConferenceResponse {
    private final LocalDate date = LocalDate.of(2020, Month.JUNE, 1);
    private final String timeSlot1 = "10:00 - 11:45";
    private final String timeSlot2 = "12:00 - 13:45";
    private final String timeSlot3 = "14:00 - 15:45";
    private final List<String> lectures = new ArrayList<>();

    public ReadConferenceResponse(List<Lecture> lectures) {
        for (Lecture lecture : lectures)
        {
            this.lectures.add(lecture.toString());
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public String getTimeSlot1() {
        return timeSlot1;
    }

    public String getTimeSlot2() {
        return timeSlot2;
    }

    public String getTimeSlot3() {
        return timeSlot3;
    }

    public List<String> getLectures() {
        return lectures;
    }
}

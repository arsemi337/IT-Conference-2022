package milosz.artur.it.conference.models;

public class ReadLectureResponse {
    private final String topic;
    private final String startTime;

    public ReadLectureResponse(String topic, String startTime) {
        this.topic = topic;
        this.startTime = startTime;
    }

    public String getTopic() {
        return topic;
    }

    public String getStartTime() {
        return startTime;
    }
}

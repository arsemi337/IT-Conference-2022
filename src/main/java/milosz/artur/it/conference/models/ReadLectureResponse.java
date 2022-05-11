package milosz.artur.it.conference.models;

import java.util.UUID;

public class ReadLectureResponse {
    private final UUID registrationId;
    private final String topic;
    private final String startTime;

    public ReadLectureResponse(UUID registrationId, String topic, String startTime) {
        this.registrationId = registrationId;
        this.topic = topic;
        this.startTime = startTime;
    }

    public UUID getRegistrationId() {
        return registrationId;
    }

    public String getTopic() {
        return topic;
    }

    public String getStartTime() {
        return startTime;
    }
}

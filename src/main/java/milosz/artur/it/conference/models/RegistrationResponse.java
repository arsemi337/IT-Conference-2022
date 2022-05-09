package milosz.artur.it.conference.models;

import java.util.UUID;

public class RegistrationResponse {
    private final UUID id, userId, lectureId;

    public RegistrationResponse(UUID id, UUID userId, UUID lectureId) {
        this.id = id;
        this.userId = userId;
        this.lectureId = lectureId;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getLectureId() {
        return lectureId;
    }
}

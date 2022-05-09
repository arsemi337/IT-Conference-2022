package milosz.artur.it.conference.models;

import java.util.UUID;

public class CreateRegistrationRequest {
    private final String userLogin, userEmail;
    private final UUID lectureId;

    public CreateRegistrationRequest(String userLogin, String userEmail, UUID lectureId) {
        this.userLogin = userLogin;
        this.userEmail = userEmail;
        this.lectureId = lectureId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public UUID getLectureId() {
        return lectureId;
    }
}

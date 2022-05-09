package milosz.artur.it.conference.models;

import java.util.UUID;

public class UserResponse {
    private final String login, email;

    public UserResponse(String login, String email) {
        this.login = login;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }
}

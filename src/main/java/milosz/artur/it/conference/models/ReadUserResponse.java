package milosz.artur.it.conference.models;

public class ReadUserResponse {
    private final String login, email;

    public ReadUserResponse(String login, String email) {
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

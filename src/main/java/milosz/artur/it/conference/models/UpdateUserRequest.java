package milosz.artur.it.conference.models;

public class UpdateUserRequest {
    private final String login;
    private final String oldEmail;
    private final String newEmail;

    public UpdateUserRequest(String login, String oldEmail, String newEmail) {
        this.login = login;
        this.oldEmail = oldEmail;
        this.newEmail = newEmail;
    }

    public String getLogin() {
        return login;
    }

    public String getOldEmail() {
        return oldEmail;
    }

    public String getNewEmail() {
        return newEmail;
    }
}

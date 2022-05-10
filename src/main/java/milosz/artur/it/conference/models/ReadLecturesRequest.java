package milosz.artur.it.conference.models;

public class ReadLecturesRequest {
    private final String login;

    public ReadLecturesRequest(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }
}

package milosz.artur.it.conference.user.domain;

import milosz.artur.it.conference.models.ReadUserResponse;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;
    @Column(name = "login")
    private String login;
    @Column(name = "email")
    private String email;

    public User() {}

    public User(String login, String email)
    {
        this.login = login;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ReadUserResponse toUserResponse()
    {
        return new ReadUserResponse(this.login, this.email);
    }

}

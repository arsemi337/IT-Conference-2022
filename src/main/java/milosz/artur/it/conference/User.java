package milosz.artur.it.conference;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;
    private String login;
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

}

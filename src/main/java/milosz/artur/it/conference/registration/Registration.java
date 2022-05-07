package milosz.artur.it.conference.registration;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Registrations")
public class Registration {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;
    @Column(name = "userId")
    private String userId;
    @Column(name = "lectureId")
    private String lectureId;

    public Registration() {}

    public Registration(String userId, String lectureId)
    {
        this.userId = userId;
        this.lectureId = lectureId;
    }

    public UUID getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getLectureId() {
        return lectureId;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setLectureId(String lectureId) {
        this.lectureId = lectureId;
    }
}

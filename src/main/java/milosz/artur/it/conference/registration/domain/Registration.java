package milosz.artur.it.conference.registration.domain;

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
    private UUID userId;
    @Column(name = "lectureId")
    private UUID lectureId;

    public Registration() {
    }

    public Registration(UUID userId, UUID lectureId) {
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

    public void setId(UUID id) {
        this.id = id;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setLectureId(UUID lectureId) {
        this.lectureId = lectureId;
    }
}

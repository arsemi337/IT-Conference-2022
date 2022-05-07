package milosz.artur.it.conference;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Lectures")
public class Lecture {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;
    @Column(name = "topic")
    private String topic;
    @Column(name = "startTime")
    private String startTime;
    @Column(name = "availablePlaces")
    private int availablePlaces;

    public Lecture() {}

    public Lecture(String topic, String startTime)
    {
        this.topic = topic;
        this.startTime = startTime;
        this.availablePlaces = 5;
    }

    public UUID getId() {
        return id;
    }

    public String getTopic() {
        return topic;
    }

    public String getStartTime() {
        return startTime;
    }

    public int getAvailablePlaces() {
        return availablePlaces;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setAvailablePlaces(int availablePlaces) {
        this.availablePlaces = availablePlaces;
    }
}

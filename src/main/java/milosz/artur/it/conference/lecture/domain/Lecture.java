package milosz.artur.it.conference.lecture.domain;

import milosz.artur.it.conference.models.ReadLectureResponse;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Lectures")
public class Lecture {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;
    @Column(name = "path")
    private String path;
    @Column(name = "title")
    private String title;
    @Column(name = "startTime")
    private String startTime;
    @Column(name = "availablePlaces")
    private int availablePlaces;

    public Lecture() {}

    public Lecture(String path, String title, String startTime, int availablePlaces)
    {
        this.path = path;
        this.title = title;
        this.startTime = startTime;
        this.availablePlaces = availablePlaces;
    }

    public UUID getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public String getTitle() {
        return title;
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

    public void setPath(String path) {
        this.path = path;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setAvailablePlaces(int availablePlaces) {
        this.availablePlaces = availablePlaces;
    }

    public ReadLectureResponse toReadLecturesResponse(String topic, String startTime)
    {
        return new ReadLectureResponse(topic, startTime);
    }

    public void decreaseAvailablePlacesNumber()
    {
        this.availablePlaces -= 1;
    }

    @Override
    public String toString() {
        return "id='" + id + '\'' +
                ", path='" + path + '\'' +
                ", title='" + title + '\'' +
                ", startTime='" + startTime + '\'' +
                ", availablePlaces=" + availablePlaces;
    }
}

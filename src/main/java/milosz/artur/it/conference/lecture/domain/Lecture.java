package milosz.artur.it.conference.lecture.domain;

import milosz.artur.it.conference.models.ReadLectureResponse;
import milosz.artur.it.conference.models.ReadLecturesByInterestResponse;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Lectures")
public class Lecture implements Comparable<Lecture> {
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
    private Integer availablePlaces;

    public Lecture() {
    }

    public Lecture(String path, String title, String startTime, Integer availablePlaces) {
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

    public Integer getAvailablePlaces() {
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

    public void setAvailablePlaces(Integer availablePlaces) {
        this.availablePlaces = availablePlaces;
    }

    public ReadLectureResponse toReadLecturesResponse(String topic, String startTime) {
        return new ReadLectureResponse(topic, startTime);
    }

    public ReadLecturesByInterestResponse toReadLecturesByInterestResponse(
            String title, String path, String startTime, Double registrationsPercentage) {
        return new ReadLecturesByInterestResponse(title, path, startTime, registrationsPercentage);
    }

    public double getPercentageOfRegistrations() {
        return (5.0 - availablePlaces) / 5.0 * 100.0;
    }

    public void decreaseAvailablePlacesNumber() {
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

    @Override
    public int compareTo(Lecture o) {
        return getAvailablePlaces().compareTo(o.getAvailablePlaces());
    }
}

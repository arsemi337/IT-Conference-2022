package milosz.artur.it.conference.models;

import java.util.UUID;

public class LectureResponse {
    private UUID id;
    private String path, title, startTime;
    private int availablePlaces;

    public LectureResponse(UUID id, String path, String title, String startTime, int availablePlaces) {
        this.id = id;
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
}

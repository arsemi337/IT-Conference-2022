package milosz.artur.it.conference.models;

public class ReadLecturesByInterestResponse implements Comparable<ReadLecturesByInterestResponse> {
    private final String title;
    private final String path;
    private final String startTime;
    private final Double registrationsPercentage;

    public ReadLecturesByInterestResponse(String title, String path, String startTime, Double registrationsPercentage) {
        this.title = title;
        this.path = path;
        this.startTime = startTime;
        this.registrationsPercentage = registrationsPercentage;
    }

    public String getTitle() {
        return title;
    }

    public String getPath() {
        return path;
    }

    public String getStartTime() {
        return startTime;
    }

    public Double getRegistrationsPercentage() {
        return registrationsPercentage;
    }

    @Override
    public int compareTo(ReadLecturesByInterestResponse o) {
        return getRegistrationsPercentage().compareTo(o.getRegistrationsPercentage());
    }
}

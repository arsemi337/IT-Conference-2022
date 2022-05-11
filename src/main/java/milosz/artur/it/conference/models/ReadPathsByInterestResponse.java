package milosz.artur.it.conference.models;

public class ReadPathsByInterestResponse implements Comparable<ReadPathsByInterestResponse>{
    private final String pathName;
    private final Double registrationsPercentage;

    public ReadPathsByInterestResponse(String pathName, Double registrationsPercentage) {
        this.pathName = pathName;
        this.registrationsPercentage = registrationsPercentage;
    }

    public String getPathName() {
        return pathName;
    }

    public Double getRegistrationsPercentage() {
        return registrationsPercentage;
    }

    @Override
    public int compareTo(ReadPathsByInterestResponse o) {
        return getRegistrationsPercentage().compareTo(o.getRegistrationsPercentage());
    }
}

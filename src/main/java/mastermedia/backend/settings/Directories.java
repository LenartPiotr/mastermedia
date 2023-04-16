package mastermedia.backend.settings;

public class Directories {

    private String original;
    private String lowResolution;
    private String sorted;

    public String getOriginal() { return original; }

    public void setOriginal(String original) { this.original = original; }

    public String getLowResolution() { return lowResolution; }

    public void setLowResolution(String lowResolution) { this.lowResolution = lowResolution; }

    public String getSorted() { return sorted; }

    public void setSorted(String sorted) { this.sorted = sorted; }

    @Override
    public String toString() {

        return "Directories [original=" + original + ", lowResolution=" + lowResolution + ", sorted=" + sorted +
               "]";

    }

}

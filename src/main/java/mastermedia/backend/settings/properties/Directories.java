package mastermedia.backend.settings.properties;

public class Directories {

    private String binaries;
    private String original;
    private String lowResolution;
    private String sorted;
    private String copy;

    public Directories(){}
    public Directories(Directories directories){
        this.binaries = directories.binaries;
        this.original = directories.original;
        this.lowResolution = directories.lowResolution;
        this.sorted = directories.sorted;
        this.copy = directories.copy;
    }

    public void setCopy(String copy) { this.copy = copy; }

    public String getCopy() { return copy; }

    public String getBinaries() { return binaries; }

    public void setBinaries(String binaries) { this.binaries = binaries; }

    public String getOriginal() { return original; }

    public void setOriginal(String original) { this.original = original; }

    public String getLowResolution() { return lowResolution; }

    public void setLowResolution(String lowResolution) { this.lowResolution = lowResolution; }

    public String getSorted() { return sorted; }

    public void setSorted(String sorted) { this.sorted = sorted; }

    @Override
    public String toString() {

        return "Directories [original=" + original + ", lowResolution=" + lowResolution + ", sorted=" + sorted + "]";

    }

}

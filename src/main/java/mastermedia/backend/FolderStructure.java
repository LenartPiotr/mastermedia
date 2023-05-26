package mastermedia.backend;

import java.io.File;

import mastermedia.backend.settings.properties.Directories;

public class FolderStructure {

    public static final String PUBLIC = "Public";
    public static final String BINARIES = ".bin";
    private Directories directories;
    private File original;
    private File lowResolution;
    private File sorted;
    private File binaries;
    private File copy;

    public void createFolderStructure(Directories dirs) {

        this.directories = dirs;

        new File(PUBLIC).mkdirs();
        (original = new File(PUBLIC + "/" + dirs.getOriginal())).mkdirs();
        (lowResolution = new File(PUBLIC + "/" + dirs.getLowResolution())).mkdirs();
        (sorted = new File(PUBLIC + "/" + dirs.getSorted())).mkdirs();
        (copy = new File(PUBLIC + "/" + dirs.getCopy())).mkdirs();
        (binaries = new File(BINARIES)).mkdirs();

    }

    public Directories getDirectories() { return directories; }

    public void setDirectories(Directories directories) { this.directories = directories; }

    public static String getPublic() { return PUBLIC; }

    public File getOriginal() { return original; }

    public File getLowResolution() { return lowResolution; }

    public File getSorted() { return sorted; }

    public File getBinaries() { return binaries; }

    public File getCopy() { return copy; }

    public void setBinaries(File binaries) { this.binaries = binaries; }

}

package mastermedia.backend;

import java.io.File;

import mastermedia.backend.settings.Directories;

public class FolderStructure {

    public static final String PUBLIC = "./Public";
    private Directories directories;

    public void createFolderStructure(Directories dirs) {

        this.directories = dirs;

        new File(PUBLIC).mkdirs();
        new File(PUBLIC + "/" + dirs.getOriginal()).mkdirs();
        new File(PUBLIC + "/" + dirs.getLowResolution()).mkdirs();
        new File(PUBLIC + "/" + dirs.getSorted()).mkdirs();

    }

    public Directories getDirectories() { return directories; }

    public void setDirectories(Directories directories) { this.directories = directories; }

}

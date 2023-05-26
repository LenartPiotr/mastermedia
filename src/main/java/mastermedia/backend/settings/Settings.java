package mastermedia.backend.settings;

import mastermedia.backend.settings.properties.Directories;
import mastermedia.backend.settings.properties.FileTypes;
import mastermedia.backend.settings.properties.Thumbnails;
import mastermedia.backend.settings.properties.Toenails;

public class Settings {

    private Directories directories;
    private FileTypes filetypes;
    private Thumbnails thumbnails;
    private Toenails toenails;

    public Settings(){}
    public Settings(Settings settings){
        this.directories = new Directories(settings.directories);
        this.filetypes = new FileTypes(settings.filetypes);
        this.thumbnails = new Thumbnails(settings.thumbnails);
        this.toenails = new Toenails(settings.toenails);
    }

    public Directories getDirectories() { return directories; }

    public void setDirectories(Directories directories) { this.directories = directories; }

    public FileTypes getFiletypes() { return filetypes; }

    public void setFiletypes(FileTypes filetypes) { this.filetypes = filetypes; }

    public Thumbnails getThumbnails() { return thumbnails; }

    public void setThumbnails(Thumbnails thumbnails) { this.thumbnails = thumbnails; }

    public Toenails getToenails() { return toenails; }

    public void setToenails(Toenails toenails) { this.toenails = toenails; }

    @Override
    public String toString() {

        return "Settings [directories=" + directories + ", filetypes=" + filetypes + ", thumbnails=" + thumbnails +
               ", toenails=" + toenails + "]";

    }

}

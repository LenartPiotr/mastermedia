package mastermedia.backend.squisher;

import java.io.File;
import java.io.IOException;

public interface IFolderSquisher {
    public boolean squishFolder(File source, File destination) throws IOException;
}

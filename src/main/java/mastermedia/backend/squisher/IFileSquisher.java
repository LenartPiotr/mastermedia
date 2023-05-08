package mastermedia.backend.squisher;

import java.io.File;
import java.io.IOException;

public interface IFileSquisher {
    public boolean squishFile(File source, File destination) throws IOException;
}

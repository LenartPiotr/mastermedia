package mastermedia.backend;

import java.io.File;

public class FolderStructure {

    public static final String PUBLIC = "./Public";
    public static final String SOURCE = "./Public/Source";
    public static final String MINI = "./Public/Mini";
    public static final String SORTED = "./Public/Sorted";

    public static void createFolderStructure() {

        new File(PUBLIC).mkdirs();
        new File(SOURCE).mkdirs();
        new File(MINI).mkdirs();
        new File(SORTED).mkdirs();

    }

}

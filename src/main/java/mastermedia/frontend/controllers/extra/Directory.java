package mastermedia.frontend.controllers.extra;

import java.io.File;
import java.util.List;

public class Directory {
    List<File> fileList;
    String name;

    public List<File> getFileList() {
        return fileList;
    }

    public void setFileList(List<File> fileList) {
        this.fileList = fileList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

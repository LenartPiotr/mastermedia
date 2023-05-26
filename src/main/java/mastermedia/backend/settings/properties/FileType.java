package mastermedia.backend.settings.properties;

import java.util.ArrayList;
import java.util.List;

public class FileType {
    private String name;
    private List<String> extensions;

    public FileType(){}
    public FileType(FileType fileType){
        this.name = fileType.name;
        this.extensions = new ArrayList<>(fileType.extensions);
    }

    public List<String> getExtensions() {
        return extensions;
    }

    public void setExtensions(List<String> extensions) {
        this.extensions = extensions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FileType{" +
                "name='" + name + '\'' +
                ", extensions=" + extensions +
                '}';
    }
}
